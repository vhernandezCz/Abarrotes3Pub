/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ConfiguracionDeducciones;
import Entity.OrdenAccesorio;
import Facade.ConfiguracionDeduccionesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.event.Observes;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lenovo
 */
@Named(value = "configuracionDeduccionesController")
@SessionScoped
public class ConfiguracionDeduccionesController implements Serializable {

    @EJB
    private ConfiguracionDeduccionesFacade configuracionDeduccionesFacade;
    
    @Inject
    private OrdenAccesorioController ordenAccesorioController;

    private ConfiguracionDeducciones configuracionDeducciones = new ConfiguracionDeducciones();
    private boolean confirm = false;

    //Constructor
    public ConfiguracionDeduccionesController() {
        this.ordenAccesorioController = new OrdenAccesorioController();
    }

    @PostConstruct
    void init() {
        configuracionDeducciones = obtenerConfiguracionDeducciones();
    }

    public ConfiguracionDeducciones obtenerConfiguracionDeducciones() {
        ConfiguracionDeducciones cd = new ConfiguracionDeducciones();
        try {
            cd = configuracionDeduccionesFacade.findByActive();
            if (cd == null) {
                cd = new ConfiguracionDeducciones();
            }
            return cd;
        } catch (Exception e) {
            e.printStackTrace();
            return cd;
        }
    }

    public String insert() {
        FacesMessage msj;
        try {

            if (configuracionDeducciones.getEmpaquetado() > 0
                    && configuracionDeducciones.getEntrega() > 0
                    && configuracionDeducciones.getImportacion() > 0) {
                configuracionDeduccionesFacade.insert(configuracionDeducciones);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La nueva configuración se ha guardado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("ConfiguracionDeducciones", msj);
                clean();

            } else if (configuracionDeducciones.getEntrega() <= 0
                    && configuracionDeducciones.getImportacion() <= 0
                    && configuracionDeducciones.getEmpaquetado() <= 0) {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La información ingresada en los campos debe ser mayor a 0.", "");
                FacesContext.getCurrentInstance().addMessage("Configuracion/ConfiguracionDeducciones", msj);
            } else if (configuracionDeducciones.getImportacion() <= 0) {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo costo de importación debe ser mayor a 0.", "");
                FacesContext.getCurrentInstance().addMessage("Configuracion/ConfiguracionDeducciones", msj);
            } else if (configuracionDeducciones.getEmpaquetado() <= 0) {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo gasto de preparación debe ser mayor a 0.", "");
                FacesContext.getCurrentInstance().addMessage("Configuracion/ConfiguracionDeducciones", msj);

            } else if (configuracionDeducciones.getEntrega() <= 0) {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo costo de entrega debe ser mayor a 0.", "");
                FacesContext.getCurrentInstance().addMessage("Configuracion/ConfiguracionDeducciones", msj);

            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva configuración no pudo ser actualizada", "");
            FacesContext.getCurrentInstance().addMessage("Configuracion/ConfiguracionDeducciones", msj);

        }
        return "ConfiguracionDeducciones";
    }

    public String guardar() {
        FacesMessage msj;
        try {
            if (configuracionDeducciones.getIdConfiguracionDeduccion() != null
                    && configuracionDeducciones.getIdConfiguracionDeduccion() > 0) {
                configuracionDeduccionesFacade.actualizarEstatusAInactivo();
            }

            configuracionDeducciones.setEsActiva(true);
            configuracionDeduccionesFacade.insert(configuracionDeducciones);
            
            //actualiza los precios sugeridos de los proveedores
            List<OrdenAccesorio> ordenAccesorioList = new ArrayList<>();
            ordenAccesorioList = ordenAccesorioController.findAll();
            for(OrdenAccesorio oa: ordenAccesorioList){
                int precioSugerido = ordenAccesorioController.calcularPrecioSugerido(oa.getPrecioInicial());
                ordenAccesorioController.actualizarPrecioSugerido(oa.getIdOrdenAccesorio(), precioSugerido);
            }
            
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La configuración de deducciones se ha guardado correctamente", "");
            FacesContext.getCurrentInstance().addMessage("ConfiguracionDeducciones", msj);

        } catch (Exception e) {

        }
        return "";
    }

    public String prepareEdit() {
        configuracionDeducciones = configuracionDeduccionesFacade.findById(1);
        return "ConfiguracionDeducciones";
    }

    public String clean() {
        configuracionDeducciones = new ConfiguracionDeducciones();
        return "";
    }

    public ConfiguracionDeducciones getConfiguracionDeducciones() {
        return configuracionDeducciones;
    }

    public void setConfiguracionDeducciones(ConfiguracionDeducciones configuracionDeducciones) {
        this.configuracionDeducciones = configuracionDeducciones;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

}
