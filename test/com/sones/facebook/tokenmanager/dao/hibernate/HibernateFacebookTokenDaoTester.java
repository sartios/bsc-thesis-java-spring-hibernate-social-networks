package com.sones.facebook.tokenmanager.model.dao.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.tokenmanager.model.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.dao.IFacebookTokenDao;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class HibernateFacebookTokenDaoTester 
{
	private	IApplicationUserDao	appUserDao;
	private	IFacebookAccountDao	accountDao;
	private	ApplicationContext	context;
	private IFacebookTokenDao tokenDao;
	
	public	HibernateFacebookTokenDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext("FacebookTokenManager/spring-FacebookTokenManager-model-nu.xml");
		accountDao	=	( HibernateFacebookAccountDao )context.getBean( "accountDao" );
		appUserDao	=	( HibernateApplicationUserDao )context.getBean( "appUserDao" );
		tokenDao	=	( HibernateFacebookTokenDao )context.getBean( "tokenDao" );
		
	}
	
	@Test
	public	void	TestSaveFacebookToken()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		FacebookAccount	account	=	SaveAccountAndReturn( appUser );
		FacebookToken	token	=	SaveFacebookTokenAndReturn( account );
		
		FacebookToken	dbToken	=	tokenDao.GetById( account.getId() );
		
		assertNotNull( "Dao returned a null object", dbToken );
		assertEquals( token.getId(), dbToken.getId() );
		assertEquals( token.getAccount(), dbToken.getAccount() );
		assertEquals( token.getValue(), dbToken.getValue() );
		
		DelteFacebookToken( token );
		DeleteAccount( account );
		DeleteApplicationUser( appUser );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	TestGetByApplicationUserNullAppUser()
	{
		tokenDao.GetByApplicationUser( null );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	TestGetByApplicationUserNullAppUserId()
	{
		ApplicationUser	user	=	new	ApplicationUser();
		tokenDao.GetByApplicationUser( user );
	}
	
	@Test
	public	void	TestGetByApplicationUserDoesNotExist()
	{
		ApplicationUser	user	=	new	ApplicationUser();
		user.setId( "1" );
		FacebookToken	token	=	tokenDao.GetByApplicationUser( user );
		assertEquals( null, token );
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
	
	private	FacebookToken	SaveFacebookTokenAndReturn( FacebookAccount account )
	{
		FacebookToken	token	=	new	FacebookToken( "access_token=AAAAAAITEghMBAFRwyoVOtijfy6CxxSwzA9c05U8d3rRNr16bOE74hgMUklusP6HiVjZAHN8zpZCmnCDFyZC9snL2V4ZCimZCYspWDf6yjXEQR7w4JR" , account);
		token.setId( "1" );
		tokenDao.Save( token );
		return	token;
	}
	
	private	void	DelteFacebookToken( FacebookToken token )
	{
		tokenDao.Delete( token );
	}
}
