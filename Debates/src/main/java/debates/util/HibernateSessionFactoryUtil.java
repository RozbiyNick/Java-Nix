package debates.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import debates.model.Argument;
import debates.model.Discussion;
import debates.model.Organisation;
import debates.model.User;
import lombok.SneakyThrows;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    @SneakyThrows
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                
                configuration.addAnnotatedClass(Organisation.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Argument.class);
                configuration.addAnnotatedClass(Discussion.class);
                
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } finally {
            	
            }
        }
        return sessionFactory;
    }
}
