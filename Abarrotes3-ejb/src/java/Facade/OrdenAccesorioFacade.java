/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Orden;
import Entity.OrdenAccesorio;
import Entity.Proveedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class OrdenAccesorioFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public OrdenAccesorio consultarPorDatosAccesorio(int idDatosAccesorio) {
        TypedQuery<OrdenAccesorio> query;
        String queryString = "SELECT oa FROM OrdenAccesorio oa WHERE oa.datosAccesorio.idDatosAccesorio = :idDatosAccesorio ORDER BY oa.precioSugerido ASC";
        query = em.createQuery(queryString, OrdenAccesorio.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public OrdenAccesorio consultarPorDatosAccesorioProveedorSugerido(int idDatosAccesorio) {
        TypedQuery<OrdenAccesorio> query;
        String queryString = "SELECT oa FROM OrdenAccesorio oa WHERE "
                + "oa.datosAccesorio.idDatosAccesorio = :idDatosAccesorio "
                + "AND oa.precioInicial = (SELECT MIN(p.precioInicial) "
                + "FROM OrdenAccesorio p WHERE p.datosAccesorio.idDatosAccesorio = :idDatosAccesorio)";
        query = em.createQuery(queryString, OrdenAccesorio.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    public List<OrdenAccesorio> findAll() {
        TypedQuery<OrdenAccesorio> query;
        query = em.createQuery("SELECT oa FROM OrdenAccesorio oa", OrdenAccesorio.class);
        return query.getResultList();
    }

    public List<OrdenAccesorio> findByIdDatosAccesorio(Integer idDatosAccesorio) {
        TypedQuery<OrdenAccesorio> query;
        String queryString = "SELECT oa FROM OrdenAccesorio oa WHERE "
                + "oa.datosAccesorio.idDatosAccesorio = :idDatosAccesorio ";
        query = em.createQuery(queryString, OrdenAccesorio.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);
        return query.getResultList();
    }

    public void delete(OrdenAccesorio ordenAccesorio) {
        em.remove(em.merge(ordenAccesorio));
    }

    public void actualizarPrecioInicio(int precioInicial, int idOrdenAccesorio, int precioSugerido) {
        String queryString = "UPDATE OrdenAccesorio oa SET oa.precioInicial = :precioInicial, oa.precioSugerido = :precioSugerido WHERE oa.idOrdenAccesorio = :idOrdenAccesorio";
        Query query = em.createQuery(queryString);
        query.setParameter("precioInicial", precioInicial);
        query.setParameter("idOrdenAccesorio", idOrdenAccesorio);
        query.setParameter("precioSugerido", precioSugerido);
        query.executeUpdate();
    }

    public void actualizarPrecioSugerido(int idOrdenAccesorio, int precioSugerido) {
        String queryString = "UPDATE OrdenAccesorio oa SET oa.precioSugerido = :precioSugerido WHERE oa.idOrdenAccesorio = :idOrdenAccesorio";
        Query query = em.createQuery(queryString);
        query.setParameter("idOrdenAccesorio", idOrdenAccesorio);
        query.setParameter("precioSugerido", precioSugerido);
        query.executeUpdate();
    }

    public List<Orden> obtenerOrdenes(int idDatosAccesorio) {
        TypedQuery<Orden> query;
        String queryString = "SELECT oa.orden FROM OrdenAccesorio oa WHERE oa.idOrdenAccesorio = :idDatosAccesorio";
        query = em.createQuery(queryString, Orden.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);
        return query.getResultList();
    }

    public List<OrdenAccesorio> determinaProveedoresSugeridos(int idDatosAccesorio) {
        TypedQuery<OrdenAccesorio> query;

        String queryString = "SELECT oa FROM OrdenAccesorio oa "
                + "WHERE oa.datosAccesorio.idDatosAccesorio = :idDatosAccesorio "
                + "AND oa.precioInicial = (SELECT MIN(oai.precioInicial) "
                + "FROM OrdenAccesorio oai WHERE oai.datosAccesorio.idDatosAccesorio = :idDatosAccesorio)";

        query = em.createQuery(queryString, OrdenAccesorio.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);

        return query.getResultList();
    }

    public int insert(OrdenAccesorio ordenAccesorio) {
        ordenAccesorio.setIdOrdenAccesorio(null);
        em.persist(ordenAccesorio);
        em.flush();
        return ordenAccesorio.getIdOrdenAccesorio();
    }

    public void update(OrdenAccesorio ordenAccesorio) {
        em.merge(ordenAccesorio);
    }

}
