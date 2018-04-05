package hibernate;

import model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductFunctionality extends AbstractFunctionality {

    public static Product getProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.close();

        return product;
    }

    public static Product deleteProduct(int id) {
        Product product = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            product = session.get(Product.class, id);
            session.delete(product);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return product;
    }

    public static List<Product> getAllProducts(){
        List list = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            list = session.createQuery("FROM Product").list();
        }catch (HibernateException ex){
            if(transaction!=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        if(list == null) return null;
        List<Product> products = new ArrayList<>();
        for(Object object:list){
            products.add((Product)object);
        }
        return products;
    }

    public static List<Product> getPorductsByFO(String email){
        List<Product> products = getAllProducts();
        List<Product> foProducts = new ArrayList<>();
        if(products==null) return null;
        for(Product product:products){
            if(product.getFranchiseOwner().getEmail().equals(email)){
                foProducts.add(product);
            }
        }
        return foProducts;
    }
}
