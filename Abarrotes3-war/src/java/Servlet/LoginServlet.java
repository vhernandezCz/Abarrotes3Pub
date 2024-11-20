/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
/* @Inject
    private UsuariosFacade usuariosFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String passwordIngresada = request.getParameter("password");

        if (email == null || passwordIngresada == null || email.isEmpty() || passwordIngresada.isEmpty()) {
            request.setAttribute("error", "Por favor, ingrese el correo y la contraseña.");
            request.getRequestDispatcher("login.xhtml").forward(request, response);
            return;
        }

        Usuarios usuarios = usuariosFacade.findByEmail(email);

        if (usuarios != null && BCrypt.checkpw(passwordIngresada, usuarios.getPassword_hash())) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarios", usuarios);
            response.sendRedirect("menu.xhtml"); // Redirige a la página de inicio en formato XHTML
        } else {
            request.setAttribute("error", "Email o contraseña incorrectos.");
            request.getRequestDispatcher("login.xhtml").forward(request, response);
        }
    }*/
}
