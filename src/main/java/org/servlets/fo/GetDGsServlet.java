package org.servlets.fo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.DGFunctionality;
import hibernate.OrderFunctiontality;
import model.DeliveryGuy;
import model.Order;
import model.StateDG;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/getDGs"})
public class GetDGsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        final List<DeliveryGuy> allDeliveryGuys = DGFunctionality.getAllDeliveryGuys();
        List<DeliveryGuy> onlineDGs = filterOnlyOnline(allDeliveryGuys);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(onlineDGs);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private List<DeliveryGuy> filterOnlyOnline(List<DeliveryGuy> allDeliveryGuys) {
        List<DeliveryGuy> onlineDGs = new ArrayList<>();
        for (final DeliveryGuy deliveryGuy : allDeliveryGuys) {
            if (deliveryGuy.getState() == StateDG.ONLINE_WAITING) onlineDGs.add(deliveryGuy);
        }
        return onlineDGs;
    }
}
