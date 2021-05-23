package debates.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface EntityMapper<E> {
	E fromResultSet(ResultSet resultSet);
	
	void fillCreateStatement(PreparedStatement statement, E entity);
	void fillUpdateStatement(PreparedStatement statement, E entity);
}
