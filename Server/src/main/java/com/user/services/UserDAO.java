package com.user.services;

import java.util.List;

import fr.unicacces.beans.Administrator;

public interface UserDAO {
	void add(User user);
	
	void remove(User user);
	
	void modif(User user);
	
	List<User> getAll();
	
	User getOne(int id);

}
