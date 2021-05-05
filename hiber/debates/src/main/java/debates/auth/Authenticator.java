package debates.auth;

import debates.dao.UserDao;
import debates.entities.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticator {
	
	public static Authenticator s = new Authenticator();
	
	public boolean loginIsValid(String login) {
		
		return true;
	}
	
	public boolean passwordIsValid(String password) {
		
		return true;
	}
	
	
	public boolean loginIsFree(String login){
        UserDao dao = new UserDao();
        return (dao.findByLogin(login) == null) ? true : false;
	}
	
	public ArrayList<String> signupDataErrors(String login, String pass, String pass2) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ArrayList<String> errors = new ArrayList<String>();
		if (!loginIsValid(login)) errors.add("Login is incorrect");
		if (!loginIsFree(login)) errors.add("Login is busy");
		if (!passwordIsValid(pass)) errors.add("Password is not valid");
		if (!pass.equals(pass2)) errors.add("Passwords must match");
		return errors;
	}
	
	public void createUser(String login, String password) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		UserDao dao = new UserDao();
		dao.save(new User(login, password));
	}
	
	public boolean loginDataIsValid(String login, String password){
		UserDao dao = new UserDao();
		User user = dao.findByLogin(login);
		if (user == null)
			return false;
		else {
			if (user.getPassword() == password)
				return true;
			else {
				return false;
			}
		}	
	}
	
	public void login(User user, HttpServletResponse response) {
		
	}
}
