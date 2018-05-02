import hibernate.*;
import model.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * @author Gonzalo de Achaval
 */
public class TestOrder {

    private Set<Order> getOrdersByFO(String foName) {
        final FranchiseOwner fo = FOFunctionality.getFranchiseOwner(foName);
        return fo.getOrders();
    }

    @Test
    public void testA() {
        FranchiseOwner fo = new FranchiseOwner("TEST", "B", "C", 1, "D", 2, 3);
        OrderedProducts p1 = new OrderedProducts(new Product("TEST", 35, fo), new Order(),"Hello", 1);
        OrderedProducts p2 = new OrderedProducts(new Product("TEST1", 40, fo), new Order(),"Hello", 1);
        OrderedProducts p3 = new OrderedProducts(new Product("TEST2", 45, fo), new Order(),"Hello", 1);

        Set<OrderedProducts> products = new HashSet<>(Arrays.asList(p1, p2, p3));
        DeliveryGuy dg = new DeliveryGuy("TEST2", "A", "B", 1, 1);
        Client client = new Client("TEST", 1, "a", "a@gmail", fo);

        Order order = new Order(30, fo, dg, client, products);
        FOFunctionality.addModel(fo);
        ClientFunctionality.addModel(client);
        DGFunctionality.addModel(dg);
        ProductFunctionality.addModel(p1);
        ProductFunctionality.addModel(p2);
        ProductFunctionality.addModel(p3);
        OrderFunctiontality.addModel(order); //persist order
        final Order onlyOrder = (Order)getOrdersByFO("TEST").toArray()[0]; //get first (only) order

        assertEquals(order.getElaborationTime(), onlyOrder.getElaborationTime());

//        FOFunctionality.deleteFranchiseOwner(fo.getEmail());
//        DGFunctionality.deleteDeliveryGuy(dg.getEmail());
    }
}
