import hibernate.FOFunctionality;
import model.FranchiseOwner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Gonzalo de Achaval
 */
public class test {


    @Test
    public void testAddingGetting(){
        FOFunctionality.addFranchiseOwner("alberto@gmail.com", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("alberto@gmail.com");
        assertEquals(franchiseOwner.getPhone(), 1562240533);
        FOFunctionality.deleteFranchiseOwner("alberto@gmail.com");
    }

    @Test
    public void testFailedGet(){
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("123");
        assertEquals(franchiseOwner, null);
    }

    @Test
    public void testDelete(){
        FOFunctionality.addFranchiseOwner("alberto@gmail.com", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);
        FOFunctionality.deleteFranchiseOwner("alberto@gmail.com");
        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner("alberto@gmail.com");
        assertEquals(franchiseOwner, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedDelete(){
        FranchiseOwner franchiseOwner = FOFunctionality.deleteFranchiseOwner("123");
        assertEquals(franchiseOwner, null);
    }
}
