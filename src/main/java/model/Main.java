package model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setId(4);
        person.setName("Eduardo");
        Session session;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("Transaction Done");
            session.save(person);
            System.out.println("Person saved");
            session.getTransaction().commit();
            session.close();
            System.out.println("Done");
            List<Person> list = readAll();
            for (Person person1:
                 list) {
                System.out.println(person1.getId());
            }
        }catch(PersistenceException exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        }

    }

    public static List<Person> readAll() {
        List<Person> students = null;
        // Create a session
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            students = session.createQuery("FROM Person ").list();
            // Commit the transaction
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
        return students;
    }
}