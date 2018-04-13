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
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.utils.Utils.convertStreamToByteArray;

@WebServlet({"/editMenu/*", "/editMenu"})
@MultipartConfig
public class EditMenuServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<Product> products = new ArrayList<>(franchiseOwner.getProducts());
        products = filterActiveProducts(products);

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
        String productModify = request.getParameter("modify");

        if(productDelete != null){
            deleteProduct(Integer.valueOf(productDelete), franchiseOwner);
        }
        else if(productModify != null){
            modifyProduct(request, response,franchiseOwner, Integer.valueOf(productModify));
        }else{
            addProduct(request, response, franchiseOwner);
        }


        response.sendRedirect(request.getContextPath() + "/editMenu");
    }


    private boolean contains(FranchiseOwner franchiseOwner, Product product){
        for(Product currentProduct: franchiseOwner.getProducts()){
            if(product.getId() == currentProduct.getId()) return true;
        }
        return false;
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response, FranchiseOwner franchiseOwner) throws ServletException, IOException {
        String name = request.getParameter("productName");
        String priceString = request.getParameter("productPrice");

        double price;
        try{
            price = Double.valueOf(priceString);
        }catch (NumberFormatException e){
            sendError(response, request);
            return;
        }


        Part filePart = request.getPart("productPic");
        InputStream fileContent = filePart.getInputStream();

        Product product = new Product(name, price, convertStreamToByteArray(fileContent), franchiseOwner);

        Hibernate.initialize(franchiseOwner.getProducts());
        franchiseOwner.getProducts().add(product);
        ProductFunctionality.addModel(product);
        FOFunctionality.modifyModel(franchiseOwner);
    }

    private void deleteProduct(int productId, FranchiseOwner franchiseOwner){
        Product product = ProductFunctionality.getProduct(productId);
        if(contains(franchiseOwner, product)){
            product.setActive(false);
            ProductFunctionality.modifyModel(product);
        }
    }

    private void modifyProduct(HttpServletRequest request, HttpServletResponse response,FranchiseOwner fo, int productId) throws IOException, ServletException {
        String name = request.getParameter("productNameEdit");
        String priceString = request.getParameter("productPriceEdit");

        double price;
        try{
            price = Double.valueOf(priceString);
        }catch (NumberFormatException e){
            sendError(response, request);
            return;
        }

        Part image = request.getPart("productPicEdit");
        InputStream fileContent = image.getInputStream();
        byte[] imgBytes = convertStreamToByteArray(fileContent);

        ProductFunctionality.deleteProduct(productId);
        ProductFunctionality.addModel(new Product(name, price, imgBytes, fo));
//        product.setName(name);
//        product.setPrice(price);
//        product.setImage(img);
//        ProductFunctionality.modifyModel(product);
    }

    private void sendError(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        request.setAttribute("errorPrice", "Please enter a number");

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/editMenu.jsp");
        dispatcher.forward(request, response);
    }

    private List<Product> filterActiveProducts(List<Product> products){
        List<Product> activeProducts = new ArrayList<>();
        for(Product product: products){
            if(product.isActive()){
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }
}
