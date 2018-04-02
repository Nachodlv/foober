package org.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/foRegister")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/foRegisterView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("here");
        String password = request.getParameter("password");
        String passwordRepeated = request.getParameter("passwordRepeated");
        System.out.println(password + " " + passwordRepeated);
        if(!password.equals(passwordRepeated)){
            request.setAttribute("errorPassword", "Passwords do not match");

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/foRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        String email = request.getParameter("email");
    }


}
