package org.servlets.fo;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import hibernate.*;
import model.*;
import org.securityfilter.AppUtils;
import org.utils.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@WebServlet({"/makingOrder/*", "/makingOrder"})
public class MakingOrderServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idClient = request.getParameter("clientId");
        if(idClient != null )request.getSession().setAttribute("client", ClientFunctionality.getClient(Integer.valueOf(idClient)));

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<Product> products = ProductFunctionality.getProductsByFO(franchiseOwner.getEmail());
        products = Utils.filterProducts(products, request);
        request.setAttribute("products", products);

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/fo/makingOrderView.jsp");

        dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String makeOrder = request.getParameter("MakeOrder");
        String searchBy = request.getParameter("searchProduct");
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());

        if(makeOrder != null) makeOrder(request, response, franchiseOwner);
        else searchProduct(searchBy, request, response);
    }

    private void makeOrder(HttpServletRequest request, HttpServletResponse response, FranchiseOwner franchiseOwner) throws IOException {
        Set<OrderedProducts> orderedProductsList = new LinkedHashSet<>();
        Order order = new Order();
        Client client = (Client)request.getSession().getAttribute("client");
        order.setClient(client);
        order.setElaborationTime(Integer.valueOf(request.getParameter("elaborationTime")));
//        order.setStatus(true); TODO post seleccion de DG
        order.setDeliveryGuy(null);
        order.setFranchiseOwner(franchiseOwner);
        order.setIssuedTime(System.currentTimeMillis());
        OrderFunctiontality.addModel(order);

        for(Product product: franchiseOwner.getProducts()){
            String quantityString = request.getParameter(String.valueOf(product.getId()));
            int quantity = 0;
            if(quantityString != null && !quantityString.equals("")) quantity = Integer.valueOf(quantityString);
            if(quantity > 0){
                String comment = request.getParameter("comment" + product.getId());
                OrderedProducts orderedProducts = new OrderedProducts(product, order, comment, quantity);
                order.getOrderedProducts().add(orderedProducts);
                product.getOrders().add(orderedProducts);

                ProductFunctionality.modifyModel(product);
                OrderedProductFunctionality.addModel(orderedProducts);

                orderedProductsList.add(orderedProducts);
            }
        }

        order.setOrderedProducts(orderedProductsList);
        OrderFunctiontality.modifyModel(order);

        response.sendRedirect(request.getContextPath() + "/foMenu");
    }

    private void searchProduct(String searchBy, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/makingOrder?searchProduct=" + searchBy);
    }
}
