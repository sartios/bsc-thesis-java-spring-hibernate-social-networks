package com.sones.facebook.keywordSearcher.logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.hibernate.feed.HibernateLinkDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.facebook.keywordSearcher.logic.retriever.LinkSearchDataManager;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.Link;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearcherLogicForLinksTester	extends	AbstractKeywordSearcherLogicTester	implements	IKeywordSearcherServiceTester
{
	private ILinkDao linkDao;
	private	IKeywordSearcherService	searcherService;
	private	IDataRetriever	dataManager;
	private	final	String	MESSAGE	=	"Status Message ";
	
	public	KeywordSearcherLogicForLinksTester()
	{
		super();
		linkDao	=	( HibernateLinkDao )getContext().getBean( "linkDao" );
		searcherService	=	( KeywordSearcherService )getContext().getBean( "keywordSearcherService" );
		dataManager	=	( LinkSearchDataManager )getContext().getBean("linkDataRetriever");
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
		Iterable< FacebookPost >	posts	=	SaveLinksThatDoNotContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoLinks(appUser);
		
		List< FacebookPostKeywordResult >	dbResults	=	(List<FacebookPostKeywordResult>) getFacebookPostKeywordResultDao().GetByApplicationUser( appUser );
		
		assertEquals( 0 , dbResults.size() );
		
		KeywordSearch search	=	getKeywordSearchDao().GetLastInsertedEntity();
		DeleteResults( dbResults );
		DeleteFacebookPostDonwloads( facebookPostDownloads );
		DeleteFacebookPosts( posts );
		DeleteDownloads( downloads );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		DeletePosts(posts);
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
		Iterable< FacebookPost >	posts	=	SaveLinksThatSomeContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoLinks(appUser);
		
		List< FacebookPostKeywordResult >	dbResults	=	(List<FacebookPostKeywordResult>) getFacebookPostKeywordResultDao().GetByApplicationUser( appUser );
		
		assertEquals( 12 , dbResults.size() );
		
		KeywordSearch search	=	getKeywordSearchDao().GetLastInsertedEntity();
		DeleteResults( dbResults );
		DeleteFacebookPostDonwloads( facebookPostDownloads );
		DeleteFacebookPosts( posts );
		DeleteDownloads( downloads );
		DeleteUserKeywords( appUserKeywords );
		DeleteKeywords( keywords );
		DeletePosts(posts);
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
		Iterable< FacebookPost >	posts	=	SaveLinksThatContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoLinks(appUser);
		
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
		DeletePosts(posts);
		DeleteFacebookPosts(posts);
		getKeywordSearchDao().Delete( search );
		getApplicationUserDao().Delete(appUser);
	}
	
	private	void	DeletePosts( Iterable<FacebookPost> posts )
	{
		for( FacebookPost post : posts )
		{
			linkDao.Delete( ( Link )post );
		}
	}
	
	private	Iterable<FacebookPost>	SaveLinksThatContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 6; postIndex++ )
		{
			Link	post	=	new	Link();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE+ getKeywordValue() + postIndex );
			linkDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveLinksThatSomeContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 4; postIndex++ )
		{
			Link	post	=	new	Link();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + getKeywordValue() + postIndex );
			linkDao.Save( post );
			posts.add( post );
		}
		for( int postIndex = 4; postIndex < 9; postIndex++ )
		{
			Link	post	=	new	Link();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + postIndex );
			linkDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveLinksThatDoNotContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 6; postIndex++ )
		{
			Link	post	=	new	Link();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + postIndex );
			linkDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
}
