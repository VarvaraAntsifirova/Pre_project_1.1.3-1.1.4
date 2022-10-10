package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static Connection connection;

    public static Connection connection () {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, "root", "root");
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.getStackTrace();
            System.out.println("Ошибка! Драйвер не найден!");
        } catch (SQLException sqlException) {
            sqlException.getStackTrace();
            System.out.println("Ошибка! Соединение с БД не установлено!");
        }
        return connection;
    }
}
