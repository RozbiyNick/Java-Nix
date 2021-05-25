package debates.dao.hibernate;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import debates.dao.UserDao;
import debates.model.User;

@Repository
public class HibernateUserDao extends HibernateGenericDao<User> implements UserDao {

	public HibernateUserDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public User findByUsername(String username) {
		try {
            User user = getCurrentSession()
                    .createQuery("from user u where u.username = :username",
                            User.class)
                    .setParameter("username", username).getSingleResult();

            return user;
        } catch (NoResultException e) {
            return null;
        }
	}

	@Override
	protected Class<User> getEntityClass() {
		return User.class;
	}
}
