package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/test";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "my179sql";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static SessionFactory getConnect() {
        SessionFactory sessionFactory;
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.hbm2dll.auto", "update")
                    .addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Connection OK");
        } catch (HibernateException e) {
            throw new IllegalStateException("Error connection: " + e.getMessage());
        }
        return sessionFactory;
    }
}
