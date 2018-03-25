package model;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    /*static {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Person.class);
        configuration.configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }*/
    public static synchronized SessionFactory getSessionFactory() {
        if ( sessionFactory == null ) {

            // exception handling omitted for brevity

            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .build();

            sessionFactory = new Configuration().buildSessionFactory( serviceRegistry );
        }
        return sessionFactory;

    }
}