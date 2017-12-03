/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.orz.pascal.example.javaee8.security_basic;

import java.io.IOException;
import javax.inject.Inject;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author koduki
 */
@WebServlet("/myservlet")
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class MyServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    @Inject
    private SecurityContext securityContext;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().write("This is a servlet \n");

        String webName = null;
        if (securityContext.getCallerPrincipal() != null) {
            webName = securityContext.getCallerPrincipal().getName();
        }
        response.getWriter().write("web username: " + webName + "\n");

        response.getWriter().write("web user has role \"admin\": " + securityContext.isCallerInRole("admin") + "\n");
        response.getWriter().write("web user has role \"users\": " + securityContext.isCallerInRole("users") + "\n");
        response.getWriter().write("web user has role \"guest\": " + securityContext.isCallerInRole("guest") + "\n");
    }

}
