/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Proveedor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class ProveedorFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public List<Proveedor> findAll() {
        TypedQuery<Proveedor> query;
        query = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
        return query.getResultList();
    }

   public Proveedor findByName(String nombre) {
    TypedQuery<Proveedor> query = em.createQuery(
        "SELECT p FROM Proveedor p WHERE LOWER(p.nombre) = :nombre", Proveedor.class);
    query.setParameter("nombre", nombre.toLowerCase());
    query.setMaxResults(1);

    List<Proveedor> resultList = query.getResultList();
    
    // Retorna el primer resultado si existe, de lo contrario retorna null
    return resultList.isEmpty() ? null : resultList.get(0);
}

    public List<Proveedor> findAllProveedor() {
        TypedQuery<Proveedor> query;
        query = em.createQuery("SELECT p FROM Proveedor p", Proveedor.class);
        return query.getResultList();
    }

    public Proveedor findById(Integer idProveedor) {
        TypedQuery<Proveedor> query;
        String queryString = "SELECT p FROM Proveedor p WHERE p.idProveedor =:idProveedor";
        query = em.createQuery(queryString, Proveedor.class);
        query.setParameter("idProveedor", idProveedor);
        return (Proveedor) query.getSingleResult();
    }

    public int insert(Proveedor proveedor) {
        FacesMessage msj;
        try {
            if (proveedor != null) {
                em.persist(proveedor);
                em.flush();
                return proveedor.getIdProveedor();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El objeto Proveedor es nulo.", "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar el objeto Proveedor: " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return -1;
    }

    public void update(Proveedor proveedor) {
        em.merge(proveedor);
    }

    public void delete(Proveedor proveedor) {
        em.remove(em.merge(proveedor));
    }
}
