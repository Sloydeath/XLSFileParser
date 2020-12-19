package by.ey.secondTask.util;

import by.ey.secondTask.entity.*;
import by.ey.secondTask.entity.Class;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Account.class);
                configuration.addAnnotatedClass(AccountGroup.class);
                configuration.addAnnotatedClass(Class.class);
                configuration.addAnnotatedClass(File.class);
                configuration.addAnnotatedClass(FileDescription.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
