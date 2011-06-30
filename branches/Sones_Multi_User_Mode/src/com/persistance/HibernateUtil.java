package com.persistance;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {

	private static SessionFactory factory;
	private static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
	private static String cfg = "/../config/hibernate.cfg.xml";
	
	public static SessionFactory buildSessionFactory() throws HibernateException{
		if(null!=factory){
			closeFactory();
		}
		Configuration configuration = new Configuration();
		configuration.configure();
		
		factory = configuration.buildSessionFactory();
		return factory;
	}
	
	public static SessionFactory buildIfNeeded(){
		if(null==factory){
			Configuration conf = new Configuration();
			conf.configure();
			factory = conf.buildSessionFactory();
		}
		return factory;
	}
	
	public static SessionFactory getSessionFactory(){
		return factory;
	}
	
	public static Session openSession() throws HibernateException{
		return factory.openSession();
	}
	
	public static void closeFactory(){
		if(null!=factory){
			try{
				factory.close();
			}
			catch (HibernateException ignored) {
				logger.error("Couldn't close session factory",ignored);
			}
		}
	}
	
	public static void close(Session session){
		if(null!=session){
			try{
				session.close();
			}
			catch (HibernateException ignored) {
				logger.error("Couldn't close session",ignored);
			}
		}
	}
	
	public static void rollback(Transaction tx){
		try{
			if(null!=tx){
				tx.rollback();
			}
		}
		catch(HibernateException ignored){
			logger.error("Couldn't rollback Transaction",ignored);
		}
	}
	
}
