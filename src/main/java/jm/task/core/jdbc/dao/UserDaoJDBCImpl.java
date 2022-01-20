package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
        private final Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.getConnection();
    }


    public void createUsersTable() {
        String sql = "CREATE TABLE Users" +
                " (id INT AUTO_INCREMENT PRIMARY KEY ,Name VARCHAR(100), " +
                "lastName VARCHAR(100)," +
                " age INT);";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table has been created!");
        }catch (SQLException throwables) {
            System.out.println("Table already exist!");
        }

    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DROP TABLE users;";
            statement.executeUpdate(sql);
            System.out.println("Table has been deleted!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Table is not exist!");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO users (Name, lastName, age) VALUE (?, ?, ?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных \n", name);

        } catch (SQLException e) {
            System.out.println("Something wrong!");

        }


    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement;
        String sql = "DELETE FROM users WHERE ID=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Something wrong");
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME,AGE FROM users";
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Something wrong");
        }


        return users;
    }

    public void cleanUsersTable() {
        Statement statement;
        String sql = "TRUNCATE TABLE users";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table has been cleaned!");
        } catch (SQLException e) {
            System.out.println("Something wrong");
        }
    }
}
