package org.servlets;

import hibernate.FOFunctionality;
import model.FranchiseOwner;
import model.UserAccount;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/foInfo")
public class FOInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FOInfoServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(loginedUser.getEmail());
        AppUtils.storeLoginedUser(request.getSession(), franchiseOwner);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/foInfoView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount userAccount = AppUtils.getLoginedUser(request.getSession());
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(userAccount.getEmail());
        String change = request.getParameter("change");
        String password = request.getParameter("changePassword");
        if(password != null){
            changePassword(request, response, franchiseOwner);
        }
        else if(change.equals("save")){
            saveChanges(request, response, franchiseOwner);
            response.sendRedirect(request.getContextPath() + "/foMenu");
        }else if(change.equals("cancel")){
            request.getSession().removeAttribute("password");
            response.sendRedirect(request.getContextPath() + "/foMenu");
        }

    }

    private void saveChanges(HttpServletRequest request, HttpServletResponse response,FranchiseOwner franchiseOwner) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = (String) request.getSession().getAttribute("password");
        String address = request.getParameter("address");
        String phoneString = request.getParameter("phone");
        String tippingPercentageString = request.getParameter("tippingPercentage");

        int phone = Integer.parseInt(phoneString);
        int tippingPercentage = Integer.parseInt(tippingPercentageString);
        if(tippingPercentage > 100 || tippingPercentage < 5){
            request.setAttribute("errorTipping", "Percentage not valid");
            RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/foInfoView.jsp");
            dispatcher.forward(request, response);
            return;
        }

        franchiseOwner.setName(name);
        franchiseOwner.setAddress(address);
        franchiseOwner.setPhone(phone);
        franchiseOwner.setTippingPercentage(tippingPercentage);

        if(password != null){
            franchiseOwner.setPassword(password);
        }

        FOFunctionality.modifyModel(franchiseOwner);
    }

    private void changePassword(HttpServletRequest request, HttpServletResponse response, FranchiseOwner franchiseOwner) throws IOException {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatPassword = request.getParameter("repeatPassword");

        if(!oldPassword.equals(franchiseOwner.getPassword())){
            response.sendRedirect(request.getContextPath() + "/foInfo?error=Invalid password!");
        }else if(!newPassword.equals(repeatPassword)){
            response.sendRedirect(request.getContextPath() + "/foInfo?error=Passwords don't match");
        }else{
            request.getSession().setAttribute("password", newPassword);
            response.sendRedirect(request.getContextPath() + "/foInfo");
        }
    }

}