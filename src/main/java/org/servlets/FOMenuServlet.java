package org.servlets;

import hibernate.ClientFunctionality;
import hibernate.FOFunctionality;
import hibernate.ProductFunctionality;
import model.Client;
import model.FranchiseOwner;
import model.Product;
import model.UserAccount;
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
import java.util.List;
import java.util.regex.Pattern;

@WebServlet({"/foMenu", "/foMenu/*"})
@MultipartConfig
public class FOMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<Client> clients = ClientFunctionality.getClientsByFO(franchiseOwner.getEmail());
        clients = filterClients(clients, request);
        request.setAttribute("clients", clients);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/foMenuView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String clientName = request.getParameter("clientName");
        String searchClient = request.getParameter("searchClient");
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(loginedUser.getEmail());

        if(clientName != null){
            newClient(franchiseOwner, request, response, clientName);
        }else{
            searchClient(searchClient, request, response);
        }
    }

    private void newClient(FranchiseOwner franchiseOwner, HttpServletRequest request, HttpServletResponse response, String clientName) throws IOException {
        String phoneString = request.getParameter("clientPhone");
        String email = request.getParameter("clientEmail");
        String address = request.getParameter("clientAddress");

        int phone;
        try {
            phone = Integer.valueOf(phoneString);
        }catch (NumberFormatException e){
            response.sendRedirect(request.getContextPath() + "/foMenu?error=Error while creating the client, invalid phone number");
            return;
        }

        Client client = new Client(clientName, phone, address, email, franchiseOwner);
        franchiseOwner.addClient(client);

        FOFunctionality.modifyModel(franchiseOwner);
        ClientFunctionality.addModel(client);

        response.sendRedirect(request.getContextPath() + "/foMenu");
    }

    private void searchClient(String searchClient, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/foMenu?searchClient=" + searchClient);
    }

    private List<Client> filterClients(List<Client> clients, HttpServletRequest request){
        String searchBy = request.getParameter("searchClient");
        if(searchBy == null) return clients;
        Pattern pattern = Pattern.compile(".*" + searchBy.toLowerCase() + ".*");
        List<Client> filteredClients = new ArrayList<>();
        for(Client client: clients){
            if(pattern.matcher(client.getName().toLowerCase()).matches()){
                filteredClients.add(client);
            }
        }
        return filteredClients;
    }
}