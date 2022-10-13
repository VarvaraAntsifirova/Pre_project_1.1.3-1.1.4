package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;

import java.sql.SQLException;

import static jm.task.core.jdbc.util.Util.connection;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService service = new UserServiceImpl();

        service.createUsersTable();

        User user1 = new User("Vera", "Petrova", (byte) 20);
        service.saveUser("Vera", "Petrova", (byte) 20);
        System.out.println("User с именем – " + user1.getName() + " добавлен в базу данных");

        User user2 = new User("Vova", "Petrov", (byte) 18);
        service.saveUser("Vova", "Petrov", (byte) 18);
        System.out.println("User с именем – " + user2.getName() + " добавлен в базу данных");

        User user3 = new User("Vlad", "Petrov", (byte) 10);
        service.saveUser("Vlad", "Petrov", (byte) 10);
        System.out.println("User с именем – " + user3.getName() + " добавлен в базу данных");

        System.out.println(service.getAllUsers());

        service.cleanUsersTable();
        service.dropUsersTable();

        connection.close();
    }
}
