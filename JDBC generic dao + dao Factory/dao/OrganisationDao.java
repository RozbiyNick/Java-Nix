package debates.dao;

import debates.model.Organisation;

public interface OrganisationDao extends Dao<Organisation>{
	
	Organisation findByName(String name);
	
}
