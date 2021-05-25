package debates.dao.hibernate;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;

import debates.dao.OrganisationDao;
import debates.model.Organisation;

public class HibernateOrganisationDao extends HibernateGenericDao<Organisation> implements OrganisationDao {

	public HibernateOrganisationDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Organisation findByName(String name) {
		try {
			Organisation organisation = getCurrentSession()
					.createQuery("from organisation o where o.name := name", Organisation.class)
					.setParameter("name", name).getSingleResult();
			return organisation;
		}
		catch (NoResultException e) {
			return null;
		}
	}

	@Override
	protected Class<Organisation> getEntityClass() {
		return Organisation.class;
	}

}
