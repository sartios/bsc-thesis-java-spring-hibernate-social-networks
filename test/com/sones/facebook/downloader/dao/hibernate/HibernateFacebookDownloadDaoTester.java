package com.sones.facebook.downloader.dao.hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.usermanager.model.ApplicationUserPlaceCriteria;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

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
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetByAppUserAfterDateNullApplicationUser()
	{
		downloadDao.GetByAppUserAfterDate(null, Calendar.getInstance().getTime());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void TestGetByAppUserAfterDateNullDate()
	{
		downloadDao.GetByAppUserAfterDate(new ApplicationUser(), null);
	}
	
	@Test
	public void TestGetByAppUserAfterDateWhenUserHasNotDownloads()
	{
		ApplicationUser appUserWithoutDownloads = new ApplicationUser();
		appUserWithoutDownloads.setId( "00000" );
		SaveIfNotExists(appUserWithoutDownloads, appUserWithoutDownloads.getId(),appUserDao);
		
		Date date = Calendar.getInstance().getTime();
		List<FacebookDownload> results = (List<FacebookDownload>) downloadDao.GetByAppUserAfterDate(appUserWithoutDownloads, date);
		Iterable<FacebookDownload> downloads = SaveAndReturnDownloads(0, 10, appUserWithoutDownloads);

		assertEquals(0, results.size());

		DeleteDownloads(downloads);
		DeleteIfExists(appUserWithoutDownloads, appUserWithoutDownloads.getId(),appUserDao);
	}
	
	@Test
	public void TestGetByAppUserAfterDateWhenThereAreNotDownloadsAfterTheDate()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId( "1" );
		SaveIfNotExists(appUser, appUser.getId(),appUserDao);
		Iterable<FacebookDownload> downloads = SaveAndReturnDownloads(0, 10, appUser);
		
		Date date = Calendar.getInstance().getTime();
		List<FacebookDownload> results = (List<FacebookDownload>) downloadDao.GetByAppUserAfterDate(appUser, date);
		
		assertEquals(0, results.size());
		
		DeleteDownloads(downloads);
		DeleteIfExists(appUser, appUser.getId(),appUserDao);

	}
	
	@Test
	public void TestGetByAppUserAfterDateCorrectly()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId( "1" );
		SaveIfNotExists(appUser, appUser.getId(),appUserDao);
		Iterable<FacebookDownload> downloads = SaveAndReturnDownloads(0, 10, appUser);
		
		Date date = downloads.iterator().next().getDate();
		System.out.println(date);
		List<FacebookDownload> results = (List<FacebookDownload>) downloadDao.GetByAppUserAfterDate(appUser, date);
		
		assertEquals(10, results.size());
		
		DeleteDownloads(downloads);
		DeleteIfExists(appUser, appUser.getId(),appUserDao);
	}
	
	private Iterable<FacebookDownload> SaveAndReturnDownloads( int firstId, int maxDownloads, ApplicationUser appUser )
	{
		List<FacebookDownload> downloads = new ArrayList<FacebookDownload>();
		for(int downloadIndex = firstId; downloadIndex < maxDownloads + firstId; downloadIndex++)
		{
			Date date = Calendar.getInstance().getTime();
			FacebookDownload download = new FacebookDownload(appUser,date);
			download.setId( String.valueOf( downloadIndex ) );
			downloads.add( download );
			SaveIfNotExists(download , download.getId() , downloadDao );
			Sleep(1000);
		}
		return downloads;
	}
	
	private void SaveIfNotExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void DeleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
	
	private void DeleteDownloads(Iterable<FacebookDownload> downloads)
	{
		for(FacebookDownload download : downloads)
		{
			DeleteIfExists(download, download.getId(), downloadDao);
		}
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
