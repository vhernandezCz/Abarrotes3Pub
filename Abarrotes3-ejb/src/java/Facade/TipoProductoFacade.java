/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.TipoProducto;
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
public class TipoProductoFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<TipoProducto> findAll() {
        TypedQuery<TipoProducto> query;
        query = em.createQuery("SELECT tp FROM TipoProducto tp", TipoProducto.class);
        return query.getResultList();
    }

    public TipoProducto findByName(String nombre) {
        TypedQuery<TipoProducto> query;
        query = em.createQuery("SELECT tp FROM TipoProducto tp WHERE LOWER(tp.nombre) = :nombre", TipoProducto.class);
        query.setParameter("nombre", nombre.toLowerCase());
        return (TipoProducto) query.getSingleResult();
    }

    public TipoProducto findById(Integer id) {
        return em.find(TipoProducto.class, id);
    }

    public void insert(TipoProducto tipoProducto) {
        em.persist(tipoProducto);
    }

    public void update(TipoProducto tipoProducto) {
        em.merge(tipoProducto);
    }

    public void delete(TipoProducto tipoProducto) {
        em.remove(em.merge(tipoProducto));
    }
}
