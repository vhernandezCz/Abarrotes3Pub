package Servlet;

import Controller.InventarioController;
import Entity.Inventario;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;

@WebServlet(name = "ManejarSeleccionadosServlet", urlPatterns = {"/ManejarSeleccionadosServlet"})
@MultipartConfig
public class ManejarSeleccionadosServlet extends HttpServlet {

    @Inject
    InventarioController inventarioController;
    // Declaración original como Map<Inventario, Integer>
    private final Map<Inventario, Integer> cantidadesProductos = new HashMap<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Manejar los elementos seleccionados
        String[] selectedItems = request.getParameterValues("checkbox");
        if (selectedItems != null && selectedItems.length > 0) {
            request.getSession().setAttribute("checkList", selectedItems);
            response.sendRedirect("/Abarrotes2-war/faces/Ventas/capturaPiezasVendidas.xhtml");
        } else {
            response.sendRedirect("/Abarrotes2-war/faces/Ventas/PinesVendidos.xhtml");

        }
    }

}
