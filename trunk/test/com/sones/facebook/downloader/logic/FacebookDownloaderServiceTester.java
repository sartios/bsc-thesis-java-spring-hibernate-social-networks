package com.sones.facebook.downloader.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.dao.hibernate.source.HibernateSourceDao;
import com.sones.facebook.dao.hibernate.source.HibernateSourceTypeDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.tokenmanager.model.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.dao.IFacebookTokenDao;
import com.sones.facebook.tokenmanager.model.dao.hibernate.HibernateFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.dao.hibernate.HibernateFacebookTokenDao;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;
import com.sones.facebook.usermanager.dao.hibernate.HibernateApplicationUserSourceDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.facebook.usermanager.model.ApplicationUserSourceId;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookDownloaderServiceTester 
{
	private	final String TOKEN_VALUE = "access_token=AAABhPDucCP8BAP5fSPElqyFwck6ig1ZAYUNwJih4iyHtzTNalAtpYXXSTacTFpaMhFZAXnk37veebRMP186RHdGmQnxLrK5MhTZAFWXSQZDZD";	private	IFacebookDownloaderService	donwloaderService;
	private	ApplicationContext	context;
	private	IApplicationUserDao	appUserDao;
	private	IFacebookAccountDao	accountDao;
	private	IFacebookTokenDao	tokenDao;
	private ISourceTypeDao sourceTypeDao;
	private ISourceDao sourceDao;
	private IApplicationUserSourceDao appUserSourceDao;
	private	ApplicationUser	applicationUser;

	
	public	FacebookDownloaderServiceTester()
	{
		context	=	new	ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-downloader-service-nu.xml");
		donwloaderService	=	( FacebookDownloaderService )context.getBean( "facebookDownloaderService" );
		appUserDao	=	( HibernateApplicationUserDao )context.getBean( "appUserDao" );
		accountDao	=	( HibernateFacebookAccountDao )context.getBean( "accountDao" );
		tokenDao	=	( HibernateFacebookTokenDao )context.getBean( "tokenDao" );
		sourceTypeDao	=	( HibernateSourceTypeDao )context.getBean( "sourceTypeDao" );
		sourceDao	=	( HibernateSourceDao )context.getBean( "sourceDao" );
		appUserSourceDao	=	( HibernateApplicationUserSourceDao )context.getBean( "appUserSourceDao" );
	}
	
	@Test
	public	void	TestDownloadWallPosts()
	{
		InitTestData();
		donwloaderService.DownloadWallPosts( applicationUser );
	}
	
	@Test
	public void TestDownloadWallPostsPublicPlacesInSources()
	{
		InitTestData();
		Iterable<Source> publicSources = SavePublicPlaceSourcesAndReturn();
		SaveAppUserSourcesAndReturn( publicSources, applicationUser );
		donwloaderService.DownloadWallPosts( applicationUser );
	}
	
	@Test
	public	void	TestDonwloadWallPostsSetATimer()
	{
		InitTestData();
		for( int downloadIndex = 0 ; downloadIndex < 500; downloadIndex++ )
		{
			donwloaderService.DownloadWallPosts( applicationUser );
			Sleep(60000);
		}
	}
	
	private	void	Sleep( long miliseconds )
	{
		try {
			Thread.sleep( miliseconds );
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private	void	InitTestData()
	{
		applicationUser	=	SaveApplicationUserAndReturn();
		FacebookAccount	account	=	SaveAccountAndReturn( applicationUser );
		FacebookToken	token	=	SaveTokenAndReturn( account );
		Iterable< Source >	sources	=	SaveSourcesAndReturn();
		Iterable< ApplicationUserSource >	appUserSources	=	SaveAppUserSourcesAndReturn(sources, applicationUser);
	}
	
	private	FacebookToken	SaveTokenAndReturn( FacebookAccount	account )
	{
		FacebookToken	token	=	new	FacebookToken();
		Number	rowCount	=	tokenDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() + 5 ) );
		token.setId( id );
		token.setAccount( account );
		token.setValue( TOKEN_VALUE );
		tokenDao.Save( token );
		return	token;
	}
	
	private	ApplicationUser	SaveApplicationUserAndReturn()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		Number	rowCount	=	appUserDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() + 5 ) );
		appUser.setId( id );
		appUserDao.Save( appUser );
		return	appUser;
	}
	
	private	FacebookAccount	SaveAccountAndReturn( ApplicationUser	appUser )
	{
		FacebookAccount	account	=	new	FacebookAccount();
		Number	rowCount	=	accountDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() + 5 ) );
		account.setId( id );
		account.setAppUser( appUser );
		accountDao.Save( account );
		return	account;
	}
	
	private Iterable< Source > SavePublicPlaceSourcesAndReturn()
	{
		SourceType	type	=	new	SourceType();
		Number	rowCount	=	sourceTypeDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() + 5 ) );
		type.setId( id );
		type.setType( "Place" );
		sourceTypeDao.Save( type );
		List< Source >	sources	=	new	ArrayList< Source >();
		sources.add( getSource( "102783179811332", type ) );
		sources.add( getSource( "103012763095683", type ) );
		sources.add( getSource( "104945772882209", type ) );
		sources.add( getSource( "106721709395327", type ) );
		sources.add( getSource( "107761595952623", type ) );
		
		for( Source source : sources )
		{
			Source	dbSource	=	sourceDao.GetById( source.getId() );
			if( dbSource == null )
			{
				sourceDao.Save( source );
			}
		}
		
		return	sources;
	}
	
	private	Iterable< Source > SaveSourcesAndReturn()
	{
		SourceType	type	=	new	SourceType();
		Number	rowCount	=	sourceTypeDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() + 5 ) );
		type.setId( id );
		type.setType( "User" );
		sourceTypeDao.Save( type );
		
		List< Source >	sources	=	new	ArrayList< Source >();
		sources.add( getSource( "545564400", type ) );
		sources.add( getSource( "550709848", type ) );
		sources.add( getSource( "566811562", type ) );
		sources.add( getSource( "600102439", type ) );
		sources.add( getSource( "612500543", type ) );
		sources.add( getSource( "666663397", type ) );
		sources.add( getSource( "675325099", type ) );
		sources.add( getSource( "701874663", type ) );
		sources.add( getSource( "703710966", type ) );
		sources.add( getSource( "747618741", type ) );
		
		for( Source source : sources )
		{
			Source	dbSource	=	sourceDao.GetById( source.getId() );
			if( dbSource == null )
			{
				sourceDao.Save( source );
			}
		}
		
		return	sources;
	}
	
	private	Source	getSource( String id, SourceType type )
	{
		Source	source	=	new	Source( type );
		source.setId( id );
		return	source;
	}
	
	private	Iterable< ApplicationUserSource >	SaveAppUserSourcesAndReturn( Iterable< Source > sources , ApplicationUser appUser )
	{
		List< ApplicationUserSource >	appUserSources	=	new	ArrayList< ApplicationUserSource >();
		for( Source source : sources )
		{
			ApplicationUserSource	appUserSource	=	new	ApplicationUserSource();
			ApplicationUserSourceId	id	=	new	ApplicationUserSourceId();
			id.setAppUser( appUser );
			id.setSource( source );
			appUserSource.setId( id );
			
			ApplicationUserSource	dbAppUserSource	=	appUserSourceDao.GetById( id );
			if( dbAppUserSource == null )
			{
				appUserSourceDao.Save( appUserSource );
			}
			appUserSources.add( appUserSource );
		}
		return	appUserSources;
	}
}
