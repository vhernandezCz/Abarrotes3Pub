/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@Named(value = "menuController")
@SessionScoped
public class MenuController implements Serializable{

    public String darBienvenida(){
        String nombreUsr = (String) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("nombre");
        String email = (String) FacesContext.getCurrentInstance().getExternalContext().
                    getSessionMap().get("email");  
        return "Bienvenido " + nombreUsr + " (" + email + ")";
    }
    
}
