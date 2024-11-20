/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Facade.UserService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private String claveUsuario;

    @EJB
    private UserService userService = new UserService();

    private static final String ADMIN_CODE = "001";
    private static final String EMPLOYEE_CODE = "002";

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);

        try {
            if (userService.authenticate(username, password)) {
                claveUsuario = userService.obtenerClaveUsuario(username);
                session.setAttribute("user", username);
                session.setAttribute("claveUsuario", claveUsuario);

                switch (claveUsuario) {
                    case ADMIN_CODE:
                        return "menuAM.xhtml";
                    case EMPLOYEE_CODE:
                        return "menuTR.xhtml";
                    default:
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Acceso denegado: No tienes permisos para acceder a esta página.", null));
                        return null;
                }
            } else {
                // Mensaje de error en caso de autenticación fallida
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o contraseña incorrectos", null));
                return null;
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error en el sistema. Por favor, inténtalo más tarde.", null));
            return null;
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the claveUsuario
     */
    public String getClaveUsuario() {
        return claveUsuario;
    }

    /**
     * @param claveUsuario the claveUsuario to set
     */
    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
}
