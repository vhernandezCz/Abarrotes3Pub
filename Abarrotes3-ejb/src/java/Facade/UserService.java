/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.DatosUsuario;
import Entity.Usuario;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class UserService {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    public Usuario findByUsername(String username) {
        TypedQuery<Usuario> query;
        String queryString = "SELECT u FROM Usuario u WHERE u.username =:username";
        query = em.createQuery(queryString, Usuario.class);
        query.setParameter("username", username);
        return (Usuario) query.getSingleResult();
    }

    public String obtenerClaveUsuario(String username) {
        TypedQuery<DatosUsuario> query;
        String queryString = "SELECT du FROM DatosUsuario du WHERE du.usuario.username = :username";
        query = em.createQuery(queryString, DatosUsuario.class);
        query.setParameter("username", username);

        // Obtén el tipo de usuario y la clave de usuario
        DatosUsuario datosUsuario = query.getSingleResult();
        return datosUsuario.getTipoUsuario().getClaveUsuario();
    }

    public boolean authenticate(String username, String password) {
        try {

            Usuario user = findByUsername(username);
            if (user != null) {
                // Compara la contraseña ingresada con el hash almacenado
                return BCrypt.checkpw(password, user.getPasswordHash());
            }
        } catch (NoResultException e) { 
            System.err.println("Error en autenticación: " + e.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
