package debates.auth;

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
	
	public boolean loginIsFree(String login) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/debates";
        String username = "root";
        String passw = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, passw)){
    			String query = "SELECT id FROM user WHERE login=?";
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, login);
    			ResultSet rs = ps.executeQuery();
    			if(rs.next())
    				return false;
    			else
    				return true;
            }
        }
        finally {
        	
        }
	}
	
	public ArrayList<String> signupDataErrors(String login, String pass, String pass2) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		ArrayList<String> errors = new ArrayList<String>();
		if (!loginIsValid(login)) errors.add("Login is incorrect");
		if (!loginIsFree(login)) errors.add("Login is busy");
		if (!passwordIsValid(pass)) errors.add("Login is busy");
		if (!pass.equals(pass2)) errors.add("Passwords must match");
		return errors;
	}
	
	public void createUser(String login, String password) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/debates";
        String username = "root";
        String passw = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, passw)){
    			String query = "INSERT INTO user (login, password) VALUES (?, ?)";
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, login);
    			ps.setString(2, password);
    			ps.execute();
            }
        }
        finally {
        	
        }
	}
	
	public boolean loginDataIsValid(String login, String password) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/debates";
        String username = "root";
        String passw = "root";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, passw)){
    			String query = "SELECT password FROM User WHERE login = ?";
    			PreparedStatement ps = conn.prepareStatement(query);
    			ps.setString(1, login);
    			ResultSet rs = ps.executeQuery();
    			if(!rs.next()) {
    				return false;
    			}
    			String actualPass = rs.getString(1);
    			System.out.println(login);
    			System.out.println(password);
    			System.out.println(actualPass);
    			rs.close();
    			ps.close();
    			conn.close();
    			if (actualPass.equals(password))
    				return true;
    			else
    				return false;
            }
        }
        finally {
        	
        }
	}
	
	public void login(User user, HttpServletResponse response) {
		
	}
}
