package hibernate;

import model.FranchiseOwner;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FOFunctionality extends AbstractFunctionality {

    public static FranchiseOwner getFranchiseOwner(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        FranchiseOwner franchiseOwner = session.get(FranchiseOwner.class, email);
        session.close();

        return franchiseOwner;
    }

    public static FranchiseOwner deleteFranchiseOwner(String email) {
        FranchiseOwner franchiseOwner = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            franchiseOwner = session.get(FranchiseOwner.class, email);
            session.delete(franchiseOwner);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return franchiseOwner;
    }
}
