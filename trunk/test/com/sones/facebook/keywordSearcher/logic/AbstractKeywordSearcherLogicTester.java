package com.sones.facebook.keywordSearcher.logic;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.hibernate.feed.HibernateFacebookPostDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.dao.hibernate.HibernateFacebookDownloadDao;
import com.sones.facebook.downloader.dao.hibernate.HibernateFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateKeywordDao;
import com.sones.facebook.keywordSearcher.dao.hibernate.HibernateKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.userManager.dao.IApplicationUserDao;
import com.sones.userManager.dao.hibernate.HibernateApplicationUserDao;
import com.sones.userManager.model.ApplicationUser;

public class AbstractKeywordSearcherLogicTester 
{
	private	final	String	KEYWORD_VALUE	=	"KeywordValue";
	private IApplicationUserDao appUserDao;
	private IKeywordDao keywordDao;
	private IApplicationUserKeywordDao appUserKeywordDao;
	private IFacebookDownloadDao downloadDao;
	private IFacebookPostDownloadDao postDownloadDao;
	private	ApplicationContext	context;
	private	IFacebookPostKeywordResultDao	keywordResultDao;
	private	IFacebookPostDao	postDao;
	private	IKeywordSearchDao	searchDao;
	
	public	AbstractKeywordSearcherLogicTester()
	{
		context	=	new	ClassPathXmlApplicationContext( "KeywordSearcher/spring-keywordSearcher-service-nu.xml" );
		appUserDao	=	( HibernateApplicationUserDao )context.getBean( "appUserDao" );
		keywordDao	=	( HibernateKeywordDao )context.getBean( "keywordDao" );
		appUserKeywordDao	=	( HibernateApplicationUserKeywordDao )context.getBean( "appUserKeywordDao" );
		downloadDao	=	( HibernateFacebookDownloadDao )context.getBean( "downloadDao" );
		postDownloadDao	=	( HibernateFacebookPostDownloadDao )context.getBean( "postDownloadDao" );
		//searcherService	=	( KeywordSearcherService )context.getBean( "keywordSearcherService" );
		keywordResultDao	=	( HibernateFacebookPostKeywordResultDao )context.getBean( "keywordSearchResultDao" );
		postDao	=	( HibernateFacebookPostDao )context.getBean( "facebookPostDao" );
		searchDao	=	( HibernateKeywordSearchDao )context.getBean( "keywordSearchDao" );
	}
	
	public	String	getKeywordValue()
	{
		return	KEYWORD_VALUE;
	}
	
	public	IFacebookPostKeywordResultDao	getFacebookPostKeywordResultDao()
	{
		return	keywordResultDao;
	}
	
	public	ApplicationContext	getContext()
	{
		return	context;
	}
	
	public	IKeywordSearchDao	getKeywordSearchDao()
	{
		return	searchDao;
	}
	
	public	IApplicationUserDao	getApplicationUserDao()
	{
		return	appUserDao;
	}
	
	public void DeleteFacebookPosts(Iterable<FacebookPost> posts) 
	{
		for( FacebookPost post : posts )
		{
			postDao.Delete( post );
		}
	}

	public	ApplicationUser	SaveUserAndReturn()
	{
		ApplicationUser	appUser	=	new	ApplicationUser();
		appUser.setId( "1" );
		appUserDao.Save( appUser );
		return	appUser;
	}
	
	public	Iterable< Keyword >	SaveKeywordsAndReturn()
	{
		List< Keyword >	keywords	=	new	ArrayList< Keyword >();
		for( int keywordIndex = 1 ; keywordIndex < 5 ; keywordIndex++ )
		{
			Keyword	keyword	=	new	Keyword();
			keyword.setId( String.valueOf( keywordIndex ) );
			keyword.setValue( KEYWORD_VALUE + keywordIndex );
			keywordDao.Save( keyword );
			keywords.add( keyword );
		}
		return	keywords;
	}
	
	public	Iterable< ApplicationUserKeyword >	SaveUserKeywordsAndReturn( Iterable< Keyword > keywords, ApplicationUser appUser )
	{
		List< ApplicationUserKeyword >	appUserKeywords	=	new	ArrayList<ApplicationUserKeyword>();
		for( Keyword keyword : keywords )
		{
			ApplicationUserKeyword	appUserKeyword	=	new	ApplicationUserKeyword( keyword, appUser );
			appUserKeywords.add( appUserKeyword );
			appUserKeywordDao.Save( appUserKeyword );
		}
		return	appUserKeywords;
	}
	
	public	void	DeleteUserKeywords( Iterable< ApplicationUserKeyword > keywords )
	{
		for( ApplicationUserKeyword keyword : keywords )
		{
			appUserKeywordDao.Delete( keyword );
		}
	}
	
	public	void	DeleteKeywords( Iterable< Keyword > keywords )
	{
		for( Keyword keyword : keywords )
		{
			keywordDao.Delete( keyword );
		}
	}
	
	public	Iterable< FacebookDownload >	SaveFacebookDownloadsAndReturn( ApplicationUser appUser )
	{
		List< FacebookDownload >	downloads	=	new	ArrayList<FacebookDownload>();
		for( int downloadIndex = 1; downloadIndex < 5; downloadIndex++ )
		{
			FacebookDownload	download	=	new	FacebookDownload();
			download.setId( String.valueOf( downloadIndex ) );
			download.setAppUser( appUser );
			Date	date	=	Calendar.getInstance().getTime();
			download.setDate( date );
			downloadDao.Save( download );
			downloads.add( download );
			Sleep(1000);
		}
		return	downloads;
	}
	
	public	Iterable< FacebookPostDownload >	SaveFacebookPostsPerDownloadAndReturn( Iterable< FacebookPost > posts, FacebookDownload download )
	{
		List< FacebookPostDownload >	postDownloads	=	new	ArrayList< FacebookPostDownload >();
		for( FacebookPost post : posts )
		{
			FacebookPostDownload	postDownload	=	new	FacebookPostDownload( post , download );
			postDownloadDao.Save( postDownload );
			postDownloads.add( postDownload );
		}
		return	postDownloads;
	}
	
	public	void	DeleteResults( Iterable< FacebookPostKeywordResult > results )
	{
		for( FacebookPostKeywordResult result : results )
		{
			keywordResultDao.Delete( result );
		}
	}
	
	public	void	DeleteFacebookPostDonwloads( Iterable< FacebookPostDownload > facebookPostDownloads )
	{
		for( FacebookPostDownload model	:	facebookPostDownloads )
		{
			postDownloadDao.Delete( model );
		}
	}
	
	public	void	DeleteDownloads( Iterable< FacebookDownload > downloads )
	{
		for( FacebookDownload model : downloads )
		{
			downloadDao.Delete( model );
		}
	}
	
	public	void	Sleep( long miliseconds )
	{
		try {
			Thread.sleep( miliseconds );
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
