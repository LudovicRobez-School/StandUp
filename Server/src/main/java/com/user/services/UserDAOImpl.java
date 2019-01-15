package com.user.services;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.user.services.*;
import com.db.DAO;

public class UserDAOImpl implements UserDAO{
	private DAO dao;

	public UserDAOImpl(DAO dao) {
		this.dao = dao;
	}

	@Override
	public void add(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		/*
		try {
			connexion = dao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO standup_user(firstname,lastname) VALUES(?,?);");
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		
		UserDAO userDao = dao.getUserDAO();*/
		
		try {
			connexion = dao.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO standup_user(email,password) VALUES(?,?);");
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();			
		}	
	}

	@Override
	public void remove(User user) {
		PreparedStatement preparedStatement = null;
		Connection connexion = null;
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("DELETE FROM standup_user WHERE id=?;");
            preparedStatement.setInt(1, user.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void modif(User user) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE standup_user SET email = ?, password = ? WHERE id = ?;");
            
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getId());
            
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public List<User> getAll() {
		List<User> result = new ArrayList<User>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = dao.getConnection();
			statement = connexion.createStatement();
			
			resultat = statement.executeQuery( "SELECT * FROM standup_user;" );
			
			while(resultat.next())
			{
				User user = new User();
				String email = resultat.getString("email");
				String password = resultat.getString("password");
				int idrecup = resultat.getInt("id");
				
				user.setEmail(email);;
				user.setPassword(password);;
				user.setId(idrecup);
				
				result.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public User getOne(int id) {
		User result = new User();
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		
        try {
        	connexion = dao.getConnection();
            preparedStatement = connexion.prepareStatement("SELECT * FROM standup_user WHERE id=?;");
            preparedStatement.setInt(1, id);
            
            resultat =  preparedStatement.executeQuery();
            
            if(resultat.next()) {

				String email = resultat.getString("email");
				String password = resultat.getString("password");
				int idRecup = resultat.getInt("id");

				result.setEmail(email);
				result.setPassword(password);;
				result.setId(idRecup);
    		}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
		
		return result;
	}
	
}
