/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Venta;
import Facade.VentaFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

/**
 *
 * @author lenovo
 */
@Named(value = "ventaController")
@SessionScoped
public class VentaController implements Serializable {

    @EJB
    private VentaFacade ventaFacade;
    private Venta venta = new Venta();

    //Constructor
    public VentaController() {
    }

    //Getter y setter
    public VentaFacade getVentaFacade() {
        return ventaFacade;
    }

    public Venta getVenta() {
        return venta;
    }

    //Metods
    public List<Venta> findAll() {
        return getVentaFacade().findAll();
    }

    public String insert(Venta venta) {
        try {
            ventaFacade.insert(venta);

        } catch (Exception e) {

        }
        return "";
    }

    public List<Integer> obtenerCheckListIds() {
        String[] selectedItems = (String[]) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("checkList");
        List<Integer> listIds = new ArrayList<>();
        
        for(String idCheck: selectedItems ){
            listIds.add(Integer.parseInt(idCheck));
        }
        
        return listIds;
    }
}
