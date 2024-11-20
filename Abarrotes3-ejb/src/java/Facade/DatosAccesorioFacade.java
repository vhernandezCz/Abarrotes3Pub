/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Categoria;
import Entity.Clasificacion;
import Entity.DatosAccesorio;
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
public class DatosAccesorioFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<DatosAccesorio> findAll() {
        TypedQuery<DatosAccesorio> query;
        query = em.createQuery("SELECT da FROM DatosAccesorio da", DatosAccesorio.class);
        return query.getResultList();
    }

    public List<Clasificacion> findAllClasificaciones() {
        TypedQuery<Clasificacion> query;
        query = em.createQuery("SELECT DISTINCT b.clasificacion FROM DatosAccesorio b", Clasificacion.class);
        return query.getResultList();
    }

    public DatosAccesorio findById(Integer idDatosAccesorio) {
        TypedQuery<DatosAccesorio> query;
        String queryString = "SELECT da FROM DatosAccesorio da WHERE da.idDatosAccesorio =:idDatosAccesorio";
        query = em.createQuery(queryString, DatosAccesorio.class);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);
        return (DatosAccesorio) query.getSingleResult();
    }

    public List<Categoria> obtenerCategorias(Integer idClasificacion) {
        TypedQuery<Categoria> query;
        String queryString = "SELECT DISTINCT da.categoria FROM DatosAccesorio da WHERE 1=1 ";
        if (idClasificacion != null && idClasificacion > 0) {
            queryString += "AND da.clasificacion.idClasificacion = :idClasificacion";
        }
        query = em.createQuery(queryString, Categoria.class);

        if (idClasificacion != null && idClasificacion > 0) {
            query.setParameter("idClasificacion", idClasificacion);
        }
        return query.getResultList();
    }

    //Consulta todos los registros que coincidan con la orden, sku y categoria
    public List<DatosAccesorio> buscarEstilos(Integer idClasificacion, Integer idCategoria) {
        TypedQuery<DatosAccesorio> query;
        String queryString = "SELECT da FROM DatosAccesorio da WHERE "
                + "da.clasificacion.idClasificacion = :idClasificacion "
                + "AND da.categoria.idCategoria = :idCategoria";
        query = em.createQuery(queryString, DatosAccesorio.class);
        query.setParameter("idClasificacion", idClasificacion);
        query.setParameter("idCategoria", idCategoria);
        return query.getResultList();
    }

    public void updateProveedor(Integer idDatosAccesorio, Proveedor proveedor) {
        String queryString = "UPDATE DatosAccesorio da SET da.proveedor = :proveedor WHERE da.idDatosAccesorio = :idDatosAccesorio";

        // Crear la consulta y setear los parámetros
        Query query = em.createQuery(queryString);
        query.setParameter("proveedor", proveedor);
        query.setParameter("idDatosAccesorio", idDatosAccesorio);

        // Ejecutar la actualización
        query.executeUpdate();
    }

    public Clasificacion findClasificacionById(Integer id) {
        return em.find(Clasificacion.class, id);
    }

    public Categoria findCategoriaById(Integer id) {
        return em.find(Categoria.class, id);
    }

    public void insert(DatosAccesorio datosAccesorio) {
        em.persist(datosAccesorio);
    }

    public int guardarDatoAccesorio(DatosAccesorio datosAccesorio) {
        em.persist(datosAccesorio);
        em.flush();
        return datosAccesorio.getIdDatosAccesorio();
    }

    public void update(DatosAccesorio datosAccesorio) {
        em.merge(datosAccesorio);
    }

}
