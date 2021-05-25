package debates.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import debates.dao.Dao;
import debates.model.Entity;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public abstract class JdbcGenericDao<E extends Entity> implements Dao<E>{
	
	protected final DataSource dataSource;
	
	protected abstract String getCreateSql();
	protected abstract String getUpdateSql();
	protected abstract String getDeleteSql();
	protected abstract String getFindByIdSql();
	protected abstract String getFindAllSql();
	protected abstract EntityMapper<E> getEntityMapper();
	
	@SneakyThrows
	@Override
	public E create(E entity) {
		try(Connection con = dataSource.getConnection();
				PreparedStatement st = con.prepareStatement(getCreateSql(),
						Statement.RETURN_GENERATED_KEYS)){
			getEntityMapper().fillCreateStatement(st, entity);
			st.execute();
			ResultSet generatedKeys = st.getGeneratedKeys();
			if(generatedKeys.next()) {
				entity.setId(generatedKeys.getLong(1));
			}
			return entity;	
		}
	}
	
	@SneakyThrows
	@Override
	public void update(E entity) {
		try(Connection con = dataSource.getConnection();
				PreparedStatement st = con.prepareStatement(getUpdateSql())){
			getEntityMapper().fillUpdateStatement(st, entity);
			st.executeUpdate();
		}
	}
	
	@SneakyThrows
	@Override
	public void delete(E entity) {
		try(Connection con = dataSource.getConnection();
				PreparedStatement st = con.prepareStatement(getDeleteSql())){
			st.setLong(1, entity.getId());
			st.execute();
		}
	}
	
	@SneakyThrows
	@Override
	public E findById(Long id) {
		try(Connection con = dataSource.getConnection();
				PreparedStatement st = con.prepareStatement(getFindByIdSql())){
			st.setLong(1, id);
			ResultSet resultSet = st.executeQuery();
			if(resultSet.next()) {
				EntityMapper<E> entityMapper = getEntityMapper();
				return entityMapper.fromResultSet(resultSet);
			}
		}
		return null;
	}

	@SneakyThrows
	@Override
	public List<E> findAll() {
		List<E> result = new LinkedList<E>();
		try(Connection con = dataSource.getConnection();
				Statement st = con.createStatement()){
			ResultSet resultSet = st.executeQuery(getFindAllSql());
			EntityMapper<E> entityMapper = getEntityMapper();
			while(resultSet.next()) {
				E e = entityMapper.fromResultSet(resultSet);
				result.add(e);
			}
		}
		return result;
	}
}
