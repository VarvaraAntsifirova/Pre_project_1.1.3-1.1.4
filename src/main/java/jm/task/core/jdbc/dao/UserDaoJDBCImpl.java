package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static Statement statement;
    public static Util util = new Util();
    public static Connection connection = util.connection();

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("CREATE TABLE user_for_task (id INT PRIMARY KEY AUTO_INCREMENT,name VARCHAR(30), lastName VARCHAR(35), age INT);");
        } catch (SQLException ignored) {}
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("DROP TABLE user_for_task;");
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("INSERT INTO user_for_task (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ");");
        } catch (SQLException throwable) {
            throwable.getStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM user_for_task WHERE id = " + id + ";");
        } catch (SQLException throwable) {
            throwable.getStackTrace();
        }
    }

    public List<User> getAllUsers() {
        ResultSet list ;
        List<User> result = new ArrayList<>();
        try {
            list = statement.executeQuery("SELECT name, lastName, age FROM user_for_task;");
            while (list.next()) {
                User user_new = new User(list.getString("name"),
                        list.getString("lastName"), list.getByte("age"));
                result.add(user_new);
            }
        } catch (SQLException throwable) {
            throwable.getStackTrace();
        }
        return result;
    }

    public void cleanUsersTable() {
        try {
            statement.executeUpdate("DELETE FROM user_for_task;");
        } catch (SQLException ignored) {}
    }
}
