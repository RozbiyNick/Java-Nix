package debates.dao;

import debates.model.User;

public interface UserDao extends Dao<User>{
	
	User findByUsername(String username);
	
}
