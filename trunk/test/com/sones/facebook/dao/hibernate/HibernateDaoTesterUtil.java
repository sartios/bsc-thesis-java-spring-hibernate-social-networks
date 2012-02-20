package com.sones.facebook.dao.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateDaoTesterUtil 
{
	private ApplicationContext daoContext;
	private ApplicationContext modelContext;
	
	public HibernateDaoTesterUtil()
	{
		daoContext =
		    new ClassPathXmlApplicationContext("spring-facebook-dao.xml");
		
		modelContext =
		    new ClassPathXmlApplicationContext("spring-facebook-nu.xml");
	}
	
	public ApplicationContext getDAOContext()
	{
		return daoContext;
	}
	
	public ApplicationContext getModelContext()
	{
		return modelContext;
	}
}
