package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase{

    public final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    public final static String jdbcDriverTest = "org.sqlite.JDBC";
    /**
     * Instance property : Connection
     */
    private static Connection instance;

    /**
     * Url property : String, (parameters added for Timezone issues between driver and server database)
     */

    public final static String url = "jdbc:mysql://localhost:3306/5g?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";


    public final static String urlTest = "jdbc:sqlite:D:/Ynov/Ydays/Stand Up v2/standup/standupDB.db";
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
    public static Connection getConnection() throws Exception {
        boolean isTest = false;
        if (instance == null) {
            if (isTest) {
                Class.forName(DataBase.jdbcDriver);
                instance = DriverManager.getConnection(url, user, password);
            } else {
                Class.forName(DataBase.jdbcDriverTest);
                instance = DriverManager.getConnection(urlTest);
                createTables(instance);
            }
        }
        return instance;
    }

    public static void createTables(Connection connection) throws Exception {
        String userTable = "CREATE TABLE IF NOT EXISTS User(user_id integer PRIMARY key,\n user_email text Not Null,\n  user_password text not null,\n user_username text,\n user_firstname text,\n user_lastname text,\n user_usertype text\n);";
        String eventTable = "CREATE TABLE IF NOT EXISTS Event(event_id integer PRIMARY key,\n event_name text Not Null,\n event_user integer not null,\n event_date date not null,\n event_longitude integer not null ,\n event_latitude integer not null,\n FOREIGN KEY(event_user) REFERENCES user(user_id)\n);";
        String musicTable = "CREATE TABLE IF NOT EXISTS Music(music_id integer PRIMARY key,\n music_title text Not Null,\n  music_style integer not null,\n music_duration integer not null,\n music_artist integer not null,\n music_uri text not null,\n FOREIGN KEY(music_artist) REFERENCES user(user_id));";
        Statement stmt = connection.createStatement();
        stmt.execute(userTable);
        stmt.execute(eventTable);
        stmt.execute(musicTable);
    }
}
