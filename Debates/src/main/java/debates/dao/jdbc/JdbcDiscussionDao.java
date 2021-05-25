package debates.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import debates.dao.DiscussionDao;
import debates.model.Argument;
import debates.model.Discussion;
import debates.model.Organisation;
import debates.model.User;
import lombok.SneakyThrows;

public class JdbcDiscussionDao extends JdbcGenericDao<Discussion> implements DiscussionDao {
	
	private static final String SQL_FIND_BY_ID = "SELECT id, organisation, author, description, question FROM discussion WHERE id = ?";
	
	private static final String SQL_CREATE = "INSERT INTO discussion (organisation, author, description, question) VALUES (?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE discussion SET organisation = ?, author = ?, description = ?, question = ? WHERE id = ?";

    private static final String SQL_FIND_ALL = "SELECT id, organisation, author, description, question FROM discussion";
    
    private static final String SQL_DELETE = "DELETE FROM discussion WHERE id = ?";

	public JdbcDiscussionDao(DataSource dataSource) {
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
	protected EntityMapper<Discussion> getEntityMapper() {
		return new EntityMapper<Discussion>() {

			@SneakyThrows
			@Override
			public Discussion fromResultSet(ResultSet resultSet) {
				return Discussion.builder()
						.organisation(Organisation.builder().id(resultSet.getLong("organisation")).build())
						.question(Argument.builder().id(resultSet.getLong("argument")).build())
						.author(User.builder().id(resultSet.getLong("author")).build())
						.description(resultSet.getString("description")).build();
			}

			@SneakyThrows
			@Override
			public void fillCreateStatement(PreparedStatement statement, Discussion entity) {
				if(!(entity.getOrganisation() == null))
					statement.setLong(1, entity.getOrganisation().getId());
				else
					statement.setNull(1, java.sql.Types.INTEGER);
				statement.setLong(2, entity.getAuthor().getId());
				statement.setString(3, entity.getDescription());
				statement.setLong(4, entity.getQuestion().getId());
			}

			@SneakyThrows
			@Override
			public void fillUpdateStatement(PreparedStatement statement, Discussion entity) {
				if(!(entity.getOrganisation() == null))
					statement.setLong(1, entity.getOrganisation().getId());
				else
					statement.setNull(1, java.sql.Types.INTEGER);
				statement.setLong(2, entity.getAuthor().getId());
				statement.setString(3, entity.getDescription());
				statement.setLong(4, entity.getQuestion().getId());
				statement.setLong(5, entity.getId());
			}
		};
	}

}
