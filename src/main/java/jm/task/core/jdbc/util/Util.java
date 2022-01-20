package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String userName = "root";
    private static final String password = "root";
    private static final String connectionUrl = "jdbc:mysql://localhost:3306/jdbslesson?autoReconnect=true";
    private static  Connection connection;
    
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
