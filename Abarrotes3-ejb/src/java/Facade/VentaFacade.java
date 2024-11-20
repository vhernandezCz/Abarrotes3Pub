/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Venta;
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
public class VentaFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<Venta> findAll() {
        TypedQuery<Venta> query;
        query = em.createQuery("SELECT v FROM Venta v", Venta.class);
        return query.getResultList();
    }

    public void insert(Venta venta) {
        em.persist(venta);
    }

    public void update(Venta venta) {
        em.merge(venta);
    }
}
