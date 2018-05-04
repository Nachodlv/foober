package hibernate;

import model.OrderedProducts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderedProductFunctionality extends AbstractFunctionality {
    public static OrderedProducts getOrderedProduct(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        OrderedProducts orderedProducts = session.get(OrderedProducts.class, id);
        session.close();

        return orderedProducts;
    }

    public static OrderedProducts deleteOrderedProduct(int id) {
        OrderedProducts product = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            product = session.get(OrderedProducts.class, id);
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
}
