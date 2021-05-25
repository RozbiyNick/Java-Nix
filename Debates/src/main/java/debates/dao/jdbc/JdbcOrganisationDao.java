package debates.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import debates.dao.OrganisationDao;
import debates.model.Organisation;
import lombok.SneakyThrows;

public class JdbcOrganisationDao extends JdbcGenericDao<Organisation> implements OrganisationDao {
	
	private static final String SQL_FIND_BY_ID = "SELECT id, name, description FROM organisation WHERE id = ?";
	
	private static final String SQL_CREATE = "INSERT INTO organisation (name, description) VALUES (?, ?)";

    private static final String SQL_UPDATE = "UPDATE organisation SET name =?, description=? WHERE id = ?";

    private static final String SQL_FIND_ALL = "SELECT id, name, description FROM organisation";
    
    private static final String SQL_DELETE = "DELETE FROM organisation WHERE id = ?";

    private static final String SQL_FIND_BY_NAME = "SELECT id, name FROM organisation WHERE name = ?";

	public JdbcOrganisationDao(DataSource dataSource) {
		super(dataSource);
	}

	@SneakyThrows
	@Override
	public Organisation findByName(String name) {
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_NAME)){
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return Organisation.builder().id(rs.getLong("id")).name(rs.getString("name")).build();
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
	protected EntityMapper<Organisation> getEntityMapper() {
		return new EntityMapper<Organisation>(){

			@SneakyThrows
			@Override
			public Organisation fromResultSet(ResultSet resultSet) {
				return Organisation.builder()
						.id(resultSet.getLong("id"))
						.name(resultSet.getString("name"))
						.description(resultSet.getString("description"))
						.build();
			}

			@SneakyThrows
			@Override
			public void fillCreateStatement(PreparedStatement statement, Organisation entity) {
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getDescription());
			}

			@SneakyThrows
			@Override
			public void fillUpdateStatement(PreparedStatement statement, Organisation entity) {
				statement.setString(1, entity.getName());
				statement.setString(2, entity.getDescription());
				statement.setLong(3, entity.getId());
			}
		};
	}
}
