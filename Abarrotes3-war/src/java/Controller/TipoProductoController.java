/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.TipoProducto;
import Facade.TipoProductoFacade;
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
@Named(value = "tipoProductoController")
@SessionScoped

public class TipoProductoController implements Serializable {

    @EJB
    private TipoProductoFacade tipoProductoFacade;
    private TipoProducto tipoProducto = new TipoProducto();
    private List<TipoProducto> tipoProductoList = new ArrayList<>();
    private boolean confirm = false;

    /**
     * Creates a new instance of TipoProducto
     */
    public TipoProductoController() {
    }

    //Metods
    public List<TipoProducto> findAll() {
        List<TipoProducto> tipoProductos = new ArrayList<>();
        try {
            tipoProductos = tipoProductoFacade.findAll();
        } catch (Exception e) {

        }
        return tipoProductos;
    }
    
    public TipoProducto findByName(String nombre) {
        try {
            TipoProducto tipoProducto = new TipoProducto();
            tipoProducto = tipoProductoFacade.findByName(nombre);
            return tipoProducto;
        } catch (Exception e) {
            return null;
        }
    }

     public String insert() {
        FacesMessage msj;
        try {
            if (tipoProducto.getNombre() != null
                    && !tipoProducto.getNombre().isEmpty()) {
                TipoProducto tp = findByName(tipoProducto.getNombre());
                if (tp == null) {
                    tipoProductoFacade.insert(tipoProducto);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo tipo de producto " + tipoProducto.getNombre() + " se ha guardado correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("AltaTipoProducto", msj);
                    clean();
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo tipo de producto " + tipoProducto.getNombre() + " no pudo ser guardada ya que ya se ha registrada con anterioridad.", "");
                    FacesContext.getCurrentInstance().addMessage("AltaTipoProducto", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre es requerido.", "");
                FacesContext.getCurrentInstance().addMessage("AltaTipoProducto", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva tipo de producto  " + tipoProducto.getNombre() + "  no pudo ser guardada " + e, "");
            FacesContext.getCurrentInstance().addMessage("AltaTipoProducto", msj);

        }
        return "ConsultaTipoProducto";
    }

    public void delete(TipoProducto tl) {
        FacesMessage msj;
        try {
            // categoria = c;
            //categoriaFacade.delete(categoria);
            tipoProductoFacade.delete(tl);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + tl.getNombre() + " fue eliminado con exito.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaTipoProducto", msj);
            mainClean("ConsultaClasificacion");
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + tl.getNombre() + " no pudo eliminarse.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaTipoProducto", msj);

        }

    }

    public String prepareDelete() {
        setConfirm(true);
        return "ConsultaTipoProducto";
    }

    public String prepareEdit(TipoProducto t) {
        tipoProducto = t;
        return "EditarTipoProducto";
    }

    public String update() {
        FacesMessage msj;
        try {
            TipoProducto tp = findByName(tipoProducto.getNombre());

            if (tp == null) {
                tipoProductoFacade.update(tipoProducto);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo producto " + tipoProducto.getNombre() + " se ha actualizado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("EditarTipoProducto", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El tipo producto " + tipoProducto.getNombre() + " no pudo ser actualizada debido a que ya se ha registrado el nombre anteriormente", "");
                FacesContext.getCurrentInstance().addMessage("EditarTipoProducto", msj);
            }

        } catch (Exception e) {

        }
        return "";
    }

    public String mainClean(String url) {
        tipoProducto = new TipoProducto();
        setConfirm(false);
        return url;
    }

    public String clean() {
        tipoProducto = new TipoProducto();
        return "";
    }

    
    /**
     * @return the tipoProductoFacade
     */
    public TipoProductoFacade getTipoProductoFacade() {
        return tipoProductoFacade;
    }

    /**
     * @param tipoProductoFacade the tipoProductoFacade to set
     */
    public void setTipoProductoFacade(TipoProductoFacade tipoProductoFacade) {
        this.tipoProductoFacade = tipoProductoFacade;
    }

    
    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the tipoProducto
     */
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    /**
     * @param tipoProducto the tipoProducto to set
     */
    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    /**
     * @return the tipoProductoList
     */
    public List<TipoProducto> getTipoProductoList() {
        return tipoProductoList;
    }

    /**
     * @param tipoProductoList the tipoProductoList to set
     */
    public void setTipoProductoList(List<TipoProducto> tipoProductoList) {
        this.tipoProductoList = tipoProductoList;
    }

}
