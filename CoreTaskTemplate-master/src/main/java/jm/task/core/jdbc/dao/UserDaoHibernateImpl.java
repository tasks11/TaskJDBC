package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() { }

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
            Query query = session.createSQLQuery(drop).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        User user;
        try {
            Query query = session.createQuery("SELECT u FROM User u WHERE u.id = :id");
            query.setParameter("id", id);
            user = (User) query.uniqueResult();
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = null;
        try {
            users = session.createQuery("FROM User").list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        String clean = "DELETE FROM users";
        Session session = Util.getConfiguration().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery(clean).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
