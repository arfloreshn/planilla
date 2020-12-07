/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilerias.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author root
 */
public class HibernateUtil {

    static SessionFactory sessionFactory = buildSessionFactory();
    private static StandardServiceRegistry registry;


    private static SessionFactory buildSessionFactory() {
 
        try {

            
         //   Configuration configuration = new Configuration().configure();
         //   StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
         //   applySettings(configuration.getProperties());
         //   SessionFactory factory = configuration.buildSessionFactory(builder.build());

         registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
         MetadataSources sources = new MetadataSources(registry);
         Metadata metadata = sources.getMetadataBuilder().build();
         sessionFactory = metadata.getSessionFactoryBuilder().build();
         return sessionFactory;
        }
      
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession() {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
