package debates.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import debates.dao.ArgumentDao;
import debates.model.Argument;
import debates.model.status.ArgumentStatus;
import lombok.SneakyThrows;

public class JdbcArgumentDao extends JdbcGenericDao<Argument> implements ArgumentDao {
	
	private static final String SQL_FIND_BY_ID = "SELECT id, text, status, parent FROM argument WHERE id = ?";
	
	private static final String SQL_CREATE = "INSERT INTO argument (text, status, parent) VALUES (?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE argument SET text = ?, status = ?, parent = ? WHERE id = ?";

    private static final String SQL_FIND_ALL = "SELECT id, text, status, parent FROM argument";
    
    private static final String SQL_DELETE = "DELETE FROM argument WHERE id = ?";

	public JdbcArgumentDao(DataSource dataSource) {
		super(dataSource);
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
	protected EntityMapper<Argument> getEntityMapper() {
		return new EntityMapper<Argument>() {

			@SneakyThrows
			@Override
			public Argument fromResultSet(ResultSet resultSet) {
				return Argument.builder()
						.id(resultSet.getLong("id"))
						.text(resultSet.getString("text"))
						.status(ArgumentStatus.valueOf(resultSet.getString("status")))
						.parent(new Argument(resultSet.getLong("parent")))
						.build();
			}

			@SneakyThrows
			@Override
			public void fillCreateStatement(PreparedStatement statement, Argument entity) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getStatus().toString());
				if(!(entity.getParent()==null))
					statement.setLong(3, entity.getParent().getId());
				else
					statement.setNull(3, java.sql.Types.INTEGER);
			}

			@SneakyThrows
			@Override
			public void fillUpdateStatement(PreparedStatement statement, Argument entity) {
				statement.setString(1, entity.getText());
				statement.setString(2, entity.getStatus().toString());
				if(!(entity.getParent()==null))
					statement.setLong(3, entity.getParent().getId());
				else
					statement.setNull(3, java.sql.Types.INTEGER);
				statement.setLong(4, entity.getId());
			}
		};
	}

}
