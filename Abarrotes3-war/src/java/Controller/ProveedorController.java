/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categoria;
import Entity.DiasSemana;
import Entity.Proveedor;
import Facade.ProveedorFacade;
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
@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController implements Serializable {

    @EJB
    private ProveedorFacade proveedorFacade;
    private Proveedor proveedor = new Proveedor();
    private boolean confirm = false;
    //private List<String> diasSemanaSeleccionadosList = new ArrayList<>();
    private DiasSemana diasSemana = new DiasSemana();

    @Inject
    private DatosAccesorioController datosAccesorioController;

    @Inject
    private CategoriaController categoriaController;

    private Categoria categoria = new Categoria();

    //Constructor
    public ProveedorController() {
    }

    /*@PostConstruct
    public void init() {
        getDiasSemana();
    }*/
    public List<Proveedor> findAll() {
        List<Proveedor> proveedorList = new ArrayList<>();
        try {
            proveedorList = proveedorFacade.findAllProveedor();
        } catch (Exception e) {

        }
        return proveedorList;
    }

    public Proveedor findByName(String nombre) {
        Proveedor p = new Proveedor();
        try {
            if (!nombre.equals("")) {
                p = proveedorFacade.findByName(nombre);

            }
        } catch (Exception e) {
        }
        return p;

    }

    public boolean validarTelefono(Proveedor prov) {
        String strTel = String.valueOf(prov.getTelefono());
        int cantidadDeCaracteres = strTel.length();
        if (cantidadDeCaracteres == 10
                && prov.getLada() != null) {
            if (prov.getLada().toString().length() == 2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /* public Boolean validarCamposAgregarProveedor(Proveedor proveedor) {
        FacesMessage msj;
        Boolean sonCamposValidos = false;
        try {
            Proveedor proveedorExistente = new Proveedor();
            proveedorExistente = findByName(proveedor.getNombre());
            //Valida que no se repitan los proveedores guardados
            if (proveedorExistente == null) {

                //Valida que el nombre, tipo y almenos un dato de contacto
                //no esté vacio, el teléfono sea correcto
                if (!proveedor.getNombre().isEmpty() && proveedor.getNombre() != null
                        && (proveedor.getCorreo() != null && !proveedor.getCorreo().isEmpty()
                        || proveedor.getDireccion() != null && !proveedor.getDireccion().isEmpty()
                        || proveedor.getTelefono() != null)
                        && (proveedor.getTipo() != null && !proveedor.getTipo().isEmpty())) {

                    if (proveedor.getTelefono() != null) {

                        boolean telefonoCorrecto = false;
                        telefonoCorrecto = validarTelefono(proveedor);

                        if (telefonoCorrecto == true) {
                            proveedor.setDiasVisita(construirLetreroDiasSeleccionados(diasSemana));
                            sonCamposValidos = true;
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                    + proveedor.getNombre() + " se ha guardado correctamente", "");
                            FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                        } else {
                            sonCamposValidos = false;
                            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo teléfono "
                                    + "debe contar con 10 números y el campo lada es requerido. ", "");
                            FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                        }

                    } else {
                        sonCamposValidos = true;
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                + proveedor.getNombre() + " se ha guardado correctamente", "");
                        FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                    }

                } else {
                    sonCamposValidos = false;
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre, tipo y almenos un dato de contacto "
                            + "son requeridos. ", "");
                    FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);

                }
            } else {
                sonCamposValidos = false;
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser guardado "
                        + "debido a que fue registrado anteriormente. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser guardado, "
                    + "consulte con el administrador. ", "");
            FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
        }
        return sonCamposValidos;
    }*/
    public void limpiarForm() {
        limpiarDiasSemana();
        this.proveedor = new Proveedor();
        this.categoria = new Categoria();
    }

    public Boolean validarCampos(Proveedor proveedor) {
        FacesMessage msj;
        Boolean sonCamposValidos = false;
        try {

            //Valida que el nombre, tipo y almenos un dato de contacto
            //no esté vacio, el teléfono sea correcto
            if (!proveedor.getNombre().isEmpty() && proveedor.getNombre() != null
                    && ((proveedor.getCorreo() != null && !proveedor.getCorreo().isEmpty())
                    || (proveedor.getDireccion() != null && !proveedor.getDireccion().isEmpty())
                    || (proveedor.getTelefono() != null)
                    && (categoria.getIdCategoria() != null
                    && categoria.getIdCategoria() > 0))) {

                if (proveedor.getTelefono() != null) {

                    boolean telefonoCorrecto = false;
                    telefonoCorrecto = validarTelefono(proveedor);

                    if (telefonoCorrecto == true) {
                        proveedor.setDiasVisita(construirLetreroDiasSeleccionados(diasSemana));
                        sonCamposValidos = true;

                    } else {
                        sonCamposValidos = false;
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo teléfono "
                                + "debe contar con 10 números y el campo lada es requerido. ", "");
                        FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                        FacesContext.getCurrentInstance().addMessage("EditarProveedor", msj);
                    }

                } else {
                    proveedor.setDiasVisita(construirLetreroDiasSeleccionados(diasSemana));
                    sonCamposValidos = true;
                }

            } else {
                sonCamposValidos = false;
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo nombre, tipo y almenos un dato de contacto "
                        + "son requeridos. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                FacesContext.getCurrentInstance().addMessage("EditarProveedor", msj);

            }

        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser actualizado, "
                    + "consulte con el administrador. ", "");
            FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
            FacesContext.getCurrentInstance().addMessage("EditarProveedor", msj);
        }
        return sonCamposValidos;
    }

    public String guardarProveedor(Proveedor proveedor) {
        FacesMessage msj;
        try {
            Proveedor proveedorExistente = new Proveedor();
            proveedorExistente = findByName(proveedor.getNombre());
            //Valida que no se repitan los proveedores guardados
            if (proveedorExistente == null) {

                if (proveedor.getNombre() != null) {
                    if (validarCampos(proveedor) == true) {
                        proveedor.setTipo(this.categoria);
                        proveedorFacade.insert(proveedor);
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                + proveedor.getNombre() + " se ha guardado correctamente", "");
                        FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                    }
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser guardado "
                        + "debido a que fue registrado anteriormente. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
            }
        } catch (Exception e) {
        }
//        ConsultaProveedor
        return "ConsultaProveedor";
    }

    public int addProveedor(Proveedor proveedor) {
        FacesMessage msj;
        int idProv = 0;
        try {
            Proveedor proveedorExistente = new Proveedor();
            proveedorExistente = findByName(proveedor.getNombre());
            //Valida que no se repitan los proveedores guardados
            if (proveedorExistente == null) {

                if (proveedor.getNombre() != null) {
                    if (validarCampos(proveedor) == true) {
                        proveedor.setTipo(this.categoria);
                        idProv = proveedorFacade.insert(proveedor);

                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                + proveedor.getNombre() + " se ha guardado correctamente", "");
                        FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                    }
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser guardado "
                        + "debido a que fue registrado anteriormente. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
            }
        } catch (Exception e) {
        }

        return idProv;
    }

    public int insert(Proveedor proveedor) {
        FacesMessage msj;
        int idProv = 0;
        try {
            Proveedor proveedorExistente = new Proveedor();
            proveedorExistente = findByName(proveedor.getNombre());
            //Valida que no se repitan los proveedores guardados
            if (proveedorExistente == null) {

                if (proveedor != null) {
                    if (validarCampos(proveedor) == true) {
                        idProv = proveedorFacade.insert(proveedor);
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                + proveedor.getNombre() + " se ha guardado correctamente", "");
                        FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
                    }
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser guardado "
                        + "debido a que fue registrado anteriormente. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaProveedor", msj);
            }
        } catch (Exception e) {
        }
        return idProv;
    }

    public Proveedor consultarPorId(int idProveedor) {
        Proveedor proveedor = new Proveedor();
        try {
            if (idProveedor > 0) {
                proveedor = proveedorFacade.findById(idProveedor);
            }
        } catch (Exception e) {
        }
        return proveedor;
    }

    public String mainClean(String url) {
        setProveedor(new Proveedor());
        setConfirm(false);
        return url;
    }

    public String prepareNuevoProveedor(String url) {
        limpiarDiasSemana();
        setProveedor(new Proveedor());
        setConfirm(false);
        return url;
    }

    public void limpiarDiasSemana() {
        diasSemana.setLunes(false);
        diasSemana.setMartes(false);
        diasSemana.setMiercoles(false);
        diasSemana.setJueves(false);
        diasSemana.setViernes(false);
        diasSemana.setSabado(false);
        diasSemana.setDomingo(false);
    }

    public String prepareEdit(Proveedor p, Categoria c) {
        proveedor = p;
        categoria = proveedor.getTipo();
        limpiarDiasSemana();
        consultarDiasAEditar(proveedor);
        return "EditarProveedor";
    }

    public String prepareDelete() {
        setConfirm(true);
        return "ConsultaProveedor";
    }

    public void delete(Proveedor prov) {
        FacesMessage msj;
        try {
            proveedorFacade.delete(prov);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El registro de " + prov.getNombre() + " fue eliminado con exito.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaProveedor", msj);
            mainClean("ConsultaProveedor");
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El registro de " + prov.getNombre() + " no pudo eliminarse.", "");
            FacesContext.getCurrentInstance().addMessage("ConsultaProveedor", msj);

        }

    }

    public String update() {
        FacesMessage msj;
        try {
            Proveedor proveedorExistente = new Proveedor();
            proveedorExistente = findByName(proveedor.getNombre());
            //Valida que no se repitan los proveedores guardados
            if (proveedorExistente != null) {

                if (proveedor != null) {
                    if (validarCampos(proveedor) == true) {
                        proveedorFacade.update(proveedor);
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El proveedor "
                                + proveedor.getNombre() + " se ha actualizado correctamente", "");
                        FacesContext.getCurrentInstance().addMessage("EditarProveedor", msj);
                    }
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor no pudo ser actualizado "
                        + "debido a que fue registrado anteriormente. ", "");
                FacesContext.getCurrentInstance().addMessage("EditarProveedor", msj);
            }
        } catch (Exception e) {
        }
        return "ConsultaProveedor";
    }

    public List<Proveedor> getTipoProducto() {
        /*List<String> tipoProdList = new ArrayList<>();
        tipoProdList.add("Proveedor fijo");
        tipoProdList.add("Abastos");
         */
        return proveedorFacade.findAll();
    }

    public List<Categoria> findCategoriasList(int idCategoria) {
        List<Categoria> lstCategorias = categoriaController.findAll();
        List<Categoria> cats = new ArrayList<>();

        cats.add(this.categoria);
        for (Categoria c : lstCategorias) {
            if (c.getIdCategoria() != this.categoria.getIdCategoria()) {
                cats.add(c);
            }
        }
        return cats;
    }

    public String construirLetreroDiasSeleccionados(DiasSemana diasSeleccionados) {
        String dias = "";
        List<String> diasList = new ArrayList<>();
        if (diasSeleccionados != null) {
            if (diasSeleccionados.isLunes() == true) {
                diasList.add("Lunes");
            }
            if (diasSeleccionados.isMartes()) {
                diasList.add("Martes");
            }
            if (diasSeleccionados.isMiercoles()) {
                diasList.add("Miércoles");
            }
            if (diasSeleccionados.isJueves()) {
                diasList.add("Jueves");
            }
            if (diasSeleccionados.isViernes()) {
                diasList.add("Viernes");
            }
            if (diasSeleccionados.isSabado()) {
                diasList.add("Sábado");
            }
            if (diasSeleccionados.isDomingo()) {
                diasList.add("Domingo");
            }
        }

        for (int i = 0; i < diasList.size(); i++) {
            if (i != (diasList.size() - 1)) {
                dias = dias + diasList.get(i) + " - ";
            } else {
                dias = dias + diasList.get(i);
            }
        }

        return dias;
    }

    public void consultarDiasAEditar(Proveedor p) {
        if (p.getDiasVisita() != null) {
            if (p.getDiasVisita().contains("Lunes")) {
                diasSemana.setLunes(true);
            }

            if (p.getDiasVisita().contains("Martes")) {
                diasSemana.setMartes(true);
            }

            if (p.getDiasVisita().contains("Miércoles")) {
                diasSemana.setMiercoles(true);
            }

            if (p.getDiasVisita().contains("Jueves")) {
                diasSemana.setJueves(true);
            }

            if (p.getDiasVisita().contains("Viernes")) {
                diasSemana.setViernes(true);
            }

            if (p.getDiasVisita().contains("Sábado")) {
                diasSemana.setSabado(true);
            }

            if (p.getDiasVisita().contains("Domingo")) {
                diasSemana.setDomingo(true);
            }
        }
    }

    public ProveedorFacade getProveedorFacade() {
        return proveedorFacade;
    }

    /**
     * @param proveedorFacade the proveedorFacade to set
     */
    public void setProveedorFacade(ProveedorFacade proveedorFacade) {
        this.proveedorFacade = proveedorFacade;
    }

    /**
     * @param confirm the confirm to set
     */
    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    /**
     * @return the confirm
     */
    public boolean isConfirm() {
        return confirm;
    }

    /**
     * @return the proveedor
     */
    public Proveedor getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * @return the datosAccesorioController
     */
    public DatosAccesorioController getDatosAccesorioController() {
        return datosAccesorioController;
    }

    /**
     * @param datosAccesorioController the datosAccesorioController to set
     */
    public void setDatosAccesorioController(DatosAccesorioController datosAccesorioController) {
        this.datosAccesorioController = datosAccesorioController;
    }

    /**
     * @return the diasSemana
     */
    public DiasSemana getDiasSemana() {
        return diasSemana;
    }

    /**
     * @param diasSemana the diasSemana to set
     */
    public void setDiasSemana(DiasSemana diasSemana) {
        this.diasSemana = diasSemana;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
