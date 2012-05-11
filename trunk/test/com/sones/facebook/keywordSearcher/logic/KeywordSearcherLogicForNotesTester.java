package com.sones.facebook.keywordSearcher.logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.dao.hibernate.feed.HibernateNoteDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.facebook.keywordSearcher.logic.retriever.NoteSearchDataManager;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.Note;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearcherLogicForNotesTester	extends	AbstractKeywordSearcherLogicTester	implements	IKeywordSearcherServiceTester
{
	private INoteDao noteDao;
	private	IKeywordSearcherService	searcherService;
	private	IDataRetriever	dataManager;
	private	final	String	MESSAGE	=	"Status Message ";
	private Date date = new Date(0);
	
	public	KeywordSearcherLogicForNotesTester()
	{
		super();
		noteDao	=	( HibernateNoteDao )getContext().getBean( "noteDao" );
		searcherService	=	( KeywordSearcherService )getContext().getBean( "keywordSearcherService" );
		dataManager	=	( NoteSearchDataManager )getContext().getBean("noteDataRetriever");
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
		Iterable< FacebookPost >	posts	=	SaveNotesThatDoNotContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoNotes(appUser, date);
		
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
		Iterable< FacebookPost >	posts	=	SaveNotesThatSomeContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoNotes(appUser, date);
		
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
		Iterable< FacebookPost >	posts	=	SaveNotesThatContainKeywordsAndReturn();
		Iterable< FacebookDownload >	downloads	=	SaveFacebookDownloadsAndReturn( appUser );
		List< FacebookPostDownload >	facebookPostDownloads	=	new	ArrayList<FacebookPostDownload>();
		for( FacebookDownload download : downloads )
		{
			List< FacebookPostDownload >	temp	=	(List<FacebookPostDownload>) SaveFacebookPostsPerDownloadAndReturn(posts, download);
			facebookPostDownloads.addAll( temp );
		}
		
		searcherService.searchForKeywordsIntoNotes(appUser, date);
		
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
			noteDao.Delete( ( Note )post );
		}
	}
	
	private	Iterable<FacebookPost>	SaveNotesThatContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 6; postIndex++ )
		{
			Note	post	=	new	Note();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE+ getKeywordValue() + postIndex );
			noteDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveNotesThatSomeContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 4; postIndex++ )
		{
			Note	post	=	new	Note();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + getKeywordValue() + postIndex );
			noteDao.Save( post );
			posts.add( post );
		}
		for( int postIndex = 4; postIndex < 9; postIndex++ )
		{
			Note	post	=	new	Note();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + postIndex );
			noteDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
	
	private	Iterable<FacebookPost>	SaveNotesThatDoNotContainKeywordsAndReturn()
	{
		List< FacebookPost >	posts	=	new	ArrayList<FacebookPost>();
		for( int postIndex = 1; postIndex < 6; postIndex++ )
		{
			Note	post	=	new	Note();
			post.setId( String.valueOf( postIndex ) );
			post.setMessage(MESSAGE + postIndex );
			noteDao.Save( post );
			posts.add( post );
		}
		return	posts;
	}
}
