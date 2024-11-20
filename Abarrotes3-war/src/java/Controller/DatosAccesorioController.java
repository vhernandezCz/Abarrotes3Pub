/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Categoria;
import Entity.Clasificacion;
import Entity.DatosAccesorio;
import Entity.DiasSemana;
import Entity.Inventario;
import Entity.Orden;
import Entity.OrdenAccesorio;
import Entity.Proveedor;
import Entity.TipoProducto;
import Estructuras.Columnas;
import Facade.DatosAccesorioFacade;
import Facade.InventarioFacade;
import Facade.OrdenAccesorioFacade;
import Facade.OrdenFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lenovo
 */
@Named(value = "datosAccesorioController")
@SessionScoped
public class DatosAccesorioController implements Serializable {

    /**
     * @return the deshabilitarLink
     */
    public Boolean getDeshabilitarLink() {
        return deshabilitarLink;
    }

    /**
     * @param deshabilitarLink the deshabilitarLink to set
     */
    public void setDeshabilitarLink(Boolean deshabilitarLink) {
        this.deshabilitarLink = deshabilitarLink;
    }

    @EJB
    private DatosAccesorioFacade datosAccesorioFacade;
    private DatosAccesorio datosAccesorio = new DatosAccesorio();

    @EJB
    private OrdenAccesorioFacade ordenAccesorioFacade;

    @EJB
    private OrdenFacade ordenFacade;

    @EJB
    private InventarioFacade inventarioFacade;

    @Inject
    private CategoriaController categoriaController;

    @Inject
    private ClasificacionController clasificacionController;

    @Inject
    private ConfiguracionDeduccionesController configuracionDeduccionesController;

    @Inject
    private OrdenAccesorioController ordenAccesorioController;

    @Inject
    private InventarioController inventarioController;

    @Inject
    private ProveedorController proveedorController;

    @Inject
    private TipoProductoController tipoProductoController;

    // private Part imagen;
    private Categoria categoria = new Categoria();
    private List<Categoria> categorias = new ArrayList<>();
    private Clasificacion clasificacion = new Clasificacion();
    private List<Clasificacion> clasificaciones = new ArrayList<>();

    private List<Orden> ordenList;
    //private Orden ordenSeleccionada;

    private Inventario inventario;
    private Inventario inventarioNuevoEstilo;

    private byte[] imagen;

    //Banderas
    private boolean showDivNuevaOrden;
    private boolean deshabilitarBtnNuevaOrden;
    //Btn agregar estilo existente
    private boolean deshabilitarBtnAgregar;
    //Btn agregar nuevo estilo
    private boolean deshabilitarBtnAgregarNuevoEstilo;

    //Data nuevo proveedor sugerido
    private String nuevaOrden;
    private int nuevoPrecioInicial;
    private String nuevoSKU;

    //Nuevo proveedor sugerido
    private String ordenName;
    private String skuName;
    private int cantidadAgregar;

    //Nuevo proveedor
    private boolean showOrdenTxt = false;

    private Columnas columnas;

    //Actualización
    private Proveedor proveedor;
    private boolean showProveedorTxt = false;
    private String nuevoProveedor;
    private String sugerirPrecio;
    private List<String> sugerirPrecioList = new ArrayList<>();
    private TipoProducto tipoProducto;
    private Boolean deshabilitarLink = true;

    //Constructor
    public DatosAccesorioController() {
    }

    @PostConstruct
    void init() {
        inicializarVariables();
    }

    public void inicializarVariables() {
        //Nueva orden proveedor sugerido
        nuevaOrden = "";
        //Actualización
        nuevoProveedor = "";

        nuevoPrecioInicial = 0;
        setNuevoSKU("");

        //Banderas
        setShowDivNuevaOrden(false);
        setDeshabilitarBtnNuevaOrden(true);
        setDeshabilitarBtnAgregar(true);
        this.deshabilitarBtnAgregarNuevoEstilo = true;

        //Columnas de imagenes
        columnas = new Columnas();
        columnas.setColumna1(new ArrayList<>());
        columnas.setColumna2(new ArrayList<>());
        columnas.setColumna3(new ArrayList<>());
        columnas.setColumna4(new ArrayList<>());
        columnas.setColumna5(new ArrayList<>());
        ordenName = "N/A";

        //Ordenes filtradas por estilo
        ordenList = new ArrayList<>();
        //ordenSeleccionada = new Orden();

        //Inicializacion modelos
        inventario = new Inventario();

        Orden orden = new Orden();
        Clasificacion tipo = new Clasificacion();
        Categoria cat = new Categoria();
        OrdenAccesorio oa = new OrdenAccesorio();
        datosAccesorio = new DatosAccesorio();

        limpiarNuevoEstiloForm();

        //Inicializar para producto vacio
        orden.setOrden(ordenName);
        orden.setSku("N/A");

        tipo.setNombre("N/A");
        cat.setNombre("N/A");

        datosAccesorio.setClasificacion(tipo);
        datosAccesorio.setCategoria(cat);
        datosAccesorio.setNombre("N/A");
        datosAccesorio.setDescripcion("N/A");
        datosAccesorio.setSku("N/A");

        TipoProducto tp = new TipoProducto();
        tp.setNombre("N/A");

        Proveedor pro = new Proveedor();
        pro.setNombre("N/A");

        oa.setDatosAccesorio(datosAccesorio);
        oa.setOrden(orden);
        oa.setPrecioInicial(0);
        oa.setTipoProducto(tp);
        oa.setProveedor(pro);

        inventario.setOrdenAccesorio(oa);

        sugerirPrecioList.add("SI");
        sugerirPrecioList.add("NO");

        setTipoProducto(new TipoProducto());
    }

    //Metods
    public String insert() {
        FacesMessage msj;
        try {
            datosAccesorioFacade.insert(datosAccesorio);
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "La nueva categoría se ha guardado correctamente", "");
            FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
            datosAccesorio = new DatosAccesorio();
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La nueva categoría no pudo ser guardada " + e, "");
            FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);

        }
        return "AltaCategoria";
    }

    public int insert(DatosAccesorio datosAccesorio) {
        int idDatosAccesorio = 0;
        String nombreSinEspacios = datosAccesorio.getNombre().trim();

        try {
            if (datosAccesorio.getImagen() != null
                    && !nombreSinEspacios.isEmpty() //                   && datosAccesorio.getCategoria().getIdCategoria() > 0) {
                    ) {
                idDatosAccesorio = datosAccesorioFacade.guardarDatoAccesorio(datosAccesorio);

            }
        } catch (Exception e) {

        }
        return idDatosAccesorio;
    }

    public List<Clasificacion> findAllClasificaciones() {
        List<Clasificacion> clasificaciones = new ArrayList<>();
        try {
            clasificaciones.addAll(datosAccesorioFacade.findAllClasificaciones());

        } catch (Exception e) {

        }
        return clasificaciones;
    }

    public List<TipoProducto> findAllTipoProducto() {
        return tipoProductoController.findAll();
    }

    public void esMostrarNuevoProveedor() {
        this.showDivNuevaOrden = true;
        if (!this.inventario.getOrdenAccesorio()
                .getDatosAccesorio()
                .getNombre()
                .equals("")) {
            setDeshabilitarBtnAgregar(true);
            
        }
    }

    public List<Categoria> findAllCategoria() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            categorias.addAll(categoriaController.findAllCategorias());

        } catch (Exception e) {

        }
        return categorias;
    }

    /* public void buscarEstilos(Integer idTipoProducto, Integer idCategoria) {
        inicializarVariables();
        List<DatosAccesorio> da = new ArrayList<>();
        try {
            if (idTipoProducto != null && idTipoProducto > 0) {
                da = datosAccesorioFacade.buscarEstilos(idTipoProducto, idCategoria);
                generarColumnas(da);
            }

        } catch (Exception e) {
        }
    }
     */
    public void buscarEstilos(Integer idTipoProducto) {
        inicializarVariables();
        List<DatosAccesorio> da = new ArrayList<>();
        List<Inventario> inv = new ArrayList<>();

        try {
            if (idTipoProducto != null && idTipoProducto > 0) {
                inv = inventarioController.buscarEstilos(idTipoProducto);
                for (Inventario i : inv) {
                    da.add(i.getOrdenAccesorio().getDatosAccesorio());
                }

                generarColumnas(da);
            }
        } catch (Exception e) {
        }
    }

    //Actualizar método
    /*public void crearNuevaOrdenProveedorSugerido() {
        Orden ord = new Orden();
        FacesMessage msj;

        String nuevoSKUSinEspacios = this.nuevoSKU.trim();
        String nuevaOrdenSinEspacios = this.nuevaOrden.trim();
        Orden ordenExistente = new Orden();

        try {
            if (!nuevaOrdenSinEspacios.isEmpty()
                    && !nuevoSKUSinEspacios.isEmpty()
                    && this.nuevoPrecioInicial > 0
                    && this.nuevoSKU != null
                    && this.nuevaOrden != null) {

                ordenExistente = ordenFacade.consultaTuplaOrdenSKU(this.nuevaOrden, this.nuevoSKU);

                if (ordenExistente == null) {
                    if (this.nuevoPrecioInicial < this.inventario.getOrdenAccesorio().getPrecioInicial()) {
                        ord.setSku(this.nuevoSKU);
                        ord.setOrden(this.nuevaOrden);

                        int idOrdenAgregada = ordenFacade.insert(ord);
                        ord.setIdOrden(idOrdenAgregada);

                        OrdenAccesorio nuevaOrdenAccesorio = new OrdenAccesorio();
                        nuevaOrdenAccesorio.setOrden(ord);
                        nuevaOrdenAccesorio.setPrecioInicial(this.nuevoPrecioInicial);

                        int precioSugerido = ordenAccesorioController.calcularPrecioSugerido(this.nuevoPrecioInicial);

                        nuevaOrdenAccesorio.setPrecioSugerido(precioSugerido);

                        nuevaOrdenAccesorio.setDatosAccesorio(this.inventario.getOrdenAccesorio().getDatosAccesorio());

                        Inventario nuevoInventario = new Inventario();
                        //Actualiza el inventario con la nueva orden
                        nuevoInventario = actualizarRelacionInventarioOrdenAccesorio(this.inventario, nuevaOrdenAccesorio);
                        this.inventario = nuevoInventario;

                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo proveedor para proveedor sugerido se ha guardado correctamente.", "");
                        FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

                        this.cantidadAgregar = 0;
                        limpiarNuevaOrdenData();
                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor para proveedor sugerido no pudo ser guardada,"
                                + " valide la información nuevamente. ", "");
                        FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

                    }

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor para proveedor sugerido no pudo ser guardada,"
                            + " la tupla orden y SKU ya se ha registrado anteriormente. ", "");
                    FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El nuevo proveedor para proveedor sugerido no pudo ser guardada,"
                        + " valide la información nuevamente. ", "");
                FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);
            }

        } catch (Exception e) {
        }
    }*/
    public void crearNuevaOrdenProveedorSugerido() {
        Orden ord = new Orden();
        FacesMessage msj;

        try {
            //Datos de proveedor nuevo
            /*ord.setSku(this.nuevoSKU);
            ord.setOrden(this.nuevaOrden);

            int idOrdenAgregada = ordenFacade.insert(ord);
            ord.setIdOrden(idOrdenAgregada);
             */
            OrdenAccesorio nuevaOrdenAccesorio = new OrdenAccesorio();
            //nuevaOrdenAccesorio.setOrden(ord);
            nuevaOrdenAccesorio.setPrecioInicial(this.nuevoPrecioInicial);

            int precioSugerido = ordenAccesorioController.calcularPrecioSugerido(this.nuevoPrecioInicial);

            nuevaOrdenAccesorio.setPrecioSugerido(precioSugerido);

            nuevaOrdenAccesorio.setDatosAccesorio(this.inventario.getOrdenAccesorio().getDatosAccesorio());

            Inventario nuevoInventario = new Inventario();
            //Actualiza el inventario con la nueva orden
            nuevoInventario = actualizarRelacionInventarioOrdenAccesorio(this.inventario, nuevaOrdenAccesorio);
            this.inventario = nuevoInventario;

            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo proveedor para proveedor sugerido se ha guardado correctamente.", "");
            FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

            this.cantidadAgregar = 0;
            limpiarNuevaOrdenData();
        } catch (Exception e) {
        }
    }

    public void agregarProductoEstiloExistente(int idOrdenAccesorio, int cantidadAgregar) {
        try {
            inventarioController.agregarProductoEstiloExistente(idOrdenAccesorio, cantidadAgregar);
            this.cantidadAgregar = 0;
        } catch (Exception e) {

        }
    }

    //Actualizar método
    public void crearProveedorSugeridoNuevoEstilo() {
        FacesMessage msj;
        try {
            //Valida que los campos no sean nulos
            if (this.nuevaOrden != null
                    && this.nuevoSKU != null
                    && this.nuevoPrecioInicial > 0) {

                String nuevaOrdenSinEspacios = this.nuevaOrden.trim();
                String nuevoSKUSinEspacios = this.nuevoSKU.trim();

                //Valida que los campos no estén vacios
                if (!nuevaOrdenSinEspacios.isEmpty() && !nuevoSKUSinEspacios.isEmpty()) {

                    //Valida que el nuevo precio sea menor al precio actual
                    if (this.nuevoPrecioInicial < this.inventario.getOrdenAccesorio().getPrecioInicial()) {

                        Orden ordenExistente = new Orden();
                        ordenExistente = ordenFacade.consultaTuplaOrdenSKU(this.nuevaOrden, this.nuevoSKU);

                        //Valida que la tupla orden y sku no este registrada, de ser asi actualiza el precio inicial
                        //y recalcula y actualiza el nuevo precio sugerido
                        if (ordenExistente == null) {
                            Orden ord = new Orden();
                            int idOrdenAgregada = ordenFacade.insert(ord);
                            ord.setSku(this.nuevoSKU);
                            ord.setOrden(this.nuevaOrden);
                            ord.setIdOrden(idOrdenAgregada);

                            OrdenAccesorio nuevaOrdenAccesorio = new OrdenAccesorio();
                            nuevaOrdenAccesorio.setOrden(ord);
                            nuevaOrdenAccesorio.setPrecioInicial(this.nuevoPrecioInicial);

                            int precioSugerido = ordenAccesorioController.calcularPrecioSugerido(this.nuevoPrecioInicial);
                            nuevaOrdenAccesorio.setPrecioSugerido(precioSugerido);
                            nuevaOrdenAccesorio.setDatosAccesorio(this.inventario.getOrdenAccesorio().getDatosAccesorio());

                            Inventario nuevoInventario = new Inventario();
                            nuevoInventario = actualizarRelacionInventarioOrdenAccesorio(this.inventario, nuevaOrdenAccesorio);
                            this.inventario = nuevoInventario;

                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo proveedor para proveedor sugerido se ha guardado correctamente.", "");
                            FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

                            this.cantidadAgregar = 0;
                            limpiarNuevaOrdenData();
                        } else {
                            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo proveedor para proveedor sugerido no se ha guardado debido a que"
                                    + " ya ha sido registrado anteriormente ", "");
                            FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

                        }
                    } else {
                        msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nuevo proveedor para proveedor sugerido no se ha guardado debido a que "
                                + "actualmente la mejor opción es el proveedor " + inventario.getOrdenAccesorio().getOrden().getOrden()
                                + ", ya que cuenta con un precio inicial de $" + inventario.getOrdenAccesorio().getPrecioInicial() + ".", "");
                        FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

                    }

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No fue posible guardar el nuevo proveedor, por favor verifique la información e intente nuevamente.", "");
                    FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No fue posible guardar el nuevo proveedor correctamente.", "");
                FacesContext.getCurrentInstance().addMessage("AgregarProductoEstiloExistente", msj);

            }
        } catch (Exception e) {
        }
    }

    //Actualizar método
    /*public void validarProveedorNuevoEstilo(String orden, String SKU) {
        FacesMessage msj;
        Orden ordenExistente = new Orden();

        try {
            String ordenSinEspacios = orden.trim();
            String skuSinEspacios = SKU.trim();

            if (!ordenSinEspacios.isEmpty() && !skuSinEspacios.isEmpty()) {
                ordenExistente = ordenFacade.consultaTuplaOrdenSKU(orden, SKU);

                if (ordenExistente == null) {

                    Orden ord = new Orden();
                    ord.setOrden(orden);
                    ord.setSku(SKU);
                    this.inventarioNuevoEstilo.getOrdenAccesorio().setOrden(ord);

                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha asignado un proveedor correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("NuevoEstilo", msj);
                    this.deshabilitarBtnAgregarNuevoEstilo = false;

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No pudo asignado un proveedor guardada debido a que "
                            + "la tupla de proveedor y SKU fue registrada anteriormente. ", "");
                    FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Los campos Proveedor y SKU son requeridos. ", "");
                FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No pudo asignado un proveedor, consulte con el "
                    + "administrador. ", "");
            FacesContext.getCurrentInstance().addMessage("AltaCategoria", msj);

        }

        limpiarNuevaOrdenData();
    }*/
    public void validarProveedorNuevoEstilo(String proveedor, String SKU) {
        FacesMessage msj;

        try {
            String proveedorSinEspacios = proveedor.trim();
            String skuSinEspacios = SKU.trim();

            if (!proveedorSinEspacios.isEmpty() && !skuSinEspacios.isEmpty()) {
                Boolean esTuplaExistente = inventarioController.consultaTuplaProveedorSKU(proveedor, SKU);

                UIComponent proveedorTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("proveedorNuevoProductoTxt");
                if (proveedorTxtComponent != null) {
                    ((UIInput) proveedorTxtComponent).resetValue();
                }

                UIComponent nuevoSKUTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("skuNuevoProductoTxt");
                if (nuevoSKUTxtComponent != null) {
                    ((UIInput) nuevoSKUTxtComponent).resetValue();
                }

                if (esTuplaExistente == false) {

                    OrdenAccesorio oa = new OrdenAccesorio();
                    oa.setProveedor(this.proveedor);
                    oa.getProveedor().setIdProveedor(this.proveedor.getIdProveedor());

                    DatosAccesorio datA = new DatosAccesorio();
                    datA.setSku(SKU);

                    oa.setDatosAccesorio(datA);

                    this.inventarioNuevoEstilo.setOrdenAccesorio(oa);

                    msj = new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Se ha asignado un proveedor correctamente", "");
                    FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);
                    this.deshabilitarBtnAgregarNuevoEstilo = false;

                } else {
                    msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "No pudo asignado un proveedor guardada debido a que "
                            + "la tupla de proveedor y SKU fue registrada anteriormente. ", "");
                    FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);
                }
            } else {
                msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Los campos Proveedor y SKU son requeridos. ", "");
                FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);
            }
        } catch (Exception e) {
            msj = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "No pudo asignado un proveedor, consulte con el "
                    + "administrador. ", "");
            FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);

        }

        limpiarNuevaOrdenData();
    }

    //Actualizar método
    public String guardarNuevoEstilo(Inventario inventario, String precSugerido) {
        //inventario.getOrdenAccesorio().getProveedor().setIdProveedor(
        //      inventario.getOrdenAccesorio().getOrden);
//        inventario.getOrdenAccesorio().setProveedor(this.proveedor);
       
        inventarioController.guardarNuevoEstilo(inventario, precSugerido);
        limpiarNuevoEstiloForm();
        return "AgregarProductoEstiloExistente";
    }

    public Inventario actualizarRelacionInventarioOrdenAccesorio(Inventario anteriorInventario, OrdenAccesorio nuevaOrdenAccesorio) {

        Inventario nuevoInventario = new Inventario();
        nuevoInventario.setEnStock(anteriorInventario.getEnStock());
        nuevoInventario.setOrdenAccesorio(nuevaOrdenAccesorio);

        //int idInventario = inventarioFacade.insert(nuevoInventario);
        //nuevoInventario.setIdInventario(idInventario);

        inventarioFacade.delete(anteriorInventario);
        return nuevoInventario;
    }

    public void limpiarNuevaOrdenData() {
        /*this.nuevaOrden = "";
        //Actualización
        this.nuevoProveedor = "";
        this.nuevoSKU = "";
        this.nuevoPrecioInicial = 0;
        this.showDivNuevaOrden = false;
        this.deshabilitarBtnAgregar = false;
         */
    }

    public void limpiarForm() {
        this.proveedorController.limpiarForm();
        this.showDivNuevaOrden = false;
        if (!this.inventario.getOrdenAccesorio().getDatosAccesorio().getNombre().equals("")) {
            setDeshabilitarBtnAgregar(true);
        }
    }

    public void consultarPorDatosAccesorio(DatosAccesorio da) {
        try {
            if (da.getIdDatosAccesorio() != null && da.getIdDatosAccesorio() > 0) {
                this.deshabilitarBtnNuevaOrden = false;
                this.deshabilitarBtnAgregar = false;
                this.deshabilitarLink = false;
                this.inventario = inventarioFacade.consultarPorDatosAccesorioProveedorSugerido(
                        da.getIdDatosAccesorio());

                /*obtenerOrdenesPorEstilo(
                        this.inventario.getOrdenAccesorio().getDatosAccesorio().getIdDatosAccesorio());
                 */ //Actualización del letrero de nuevo producto con estilo existente
                //this.ordenName = this.inventario.getOrdenAccesorio().getOrden().getOrden();
                //this.skuName = this.inventario.getOrdenAccesorio().getOrden().getSku();
            }
        } catch (Exception e) {

        }
    }

    public void asignarNuevoProveedorProductoRegistrado(Proveedor proveedor, OrdenAccesorio ordenAccesorio) {
        boolean esCamposLlenos = false;
        esCamposLlenos = proveedorController.validarCampos(proveedor);

        if (esCamposLlenos == true) {
            int idProv = 0;
            idProv = proveedorController.addProveedor(proveedor);
            proveedor.setIdProveedor(idProv);

            actualizarProveedor(ordenAccesorio, proveedor);
            if (!this.inventario.getOrdenAccesorio().getDatosAccesorio().getNombre().equals("")) {
                setDeshabilitarBtnAgregar(true);
            }
        }
    }

    private Proveedor determinaProveedorSugerido(int idDatosAccesorio) {
        Proveedor proveedorSugerido = new Proveedor();
        try {
            OrdenAccesorio ordenAccesorio = new OrdenAccesorio();
            ordenAccesorio = this.ordenAccesorioController.determinaProveedorSugerido(idDatosAccesorio);

            proveedorSugerido = ordenAccesorio.getProveedor();
        } catch (Exception e) {

        }
        return proveedorSugerido;
    }

    private void actualizarProveedor(OrdenAccesorio ordenAccesorio, Proveedor prov) {
        ordenAccesorio.setProveedor(prov);
        ordenAccesorio.setPrecioInicial(this.nuevoPrecioInicial);
        this.ordenAccesorioFacade.insert(ordenAccesorio);

        Proveedor proveedorSugerido = new Proveedor();
        proveedorSugerido = determinaProveedorSugerido(ordenAccesorio.getDatosAccesorio().getIdDatosAccesorio());

        datosAccesorioFacade.updateProveedor(
                ordenAccesorio.getDatosAccesorio().getIdDatosAccesorio(),
                proveedorSugerido);
        inventario.getOrdenAccesorio().setProveedor(proveedorSugerido);
        inventarioController.update(inventario);
    }

    public void obtenerOrdenesPorEstilo(int idDatosAccesorio) {
        ordenList = new ArrayList<>();
        try {
            if (idDatosAccesorio > 0) {
                ordenList.addAll(ordenAccesorioFacade.obtenerOrdenes(idDatosAccesorio));
            }
        } catch (Exception e) {

        }
    }
    

    public Object generarColumnas(List<DatosAccesorio> listaDatosAccesorios) {
        try {
            columnas = new Columnas();
            List<Object> columna1 = new ArrayList<>();
            List<Object> columna2 = new ArrayList<>();
            List<Object> columna3 = new ArrayList<>();
            List<Object> columna4 = new ArrayList<>();
            List<Object> columna5 = new ArrayList<>();

            double objetosPorColumna = 0;
            objetosPorColumna = Math.ceil(Math.floor(listaDatosAccesorios.size() / 5));

            int a = 0;
            int b = 0;
            do {
                columna1.add(listaDatosAccesorios.get(a));
                columnas.setColumna1(columna1);
                a++;

                columna2.add(listaDatosAccesorios.get(a));
                columnas.setColumna2(columna2);
                a++;

                columna3.add(listaDatosAccesorios.get(a));
                columnas.setColumna3(columna3);
                a++;

                columna4.add(listaDatosAccesorios.get(a));
                columnas.setColumna4(columna4);
                a++;

                columna5.add(listaDatosAccesorios.get(a));
                columnas.setColumna5(columna5);
                a++;
                b++;
            } while (b <= objetosPorColumna);
        } catch (Exception e) {
        }
        return columnas;
    }

    public String getImagenBase64(DatosAccesorio da) {
        if (da.getImagen() != null) {
            byte[] imageData = da.getImagen();
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
        }
        return "";
    }

    public String procesarArchivo() {
        try {
            // Obtiene la entrada de archivo almacenada en el atributo de sesión
            this.imagen = (byte[]) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("imageBytes");

            String imgPruebaBase64 = Base64.getEncoder().encodeToString(this.imagen);
            return imgPruebaBase64;
        } catch (Exception ex) {
            // Manejo de excepciones
        }
        return null;
    }

    public String prepareEdit(Inventario i) {

        UIComponent nuevoSKUTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("skuNuevoProductoTxt");
        if (nuevoSKUTxtComponent != null) {
            ((UIInput) nuevoSKUTxtComponent).resetValue();
        }

        String imgString = procesarArchivo();
        inventarioNuevoEstilo = i;

        if (imgString != null) {
            this.inventarioNuevoEstilo.getOrdenAccesorio().getDatosAccesorio().setImagen(this.imagen);
            this.inventarioNuevoEstilo.getOrdenAccesorio().getDatosAccesorio().setProveedor(this.proveedor);
            return "NuevoEstiloAñadirProveedorParte2";
        } else {
            FacesMessage msj;
            msj = new FacesMessage(FacesMessage.SEVERITY_INFO, "Complete la información para poder avanzar.", "");
            FacesContext.getCurrentInstance().addMessage("NuevoEstiloAñadirProveedor", msj);
            return "";
        }
        //return "NuevoEstilo";
    }

    public String prepareProductoNuevoWizard3(Inventario i, String preSugerido) {

        if (this.sugerirPrecio.equals("SI")) {
            return "NuevoEstiloAñadirProveedorParte3";
        } else {
            //i.getOrdenAccesorio().setTipoProducto(this.inventarioNuevoEstilo.getOrdenAccesorio().getTipoProducto());
            guardarNuevoEstilo(i, preSugerido);
            //A pantalla de inicio de wizard
            return "NuevoEstiloAñadirProveedor";
        }

    }

    public String prepararNuevaOrden(String url) {
        this.nuevaOrden = "";
        this.nuevoSKU = "";
        setDeshabilitarBtnAgregar(true);
        return proveedorController.mainClean(url);
        /*setShowDivNuevaOrden(true);
        setShowOrdenTxt(true);*/
    }

    public void ocultarNuevaOrden() {
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("javax.faces.validator.DISABLE_DEFAULT_BEAN_VALIDATOR", Boolean.TRUE);
        this.showDivNuevaOrden = false;
        this.deshabilitarBtnAgregar = false;
        this.nuevaOrden = "";
        this.nuevoSKU = "";
        this.nuevoPrecioInicial = 0;
        this.showOrdenTxt = false;
        this.showProveedorTxt = false;
        this.deshabilitarBtnAgregarNuevoEstilo = true;

        // Reiniciar los componentes relacionados con los validadores
        UIComponent nuevoOrdenTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevaOrdenTxt");
        if (nuevoOrdenTxtComponent != null) {
            ((UIInput) nuevoOrdenTxtComponent).resetValue();
        }

        UIComponent nuevoSKUTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoSKUTxt");
        if (nuevoSKUTxtComponent != null) {
            ((UIInput) nuevoSKUTxtComponent).resetValue();
        }

        UIComponent nuevoPrecioInicialTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoPrecioInicialTxt");
        if (nuevoPrecioInicialTxtComponent != null) {
            ((UIInput) nuevoPrecioInicialTxtComponent).resetValue();
        }
    }

    public void limpiarNuevoEstiloForm() {

        deshabilitarBtnAgregarNuevoEstilo = true;

        inventarioNuevoEstilo = new Inventario();
        Orden ordenNuevoEstilo = new Orden();
        OrdenAccesorio ordenAccesorioNuevoEstilo = new OrdenAccesorio();
        DatosAccesorio datosAccesorioNuevoEstilo = new DatosAccesorio();

        //Inicialización para agregar nuevo estilo
        ordenNuevoEstilo.setOrden("");
        ordenNuevoEstilo.setSku("");
        ordenAccesorioNuevoEstilo.setOrden(ordenNuevoEstilo);
        ordenAccesorioNuevoEstilo.setPrecioInicial(0);
        ordenAccesorioNuevoEstilo.setDatosAccesorio(datosAccesorioNuevoEstilo);

        inventarioNuevoEstilo.setOrdenAccesorio(ordenAccesorioNuevoEstilo);

        // Reiniciar los componentes relacionados con los validadores
        UIComponent nuevoOrdenTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevaOrdenTxt");
        if (nuevoOrdenTxtComponent != null) {
            ((UIInput) nuevoOrdenTxtComponent).resetValue();
        }

        UIComponent nuevoSKUTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoSKUTxt");
        if (nuevoSKUTxtComponent != null) {
            ((UIInput) nuevoSKUTxtComponent).resetValue();
        }

        UIComponent nuevoPrecioInicialTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoPrecioInicialTxt");
        if (nuevoPrecioInicialTxtComponent != null) {
            ((UIInput) nuevoPrecioInicialTxtComponent).resetValue();
        }

    }

    public void limpiarNuevoEstiloParte2() {

        //Inicialización para agregar nuevo estilo
        inventarioNuevoEstilo.getOrdenAccesorio().getDatosAccesorio().setNombre("");
        inventarioNuevoEstilo.getOrdenAccesorio().getDatosAccesorio().setDescripcion("");
        inventarioNuevoEstilo.getOrdenAccesorio().setPrecioInicial(0);
        inventarioNuevoEstilo.setEnStock(0);

        // Reiniciar los componentes relacionados con los validadores
        UIComponent nuevoOrdenTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevaOrdenTxt");
        if (nuevoOrdenTxtComponent != null) {
            ((UIInput) nuevoOrdenTxtComponent).resetValue();
        }

        UIComponent nuevoSKUTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoSKUTxt");
        if (nuevoSKUTxtComponent != null) {
            ((UIInput) nuevoSKUTxtComponent).resetValue();
        }

        UIComponent nuevoPrecioInicialTxtComponent = FacesContext.getCurrentInstance().getViewRoot().findComponent("nuevoPrecioInicialTxt");
        if (nuevoPrecioInicialTxtComponent != null) {
            ((UIInput) nuevoPrecioInicialTxtComponent).resetValue();
        }

    }

    public String mainClean(String url) {
        datosAccesorio = new DatosAccesorio();
        return url;
    }

    public String regresar(String url) {
        return url;
    }

    public void categoriaChange() {
        this.clasificaciones = this.clasificacionController
                .obtenerClasificaciones(this.categoria.getIdCategoria());
    }

    public List<Clasificacion> obtenerTodasClasificaciones() {
        this.clasificaciones = this.clasificacionController
                .findAll();
        return this.clasificaciones;
    }

    //Getter y Setter
    /**
     * @return the configuracionPrecio
     */
    public DatosAccesorioFacade getDatosAccesorioFacade() {
        return datosAccesorioFacade;
    }

    public void setDatosAccesorioFacade(DatosAccesorioFacade datosAccesorioFacade) {
        this.datosAccesorioFacade = datosAccesorioFacade;
    }

    public DatosAccesorio getDatosAccesorio() {
        return datosAccesorio;
    }

    public void setDatosAccesorio(DatosAccesorio datosAccesorio) {
        this.datosAccesorio = datosAccesorio;
    }

    /**
     * @return the columnas
     */
    public Columnas getColumnas() {
        return columnas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(Columnas columnas) {
        this.columnas = columnas;
    }

    /**
     * @return the skuName
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * @param skuName the skuName to set
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName;
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

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    /**
     * @return the ordenList
     */
    public List<Orden> getOrdenList() {
        return ordenList;
    }

    /**
     * @return the ordenSeleccionada
     */
    /*public Orden getOrdenSeleccionada() {
        return ordenSeleccionada;
    }*/
    /**
     * @param ordenSeleccionada the ordenSeleccionada to set
     */
    /*public void setOrdenSeleccionada(Orden ordenSeleccionada) {
        this.ordenSeleccionada = ordenSeleccionada;
    }*/
    /**
     * @return the inventario
     */
    public Inventario getInventario() {
        return inventario;
    }

    public String getNuevaOrden() {
        return nuevaOrden;
    }

    /**
     * @param nuevaOrden the nuevaOrden to set
     */
    public void setNuevaOrden(String nuevaOrden) {
        this.nuevaOrden = nuevaOrden;
    }

    /**
     * @return the nuevoPrecioInicial
     */
    public int getNuevoPrecioInicial() {
        return nuevoPrecioInicial;
    }

    /**
     * @param nuevoPrecioInicial the nuevoPrecioInicial to set
     */
    public void setNuevoPrecioInicial(int nuevoPrecioInicial) {
        this.nuevoPrecioInicial = nuevoPrecioInicial;
    }

    /**
     * @return the showDivNuevaOrden
     */
    public boolean isShowDivNuevaOrden() {
        return showDivNuevaOrden;
    }

    /**
     * @param showDivNuevaOrden the showDivNuevaOrden to set
     */
    public void setShowDivNuevaOrden(boolean showDivNuevaOrden) {
        this.showDivNuevaOrden = showDivNuevaOrden;
    }

    /**
     * @return the deshabilitarBtnNuevaOrden
     */
    public boolean isDeshabilitarBtnNuevaOrden() {
        return deshabilitarBtnNuevaOrden;
    }

    /**
     * @param deshabilitarBtnNuevaOrden the deshabilitarBtnNuevaOrden to set
     */
    public void setDeshabilitarBtnNuevaOrden(boolean deshabilitarBtnNuevaOrden) {
        this.deshabilitarBtnNuevaOrden = deshabilitarBtnNuevaOrden;
    }

    /**
     * @return the deshabilitarBtnAgregar
     */
    public boolean isDeshabilitarBtnAgregar() {
        return deshabilitarBtnAgregar;
    }

    /**
     * @param deshabilitarBtnAgregar the deshabilitarBtnAgregar to set
     */
    public void setDeshabilitarBtnAgregar(boolean deshabilitarBtnAgregar) {
        this.deshabilitarBtnAgregar = deshabilitarBtnAgregar;
    }

    /**
     * @return the nuevoSKU
     */
    public String getNuevoSKU() {
        return nuevoSKU;
    }

    /**
     * @param nuevoSKU the nuevoSKU to set
     */
    public void setNuevoSKU(String nuevoSKU) {
        this.nuevoSKU = nuevoSKU;
    }

    /**
     * @return the cantidadAgregar
     */
    public int getCantidadAgregar() {
        return cantidadAgregar;
    }

    /**
     * @param cantidadAgregar the cantidadAgregar to set
     */
    public void setCantidadAgregar(int cantidadAgregar) {
        this.cantidadAgregar = cantidadAgregar;
    }

    /**
     * @return the configuracionDeduccionesController
     */
    public ConfiguracionDeduccionesController getConfiguracionDeduccionesController() {
        return configuracionDeduccionesController;
    }

    /**
     * @param configuracionDeduccionesController the
     * configuracionDeduccionesController to set
     */
    public void setConfiguracionDeduccionesController(ConfiguracionDeduccionesController configuracionDeduccionesController) {
        this.configuracionDeduccionesController = configuracionDeduccionesController;
    }

    /**
     * @return the clasificaciones
     */
    public List<Clasificacion> getClasificaciones() {
        return clasificaciones;
    }

    /**
     * @param clasificaciones the clasificaciones to set
     */
    public void setClasificaciones(List<Clasificacion> clasificaciones) {
        this.clasificaciones = clasificaciones;
    }

    /**
     * @return the inventarioNuevoEstilo
     */
    public Inventario getInventarioNuevoEstilo() {
        return inventarioNuevoEstilo;
    }

    /**
     * @param inventarioNuevoEstilo the inventarioNuevoEstilo to set
     */
    public void setInventarioNuevoEstilo(Inventario inventarioNuevoEstilo) {
        this.inventarioNuevoEstilo = inventarioNuevoEstilo;
    }

    /**
     * @return the showOrdenSelect
     */
    public boolean isShowOrdenTxt() {
        return showOrdenTxt;
    }

    /**
     * @param showOrdenSelect the showOrdenSelect to set
     */
    public void setShowOrdenTxt(boolean showOrdenTxt) {
        this.showOrdenTxt = showOrdenTxt;
    }

    /**
     * @return the deshabilitarBtnAgregarNuevoEstilo
     */
    public boolean isDeshabilitarBtnAgregarNuevoEstilo() {
        return deshabilitarBtnAgregarNuevoEstilo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
     * @return the showProveedorTxt
     */
    public boolean isShowProveedorTxt() {
        return showProveedorTxt;
    }

    /**
     * @param showProveedorTxt the showProveedorTxt to set
     */
    public void setShowProveedorTxt(boolean showProveedorTxt) {
        this.showProveedorTxt = showProveedorTxt;
    }

    /**
     * @return the nuevoProveedor
     */
    public String getNuevoProveedor() {
        return nuevoProveedor;
    }

    /**
     * @param nuevoProveedor the nuevoProveedor to set
     */
    public void setNuevoProveedor(String nuevoProveedor) {
        this.nuevoProveedor = nuevoProveedor;
    }

    /**
     * @return the sugerirPrecio
     */
    public String getSugerirPrecio() {
        return sugerirPrecio;
    }

    /**
     * @param sugerirPrecio the sugerirPrecio to set
     */
    public void setSugerirPrecio(String sugerirPrecio) {
        this.sugerirPrecio = sugerirPrecio;
    }

    /**
     * @return the sugerirPrecioList
     */
    public List<String> getSugerirPrecioList() {
        return sugerirPrecioList;
    }

    /**
     * @param sugerirPrecioList the sugerirPrecioList to set
     */
    public void setSugerirPrecioList(List<String> sugerirPrecioList) {
        this.sugerirPrecioList = sugerirPrecioList;
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

}
