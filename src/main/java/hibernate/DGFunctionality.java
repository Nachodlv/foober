package hibernate;

import model.DeliveryGuy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gonzalo de Achaval
 */
public class DGFunctionality extends AbstractFunctionality {

    public static DeliveryGuy getDeliveryGuy(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DeliveryGuy deliveryGuy = session.get(DeliveryGuy.class, email);
        session.close();

        return deliveryGuy;
    }

    public static DeliveryGuy deleteDeliveryGuy(String email) {
        DeliveryGuy deliveryGuy = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            deliveryGuy = session.get(DeliveryGuy.class, email);
            session.delete(deliveryGuy);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return deliveryGuy;
    }

    public static List<DeliveryGuy> getAllDeliveryGuys() {
        List list = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("FROM DeliveryGuy").list();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        if (list == null) return null;
        List<DeliveryGuy> deliveryGuys = new ArrayList<>();
        for (Object object : list) {
            deliveryGuys.add((DeliveryGuy) object);
        }
        return deliveryGuys;
    }
}




