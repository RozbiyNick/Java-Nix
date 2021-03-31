package db;

import classes.*;
import db.DAO.*;

public class Main {
	
	public static void main(String[] args) {
		
		// adding a User
		User kolya = new User("Nick", "password");
		User olya = new User("ick", "password");
		User lya = new User("ck", "password");
		UserDAO userDAO = new UserDAO();
		
		userDAO.create(kolya);
		
		// deleting a user
		userDAO.delete(1);
		
		// updating User
		userDAO.update(1, lya);
	}

}
