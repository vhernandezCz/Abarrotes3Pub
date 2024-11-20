/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Categoria;
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
public class CategoriaFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<Categoria> findAll() {
        TypedQuery<Categoria> query;
        query = em.createQuery("SELECT c FROM Categoria c", Categoria.class);
        return query.getResultList();
    }
    
    public List<Categoria> findAllCategorias() {
        TypedQuery<Categoria> query;
        query = em.createQuery("SELECT DISTINCT b.categoria FROM DatosAccesorio b", Categoria.class);
        return query.getResultList();
    }

    public Categoria findByName(String nombre) {
        TypedQuery<Categoria> query;
        query = em.createQuery("SELECT n FROM Categoria n WHERE LOWER(n.nombre) = :nombre", Categoria.class);
        query.setParameter("nombre", nombre.toLowerCase());
        return (Categoria) query.getSingleResult();
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
