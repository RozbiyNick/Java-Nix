package debates.dao;

public interface DebatesDaoFactory {
	public UserDao getUserDao();
	public DiscussionDao getDiscussionDao();
	public ArgumentDao getArgumentDao();
	public OrganisationDao getOrganisationDao();
}
