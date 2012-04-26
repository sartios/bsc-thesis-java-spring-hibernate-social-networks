package com.sones.facebook.tokenmanager.dao.hibernate;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.dao.hibernate.HibernateFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateFacebookAccountDaoTester 
{
	private	IApplicationUserDao	appUserDao;
	private	IFacebookAccountDao	accountDao;
	private	ApplicationContext	context;
	
	public	HibernateFacebookAccountDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext("FacebookTokenManager/spring-FacebookTokenManager-model-nu.xml");
		accountDao	=	( HibernateFacebookAccountDao )context.getBean( "accountDao" );
		appUserDao	=	( HibernateApplicationUserDao )context.getBean( "appUserDao" );
	}
	
	@Test
	public	void	SaveFacebookAccountTest()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		FacebookAccount	account	=	SaveAccountAndReturn( appUser );
		
		FacebookAccount	dbAccount	=	accountDao.GetById( account.getId() );
		
		assertNotNull( "Dao returned a null object", dbAccount );
		assertEquals( account.getId(), dbAccount.getId() );
		assertEquals( account.getAppUser(), dbAccount.getAppUser() );
		assertEquals( account.getEmail(), dbAccount.getEmail() );
		
		DeleteAccount( account );
		DeleteApplicationUser( appUser );
	}
	
	
	private	ApplicationUser	SaveUserAndReturn()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		appUser.setId( "1" );
		appUserDao.Save( appUser );
		return	appUser;
	}
	
	private	void	DeleteApplicationUser( ApplicationUser appUser )
	{
		appUserDao.Delete( appUser );
	}
	
	private	FacebookAccount	SaveAccountAndReturn( ApplicationUser appUser )
	{
		FacebookAccount	account	=	new	FacebookAccount( appUser );
		account.setId( "1" );
		account.setEmail( "sartios.sones@gmail.com" );
		accountDao.Save( account );
		return	account;
	}
	
	private	void	DeleteAccount( FacebookAccount account )
	{
		accountDao.Delete( account );
	}
}
