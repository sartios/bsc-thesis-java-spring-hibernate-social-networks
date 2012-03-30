package com.sones.facebook.downloader.dao.hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class HibernateFacebookDownloadDaoTester 
{
	private	IFacebookDownloadDao	downloadDao;
	private	IApplicationUserDao	appUserDao;
	private	ApplicationContext	context;
	
	public	HibernateFacebookDownloadDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "FacebookDownloader/spring-facebook-downloader-nu.xml" );
		downloadDao	=	(HibernateFacebookDownloadDao)context.getBean( "facebookDownloadDao" );
		appUserDao	=	(HibernateApplicationUserDao)context.getBean( "appUserDao" );
	}
	
	@Test( expected = IllegalArgumentException.class)
	public	void	TestGetLastAppUserDownloadNullApplicationUser()
	{
		downloadDao.GetLastAppUserDownload( null );
	}
	
	@Test( expected = IllegalArgumentException.class)
	public	void	TestGetLastAppUserDownloadEmptyApplicationUserId()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		downloadDao.GetLastAppUserDownload( appUser );
	}
	
	@Test
	public	void	TestGetLastAppUserDownloadNoResults()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		appUser.setId( "aaa" );
		FacebookDownload	download	=	downloadDao.GetLastAppUserDownload( appUser );
		assertEquals( null , download );
	}
	
	@Test
	public	void	TestGetLastAppUserDownload()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		appUser.setId( "1" );
		appUserDao.Save( appUser );
		
		List< FacebookDownload >	downloads	=	new	ArrayList< FacebookDownload >();
		for( int downloadIndex = 0 ; downloadIndex < 25 ; downloadIndex++ )
		{
			FacebookDownload	download	=	new	FacebookDownload();
			download.setId( String.valueOf( downloadIndex ) );
			download.setAppUser( appUser );
			download.setDate( Calendar.getInstance().getTime() );
			downloadDao.Save( download );
			downloads.add( download );
			Sleep( 1000 );
		}
		
		FacebookDownload	dbDownload	=	downloadDao.GetLastAppUserDownload( appUser );
		FacebookDownload	lastDownload	=	downloads.get( downloads.size() - 1 );
		assertEquals( lastDownload.getId() , dbDownload.getId() );
		assertEquals( lastDownload.getAppUser() , dbDownload.getAppUser() );
		assertEquals( lastDownload.getDate().toGMTString() , dbDownload.getDate().toGMTString() );
		
		for( FacebookDownload tmpDownload : downloads )
		{
			downloadDao.Delete( tmpDownload );
		}
		appUserDao.Delete( appUser );
		
	}
	
	private	void	Sleep( long miliseconds )
	{
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
