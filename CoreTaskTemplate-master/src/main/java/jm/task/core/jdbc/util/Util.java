package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

//    private static SessionFactory conn;
//    private static Util instance;

    private Util() {
        getConn();
    }

//    public static Util getInstance() {
//        if (instance == null) {
//            instance = new Util();
//        }
//        return instance;
//    }

    public static Connection getConn() {
        String dbURL = "jdbc:mysql://localhost:3306/conn?useSSL=false";
        String name = "root";
        String secondName = "root";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, name, secondName);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }
}
