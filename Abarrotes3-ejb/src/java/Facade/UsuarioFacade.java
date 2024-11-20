/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Bitacora;
import Entity.Usuario;
import Entity.Usuarios;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class UsuarioFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    //Consulta todos los registros disponibles
    public Usuarios findByEmail(String email) {
    try {
            TypedQuery<Usuarios> query = em.createQuery("SELECT u FROM Usuarios u WHERE u.email = :email", Usuarios.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; // Retorna null si no encuentra el usuario
        }
    }
    
     public int insert(Usuario usuario) {
        FacesMessage msj;
        try {
            if (usuario != null) {
                em.persist(usuario);
                em.flush();
                return usuario.getNumeroEmpleado();
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: El objeto Usuario es nulo.", "");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al insertar el objeto Usuario: " + e.getMessage(), "");
            FacesContext.getCurrentInstance().addMessage(null, msj);
        }
        return -1;
    }
}
