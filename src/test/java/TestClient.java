import hibernate.ClientFunctionality;
import hibernate.FOFunctionality;
;
import hibernate.ProductFunctionality;
import model.FranchiseOwner;
import model.Client;
import model.Product;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Used to create Client tables...
 */
public class TestClient {

    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM CLIENT WHERE EMAIL='TEST' OR EMAIL='TEST2'");
        //stmt.executeUpdate("DELETE FROM USERACCOUNT WHERE EMAIL='TEST'");
        //stmt.executeUpdate("DELETE FROM FRANCHISEOWNER WHERE EMAIL='TEST'");
    }

    public Client getClient(String email){
        List<Client> clients = ClientFunctionality.getAllClients();
        if(clients == null) return null;
        for(Client client:clients){
            if(client.getEmail().equals(email)){
                return client;
            }
        }
        return null;
    }

    @Test
    public void testGetter(){
        FranchiseOwner franchiseOwner = new FranchiseOwner("TEST", "b", "b",
                1, "1", 1290, 10);
        Client client = new Client("nacho", 1, "1", "TEST", franchiseOwner);
        FOFunctionality.addModel(franchiseOwner);
        franchiseOwner.addClient(client);
        ClientFunctionality.addModel(client);
        client = getClient("TEST");
        assertEquals(client.getPhone(), 1);
    }
}
