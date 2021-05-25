package debates.test;

import org.hibernate.Session;
import org.hibernate.Transaction;

import debates.dao.DaoFactory;
import debates.dao.hibernate.HibernateArgumentDao;
import debates.dao.hibernate.HibernateOrganisationDao;
import debates.dao.hibernate.HibernateUserDao;
import debates.model.Argument;
import debates.model.Organisation;
import debates.model.User;
import debates.util.HibernateSessionFactoryUtil;
import lombok.SneakyThrows;

public class HibernateTest {

	@SneakyThrows
	public static void main(String[] args) {
		try {
			System.out.println("Test find user");
			HibernateUserDao userDao = (HibernateUserDao) DaoFactory.getInstance("hibernate", "src/main/resources/db.properties").getUserDao();
			
			Session session = HibernateSessionFactoryUtil.getSessionFactory().getCurrentSession();
			Transaction tx = session.beginTransaction();
			
			User u = userDao.findByUsername("Nickname");
			
			tx.commit();
			System.out.println(u.toString());
		}
		finally {
			
		}	
	}
}
