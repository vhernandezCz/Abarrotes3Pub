/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.ConfiguracionDeducciones;
import Entity.DatosAccesorio;
import Entity.OrdenAccesorio;
import Facade.OrdenAccesorioFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lenovo
 */
@Named(value = "ordenAccesorioController")
@SessionScoped
public class OrdenAccesorioController implements Serializable {

    @EJB
    private OrdenAccesorioFacade ordenAccesorioFacade;
    //private OrdenAccesorio ordenAccesorio = new OrdenAccesorio();

    @Inject
    private ConfiguracionDeduccionesController configuracionDeduccionesController;

    //Metods
    public OrdenAccesorio consultarPorDatosAccesorio(DatosAccesorio datosAccesorio) {
        FacesMessage msj;
        OrdenAccesorio oa = new OrdenAccesorio();
        try {
            if (datosAccesorio.getIdDatosAccesorio() > 0
                    && datosAccesorio.getIdDatosAccesorio() > 0) {
                oa = ordenAccesorioFacade.consultarPorDatosAccesorio(datosAccesorio.getIdDatosAccesorio());
            }
        } catch (Exception e) {

        }
        return oa;
    }

    public List<OrdenAccesorio> findAll() {
        List<OrdenAccesorio> ordenAccesorioList = null;
        FacesMessage msj;
        try {
            ordenAccesorioList = new ArrayList<>();
            ordenAccesorioList = ordenAccesorioFacade.findAll();
            if (ordenAccesorioList == null || ordenAccesorioList.isEmpty()) {
                String sinRegistros = "No se encontró ningun accesorio relacionada con la busqueda.";

                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, sinRegistros, "");
                FacesContext.getCurrentInstance().addMessage("AltaProducto", msj);

            }
        } catch (Exception e) {

        }
        return ordenAccesorioList;
    }

    public void eliminarProveedorDuplicado(int idDatosAccesorio) {
        List<OrdenAccesorio> ordenAccesorioList = new ArrayList<>();

        ordenAccesorioList = this.ordenAccesorioFacade.findByIdDatosAccesorio(
                idDatosAccesorio);

        OrdenAccesorio object = new OrdenAccesorio();
        if (ordenAccesorioList.size() > 1) {
            object = ordenAccesorioList.get(0);
            for (OrdenAccesorio oa : ordenAccesorioList) {
                if (object.equals(oa)
                        && oa.getIdOrdenAccesorio() < object.getIdOrdenAccesorio()) {
                    object = oa;
                }

            }
             ordenAccesorioFacade.delete(object);
        }
    }

    public int calcularPrecioSugerido(int precioInicial) {
        int precioSugerido = 0;
        ConfiguracionDeducciones cd = new ConfiguracionDeducciones();
        cd = configuracionDeduccionesController.obtenerConfiguracionDeducciones();

        int margenBeneficio = (cd.getPorcentajeMargenBeneficio() * precioInicial) / 100;
        int comision = (cd.getComisionesPorcentaje() * precioInicial) / 100;
        int totalParcial = precioInicial + cd.getEntrega() + comision + cd.getEmpaquetado();

        int importacion = cd.getImportacion() / 400;
        precioSugerido = importacion + margenBeneficio + totalParcial;
        return precioSugerido;
    }

    public int insert(OrdenAccesorio ordenAccesorio) {
        int idNuevaOrdenAccesorio = 0;
        FacesMessage msj;

        try {
            if (ordenAccesorio.getPrecioInicial() > 0
                    && ordenAccesorio.getDatosAccesorio().getIdDatosAccesorio() > 0
                    && ordenAccesorio.getProveedor().getIdProveedor() > 0
                    && ordenAccesorio.getTipoProducto().getIdTipoProducto() > 0) {
                idNuevaOrdenAccesorio = ordenAccesorioFacade.insert(ordenAccesorio);
            }
        } catch (Exception e) {

        }
        return idNuevaOrdenAccesorio;
    }

    public OrdenAccesorio determinaProveedorSugerido(int idDatosAccesorio) {
        OrdenAccesorio ordenAccesorio = new OrdenAccesorio();
        try {
            if (idDatosAccesorio > 0) {
                List<OrdenAccesorio> lstOrdenAccesorio = new ArrayList<>();
                lstOrdenAccesorio = ordenAccesorioFacade.determinaProveedoresSugeridos(idDatosAccesorio);
                ordenAccesorio = lstOrdenAccesorio.get(0);
            }
        } catch (Exception e) {

        }

        return ordenAccesorio;
    }

    public void actualizarPrecioSugerido(int idOrdenAccesorio, int precioSugerido) {
        try {
            if (idOrdenAccesorio > 0
                    && precioSugerido > 0) {
                ordenAccesorioFacade.actualizarPrecioSugerido(idOrdenAccesorio, precioSugerido);
            }
        } catch (Exception e) {

        }
    }

    /**
     * @return the ordenAccesorioFacade
     */
    public OrdenAccesorioFacade getOrdenAccesorioFacade() {
        return ordenAccesorioFacade;
    }

    /**
     * @param ordenAccesorioFacade the ordenAccesorioFacade to set
     */
    public void setOrdenAccesorioFacade(OrdenAccesorioFacade ordenAccesorioFacade) {
        this.ordenAccesorioFacade = ordenAccesorioFacade;
    }

}
