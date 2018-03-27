import hibernate.FOFunctionality;
import model.FranchiseOwner;
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
public class TestFO {

    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM FRANCHISEOWNER WHERE EMAIL='TEST' OR EMAIL='TEST2'");
    }

    @Test
    public void testAddingGetting(){
        FOFunctionality.addFranchiseOwner("TEST", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("TEST");
        assertEquals(franchiseOwner.getPhone(), 1562240533);
    }

    @Test
    public void testFailedGet(){
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("123");
        assertEquals(franchiseOwner, null);
    }

    @Test
    public void testDelete(){
        FOFunctionality.addFranchiseOwner("TEST", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);
        FOFunctionality.deleteFranchiseOwner("TEST");
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("TEST");
        assertEquals(franchiseOwner, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedDelete(){
        FranchiseOwner franchiseOwner = FOFunctionality.deleteFranchiseOwner("123");
        assertEquals(franchiseOwner, null);
    }

    @Test(expected = PersistenceException.class)
    public void testRepeatedKey(){
        FOFunctionality.addFranchiseOwner("TEST", "B", "C",1, "D", 2, 3);
        FOFunctionality.addFranchiseOwner("TEST", "C", "D",2, "E", 3, 4);
    }

    @Test
    public void testRepeatedFieldsButKey(){
        FOFunctionality.addFranchiseOwner("TEST", "B", "C",1, "D", 2, 3);
        FOFunctionality.addFranchiseOwner("TEST2", "B", "C",1, "D", 2, 3);
    }
}
