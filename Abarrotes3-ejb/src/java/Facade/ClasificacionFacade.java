/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Clasificacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class ClasificacionFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<Clasificacion> findAll() {
        TypedQuery<Clasificacion> query;
        query = em.createQuery("SELECT c FROM Clasificacion c", Clasificacion.class);
        return query.getResultList();
    }

    public Clasificacion findByName(String nombre) {
        TypedQuery<Clasificacion> query;
        query = em.createQuery("SELECT n FROM Clasificacion n WHERE LOWER(n.nombre) = :nombre", Clasificacion.class);
        query.setParameter("nombre", nombre.toLowerCase());
        return (Clasificacion) query.getSingleResult();
    }

    public List<Clasificacion> findByCategoria(int idCategoria) {
        TypedQuery<Clasificacion> query;
        query = em.createQuery("SELECT c FROM Clasificacion c WHERE c.datosAccesorioList", Clasificacion.class);
        return query.getResultList();
    }

    public void insert(Clasificacion clasificacion) {
        em.persist(clasificacion);
    }

    public void update(Clasificacion clasificacion) {
        em.merge(clasificacion);
    }

    public void delete(Clasificacion clasificacion) {
        em.remove(em.merge(clasificacion));
    }
}
