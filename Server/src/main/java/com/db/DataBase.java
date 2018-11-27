package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {


    public final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    /**
     * Instance property : Connection
     */
    private static Connection instance;

    /**
     * Url property : String, (parameters added for Timezone issues between driver and server database)
     */

    public final static String url = "jdbc:mysql://localhost:3306/5g?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    /**
     * User property : String
     */
    public final static String user = "root";

    /**
     * Password property : String
     */
    public final static String password = "root";

    /**
     * Method which returns a connection whether from instance property or from the connection if null
     * @return instance is java.sql.Connection
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException,ClassNotFoundException {
        if(instance == null) {
            Class.forName(DataBase.jdbcDriver);
            instance = DriverManager.getConnection(url, user, password);
        }
        return instance;
    }
}
