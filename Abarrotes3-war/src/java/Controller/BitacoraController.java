/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.Bitacora;
import Facade.BitacoraFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author lenovo
 */
@Named(value = "bitacoraController")
@SessionScoped
public class BitacoraController implements Serializable {

    @EJB
    private BitacoraFacade bitacoraFacade;
    private Bitacora bitacora = new Bitacora();
    private List<Bitacora> bitacoraList = new ArrayList<>();

    //Constructor
    public BitacoraFacade getBitacoraFacade() {
        return bitacoraFacade;
    }

    //Getter y setter
    public void setBitacoraFacade(BitacoraFacade bitacoraFacade) {
        this.bitacoraFacade = bitacoraFacade;
    }

    public Bitacora getBitacora() {
        return bitacora;
    }

    public void setBitacora(Bitacora bitacora) {
        this.bitacora = bitacora;
    }

    public List<Bitacora> getBitacoraList() {
        return bitacoraList;
    }

    public void setBitacoraList(List<Bitacora> bitacoraList) {
        this.bitacoraList = bitacoraList;
    }

    //Metods
    //Consulta todas los registros guardados en la bitacora
    public List<Bitacora> findAll() {
        List<Bitacora> bitacoras = new ArrayList<>();
        try {
            bitacoras = bitacoraFacade.findAll();
        } catch (Exception e) {

        }
        return bitacoras;
    }

    //Consulta todas las ordenes registradas sin que se repitan
    public List<String> findAllOrder() {
        List<String> ordenList = new ArrayList<>();

        try {
            ordenList.add("Seleccione una orden");
            List<String> ordenes = bitacoraFacade.findAllOrder();
            ordenList.addAll(ordenes);
        } catch (Exception e) {

        }
        return ordenList;
    }

    public List<String> findAllProveedor() {
        List<String> proveedorList = new ArrayList<>();

        try {
            proveedorList.add("Seleccione un proveedor");
            List<String> proveedores = bitacoraFacade.findAllProveedor();
            proveedorList.addAll(proveedores);
        } catch (Exception e) {

        }
        return proveedorList;
    }

    /* 
        Consulta todos los registros guardados en la bitácora 
        por medio de la orden.
     */
    public List<Bitacora> findByOrder(String bitacora) {
        bitacoraList = new ArrayList<>();

        Bitacora elementoVacio = new Bitacora();
        elementoVacio.setSku("Seleccione un SKU");

        bitacoraList.add(elementoVacio);

        try {
            List<Bitacora> bitacoras = new ArrayList<>();
            bitacoras = bitacoraFacade.findByOrder(bitacora);

            for (Bitacora bt : bitacoras) {
                bitacoraList.add(bt);
            }
        } catch (Exception e) {

        }
        return bitacoraList;
    }

    /*
        Agrega nuevos registros a la bitácora
     */
    public String insert(Bitacora bitacora) {
        try {
            bitacoraFacade.insert(bitacora);
        } catch (Exception e) {

        }
        return "";
    }

    /*
        Obtiene los registros en la bitácora por medio de la orden, SKU o nombre.
     */
    public List<Bitacora> buscar(String proveedor, String sku, String tipoProd) {
        bitacoraList = new ArrayList<>();
        try {
            if ((proveedor.equals("Seleccione un proveedor") == true
                    && tipoProd.equals("Seleccione un tipo de producto") == true
                    && sku.equals("") == true)
                    
                    || (proveedor.equals("") == true
                    && sku.equals("") == true
                    && tipoProd.equals("") == true)) {
                bitacoraList = bitacoraFacade.findAll();
            } else {
                bitacoraList = bitacoraFacade.findByOrderSKU(proveedor, sku, tipoProd);
            }
        } catch (Exception e) {

        }
        return bitacoraList;
    }

    public String getImagenBase64(Bitacora bitacora) {
        if (bitacora.getImagen() != null) {
            byte[] imageData = bitacora.getImagen();
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData);
        }
        return "";
    }

}
