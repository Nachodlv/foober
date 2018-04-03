package hibernate;

import model.UserAccount;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UAFunctionality extends AbstractFunctionality {

    public static UserAccount getUserAccount(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        UserAccount userAccount = session.get(UserAccount.class, email);
        session.close();

        return userAccount;
    }

    public static UserAccount deleteUserAccount(String email) {
        UserAccount userAccount = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            userAccount = session.get(UserAccount.class, email);
            session.delete(userAccount);
            transaction.commit();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return userAccount;
    }

}
