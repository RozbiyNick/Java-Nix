package debates.dao;

import java.util.List;

public interface Dao<E> {
	
	E create(E entity);
	
	void update(E entity);
	
	void delete(E entity);
	
	E findById(Long id);
	
	List<E> findAll();
}
