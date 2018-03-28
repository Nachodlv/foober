package hibernate;

import model.FranchiseOwner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FOFunctionality extends AbstractFunctionality {

    public static FranchiseOwner getFranchiseOwner(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FranchiseOwner franchiseOwner = session.get(FranchiseOwner.class, String.valueOf(email));
        session.close();

        return franchiseOwner;
    }

    public static FranchiseOwner deleteFranchiseOwner(String email){
        FranchiseOwner franchiseOwner = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            franchiseOwner = session.get(FranchiseOwner.class, String.valueOf(email));
            session.delete(franchiseOwner);
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
        return franchiseOwner;
    }


    public static void modifyFranchiseOwner(String email, String name, String password, int phone, String address,
                                                      int menuId, int tippingPercentage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            FranchiseOwner franchiseOwner = session.get(FranchiseOwner.class, String.valueOf(email));
            franchiseOwner.setEmail(email);
            franchiseOwner.setName(name);
            franchiseOwner.setPassword(password);
            franchiseOwner.setPhone(phone);
            franchiseOwner.setAddress(address);
            franchiseOwner.setMenuId(menuId);
            franchiseOwner.setTippingPercentage(tippingPercentage);

            session.update(franchiseOwner);
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
    }
}
