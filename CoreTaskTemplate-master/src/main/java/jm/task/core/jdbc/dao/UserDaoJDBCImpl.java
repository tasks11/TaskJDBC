package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Statement statement;
    private ResultSet resultSet;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users" +
                "(id INT(11) PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(25) NOT NULL," +
                "lastname VARCHAR(25) NOT NULL," +
                "age INT(11) NOT NULL)";
        try {
            statement = Util.getConn().createStatement();
            statement.executeUpdate(create);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE users";
        try {
            statement = Util.getConn().createStatement();
            statement.executeUpdate(drop);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String save = "INSERT INTO users (name, lastname, age) VALUES ('" + name +
                "', '" + lastName + "', '" + age + "')";
        String getUser = "SELECT * FROM users WHERE name='" + name + "'";
        try {
            statement = Util.getConn().createStatement();
            statement.executeUpdate(save);
            resultSet = statement.executeQuery(getUser);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        (byte) resultSet.getLong(4)
                );
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String deleteById = "DELETE FROM users WHERE id='" + id +"'";
        try {
            statement = Util.getConn().createStatement();
            statement.executeUpdate(deleteById);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getAllUser = "SELECT * FROM users";
        List <User>  list = new ArrayList();
        try{
            statement = Util.getConn().createStatement();
            resultSet = statement.executeQuery(getAllUser);
            while (resultSet.next()) {
                list.add(
                        new User(
                                resultSet.getLong(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                (byte) resultSet.getLong(4)

                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(list.toString());
        return list;
    }

    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        try {
            statement = Util.getConn().createStatement();
            statement.executeUpdate(clean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
