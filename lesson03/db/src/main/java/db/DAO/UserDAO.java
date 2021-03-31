package db.DAO;

import java.util.ArrayList;
import classes.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;

public class UserDAO {
	
	private static String url = "jdbc:h2:~/test";
	private static String user = "sa";
	private static String password = "";
	
	public ArrayList<User> getAll(){
		ArrayList<User> result = new ArrayList<User>();
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement s = connection.createStatement();
			String query = "SELECT * FROM Users";
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				result.add(new User(rs.getString(1), rs.getString(2)));
			}
			rs.close();
			s.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public User getById(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "SELECT * FROM Users WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.close();
			ps.close();
			connection.close();
			return new User(rs.getString(1), rs.getString(2));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(int id, User u) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "UPDATE Users SET login = ?, password  = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1,  u.getLogin());
			ps.setString(2,  u.getPassword());
			ps.setInt(3, id);
			ps.executeUpdate();
			
			ps.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(User u) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "INSERT INTO Users (login, password) VALUES (?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,  u.getLogin());
			ps.setString(2,  u.getPassword());
			boolean executed = ps.execute();
			ps.close();
			connection.close();
			return executed;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "DELETE FROM Users WHERE id=?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1,  id);
			boolean executed = ps.execute();
			ps.close();
			connection.close();
			return executed;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
