import hibernate.UAFunctionality;
import hibernate.UAFunctionality;
import model.DeliveryGuy;
import model.UserAccount;
import model.FranchiseOwner;
import model.UserAccount;
import org.junit.After;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.assertEquals;

public class TestUA {

    private final byte[] bytes = new byte[0];

    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM DELIVERYGUY WHERE EMAIL='TEST' OR EMAIL='TEST1'");
        stmt.executeUpdate("DELETE FROM FRANCHISEOWNER WHERE EMAIL='TEST' OR EMAIL='TEST1'");
        stmt.executeUpdate("DELETE FROM USERACCOUNT WHERE EMAIL='TEST' OR EMAIL='TEST1'");
    }

    @Test
    public void testGetting() {
        UAFunctionality.addModel(new DeliveryGuy("TEST", "A", "a",3,  1));
        UAFunctionality.addModel(new FranchiseOwner("TEST1", "A", "a", 3, ".jpg"));
        UserAccount userAccount = UAFunctionality.getUserAccount("TEST");
        assertEquals(userAccount.getRole(), "DG");
        UserAccount userAccount2 = UAFunctionality.getUserAccount("TEST1");
        assertEquals(userAccount2.getRole(), "FO");
    }

    @Test
    public void testFailedGet() {
        UserAccount userAccount = UAFunctionality.getUserAccount("123");
        assertEquals(userAccount, null);
    }

    @Test
    public void testDelete() {
        UAFunctionality.addModel(new DeliveryGuy("TEST", "A", "a",3, 1));
        UAFunctionality.deleteUserAccount("TEST");
        UserAccount userAccount = UAFunctionality.getUserAccount("TEST");
        assertEquals(userAccount, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedDelete() {
        UserAccount userAccount = UAFunctionality.deleteUserAccount("123");
        assertEquals(userAccount, null);
    }

    @Test(expected = PersistenceException.class)
    public void testRepeatedKey() {
        UAFunctionality.addModel(new DeliveryGuy("TEST", "A", "a",3, 1));
        UAFunctionality.addModel(new FranchiseOwner("TEST", "A", "a", 3, ".jpg"));
    }
}
