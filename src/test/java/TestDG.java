import hibernate.AbstractFunctionality;
import hibernate.DGFunctionality;
import model.DeliveryGuy;
import org.junit.After;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

/**
 * @author Gonzalo de Achaval
 */
public class TestDG {
    
    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM DELIVERYGUY WHERE EMAIL='TEST' OR EMAIL='TEST2'");
    }

    @Test
    public void testAddingGetting(){
        DGFunctionality.addModel(new DeliveryGuy("TEST", "El puestito de Alberto", "alberto123",
                1562240533, ".jpg", 1));

        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy("TEST");
        assertEquals(deliveryGuy.getPhone(), 1562240533);
    }

    @Test
    public void testFailedGet(){
        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy("123");
        assertEquals(deliveryGuy, null);
    }

    @Test
    public void testDelete(){
        DGFunctionality.addModel(new DeliveryGuy("TEST", "El puestito de Alberto", "alberto123",
                1562240533, ".jpg", 1));
        DGFunctionality.deleteDeliveryGuy("TEST");
        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy("TEST");
        assertEquals(deliveryGuy, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedDelete(){
        DeliveryGuy deliveryGuy = DGFunctionality.deleteDeliveryGuy("123");
        assertEquals(deliveryGuy, null);
    }

    @Test(expected = PersistenceException.class)
    public void testRepeatedKey(){
        DGFunctionality.addModel(new DeliveryGuy("TEST", "B", "C",1, ".jpg", 2));
        DGFunctionality.addModel(new DeliveryGuy("TEST", "C", "D",2, ".jpg", 3));
    }

    @Test
    public void testRepeatedFieldsButKey(){
        DGFunctionality.addModel(new DeliveryGuy("TEST", "B", "C",1, ".jpg", 2));
        DGFunctionality.addModel(new DeliveryGuy("TEST2", "B", "C",1, ".jpg", 2));
    }

    @Test
    public void testModify(){
        DeliveryGuy deliveryGuy = new DeliveryGuy("TEST", "A", "B",1, ".jpg", 1);
        DGFunctionality.addModel(deliveryGuy);
        deliveryGuy.setName("new Name");
        DGFunctionality.modifyModel(deliveryGuy);
        DeliveryGuy newDeliveryGuy = DGFunctionality.getDeliveryGuy("TEST");
        assertEquals(deliveryGuy.getName(), newDeliveryGuy.getName());
    }
}
