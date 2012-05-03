package com.sones.facebook.graphApi.GraphApiHandler;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public class FacebookGraphApiHandlerTester 
{
	private	ApplicationContext context;
	private	IFacebookGraphApiHandler handler;
	private	String	sourceId;
	private	String	token;
	
	public FacebookGraphApiHandlerTester()
	{
		context = new ClassPathXmlApplicationContext("spring-facebookGraphApi.xml");
		handler = (FacebookGraphApiHandler) context.getBean("graphApi");
		sourceId = "100000866964787";
		token = "access_token=AAACV6ZAIZClUQBAIUWj1gLZCZCFmkFugI4MLsBIv5MbBybzYTVTmFeWzd4LVQflYG9s50RA3GcOE771VVGe8HrMT7jHZBuNqCC0F4Ppc4CQZDZD";
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsNullSource()
	{
		handler.GetWallPosts(null, "a token");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsEmptySource()
	{
		handler.GetWallPosts("", "a token");
	}
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsNullToken()
	{
		handler.GetWallPosts("a source", null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetWallPostsEmptyToken()
	{
		handler.GetWallPosts("a source", "");
	}
	
	@Test
	public void TestGetWallPostsSize()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(sourceId, token);
		assertEquals("Error @ post number",25, ((Set<WallFacebookPostCreateDto>)posts).size());
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	TestGetWallPostsAfterDateNullSource()
	{
		Date	date	=	Calendar.getInstance().getTime();
		FacebookToken	token	=	 new FacebookToken();
		handler.GetWallPostsAfterDate( null, token, date  );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	TestGetWallPostsAfterDateNullToken()
	{
		Date	date	=	Calendar.getInstance().getTime();
		Source	source	=	 new Source();
		handler.GetWallPostsAfterDate( source, null, date  );
	}
	
	@Test( expected = IllegalArgumentException.class )
	public	void	TestGetWallPostsAfterDateNullDate()
	{
		FacebookToken	token	=	 new FacebookToken();
		Source	source	=	 new Source();
		handler.GetWallPostsAfterDate( source, token, null  );
	}
	
	@Test
	public	void	TestGetWallPostsAfterDate()
	{
		FacebookToken	facebookToken	=	 new FacebookToken();
		facebookToken.setValue( token );
		Source	source	=	 new Source();
		source.setId( sourceId );
		Date	date	=	Calendar.getInstance().getTime();
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPostsAfterDate( source, facebookToken, date  );
		assertEquals("Error @ post number",25, ((Set<WallFacebookPostCreateDto>)posts).size());
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void TestGetPublicPlacesNullCriteria()
	{
		handler.GetPublicPlaces(null, new FacebookToken());
	}
	
	@Test( expected = IllegalArgumentException.class )
	public void TestGetPublicPlacesNullToken()
	{
		handler.GetPublicPlaces(new Criteria(), null);
	}
	
	@Test
	public void TestGetPublicPlaces()
	{
		Criteria criteria = new Criteria( "party" );
		FacebookToken facebookToken = new FacebookToken();
		facebookToken.setValue( "access_token=AAACV6ZAIZClUQBAIUWj1gLZCZCFmkFugI4MLsBIv5MbBybzYTVTmFeWzd4LVQflYG9s50RA3GcOE771VVGe8HrMT7jHZBuNqCC0F4Ppc4CQZDZD" );
		Iterable< Place > places = handler.GetPublicPlaces(criteria, facebookToken);
		assertNotNull( places );
		assertTrue( ((Set)places).size() > 0 );
	}
	
	@Test
	public void TestGetFacebookFriendsCorrectly()
	{
		FacebookToken token = new FacebookToken();
		token.setValue("access_token=AAACV6ZAIZClUQBAIUWj1gLZCZCFmkFugI4MLsBIv5MbBybzYTVTmFeWzd4LVQflYG9s50RA3GcOE771VVGe8HrMT7jHZBuNqCC0F4Ppc4CQZDZD");
		
		FacebookAccount account = new FacebookAccount();
		account.setId(sourceId);
		
		Iterable<FacebookFriend> friends = handler.GetFacebookFriends(account, token);
		FacebookFriend friend = null;
		for(FacebookFriend temp : friends)
		{
			friend = temp;
			break;
		}
		
		int count = 0;
		for(FacebookFriend temp : friends)
		{
			count++;
		}
		assertEquals(538, count);
		assertEquals("Chrysovalantis Nikolaou", friend.getName());
		assertEquals("502894293", friend.getId());
	}
}
