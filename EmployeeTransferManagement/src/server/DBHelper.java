package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class DBHelper {
    Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public DBHelper() {
        Properties p = new Properties();
        try {
            FileReader fin = new FileReader(new File("connection.properties"));
            p.load(fin);
            String host = p.getProperty("ServerID");
            String port = p.getProperty("Port");
            String dbname = p.getProperty("Database");
            String user = p.getProperty("Username");
            String pw = p.getProperty("Password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbname + ";encrypt=true;trustServerCertificate=true;";
            con = DriverManager.getConnection(url, user, pw);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:: Cannot connect to server");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error 100:: Cannot find class");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Error 102:: Config is null");
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error:: Cannot find properties file");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error:: IOException");
        }
    }
    
}
