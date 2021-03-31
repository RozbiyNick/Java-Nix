package db.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import classes.*;

public class DiscussionDAO {
	
	private static String url = "jdbc:h2:~/test";
	private static String user = "sa";
	private static String password = "";
	
	
	public ArrayList<Discussion> getAll(){
		ArrayList<Discussion> result = new ArrayList<Discussion>();
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement s = connection.createStatement();
			String query = "SELECT * FROM Discussions";
			ResultSet rs = s.executeQuery(query);
			while(rs.next()) {
				result.add(new Discussion(rs.getString(1), (new UserDAO()).getById(rs.getInt(2)), (new ArgumentDAO()).getById(rs.getInt(3))));
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
	
	public Discussion getById(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "SELECT * FROM Discussions WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.close();
			ps.close();
			connection.close();
			return new Discussion(rs.getString(1), (new UserDAO()).getById(rs.getInt(2)), (new ArgumentDAO()).getById(rs.getInt(3)));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(int id, String question, int author_id, int root_id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "UPDATE Discussion SET question = ?, author  = ?, root = ? WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1,  question);
			ps.setInt(2,  author_id);
			ps.setInt(3, root_id);
			ps.setInt(4, id);
			ps.executeUpdate();
			
			ps.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(String question, int author_id, int root_id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "INSERT INTO Discussions (question, author, root) VALUES (?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,  question);
			ps.setInt(2,  author_id);
			ps.setInt(3, root_id);
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
			String query = "DELETE FROM Discussions WHERE id=?";
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
