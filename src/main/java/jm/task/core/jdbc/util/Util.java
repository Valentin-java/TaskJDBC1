package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String CONNECTION_STRING = "jdbc:mysql://localhost/dbtest?useSSL=false";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";


    public static Connection getMySQLConnection()
            throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER_NAME);
        return DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
    }

}
