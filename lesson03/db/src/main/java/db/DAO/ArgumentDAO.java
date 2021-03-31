package db.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import classes.*;

public class ArgumentDAO {
	
	private static String url = "jdbc:h2:~/test";
	private static String user = "sa";
	private static String password = "";
	
	public Argument getById(int id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "SELECT * FROM Arguments WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.close();
			ps.close();
			connection.close();
			return new Argument(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), (new UserDAO()).getById(rs.getInt(5)));
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void update(int id, String thesis, String explanation, String evidences, int father_id, int author_id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "UPDATE Arguments SET thesis = ?, explanation  = ?, evidences = ?, father_id = ?, author_id = ?  WHERE id = ?";
			PreparedStatement ps = connection.prepareStatement(query);
			
			ps.setString(1,  thesis);
			ps.setString(2,  explanation);
			ps.setString(3,  evidences);
			ps.setInt(4,  father_id);
			ps.setInt(5, author_id);
			
			ps.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create(String thesis, String explanation, String evidences, int father_id, int author_id) {
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			String query = "INSERT INTO Arguments (thesis, explanation, evidences, father_id, author_id) VALUES (?,?,?,?,?)";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,  thesis);
			ps.setString(2,  explanation);
			ps.setString(3,  evidences);
			ps.setInt(4,  father_id);
			ps.setInt(5, author_id);
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
			String query = "DELETE FROM Arguments WHERE id=?";
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
