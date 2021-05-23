package debates.dao.jdbc;

import debates.dao.ArgumentDao;
import debates.dao.DiscussionDao;
import debates.dao.OrganisationDao;
import debates.dao.UserDao;

public interface DebatesDaoFactory {
	public UserDao getUserDao();
	public DiscussionDao getDiscussionDao();
	public ArgumentDao getArgumentDao();
	public OrganisationDao getOrganisationDao();
}
