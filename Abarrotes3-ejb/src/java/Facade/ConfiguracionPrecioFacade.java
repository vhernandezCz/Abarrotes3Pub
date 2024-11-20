/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.ConfiguracionPrecio;
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
public class ConfiguracionPrecioFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<ConfiguracionPrecio> findAll() {
        TypedQuery<ConfiguracionPrecio> query;
        query = em.createQuery("SELECT cp FROM ConfiguracionPrecio cp", ConfiguracionPrecio.class);
        return query.getResultList();
    }
    

    public void insert(ConfiguracionPrecio configuracionPrecio) {
        em.persist(configuracionPrecio);
    }

    public void update(ConfiguracionPrecio configuracionPrecio) {
        em.merge(configuracionPrecio);
    }
}
