package itrequests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainConnection {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=VBoxData;encrypt=true;trustServerCertificate=true;", "sa", "dangro2004F");

        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }
        return conn;
    }
}