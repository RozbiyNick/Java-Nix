package debates.dao.jdbc;

import java.io.FileInputStream;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import debates.dao.ArgumentDao;
import debates.dao.DiscussionDao;
import debates.dao.OrganisationDao;
import debates.dao.UserDao;
import lombok.SneakyThrows;

public class DaoFactory {
	
	private static DaoFactory instance;
	private DebatesDaoFactory factory;
	private MysqlDataSource ds;
	
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
			
		}
	}
}
