/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final String EMPLOYEE_CODE = "002";
    private static final String ADMIN_CODE = "001";
    private static final String LOGIN_PAGE = "/login.xhtml";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String url = req.getRequestURI();
        // Definir permisos de menú
        if (url.contains("/menuTR.xhtml") && !EMPLOYEE_CODE.equals(session.getAttribute("claveUsuario"))) {
            if (url.contains("/menuTR.xhtml") && !ADMIN_CODE.equals(session.getAttribute("claveUsuario"))) {
                res.sendRedirect(req.getContextPath() + "/noAutorizado.xhtml");
                return;
            } else {
                res.sendRedirect(req.getContextPath() + "/faces/menuAM.xhtml");
                return;
            }
        }

        if (url.contains("/menuAM.xhtml") && !ADMIN_CODE.equals(session.getAttribute("claveUsuario"))) {
            if (url.contains("/menuAM.xhtml") && !EMPLOYEE_CODE.equals(session.getAttribute("claveUsuario"))) {
                res.sendRedirect(req.getContextPath() + "/noAutorizado.xhtml");
                return;
            } else {
                res.sendRedirect(req.getContextPath() + "/faces/menuTR.xhtml");
                return;
            }
        }
        
       
        
        if (url.contains("/menuTR.xhtml") 
                && !ADMIN_CODE.equals(session.getAttribute("claveUsuario")) 
                && !EMPLOYEE_CODE.equals(session.getAttribute("claveUsuario"))) {
            res.sendRedirect(req.getContextPath() + "/noAutorizado.xhtml");
            return;
        }
        
        if (url.contains("/menuAM.xhtml") 
                && !ADMIN_CODE.equals(session.getAttribute("claveUsuario")) 
                && !EMPLOYEE_CODE.equals(session.getAttribute("claveUsuario"))) {
            res.sendRedirect(req.getContextPath() + "/noAutorizado.xhtml");
            return;
        }
         
      /*  if (url.contains("/Ventas/") && !EMPLOYEE_CODE.equals(session.getAttribute("claveUsuario"))) {
            res.sendRedirect(req.getContextPath() + "/noAutorizado.xhtml");
            return;
        }*/

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
