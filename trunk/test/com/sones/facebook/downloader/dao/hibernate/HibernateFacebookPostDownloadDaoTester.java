package com.sones.facebook.downloader.dao.hibernate;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
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
		facebookPostDownloadDao	=	(HibernateFacebookPostDownloadDao)context.getBean("facebookPostDownloadDao");
		downloadDao	=	(HibernateFacebookDownloadDao)context.getBean("facebookDownloadDao");
		postDao	=	(HibernateFacebookPostDao)context.getBean("facebookPostDao");
		facebookUserDao	=	(HibernateUserDao)context.getBean("facebookUserDao");
		appUserDao	=	(HibernateApplicationUserDao)context.getBean("appUserDao");
	}
	
	@Before
	public	void	setUp()
	{
		facebookPostDownload	=	(FacebookPostDownload) context.getBean("facebookPostDownload");
		download	=	facebookPostDownload.getId().getDownload();
		post	=	facebookPostDownload.getId().getPost();
		post.setUser( null );
		appUser	=	download.getAppUser();
		saveIfNotExists(appUser, appUser.getId(), appUserDao);
		saveIfNotExists(download, download.getId(), downloadDao);
		saveIfNotExists(post, post.getId(), postDao);
		saveIfNotExists(facebookPostDownload, facebookPostDownload.getId(), facebookPostDownloadDao);
	}
	
	@After
	public void tearDown()
	{
		deleteIfExists(facebookPostDownload, facebookPostDownload.getId(), facebookPostDownloadDao);
		deleteIfExists(post, post.getId(), postDao);
		deleteIfExists(download, download.getId(), downloadDao);
		deleteIfExists(appUser, appUser.getId(), appUserDao);
	}
	
	@Test
	public	void	testSaveFacebookPostDownload()
	{				
		FacebookPostDownload dbModel = facebookPostDownloadDao.GetById( facebookPostDownload.getId() );
		assertNotNull("Object is null",dbModel);
		assertEquals(facebookPostDownload.getId(), dbModel.getId());
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
		Set<FacebookDownload> downloads	= (Set<FacebookDownload>) getDownloads(appUser);
		Set<FacebookPost>	posts	=	(Set<FacebookPost>) getFacebookPosts();
		Set<FacebookPostDownload>	facebookPostDownloads	=	(Set<FacebookPostDownload>) getFacebookPostDownloads(downloads, posts);
		
		Iterator< FacebookPostDownload >	iterator	=	facebookPostDownloads.iterator();
		iterator.next();
		Date	date	=	iterator.next().getId().getDownload().getDate();
		
		Set	resutls	=	(Set) facebookPostDownloadDao.getFacebookPostAfterDateByAppUser(date, appUser);
		assertEquals(6, resutls.size());
		
		deleteFacebookPostDownloads(facebookPostDownloads);
		deleteFacebookDownloads(downloads);
		deleteFacebookPosts(posts);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void GetInDownloadsNullDownloads()
	{
		facebookPostDownloadDao.GetInDownloads( null );
	}
	
	@Test
	public void GetInDownloadsCorrectly()
	{
		Set<FacebookDownload> downloads	= (Set<FacebookDownload>) getDownloads(appUser);
		Set<FacebookPost>	posts	=	(Set<FacebookPost>) getFacebookPosts();
		List<FacebookPostDownload>	facebookPostDownloads	=	(List<FacebookPostDownload>) getFacebookPostDownloads(downloads, posts);
		Set<FacebookDownload> filterDownloads = (Set<FacebookDownload>) getMaxDownloadsFrom(2,downloads);

		List	resutls	=	(List) facebookPostDownloadDao.GetInDownloads(filterDownloads);
		assertEquals(6, resutls.size());
		
		deleteFacebookPostDownloads(facebookPostDownloads);
		deleteFacebookDownloads(downloads);
		deleteFacebookPosts(posts);
	}
	
	private void deleteIfExists(Object object, Object id, IGenericDao dao)
	{
		if( dao.GetById(id) != null )
		{
			dao.Delete(object);
		}
	}
	
	private void saveIfNotExists(Object object, Object id, IGenericDao dao)
	{
		if( dao.GetById(id) == null )
		{
			dao.Save(object);
		}
	}
	
	private Iterable<FacebookDownload> getDownloads( ApplicationUser appUser )
	{
		Set<FacebookDownload> downloads	=	new	HashSet<FacebookDownload>();
		for( int index = 1; index < 6 ; index++ )
		{
			sleep(1000);
			FacebookDownload genDonwload = new FacebookDownload();
			genDonwload.setId( String.valueOf( index ) );
			genDonwload.setAppUser( appUser );
			genDonwload.setDate( Calendar.getInstance().getTime() );
			saveIfNotExists(genDonwload, genDonwload.getId(), downloadDao);
			downloads.add( genDonwload );
		}
		return downloads;
	}
	
	private void sleep( long milliseconds )
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private Iterable<FacebookPostDownload> getFacebookPostDownloads(Iterable<FacebookDownload> downloads, Iterable<FacebookPost> posts)
	{
		List<FacebookPostDownload> facebookPostDownloads = new ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			for( FacebookPost post : posts )
			{
				FacebookPostDownload	facebookPostDownload	=	new	FacebookPostDownload( post, download );
				saveIfNotExists(facebookPostDownload, facebookPostDownload.getId(), facebookPostDownloadDao);
				facebookPostDownloads.add( facebookPostDownload );
			}
		}
		return facebookPostDownloads;
	}
	
	private Iterable<FacebookPost> getFacebookPosts()
	{
		Set<FacebookPost>	posts	=	new	HashSet<FacebookPost>();
		for( int postIndex = 1; postIndex < 4; postIndex++ )
		{
			FacebookPost	post	=	new	FacebookPost();
			post.setId( String.valueOf( postIndex ) );
			saveIfNotExists(post, post.getId(), postDao);
			posts.add( post );
		}
		return posts;
	}
	
	private void deleteFacebookPosts( Iterable<FacebookPost> posts )
	{
		for( FacebookPost post : posts )
		{
			deleteIfExists(post, post.getId(),postDao);
		}
	}
	
	private void deleteFacebookDownloads( Iterable<FacebookDownload> downloads )
	{
		for( FacebookDownload download : downloads )
		{
			deleteIfExists(download, download.getId(),downloadDao);
		}
	}
	
	private void deleteFacebookPostDownloads( Iterable<FacebookPostDownload> postDownloads )
	{
		for( FacebookPostDownload postDownload : postDownloads )
		{
			deleteIfExists(postDownload, postDownload.getId(),facebookPostDownloadDao);
		}
	}
	
	private Iterable<FacebookDownload> getMaxDownloadsFrom( int maxNum , Iterable<FacebookDownload> downloads )
	{
		int count = 0;
		Set<FacebookDownload> filterDownloads = new HashSet<FacebookDownload>();
		for( FacebookDownload download : downloads )
		{
			filterDownloads.add(download);
			count++;
			if(count == maxNum)
			{
				break;
			}
		}
		return filterDownloads;
	}
}
