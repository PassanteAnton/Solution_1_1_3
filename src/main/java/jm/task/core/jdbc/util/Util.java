package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/jdbslesson?autoReconnect=true";
    private static  Connection connection;

    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();

        Properties property = new Properties();
        property.setProperty(Environment.URL, connectionUrl);
        property.setProperty(Environment.USER, userName);
        property.setProperty(Environment.PASS, password);

        configuration.setProperties(property);
        configuration.addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        try {
            sessionFactory = configuration.buildSessionFactory(registry.build());
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = DriverManager.getConnection(connectionUrl, userName, password);
                System.out.println("Connected");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
}
}
