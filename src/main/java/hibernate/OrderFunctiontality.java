package hibernate;

import model.Order;
import model.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gonzalo de Achaval
 */
public class OrderFunctiontality extends AbstractFunctionality {

    public static Order getOrder(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Order order = session.get(Order.class, id);
        session.close();

        return order;
    }

    public static Order deleteOrder(int id) {
        Order order = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            order = session.get(Order.class, id);
            session.delete(order);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return order;
    }

    public static List<Order> getAllOrders(){
        List list = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            list = session.createQuery("FROM Order").list();
        }catch (HibernateException ex){
            if(transaction!=null){
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        if(list == null) return null;
        List<Order> orders = new ArrayList<>();
        for(Object object:list){
            orders.add((Order)object);
        }
        return orders;
    }

    public static List<Order> getOrdersByFO(String email){
        List<Order> orders = getAllOrders();
        List<Order> foOrders = new ArrayList<>();
        if(orders==null) return null;
        for(Order order:orders){
            if(order.getFranchiseOwner().getEmail().equals(email)){
                foOrders.add(order);
            }
        }
        return foOrders;
    }

    public static List<Order> getOrdersByDG(String email){
        List<Order> orders = getAllOrders();
        List<Order> dgOrders = new ArrayList<>();
        if(orders==null) return null;
        for(Order order:orders){
            if(order.getDeliveryGuy().getEmail().equals(email)){
                dgOrders.add(order);
            }
        }
        return dgOrders;
    }
}