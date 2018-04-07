package org.servlets;

import hibernate.FOFunctionality;
import hibernate.ProductFunctionality;
import model.FranchiseOwner;
import model.Product;
import model.UserAccount;
import org.hibernate.Hibernate;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet({"/editMenu/*", "/editMenu"})
@MultipartConfig
public class EditMenuServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<Product> products = ProductFunctionality.getPorductsByFO(franchiseOwner.getEmail());
        request.setAttribute("products", products);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/editMenu.jsp");


        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UserAccount userAccount = AppUtils.getLoginedUser(request.getSession());
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(userAccount.getEmail());

        String productDelete = request.getParameter("delete");
        if(productDelete != null){
            deleteProduct(Integer.valueOf(productDelete), franchiseOwner);
            response.sendRedirect(request.getContextPath() + "/editMenu");
            return;
        }

        String name = request.getParameter("productName");
        String priceString = request.getParameter("productPrice");
        double price;
        try{
            price = Double.valueOf(priceString);
        }catch (NumberFormatException e){
            request.setAttribute("errorPrice", "Please enter a number");

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/editMenu.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Product product = new Product(name, price, new byte[]{}, franchiseOwner);

        Hibernate.initialize(franchiseOwner.getProducts());
        franchiseOwner.getProducts().add(product);
        ProductFunctionality.addModel(product);
        FOFunctionality.modifyModel(franchiseOwner);

        response.sendRedirect(request.getContextPath() + "/editMenu");
    }


    private boolean contains(FranchiseOwner franchiseOwner, Product product){
        for(Product currentProduct: franchiseOwner.getProducts()){
            if(product.getId() == currentProduct.getId()) return true;
        }
        return false;
    }

    private void deleteProduct(int productId, FranchiseOwner franchiseOwner){
        Product product = ProductFunctionality.getProduct(productId);
        if(contains(franchiseOwner, product)){
            ProductFunctionality.deleteProduct(productId);
            franchiseOwner.getProducts().remove(product);
            FOFunctionality.modifyModel(franchiseOwner);
        }
    }

}
