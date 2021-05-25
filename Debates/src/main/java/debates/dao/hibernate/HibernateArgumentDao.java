package debates.dao.hibernate;

import org.hibernate.SessionFactory;

import debates.dao.ArgumentDao;
import debates.model.Argument;

public class HibernateArgumentDao extends HibernateGenericDao<Argument> implements ArgumentDao {

	public HibernateArgumentDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	protected Class<Argument> getEntityClass() {
		return Argument.class;
	}

}
