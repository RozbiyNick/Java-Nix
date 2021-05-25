package debates.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import debates.dao.UserDao;
import debates.model.User;
import lombok.SneakyThrows;

public class JdbcUserDao extends JdbcGenericDao<User> implements UserDao {

	public JdbcUserDao(DataSource dataSource) {
		super(dataSource);
	}
	
	private static final String SQL_FIND_BY_ID = "SELECT id, username, password, name, description, organisation_id FROM user WHERE id = ?";

    private static final String SQL_CREATE = "INSERT INTO user (username, password, name, description, organisation_id) VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE user SET username = ?, password = ?, name =?, description=?, organisation_id=? WHERE id = ?";

    private static final String SQL_FIND_ALL = "SELECT id, username, password, name, description, organisation_id FROM user";
    
    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?";

    private static final String SQL_FIND_BY_USERNAME = "SELECT id, name FROM user WHERE username = ?";
    @SneakyThrows
	@Override
	public User findByUsername(String username) {
		try(Connection con = dataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(SQL_FIND_BY_USERNAME)){
					ps.setString(1, username);
					ResultSet rs = ps.executeQuery();
					if(rs.next()) {
						return User.builder().id(rs.getLong("id"))
								.name(rs.getString("name"))
								.build();
					}
				}
		return null;
	}

	@Override
	protected String getCreateSql() {
		return SQL_CREATE;
	}

	@Override
	protected String getUpdateSql() {
		return SQL_UPDATE;
	}

	@Override
	protected String getDeleteSql() {
		return SQL_DELETE;
	}

	@Override
	protected String getFindByIdSql() {
		return SQL_FIND_BY_ID;
	}

	@Override
	protected String getFindAllSql() {
		return SQL_FIND_ALL;
	}

	@Override
	protected EntityMapper<User> getEntityMapper() {
		return new EntityMapper<User>() {
			
			@SneakyThrows
			@Override
			public User fromResultSet(ResultSet resultSet) {
				return User.builder().id(resultSet.getLong("id"))
						.username(resultSet.getString("username"))
						.password(resultSet.getString("password"))
						.name(resultSet.getString("name"))
						.description(resultSet.getString("description"))
						.organisation(new JdbcOrganisationDao(dataSource).findById(resultSet.getLong("organisation_id")))
						.build();
			}
			
			@SneakyThrows
			@Override
			public void fillCreateStatement(PreparedStatement statement, User entity) {
				statement.setString(1, entity.getUsername());
				statement.setString(2, entity.getPassword());
				statement.setString(3, entity.getName());
				statement.setString(4, entity.getDescription());
				if(!(entity.getOrganisation() == null)) {
					statement.setLong(5, entity.getOrganisation().getId());
				}
				else {
					statement.setNull(5, java.sql.Types.INTEGER);
				}
			}

			@SneakyThrows
			@Override
			public void fillUpdateStatement(PreparedStatement statement, User entity) {
				statement.setString(1, entity.getUsername());
				statement.setString(2, entity.getPassword());
				statement.setString(3, entity.getName());
				statement.setString(4, entity.getDescription());
				if(!(entity.getOrganisation()==null)) {
					statement.setLong(5, entity.getOrganisation().getId());
				}
				else {
					statement.setNull(5, java.sql.Types.INTEGER);
				}
				statement.setLong(6, entity.getId());
			}
		};
	}

}
