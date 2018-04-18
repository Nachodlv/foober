import hibernate.*;
import model.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
        Product p1 = new Product("TEST", 35, new byte[]{}, fo);
        Product p2 = new Product("TEST1", 40, new byte[]{}, fo);
        Product p3 = new Product("TEST2", 45, new byte[]{}, fo);
        Set<Product> products = new HashSet<>(Arrays.asList(p1, p2, p3));
        DeliveryGuy dg = new DeliveryGuy("TEST2", "A", "B", 1, new byte[0], 1);
        Client client = new Client("TEST", 1, "a", "a@gmail", fo);

        Order order = new Order(true, 30, fo, dg, client, products);
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
