/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Orden;
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
public class OrdenFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    //Consulta todos los registros disponibles
    public List<Orden> findAll() {
        TypedQuery<Orden> query;
        query = em.createQuery("SELECT o FROM Orden o", Orden.class);
        return query.getResultList();
    }

    public List<String> findAllOrder() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT o.orden FROM Orden o", String.class);
        return query.getResultList();
    }

    public Orden findOrdenById(Integer id) {
        return em.find(Orden.class, id);
    }

    public Orden consultaTuplaOrdenSKU(String nuevaOrden, String nuevoSKU) {
        TypedQuery<Orden> query;
        String queryString = "SELECT o FROM Orden o WHERE o.orden = :nuevaOrden AND o.sku = :nuevoSKU";
        query = em.createQuery(queryString, Orden.class);
        query.setParameter("nuevaOrden", nuevaOrden);
        query.setParameter("nuevoSKU", nuevoSKU);
        query.setMaxResults(1);

        List<Orden> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    public int insert(Orden orden) {
        em.persist(orden);
        em.flush();
        return orden.getIdOrden();
    }

    public Orden update(Orden orden) {
        em.merge(orden);
        em.flush();
        return orden;
    }

}
