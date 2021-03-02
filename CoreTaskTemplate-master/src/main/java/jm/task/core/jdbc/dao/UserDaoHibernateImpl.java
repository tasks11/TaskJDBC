package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users" +
                "(id INT(11) PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(25) NOT NULL," +
                "lastname VARCHAR(25) NOT NULL," +
                "age INT(11) NOT NULL)";
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery(create);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to create table");
        } finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        try {
        Query query = session.createSQLQuery(drop);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not drop");
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Can`t add user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
