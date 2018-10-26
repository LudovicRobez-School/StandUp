package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BddConnection {
    private static final Logger logger = Logger.getLogger(BddConnection.class.getName());

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String pwd = "";
    private static String user = "";
    private static Connection connect;

    public BddConnection() {
    }

    public static Connection getConnection(){
        try {
            if(connect == null) {
                Class.forName(driver);
                connect = DriverManager.getConnection(url, user, pwd);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "", e);
        }
        return connect;
    }

}
