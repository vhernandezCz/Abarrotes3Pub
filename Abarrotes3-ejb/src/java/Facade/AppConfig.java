/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lenovo
 */
@ApplicationScoped
public class AppConfig {

    @Produces
    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;
    
}
