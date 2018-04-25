import hibernate.FOFunctionality;
import hibernate.ProductFunctionality;
import model.FranchiseOwner;
import model.Product;
import org.junit.After;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestProduct {

    @After
    public void clearDB() throws SQLException, ClassNotFoundException {
        Connection con;
        Statement stmt;
        con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
        stmt = con.createStatement();
        stmt.executeUpdate("DELETE FROM PRODUCT WHERE NAME='TEST' OR NAME='TEST2'");
        stmt.executeUpdate("DELETE FROM FRANCHISEOWNER WHERE EMAIL='TEST' OR EMAIL='TEST2'");
        stmt.executeUpdate("DELETE FROM USERACCOUNT WHERE EMAIL='TEST' OR EMAIL='TEST2'");
    }

    private Product getProduct(String name){
        List<Product> products = ProductFunctionality.getAllProducts();
        if(products == null) return null;
        for(Product product:products){
            if(product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }

    @Test
    public void testGetter(){
        FranchiseOwner franchiseOwner = new FranchiseOwner("TEST", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);
        Product product = new Product("TEST", 35, franchiseOwner);
        FOFunctionality.addModel(franchiseOwner);
        franchiseOwner.addProduct(product);
        ProductFunctionality.addModel(product);
        //List<Product> products = ProductFunctionality.getAllProducts();
        product = getProduct("TEST");
        assertEquals(product.getPrice(), 35, 0);
    }

    @Test
    public void testFailedGet(){
        Product product = ProductFunctionality.getProduct(-1);
        assertEquals(product, null);
    }

    @Test
    public void testDelete(){
        FranchiseOwner franchiseOwner = new FranchiseOwner("TEST", "El puestito de Alberto", "alberto123",
                1562240533, "Rawson4060", 123, 10);
        FOFunctionality.addModel(franchiseOwner);
        Product product = new Product("TEST", 35, franchiseOwner);
        Product product2 = new Product("TEST2", 35, franchiseOwner);
        ProductFunctionality.addModel(product);
        ProductFunctionality.addModel(product2);
        product = getProduct("TEST");
        int id = product.getId();
        ProductFunctionality.deleteProduct(id);
        product = getProduct("TEST");
        assertEquals(product, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFailedDelete(){
        ProductFunctionality.deleteProduct(-1);
    }

    @Test
    public void testRepeatedFieldsButKey() {
        FranchiseOwner franchiseOwner = new FranchiseOwner("TEST", "B", "C", 1, "D", 2, 3);
        Product product = new Product("TEST", 35, franchiseOwner);
        Product product2 = new Product("TEST", 35, franchiseOwner);
        FOFunctionality.addModel(franchiseOwner);
        ProductFunctionality.addModel(product);
        ProductFunctionality.addModel(product2);
    }

    @Test
    public void testModify() {
        FranchiseOwner franchiseOwner = new FranchiseOwner("TEST", "B", "C", 1, "D", 2, 3);
        Product product = new Product("TEST", 35, franchiseOwner);
        FOFunctionality.addModel(franchiseOwner);
        ProductFunctionality.addModel(product);
        product.setName("TEST2");
        product.setPrice(25);
        ProductFunctionality.modifyModel(product);
        product = getProduct("TEST2");
        assertEquals(product.getPrice(), 25, 0);
    }
}
