package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory;

    private static final String createUsersTableQuery = "CREATE TABLE IF NOT EXISTS users(" +
            "id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL," +
            "lastname VARCHAR(255) NOT NULL," +
            "age TINYINT UNSIGNED)";

    private static final String dropUsersTableQuery = "DROP TABLE IF EXISTS users";

    private static final String cleanUsersTableQuery = "TRUNCATE TABLE users";

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getConnect();
    }


    @Override
    public void createUsersTable() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery(createUsersTableQuery).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error create table: " + e.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery(dropUsersTableQuery).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error drop table: " + e.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error save user: " + e.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error remove user by id: " + e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            users = (List<User>) session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error get all users: " + e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createSQLQuery(cleanUsersTableQuery).executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new IllegalStateException("Error clean users table: " + e.getMessage());
        }
    }
}
