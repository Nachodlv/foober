import hibernate.DGFunctionality;
import hibernate.UAFunctionality;
import model.DeliveryGuy;
import model.FranchiseOwner;
import model.UserAccount;
import org.hsqldb.rights.User;
import org.junit.After;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUA {

    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM DELIVERYGUY WHERE EMAIL='TEST' OR EMAIL='TEST1'");
        stmt.executeUpdate("DELETE FROM FRANCHISEOWNER WHERE EMAIL='TEST' OR EMAIL='TEST1'");
        stmt.executeUpdate("DELETE FROM USERACCOUNT WHERE EMAIL='TEST' OR EMAIL='TEST1'");

        DGFunctionality.addModel(new FranchiseOwner("TEST", "B", "C",1, "D", 2, 3));
        DGFunctionality.addModel(new DeliveryGuy("TEST2", "El puestito de Alberto", "alberto123",
                1562240533, ".jpg", 1));
    }

    @Test
    public void testUA(){
        UserAccount userAccount = new FranchiseOwner("TEST", "B", "C",1, "D", 2, 3);
        //UAFunctionality.addModel(userAccount);

        DeliveryGuy userAccount1 = new DeliveryGuy("TEST2", "El puestito de Alberto", "alberto123",
                1562240533, ".jpg", 1);
        DGFunctionality.addModel(userAccount1);

    }

    @Test
    public void getUA(){
        UserAccount userAccount = UAFunctionality.getUserAccount("TEST");
        System.out.println(userAccount.getRole());
    }

}
