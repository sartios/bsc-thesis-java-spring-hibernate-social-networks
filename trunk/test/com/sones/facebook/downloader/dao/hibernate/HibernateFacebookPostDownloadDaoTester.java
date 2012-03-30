package com.sones.facebook.downloader.dao.hibernate;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.hibernate.feed.HibernateFacebookPostDao;
import com.sones.facebook.dao.hibernate.source.HibernateUserDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.source.User;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class HibernateFacebookPostDownloadDaoTester 
{
	/**
	 * The facebook post download is the main object under test.
	 */
	private	FacebookPostDownload	facebookPostDownload;
	
	/**
	 * The facebook download.
	 */
	private	FacebookDownload	download;
	
	/**
	 * The post that were donwloaded during download session.
	 */
	private	FacebookPost	post;
	
	/**
	 * The facebook post download dao.
	 */
	private	IFacebookPostDownloadDao	facebookPostDownloadDao;
	
	/**
	 * The facebook download dao.
	 */
	private	IFacebookDownloadDao	downloadDao;
	
	/**
	 * The facebook post dao.
	 */
	private	IFacebookPostDao	postDao;
	
	/**
	 * The facebook user dao.
	 */
	private	IUserDao	facebookUserDao;
	
	/**
	 * The spring application context provider.
	 */
	private	ApplicationContext	context;
	
	/**
	 * The application user for who the download ran.
	 */
	private	ApplicationUser	appUser;
	
	/**
	 * The application user dao.
	 */
	private	IApplicationUserDao	appUserDao;
	
	/**
	 * Initializes the object and the application context provider.
	 */
	public	HibernateFacebookPostDownloadDaoTester()
	{
		context	=	new	ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-downloader-nu.xml");
	}
	
	@Before
	public	void	setUp()
	{
		facebookPostDownload	=	(FacebookPostDownload) context.getBean("facebookPostDownload");
		
		facebookPostDownloadDao	=	(HibernateFacebookPostDownloadDao)context.getBean("facebookPostDownloadDao");
		downloadDao	=	(HibernateFacebookDownloadDao)context.getBean("facebookDownloadDao");
		postDao	=	(HibernateFacebookPostDao)context.getBean("facebookPostDao");
		facebookUserDao	=	(HibernateUserDao)context.getBean("facebookUserDao");
		appUserDao	=	(HibernateApplicationUserDao)context.getBean("appUserDao");
	}
	
	@After
	public	void	tearDown()
	{
		
	}
	
	@Test
	public	void	testSaveFacebookPostDownload()
	{
		download	=	facebookPostDownload.getId().getDownload();
		post	=	facebookPostDownload.getId().getPost();
		
		appUser	=	download.getAppUser();
		appUserDao.Save( appUser );
		
		downloadDao.Save( download );
		
		facebookUserDao.Save( post.getUser() );
		postDao.Save( post );
		
		facebookPostDownloadDao.Save( facebookPostDownload );
		
		FacebookPostDownload dbModel = facebookPostDownloadDao.GetById( facebookPostDownload.getId() );
		
		assertNotNull("Object is null",dbModel);
		assertEquals(facebookPostDownload.getId(), dbModel.getId());
		
		facebookPostDownloadDao.Delete( facebookPostDownload );
		postDao.Delete( post );
		facebookUserDao.Delete( post.getUser() );
		downloadDao.Delete( download );
		appUserDao.Delete( appUser );
	}
	
	@Test( expected=IllegalArgumentException.class )
	public	void	getFacebookPostAfterDateByAppUser_NullAppUser()
	{
		facebookPostDownloadDao.getFacebookPostAfterDateByAppUser( new Date(), null );
	}
	
	@Test( expected=IllegalArgumentException.class )
	public	void	getFacebookPostAfterDateByAppUser_NullDate()
	{
		facebookPostDownloadDao.getFacebookPostAfterDateByAppUser( null, new ApplicationUser() );
	}
	
	@Test
	public	void	getFacebookPostAfterDateByAppUser()
	{
		ApplicationUser	genAppUser	=	new	ApplicationUser();
		genAppUser.setId( "1" );
		appUserDao.Save( genAppUser );
		Set<FacebookDownload> downloads	=	new	HashSet<FacebookDownload>();
		for( int index = 1; index < 4 ; index++ )
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			FacebookDownload genDonwload = new FacebookDownload();
			genDonwload.setId( String.valueOf( index ) );
			genDonwload.setAppUser( genAppUser );
			genDonwload.setDate( Calendar.getInstance().getTime() );
			downloadDao.Save( genDonwload );
			downloads.add( genDonwload );
		}
		User	genFacebookUser	=	new	User();
		genFacebookUser.setId( "1" );
		facebookUserDao.Save( genFacebookUser );
		Set<FacebookPostDownload>	facebookPostDownloads	=	new	HashSet<FacebookPostDownload>();
		Set<FacebookPost>	posts	=	new	HashSet<FacebookPost>();
		for( int postIndex = 1; postIndex < 4; postIndex++ )
		{
			FacebookPost	post	=	new	FacebookPost();
			post.setId( String.valueOf( postIndex ) );
			post.setUser( genFacebookUser );
			postDao.Save( post );
			posts.add( post );
		}
		for( FacebookDownload download : downloads )
		{
			for( FacebookPost post : posts )
			{
				FacebookPostDownload	facebookPostDownload	=	new	FacebookPostDownload( post, download );
				facebookPostDownloadDao.Save( facebookPostDownload );
				facebookPostDownloads.add( facebookPostDownload );
			}
		}
		
		Iterator< FacebookPostDownload >	iterator	=	facebookPostDownloads.iterator();
		iterator.next();
		Date	date	=	iterator.next().getId().getDownload().getDate();
		
		Set	resutls	=	(Set) facebookPostDownloadDao.getFacebookPostAfterDateByAppUser(date, genAppUser);
		assertEquals(6, resutls.size());
		
		for( FacebookPostDownload facebookPostDownload : facebookPostDownloads )
		{
			facebookPostDownloadDao.Delete( facebookPostDownload );
		}
		for( FacebookDownload download : downloads )
		{
			downloadDao.Delete( download );
		}
		for( FacebookPost post : posts )
		{
			postDao.Delete( post );
		}
		appUserDao.Delete( genAppUser );
		facebookUserDao.Delete( genFacebookUser );
	}
}
