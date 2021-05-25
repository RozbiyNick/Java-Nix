package debates.dao.hibernate;

import org.hibernate.SessionFactory;

import debates.dao.DiscussionDao;
import debates.model.Discussion;

public class HibernateDiscussionDao extends HibernateGenericDao<Discussion> implements DiscussionDao {

	public HibernateDiscussionDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	protected Class<Discussion> getEntityClass() {
		return Discussion.class;
	}

}
