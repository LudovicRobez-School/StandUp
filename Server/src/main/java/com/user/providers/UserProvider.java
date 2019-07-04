package com.user.providers;

import com.database.DataBase;
import com.user.ressources.User;
import com.user.ressources.UserType;

import java.sql.Connection;
import java.sql.ResultSet;

public class UserProvider {

    private Connection connection;

    public UserProvider() throws Exception {
        this.connection =  DataBase.getConnection();
    }

    public User findUserById(int id) throws Exception{
        //TODO: left inner join
        String query = "SELECT * FROM User WHERE user_id='%1$d';";
        ResultSet result = connection.createStatement().executeQuery(String.format(query, id));
        if (result.first()){
            return new User(result.getInt("user_id"),result.getString("user_email"),result.getString("user_password"),result.getString("user_username"),result.getString("user_firstname"),result.getString("user_lastname"),UserType.valueOf(result.getString("user_usertype")));
        }
        return null;
    }

    public boolean addUser(User user) throws Exception{
        String query = "INSERT INTO User (user_email,user_password,user_username) VALUES ('%1$s','%2$s','%3$s');" ;
        int result = connection.createStatement().executeUpdate(query.format(query, user.getEmail(),user.getPassword(),user.getUsername()));
        return result > 0 ? true : false ;
    }

    public boolean UpdateUserById(User user) throws Exception{
        String query = "UPDATE User SET user_email='%2$s',user_password='%3$s',user_username='%4$s' WHERE user_id='%1$d';";
        int result = connection.createStatement().executeUpdate(query.format(query,user.getId(),user.getEmail(),user.getPassword(),user.getUsername()));
        return result > 0 ? true : false ;
    }
}
