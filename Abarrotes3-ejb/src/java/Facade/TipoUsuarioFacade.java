/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Categoria;
import Entity.TipoUsuario;
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
public class TipoUsuarioFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<TipoUsuario> findAll() {
        TypedQuery<TipoUsuario> query;
        query = em.createQuery("SELECT tu FROM TipoUsuario tu", TipoUsuario.class);
        return query.getResultList();
    }
    
    public TipoUsuario findById(Integer idTipoUsuario) {
        TypedQuery<TipoUsuario> query;
        query = em.createQuery("SELECT tu FROM TipoUsuario tu WHERE tu.idTipoUsuario = :idTipoUsuario", TipoUsuario.class);
        query.setParameter("idTipoUsuario", idTipoUsuario);
        return (TipoUsuario) query.getSingleResult();
    }

    public void insert(Categoria categoria) {
        em.persist(categoria);
    }

    public void update(Categoria categoria) {
        em.merge(categoria);
    }

    public void delete(Categoria categoria) {
        em.remove(em.merge(categoria));
    }
}
