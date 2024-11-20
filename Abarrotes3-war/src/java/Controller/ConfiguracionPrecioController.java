/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ConfiguracionPrecio;
import Facade.ConfiguracionPrecioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author lenovo
 */
@Named(value = "configuracionPrecioController")
@SessionScoped
public class ConfiguracionPrecioController implements Serializable {

    @EJB
    private ConfiguracionPrecioFacade configuracionPrecioFacade;
    private ConfiguracionPrecio configuracionPrecio = new ConfiguracionPrecio();

    //Constructor
    public ConfiguracionPrecioController() {
    }

    //Getter y setter
    public ConfiguracionPrecioFacade getConfiguracionPrecioFacade() {
        return configuracionPrecioFacade;
    }

    public void setConfiguracionPrecioFacade(ConfiguracionPrecioFacade configuracionPrecioFacade) {
        this.configuracionPrecioFacade = configuracionPrecioFacade;
    }

    public ConfiguracionPrecio getConfiguracionPrecio() {
        return configuracionPrecio;
    }

    public void setConfiguracionPrecio(ConfiguracionPrecio configuracionPrecio) {
        this.configuracionPrecio = configuracionPrecio;
    }

    //Métods
    public List<ConfiguracionPrecio> findAll() {
        return getConfiguracionPrecioFacade().findAll();
    }

    public String insert(ConfiguracionPrecio configuracionPrecio) {
        try {
            configuracionPrecioFacade.insert(configuracionPrecio);
        } catch (Exception e) {

        }
        return "";
    }
}
