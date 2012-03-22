package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextProvider 
{
	private	ApplicationContext	modelContext;
	private	ApplicationContext	daoContext;
	private	ApplicationContext	appUserContext = null;
	
	public	ApplicationContextProvider()
	{
		modelContext	=	new	ClassPathXmlApplicationContext("KeywordSearcher/spring-facebook-keywordSearcher-nu.xml");
		daoContext	=	new	ClassPathXmlApplicationContext("KeywordSearcher/spring-facebook-keywordSearcher-dao.xml");
	}
	
	public	ApplicationContext	getModelContext()
	{
		return	modelContext;
	}
	
	public	ApplicationContext	getDaoContext()
	{
		return	daoContext;
	}
	
	public	ApplicationContext getAppUserContext()
	{
		if( appUserContext == null )
		{
			appUserContext = new ClassPathXmlApplicationContext("UserManager/spring-userManager-dao.xml");
		}
		return	appUserContext;
	}
}
