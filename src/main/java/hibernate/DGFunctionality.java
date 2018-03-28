package hibernate;

import model.DeliveryGuy;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Gonzalo de Achaval
 */
public class DGFunctionality extends AbstractFunctionality {

    public static DeliveryGuy getDeliveryGuy(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        DeliveryGuy deliveryGuy = session.get(DeliveryGuy.class, String.valueOf(email));
        session.close();

        return deliveryGuy;
    }

    public static DeliveryGuy deleteDeliveryGuy(String email) {
        DeliveryGuy deliveryGuy = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            deliveryGuy = session.get(DeliveryGuy.class, String.valueOf(email));
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


    public static void modifyDeliveryGuy(String email, String name, String password, int phone, String id,
                                         int meansOfTransport) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            DeliveryGuy deliveryGuy = session.get(DeliveryGuy.class, String.valueOf(email));
            deliveryGuy.setEmail(email);
            deliveryGuy.setName(name);
            deliveryGuy.setPassword(password);
            deliveryGuy.setPhone(phone);
            deliveryGuy.setId(id);
            deliveryGuy.setMeansOfTransport(meansOfTransport);

            session.update(deliveryGuy);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }
}




