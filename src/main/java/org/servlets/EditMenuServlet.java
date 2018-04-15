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
import java.util.regex.Pattern;

import static org.utils.Utils.convertStreamToByteArray;

@WebServlet({"/editMenu/*", "/editMenu"})
@MultipartConfig
public class EditMenuServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<Product> products = new ArrayList<>(franchiseOwner.getProducts());
        products = filterProducts(products, request);

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
        String searchProduct = request.getParameter("searchProduct");

        if(productDelete != null){
            deleteProduct(Integer.valueOf(productDelete), franchiseOwner);
            response.sendRedirect(request.getContextPath() + "/editMenu");
        }
        else if(productModify != null) {
            modifyProduct(request, response, franchiseOwner, Integer.valueOf(productModify));
        }else if(searchProduct != null){
            searchProduct(searchProduct, request, response);
        }else{
            addProduct(request, response, franchiseOwner);
        }
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
            response.sendRedirect(request.getContextPath() + "/editMenu?error=Invalid price");
            return;
        }

        Part filePart = request.getPart("productPic");
        InputStream fileContent = filePart.getInputStream();

        Product product = new Product(name, price, convertStreamToByteArray(fileContent), franchiseOwner);

        Hibernate.initialize(franchiseOwner.getProducts());
        franchiseOwner.getProducts().add(product);
        ProductFunctionality.addModel(product);
        FOFunctionality.modifyModel(franchiseOwner);

        response.sendRedirect(request.getContextPath() + "/editMenu");
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
            response.sendRedirect(request.getContextPath() + "/editMenu?error=Invalid price");
            return;
        }

        Part image = request.getPart("productPicEdit");
        InputStream fileContent = image.getInputStream();
        byte[] imgBytes = convertStreamToByteArray(fileContent);
        if(imgBytes.length == 0){
            imgBytes = ProductFunctionality.getProduct(productId).getImage();
        }
        ProductFunctionality.deleteProduct(productId);
        ProductFunctionality.addModel(new Product(name, price, imgBytes, fo));
        response.sendRedirect(request.getContextPath() + "/editMenu");
//        product.setName(name);
//        product.setPrice(price);
//        product.setImage(img);
//        ProductFunctionality.modifyModel(product);
    }

    private void searchProduct(String searchBy, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/editMenu?searchProduct=" + searchBy);
    }

    private List<Product> filterProducts(List<Product> products, HttpServletRequest request){
        String searchBy = request.getParameter("searchProduct");
        Pattern pattern;
        if(searchBy != null) pattern = Pattern.compile(".*" + searchBy.toLowerCase() + ".*");
        else pattern = Pattern.compile(".*");

        List<Product> activeProducts = new ArrayList<>();
        for(Product product: products){
            if(product.isActive() && pattern.matcher(product.getName().toLowerCase()).matches()){
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }
}
