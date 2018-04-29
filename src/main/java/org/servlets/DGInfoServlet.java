package org.servlets;

import hibernate.DGFunctionality;
import model.DeliveryGuy;
import model.UserAccount;
import org.securityfilter.AppUtils;
import org.utils.Utils;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/dgInfo")
public class DGInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());
        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(loginedUser.getEmail());
        AppUtils.storeLoginedUser(request.getSession(), deliveryGuy);


        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dgInfoView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount userAccount = AppUtils.getLoginedUser(request.getSession());
        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(userAccount.getEmail());
        String change = request.getParameter("change");
        String password = request.getParameter("changePassword");
        if(password != null){
            changePassword(request, response, deliveryGuy);
        }
        else if(change.equals("save")){
            saveChanges(request, deliveryGuy);
            response.sendRedirect(request.getContextPath() + "/dgMenu");
        }else if(change.equals("cancel")){
            request.getSession().removeAttribute("password");
            response.sendRedirect(request.getContextPath() + "/dgMenu");
        }
    }

    private void saveChanges(HttpServletRequest request, DeliveryGuy deliveryGuy) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = (String) request.getSession().getAttribute("password");
        String phoneString = request.getParameter("phone");
        String meansOfTransportString = request.getParameter("newMeansOfTransport");

        int phone = Integer.parseInt(phoneString);
        int meansOfTransport = Integer.parseInt(meansOfTransportString);

        deliveryGuy.setName(name);
        deliveryGuy.setPhone(phone);
        deliveryGuy.setMeansOfTransport(meansOfTransport);

        if(password != null){
            deliveryGuy.setPassword(password);
        }

        Part part = request.getPart("dgPicEdit");
        String email = deliveryGuy.getEmail();
        Utils.writeImage(email, part, getServletContext());

        DGFunctionality.modifyModel(deliveryGuy);
    }


    private void changePassword(HttpServletRequest request, HttpServletResponse response, DeliveryGuy deliveryGuy) throws IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");

        if(!oldPassword.equals(deliveryGuy.getPassword())){
            response.sendRedirect(request.getContextPath() + "/dgInfo?error=Invalid password!");
        }else if(!newPassword.equals(repeatPassword)){
            response.sendRedirect(request.getContextPath() + "/dgInfo?error=Passwords don't match");
        }else{
            request.getSession().setAttribute("password", newPassword);
            response.sendRedirect(request.getContextPath() + "/dgInfo");
        }
    }
}
