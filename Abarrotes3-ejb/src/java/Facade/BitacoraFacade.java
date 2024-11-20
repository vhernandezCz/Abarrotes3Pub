/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Bitacora;
import java.awt.Image;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;

/**
 *
 * @author lenovo
 */
@Stateless
@LocalBean
public class BitacoraFacade {

    @PersistenceContext(unitName = "Abarrotes3-ejbPU")
    private EntityManager em;

    //Consulta todos los registros disponibles
    public List<Bitacora> findAll() {
        TypedQuery<Bitacora> query;
        query = em.createQuery("SELECT b FROM Bitacora b", Bitacora.class);
        return query.getResultList();
    }

    //Consulta solo las ordenes registradas sin que estas se repitan
    public List<String> findAllOrder() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT b.orden FROM Bitacora b", String.class);
        return query.getResultList();

    }

    public List<String> findAllProveedor() {
        TypedQuery<String> query;
        query = em.createQuery("SELECT DISTINCT b.proveedor FROM Bitacora b", String.class);
        return query.getResultList();

    }

    //Consulta todos los registros que coincidan con la orden
    public List<Bitacora> findByOrder(String orden) {
        TypedQuery<Bitacora> query;
        String queryString = "SELECT o FROM Bitacora o WHERE 1=1";

        if (orden != null && orden.equals("") != true) {
            queryString += " AND o.orden = :orden";
        }

        query = em.createQuery(queryString, Bitacora.class);

        if (orden != null && orden.equals("") != true) {
            query.setParameter("orden", orden);
        }

        return query.getResultList();
    }

    public List<Bitacora> findByOrderSKU(String proveedor, String sku, String tipoProducto) {
        TypedQuery<Bitacora> query;
        String queryString = "SELECT b FROM Bitacora b WHERE 1=1";

        if (proveedor != null && !proveedor.isEmpty() && !proveedor.equals("Seleccione un proveedor")) {
            queryString += " AND b.proveedor = :proveedor";
        }
        if (sku != null && !sku.isEmpty() && !sku.equals("Seleccione un SKU")) {
            queryString += " AND b.sku = :sku";
        }
        if (tipoProducto != null && !tipoProducto.isEmpty()) {
            queryString += " AND b.tipoProducto = :tipoProducto";
        }

        query = em.createQuery(queryString, Bitacora.class);

        if (proveedor != null && !proveedor.isEmpty() && !proveedor.equals("Seleccione un proveedor")) {
            query.setParameter("proveedor", proveedor);
        }
        if (sku != null && !sku.isEmpty() && !sku.equals("Seleccione un SKU")) {
            query.setParameter("sku", sku);
        }
        if (tipoProducto != null && !tipoProducto.isEmpty()) {
            query.setParameter("tipoProducto", tipoProducto);
        }

        return query.getResultList();
    }

    public byte[] obtenerImagen(Long idBitacora) {
        byte[] imagenBytes = null;

        try (
                Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
                PreparedStatement statement = connection.prepareStatement("SELECT imagen FROM ABADB260824.Bitacora WHERE idBitacora = ?");) {

            statement.setLong(1, idBitacora);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Blob imagenBlob = resultSet.getBlob("imagen");
                if (imagenBlob != null) {
                    imagenBytes = imagenBlob.getBytes(1, (int) imagenBlob.length());
                }
            }
        } catch (SQLException e) {
            // Manejar cualquier excepción de base de datos de manera adecuada
            e.printStackTrace();
        }

        return imagenBytes;
    }

    public void insert(Bitacora bitacora) {
        em.persist(bitacora);
    }

    public void update(Bitacora bitacora) {
        em.merge(bitacora);
    }

    public Image cargarImagen(String rutaImagen) {
        ImageIcon imagenIcono = new ImageIcon(getClass().getResource(rutaImagen)); // Carga la imagen desde la ruta especificada
        Image imagen = imagenIcono.getImage(); // Obtiene la imagen del ImageIcon
        return imagen; // Retorna la imagen cargada
    }
}
