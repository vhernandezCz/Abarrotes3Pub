/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.ConfiguracionDeducciones;
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
public class ConfiguracionDeduccionesFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public ConfiguracionDeducciones findById(Integer idConfiguracionDeducciones) {
        TypedQuery<ConfiguracionDeducciones> query;
        String queryString = "SELECT cd FROM ConfiguracionDeducciones cd WHERE cd.idConfiguracionDeduccion =:idConfiguracionDeduccion";
        query = em.createQuery(queryString, ConfiguracionDeducciones.class);
        query.setParameter("idConfiguracionDeduccion", idConfiguracionDeducciones);
        return (ConfiguracionDeducciones) query.getSingleResult();
    }

    public ConfiguracionDeducciones findByActive() {
        TypedQuery<ConfiguracionDeducciones> query;
        String queryString = "SELECT cd FROM ConfiguracionDeducciones cd WHERE cd.esActiva =:active";
        query = em.createQuery(queryString, ConfiguracionDeducciones.class);
        query.setParameter("active", true);
        return query.getSingleResult();
    }

    public void actualizarEstatusAInactivo() {
        String queryString = "UPDATE ConfiguracionDeducciones cd SET cd.esActiva = 0 WHERE cd.esActiva = 1";
        Query query = em.createQuery(queryString);
        query.executeUpdate();
    }

    public void insert(ConfiguracionDeducciones configuracionDeducciones) {
        em.persist(configuracionDeducciones);
    }

    public void update(ConfiguracionDeducciones configuracionDeducciones) {
        em.merge(configuracionDeducciones);
    }
}
