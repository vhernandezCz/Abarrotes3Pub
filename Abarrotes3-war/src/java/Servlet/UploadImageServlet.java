/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UploadImageServlet", urlPatterns = {"/UploadImageServlet"})
@MultipartConfig
public class UploadImageServlet extends HttpServlet {

    private boolean estaCargadaImagen = false;

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Part filePart = request.getPart("file");

            if (filePart != null && filePart.getSize() > 0) { // Verificar si el archivo no está vacío
                InputStream inputStream = filePart.getInputStream();
                this.estaCargadaImagen = true;
                byte[] imageBytes = inputStreamToByteArray(inputStream);
                // Almacena el byte[] en la sesión
                request.getSession().setAttribute("imageBytes", imageBytes);
                // Envía un mensaje como parte de la respuesta
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("La imagen se cargó correctamente");
            } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("No se ha seleccionado ninguna imagen");
            }
        } catch (Exception ex) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Error al cargar la imagen");
        }
}

    public byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096]; // Tamaño del buffer

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        // Cierra los flujos
        inputStream.close();
        outputStream.close();

        // Obtiene el byte[] del ByteArrayOutputStream
        return outputStream.toByteArray();
    }

}
