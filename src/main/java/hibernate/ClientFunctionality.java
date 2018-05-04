package hibernate;

import model.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gonzalo de Achaval
 */
public class ClientFunctionality extends AbstractFunctionality {

    public static Client getClient(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Client client = session.get(Client.class, id);
        session.close();

        return client;
    }

    public static List<Client> getAllClients() {
        List list = null;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("FROM Client").list();
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        if (list == null) return null;
        List<Client> clients = new ArrayList<>();
        for (Object object : list) {
            clients.add((Client) object);
        }
        return clients;
    }

    public static List<Client> getClientsByFO(String email) {
        List<Client> clients = getAllClients();
        List<Client> foClients = new ArrayList<>();
        if (clients == null) return null;
        for (Client client : clients) {
            if (client.getFranchiseOwner().getEmail().equals(email)) {
                foClients.add(client);
            }
        }
        return foClients;
    }
}
