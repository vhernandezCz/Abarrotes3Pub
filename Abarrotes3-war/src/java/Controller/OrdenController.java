/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Orden;
import Facade.OrdenFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@Named(value = "ordenController")
@SessionScoped
public class OrdenController implements Serializable {

    @EJB
    private OrdenFacade ordenFacade;
    private Orden orden = new Orden();

    //Constructor
    public OrdenController() {
    }

    public List<String> findAllOrder() {
        List<String> ordenList = new ArrayList<>();
        try {
            ordenList = ordenFacade.findAllOrder();
        } catch (Exception e) {

        }
        return ordenList;
    }

    public int insert(Orden orden) {
        FacesMessage msj;
        Orden ordenExistente = new Orden();
        int idNuevaOrden = 0;
        try {
            String ordenSinEspacios = orden.getOrden().trim();
            String skuSinEspacios = orden.getSku().trim();

            ordenExistente = ordenFacade.consultaTuplaOrdenSKU(orden.getOrden(), orden.getSku());
            if (!ordenSinEspacios.isEmpty() && !skuSinEspacios.isEmpty()) {
                if (ordenExistente == null) {
                    idNuevaOrden = ordenFacade.insert(orden);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nueva orden se ha guardado correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva orden no pudo ser guardada debido a que fue registrada anteriormente. ", "");
                    FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Los campos Orden y SKU son requeridos. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva orden no pudo ser guardada, consulte con el administrador. ", "");
            FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
        }
        return idNuevaOrden;
    }

    public Orden consultarPorId(int idOrden) {
        Orden orden = new Orden();
        try {
            if (idOrden > 0) {
                orden = ordenFacade.findOrdenById(idOrden);
            }
        } catch (Exception e) {
        }
        return orden;
    }

    /**
     * @return the ordenFacade
     */
    public OrdenFacade getOrdenFacade() {
        return ordenFacade;
    }

    /**
     * @param ordenFacade the ordenFacade to set
     */
    public void setOrdenFacade(OrdenFacade ordenFacade) {
        this.ordenFacade = ordenFacade;
    }
}
