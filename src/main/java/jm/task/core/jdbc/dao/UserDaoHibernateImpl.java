package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user_for_task (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(35), age INT);").addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.createSQLQuery("DROP TABLE IF EXISTS user_for_task;");
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        User user_new = new User(name, lastName, age);
        session.save(user_new);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(String.valueOf(id), session.load(User.class, id));
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        List<User> result = session.createQuery("FROM User").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        session.getTransaction().begin();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
