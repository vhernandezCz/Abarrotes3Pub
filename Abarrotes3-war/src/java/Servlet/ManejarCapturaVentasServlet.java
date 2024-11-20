/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Controller.InventarioController;
import Entity.Inventario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ManejarCapturaVentasServlet", urlPatterns = {"/ManejarCapturaVentasServlet"})
@MultipartConfig
public class ManejarCapturaVentasServlet extends HttpServlet {

    @Inject
    InventarioController inventarioController;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener la lista de cantidades desde el formulario
        String[] cantidadesStr = request.getParameterValues("cantidadPiezasVendidas");
        List<Integer> cantidades = new ArrayList<>();
        if (cantidadesStr != null) {
            for (String cantidadStr : cantidadesStr) {
                if (!cantidadStr.equals("")) {
                    // Convertir las cantidades a enteros y agregarlas a la lista
                    cantidades.add(Integer.parseInt(cantidadStr));
                }
            }
        }

        if (cantidades.size() != cantidadesStr.length) {
            response.sendRedirect("/Abarrotes2-war/faces/Ventas/capturaPiezasVendidas.xhtml");
        } else {

            String[] selectedItems = (String[]) request.getSession().getAttribute("checkList");

            // Verificar si selectedItems no es nulo
            if (selectedItems != null) {
                // Obtener la lista de IDs de inventario correspondientes a los elementos seleccionados
                List<Inventario> productosEnInventario = new ArrayList<>();
                for (String idInventarioStr : selectedItems) {
                    // Convertir los IDs de inventario a enteros y agregarlos a la lista
                    productosEnInventario.add(obtenerInventarioPorId(Integer.parseInt(idInventarioStr)));
                }

                // Procesar los IDs de inventario y las cantidades
                for (int i = 0; i < productosEnInventario.size(); i++) {
                    Inventario producto = new Inventario();
                    producto = productosEnInventario.get(i);

                    int enStock = producto.getEnStock();
                    int cantidad = cantidades.get(i);

                    if (enStock > 0) {
                        enStock = enStock - cantidad;
                        //Aqui se registra la venta 
                        if(enStock >= 0){
                            inventarioController.actualizarStock(producto.getIdInventario(), enStock);                    
                        }
                    } else {
                        response.sendRedirect("/Abarrotes2-war/faces/Ventas/capturaPiezasVendidas.xhtml");
                    }

                }
                response.sendRedirect("/Abarrotes2-war/faces/Ventas/PinesVendidos.xhtml");
            } else {
                response.sendRedirect("/Abarrotes2-war/faces/Ventas/capturaPiezasVendidas.xhtml");
            }
        }
    }

    private Inventario obtenerInventarioPorId(int id) {
        // Lógica para obtener el objeto Inventario por su id
        Inventario inventario = inventarioController.consultaPorIdInventario(id);
        if (inventario == null) {
            // Manejar el caso cuando el inventario no se encuentra
            throw new IllegalArgumentException("No se encontró el inventario con ID: " + id);
        }
        return inventario;
    }
}
