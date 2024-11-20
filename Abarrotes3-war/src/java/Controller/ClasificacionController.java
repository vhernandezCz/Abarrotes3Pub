/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categoria;
import Entity.Clasificacion;
import Entity.DatosAccesorio;
import Entity.Inventario;
import Facade.ClasificacionFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lenovo
 */
@Named(value = "clasificacionController")
@SessionScoped
public class ClasificacionController implements Serializable {

    @EJB
    private ClasificacionFacade clasificacionFacade;

    @Inject
    private InventarioController inventarioController;

    @Inject
    private DatosAccesorioController datosAccesorioController;

    private Clasificacion clasificacion = new Clasificacion();
    private List<Clasificacion> clasificacionList = new ArrayList<>();
    private boolean confirm = false;

    //Constructor
    public ClasificacionController() {
    }

    //Metods
    public List<Clasificacion> findAll() {
        List<Clasificacion> clasificacion = new ArrayList<>();
        try {
            clasificacion = getClasificacionFacade().findAll();
        } catch (Exception e) {

        }
        return clasificacion;
    }

    public Clasificacion findByName(String nombre) {
        try {
            Clasificacion clasificacion = new Clasificacion();
            clasificacion = clasificacionFacade.findByName(nombre);
            return clasificacion;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Clasificacion> obtenerClasificaciones(Integer idCategoria) {
        List<Clasificacion> clasificaciones = new ArrayList<>();

        try {
            if (idCategoria != null) {
                Set<Integer> idsUnicos = new HashSet<>(); // Conjunto para almacenar los IDs únicos

                List<Inventario> inventarioList = this.inventarioController.findAll();

                for (Inventario inventario : inventarioList) {
                    DatosAccesorio datosAccesorio = inventario.getOrdenAccesorio().getDatosAccesorio();
                    if (datosAccesorio.getCategoria().getIdCategoria().equals(idCategoria)) {
                        int idClasificacion = datosAccesorio.getClasificacion().getIdClasificacion();
                        // Si el ID de clasificación no está en el conjunto, agregamos la clasificación a la lista y el ID al conjunto
                        if (!idsUnicos.contains(idClasificacion)) {
                            clasificaciones.add(datosAccesorio.getClasificacion());
                            idsUnicos.add(idClasificacion);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
        return clasificaciones;
    }

    public String insert() {
        FacesMessage msj;
        try {
            if (clasificacion.getNombre() != null
                    && !clasificacion.getNombre().isEmpty()) {
                Clasificacion cat = findByName(clasificacion.getNombre());
                if (cat == null) {
                    clasificacionFacade.insert(clasificacion);
                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La nueva clasificación " + clasificacion.getNombre() + " se ha guardado correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("AltaClasificacion", msj);
                    clean();
                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clasificacion  " + clasificacion.getNombre() + " no pudo ser guardada ya que ya se ha registrada con anterioridad.", "");
                    FacesContext.getCurrentInstance().addMessage("AltaClasificacion", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre es requerido.", "");
                FacesContext.getCurrentInstance().addMessage("AltaClasificacion", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva clasificacion  " + clasificacion.getNombre() + "  no pudo ser guardada " + e, "");
            FacesContext.getCurrentInstance().addMessage("AltaClasificacion", msj);

        }
        return "AltaClasificacion";
    }

    public void delete(Clasificacion cl) {
        FacesMessage msj;
        try {
            // categoria = c;
            //categoriaFacade.delete(categoria);
            clasificacionFacade.delete(cl);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + cl.getNombre() + " fue eliminado con exito.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaClasificacion", msj);
            mainClean("ConsultaClasificacion");
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + cl.getNombre() + " no pudo eliminarse.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaClasificacion", msj);

        }

    }

    public String prepareDelete() {
        setConfirm(true);
        return "ConsultaClasificacion";
    }

    public String prepareEdit(Clasificacion c) {
        clasificacion = c;
        return "EditarClasificacion";
    }

    public String update() {
        FacesMessage msj;
        try {
            Clasificacion cat = findByName(clasificacion.getNombre());

            if (cat == null) {
                clasificacionFacade.update(clasificacion);
                msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La clasificacion " + clasificacion.getNombre() + " se ha actualizado correctamente", "");
                FacesContext.getCurrentInstance().addMessage("EditarClasificacion", msj);
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La clasificacion " + clasificacion.getNombre() + " no pudo ser actualizada debido a que ya se ha registrado el nombre anteriormente", "");
                FacesContext.getCurrentInstance().addMessage("EditarClasificacion", msj);
            }

        } catch (Exception e) {

        }
        return "";
    }

    public String mainClean(String url) {
        clasificacion = new Clasificacion();
        setConfirm(false);
        return url;
    }

    public String clean() {
        clasificacion = new Clasificacion();
        return "";
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
     * @return the clasificacionFacade
     */
    public ClasificacionFacade getClasificacionFacade() {
        return clasificacionFacade;
    }

    /**
     * @param clasificacionFacade the clasificacionFacade to set
     */
    public void setClasificacionFacade(ClasificacionFacade clasificacionFacade) {
        this.clasificacionFacade = clasificacionFacade;
    }

    /**
     * @return the clasificacion
     */
    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    /**
     * @param clasificacion the clasificacion to set
     */
    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    /**
     * @return the clasificacionList
     */
    public List<Clasificacion> getClasificacionList() {
        return clasificacionList;
    }

    /**
     * @param clasificacionList the clasificacionList to set
     */
    public void setClasificacionList(List<Clasificacion> clasificacionList) {
        this.clasificacionList = clasificacionList;
    }

}
