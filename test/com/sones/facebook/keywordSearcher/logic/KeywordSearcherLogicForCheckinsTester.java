package com.sones.facebook.keywordSearcher.logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.dao.hibernate.feed.HibernateCheckinDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.logic.retriever.CheckinSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.ICheckinSearchDataManager;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearcherLogicForCheckinsTester	extends	AbstractKeywordSearcherLogicTester	implements	IKeywordSearcherServiceTester
{
	private ICheckinDao checkinDao;
	private	IKeywordSearcherService	searcherService;
	private	ICheckinSearchDataManager	dataManager;
	
	public	KeywordSearcherLogicForCheckinsTester()
	{
		super();
		checkinDao	=	( HibernateCheckinDao )getContext().getBean( "checkinDao" );
		searcherService	=	( KeywordSearcherService )getContext().getBean( "keywordSearcherService" );
		dataManager	=	( CheckinSearchDataManager )getContext().getBean("checkinDataRetriever");
		searcherService.addDataRetriever(dataManager);
	}

	@Before
	public	void	setUp()
	{
	
	}
	
	@Test
	@Override
	public	void	testSearchForKeywordsWhenThereAreNotPostsThatContainTheKeywords()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		Iterable<ApplicationUserKeyword > appUserKeywords	=	SaveUserKeywordsAndReturn( keywords, appUser );
		Iterable< FacebookPost >	posts	=	SaveCheckinsThatDoNotContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoCheckins(appUser);
		
		List< FacebookPostKeywordResult >	dbResults	=	(List<FacebookPostKeywordResult>) getFacebookPostKeywordResultDao().GetByApplicationUser( appUser );
		
		assertEquals( 0 , dbResults.size() );
		
		KeywordSearch search	=	getKeywordSearchDao().GetLastInsertedEntity();
		DeleteResults( dbResults );
		DeleteFacebookPostDonwloads( facebookPostDownloads );
		DeleteFacebookPosts( posts );
		DeleteDownloads( downloads );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		DeleteCheckins(posts);
		DeleteFacebookPosts(posts);
		getKeywordSearchDao().Delete( search );
		getApplicationUserDao().Delete(appUser);
	}
	
	@Test
	@Override
	public	void	testSearchForKeywordsWhenThereAreSomePostsThatContainTheKeywords()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		Iterable<ApplicationUserKeyword > appUserKeywords	=	SaveUserKeywordsAndReturn( keywords, appUser );
		Iterable< FacebookPost >	posts	=	SaveCheckinsThatSomeContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoCheckins(appUser);
		
		List< FacebookPostKeywordResult >	dbResults	=	(List<FacebookPostKeywordResult>) getFacebookPostKeywordResultDao().GetByApplicationUser( appUser );
		
		assertEquals( 12 , dbResults.size() );
		
		KeywordSearch search	=	getKeywordSearchDao().GetLastInsertedEntity();
		DeleteResults( dbResults );
		DeleteFacebookPostDonwloads( facebookPostDownloads );
		DeleteFacebookPosts( posts );
		DeleteDownloads( downloads );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		DeleteCheckins(posts);
		DeleteFacebookPosts(posts);
		getKeywordSearchDao().Delete( search );
		getApplicationUserDao().Delete(appUser);
	}
	
	@Test
	@Override
	public	void	testSearchForKeywordsWhenEveryPostContainsTheKeyword()
	{
		ApplicationUser	appUser	=	SaveUserAndReturn();
		Iterable< Keyword >	keywords	=	SaveKeywordsAndReturn();
		Iterable<ApplicationUserKeyword > appUserKeywords	=	SaveUserKeywordsAndReturn( keywords, appUser );
		Iterable< FacebookPost >	posts	=	SaveCheckinsThatContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoCheckins(appUser);
		
		List< FacebookPostKeywordResult >	dbResults	=	(List<FacebookPostKeywordResult>) getFacebookPostKeywordResultDao().GetByApplicationUser( appUser );
		
		assertEquals( 16 , dbResults.size() );
		
		String	searchId	=	dbResults.iterator().next().getSearch().getId();
		KeywordSearch search	=	getKeywordSearchDao().GetById(searchId);
		DeleteResults( dbResults );
		DeleteFacebookPostDonwloads( facebookPostDownloads );
		DeleteFacebookPosts( posts );
		DeleteDownloads( downloads );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		DeleteCheckins(posts);
		DeleteFacebookPosts(posts);
		getKeywordSearchDao().Delete( search );
		getApplicationUserDao().Delete(appUser);
	}
	
	private	void	DeleteCheckins( Iterable<FacebookPost> posts )
	{
		for( FacebookPost checkin : posts )
		{
			checkinDao.Delete( (Checkin)checkin );
		}
	}
	
	private	Iterable<FacebookPost>	SaveCheckinsThatContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int checkinIndex = 1; checkinIndex < 6; checkinIndex++ )
		{
			Checkin	checkin	=	new	Checkin();
			checkin.setId( String.valueOf( checkinIndex ) );
			checkin.setMessage("Checkin "+ getKeywordValue() + checkinIndex );
			checkinDao.Save( checkin );
			posts.add( checkin );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveCheckinsThatSomeContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int checkinIndex = 1; checkinIndex < 4; checkinIndex++ )
		{
			Checkin	checkin	=	new	Checkin();
			checkin.setId( String.valueOf( checkinIndex ) );
			checkin.setMessage("Checkin "+ getKeywordValue() + checkinIndex );
			checkinDao.Save( checkin );
			posts.add( checkin );
		}
		for( int checkinIndex = 4; checkinIndex < 9; checkinIndex++ )
		{
			Checkin	checkin	=	new	Checkin();
			checkin.setId( String.valueOf( checkinIndex ) );
			checkin.setMessage("Checkin " + checkinIndex );
			checkinDao.Save( checkin );
			posts.add( checkin );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveCheckinsThatDoNotContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int checkinIndex = 1; checkinIndex < 6; checkinIndex++ )
		{
			Checkin	checkin	=	new	Checkin();
			checkin.setId( String.valueOf( checkinIndex ) );
			checkin.setMessage("Checkin " + checkinIndex );
			checkinDao.Save( checkin );
			posts.add( checkin );
		}
		return	posts;
	}
}
