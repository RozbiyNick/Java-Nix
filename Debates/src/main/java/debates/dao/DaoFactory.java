package debates.dao;

import java.io.FileInputStream;
import java.util.Properties;

import org.hibernate.SessionFactory;

import com.mysql.cj.jdbc.MysqlDataSource;

import debates.dao.hibernate.HibernateArgumentDao;
import debates.dao.hibernate.HibernateDiscussionDao;
import debates.dao.hibernate.HibernateOrganisationDao;
import debates.dao.hibernate.HibernateUserDao;
import debates.dao.jdbc.JdbcArgumentDao;
import debates.dao.jdbc.JdbcDiscussionDao;
import debates.dao.jdbc.JdbcOrganisationDao;
import debates.dao.jdbc.JdbcUserDao;
import debates.util.HibernateSessionFactoryUtil;
import lombok.SneakyThrows;

public class DaoFactory {
	
	private static DaoFactory instance;
	private DebatesDaoFactory factory;
	private MysqlDataSource ds;
	private static SessionFactory hiberSessionFactory;
	
	public static DaoFactory getInstance(String type, String propsPath) {
		if (instance==null) {
			instance = new DaoFactory(type, propsPath);
		}
		return instance;
	}
	
	public UserDao getUserDao() {
		return factory.getUserDao();
	}
	public DiscussionDao getDiscussionDao() {
		return factory.getDiscussionDao();
	}
	public ArgumentDao getArgumentDao() {
		return factory.getArgumentDao();
	}
	public OrganisationDao getOrganisationDao() {
		return factory.getOrganisationDao();
	}
	public DaoFactory(String type, String propsPath) {
		init(type, propsPath);
	}
	
	@SneakyThrows
	private void init(String type, String propsPath) {
		switch(type) {
		case "jdbc":
			
			Properties props = new Properties();
			try (FileInputStream fis = new FileInputStream(propsPath)) {
	            props.load(fis);
	        }
			
			hiberSessionFactory = null;
			ds = new MysqlDataSource();
	        ds.setURL(props.getProperty("mysql.url"));
	        ds.setUser(props.getProperty("mysql.username"));
	        ds.setPassword(props.getProperty("mysql.password"));
	        
			factory = new DebatesDaoFactory() {

				@Override
				public UserDao getUserDao() {
					return new JdbcUserDao(ds);
				}

				@Override
				public DiscussionDao getDiscussionDao() {
					return new JdbcDiscussionDao(ds);
				}

				@Override
				public ArgumentDao getArgumentDao() {
					return new JdbcArgumentDao(ds);
				}

				@Override
				public OrganisationDao getOrganisationDao() {
					return new JdbcOrganisationDao(ds);
				}
			};
		case "hibernate":
			hiberSessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
			ds = null;
			factory = new DebatesDaoFactory() {
				
				@Override
				public UserDao getUserDao() {
					return new HibernateUserDao(hiberSessionFactory);
				}
				
				@Override
				public OrganisationDao getOrganisationDao() {
					return new HibernateOrganisationDao(hiberSessionFactory);
				}
				
				@Override
				public DiscussionDao getDiscussionDao() {
					return new HibernateDiscussionDao(hiberSessionFactory);
				}
				
				@Override
				public ArgumentDao getArgumentDao() {
					return new HibernateArgumentDao(hiberSessionFactory);
				}
			};
		}
	}
}
