package com.sones.facebook.graphApi.jsonhandler;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.facebook.JsonHandler.FacebookJsonHandler;
import com.sones.facebook.JsonHandler.IFacebookJsonHandler;
import com.sones.facebook.placemanager.model.Place;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallCheckinCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallCommentCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallLinkCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallNoteCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallPhotoCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallStatusMessageCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallUserCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallVideoCreateDto;

import testingUtilities.FeedReader;

public class FacebookJsonHandlerTester 
{
	private	IFacebookJsonHandler handler;
	private	FeedReader reader = new FeedReader();
	private final Logger _LOGGER;
	
	public FacebookJsonHandlerTester()
	{
		_LOGGER = Logger.getLogger(FacebookJsonHandlerTester.class);
	}
	
	@Before
	public	void	setUp()
	{
		handler = new FacebookJsonHandler();
	}
	
	@After
	public	void	tearDown()
	{
		handler = null;
	}
	
	@Test
	public	void	TestGetWallPostNotNullCollection()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookWall());
		assertNotNull(posts);
	}
	
	@Test
	public	void	TestGetWallPostObjectNotNull()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookWall());
		assertNotNull(posts.iterator().next());
	}
	
	@Test
	public	void	TestGetWallPostObjectType()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookWall());
		assertEquals("Photo",posts.iterator().next().getType());
	}
	
	@Test
	public	void	TestGetWallPostSize()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookWall());
		assertEquals(25,((HashSet<WallFacebookPostCreateDto>)posts).size());
	}
	
	@Test
	public	void	TestGetWallPostVideo()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookVideo());
		Iterator<WallFacebookPostCreateDto> iterator = posts.iterator();
		WallVideoCreateDto video = (WallVideoCreateDto)(iterator.next());
		assertEquals("100000866964787_211949868835890",video.getId().getId());
		assertEquals("100000866964787",video.getUser().getId().getId());
		assertEquals("Skies & Kwstas Tournas ela hlie mou", video.getName());
		assertEquals("\u03c3\u03ba\u03b9\u03b5\u03c2 \u03ba\u03c9\u03c3\u03c4\u03b1\u03c2 \u03c4\u03bf\u03c5\u03c1\u03bd\u03b1\u03c2 \u03b5\u03bb\u03b1 \u03b7\u03bb\u03b9\u03b5 \u03bc\u03bf\u03c5,", video.getDescription());
		assertEquals("https://s-external.ak.fbcdn.net/safe_image.php?d=1211cc367da9292999f722fac5ddc963&w=130&h=130&url=http\u00253A\u00252F\u00252Fi3.ytimg.com\u00252Fvi\u00252FVY6U30pbNXM\u00252Fdefault.jpg", video.getPicture());
	}
	
	@Test
	public	void	TestGetWallPostVideoComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookVideo());
		Iterator<WallFacebookPostCreateDto> iterator = posts.iterator();
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) iterator.next().getComments();
		assertEquals(1, comments.size());
		assertEquals("100000866964787_220884114600511_3814425", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostStatusMessage()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookStatusMessage());
		WallStatusMessageCreateDto statusMessage = (WallStatusMessageCreateDto)posts.iterator().next();
		assertEquals("100000866964787_206852752686955", statusMessage.getId().getId());
		assertEquals("100000866964787",statusMessage.getUser().getId().getId());
		assertEquals("test", statusMessage.getMessage());
	}
	
	@Test
	public	void	TestGetWallPostStatusMessageComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookStatusMessage());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		assertEquals(2, comments.size());
		assertEquals("100000866964787_206852752686955_2922754", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostCheckin()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookCheckin());
		WallCheckinCreateDto checkin = (WallCheckinCreateDto)posts.iterator().next();
		assertEquals("100000866964787_340360402669522", checkin.getId().getId());
		assertEquals("100000866964787",checkin.getUser().getId().getId());
		assertEquals("somewhere", checkin.getMessage());
		assertEquals("181229008591952", checkin.getPlace().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostCheckinComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookCheckin());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		assertEquals(2, comments.size());
		assertEquals("100000866964787_340360402669522_4296545", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostLink()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookLink());
		WallLinkCreateDto link = (WallLinkCreateDto)posts.iterator().next();
		assertEquals("100000866964787_120642554686319", link.getId().getId());
		assertEquals("1310204556",link.getUser().getId().getId());
		assertEquals("Error @ link message","\u0391\u03c5\u03c4\u03cc \u03c0\u03bf\u03c5 \u03c3\u03c5\u03b6\u03b7\u03c4\u03bf\u03cd\u03c3\u03b1\u03bc\u03b5 \u03c4\u03b9\u03c2 \u03c0\u03c1\u03bf\u03ac\u03bb\u03bb\u03b5\u03c2 ! ", link.getMessage());
		assertEquals("Error @ link description","Link description",link.getDescription());
		assertEquals("Error @ link icon","https://s-static.ak.facebook.com/rsrc.php/v1/yD/r/aS8ecmYRys0.gif", link.getIcon());
		assertEquals("Error @ link link","http://www.tomahok.com/the-hood/yakuza", link.getLink());
		assertEquals("Error @ link name","Tomahok - YAKUZA", link.getName());
		assertEquals("Error @ link picture","https://s-external.ak.fbcdn.net/safe_image.php?d=84160ddd9cc591f4b4db0ad8f1cc1bcb&w=90&h=90&url=http\u00253A\u00252F\u00252Fwww.tomahok.com\u00252Fmedia\u00252Fresampled\u00252FarticleMenuElement\u00252F1029\u00252Fresampled_spanos.jpg", link.getPicture());
		assertEquals("Error @ link type","Link", link.getType());
	}
	
	@Test
	public	void	TestGetWallPostLinkComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookLink());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		assertEquals(2, comments.size());
		assertEquals("100000866964787_206852752686955_2922754", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostPhoto()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookPhoto());
		WallPhotoCreateDto photo = (WallPhotoCreateDto)posts.iterator().next();
		assertEquals("100000866964787_207711595934404", photo.getId().getId());
		assertEquals("100000866964787",photo.getUser().getId().getId());
		assertEquals("Error @ photo icon","https://s-static.ak.facebook.com/rsrc.php/v1/yz/r/StEh3RhPvjk.gif", photo.getIcon());
		assertEquals("Error @ photo link","https://www.facebook.com/photo.php?fbid=207711585934405&set=a.207711299267767.49956.100000866964787&type=1", photo.getLink());
		assertEquals("Error @ photo name","Wall Photos", photo.getName());
		assertEquals("Error @ photo picture","https://fbcdn-photos-a.akamaihd.net/hphotos-ak-snc6/246716_207711585934405_100000866964787_552607_5410588_s.jpg", photo.getPicture());
		assertEquals("Error @ photo type","Photo", photo.getType());
	}
	
	@Test
	public	void	TestGetWallPostPhotoComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookPhoto());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		assertEquals(1, comments.size());
		assertEquals("100000866964787_207711595934404_408074", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostNote()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookNote());
		WallNoteCreateDto note = (WallNoteCreateDto)posts.iterator().next();
		assertEquals("122788341354", note.getId().getId());
		assertEquals("6628568379",note.getUser().getId().getId());
		assertEquals("Error @ note subject","Facebook for iPhone 3.0", note.getSubject());
		assertEquals("Error @ note message","<div><span class=\"photo_right\"><img class=\"photo_img img\" src=\"https://fbcdn-photos-a.akamaihd.net/hphotos-ak-snc1/5650_98675398379_254285_a.jpg\" alt=\"\" /><span class=\"caption\"></span></span><span class=\"photo_right\"><img class=\"photo_img img\" src=\"https://fbcdn-photos-a.akamaihd.net/hphotos-ak-snc1/5650_98675293379_6628568379_2126292_547965_a.jpg\" alt=\"\" /><span class=\"caption\"></span></span>Facebook for iPhone 3.0 is coming very soon.  I can&#039;t predict an exact date when I will submit to Apple, but I can say that I am about 98\u0025 done.  So what&#039;s new in this update?<br /><br />1. The &quot;new&quot; News Feed<br />2. Like<br />3. Events (including the ability to RSVP)<br />4. Notes<br />5. Pages<br />6. Create new photo albums<br />7. Upload photos to any album<br />8. Zoom into photos<br />9. Easier photo tagging<br />10. Profile Pictures albums<br />11. A new home screen for easy access to all your stuff, search, and notifications<br />12. Add your favorite profiles and pages to the home screen<br />13. Better Notifications (they link to the comments so you can reply)<br />14. Quickly call or text people right from the Friends page<br />15. Messages you are typing will be restored if you quit or are interrupted by a phone call<br /><br />The one feature everyone is asking for, Push Notifications, is in development but it won&#039;t make it into 3.0.  You can expect it in a 3.1 update later this summer.<br /><br />When I have submitted the app to Apple I will let you all know!<br /><br />- Joe<br /></div>", note.getMessage());
		assertEquals("Error @ note type","Note", note.getType());
	}
	
	@Test
	public	void	TestGetWallPostNoteComments()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookNote());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		assertEquals(25, comments.size());
		assertEquals("122788341354_2769270", comments.iterator().next().getId().getId());
	}
	
	@Test
	public	void	TestGetWallPostFromSameUser()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getWallWithSameUserPosts());
		Iterator<WallFacebookPostCreateDto> iterator = posts.iterator();
		assertTrue("Iterator is empty", iterator.hasNext());
		for( ; iterator.hasNext(); )
		{
			WallUserCreateDto user = iterator.next().getUser();
			_LOGGER.warn("User id: " + user.getId().getId()); 
			assertNotNull(user);
		}
	}
	
	@Test
	public	void	TestGetWallPostCommentPost()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.getFacebookPhoto());
		HashSet<WallCommentCreateDto> comments = (HashSet<WallCommentCreateDto>) posts.iterator().next().getComments();
		Iterator<WallCommentCreateDto> iterator = comments.iterator();
		WallCommentCreateDto comment = iterator.next();
		for(;iterator.hasNext();comment = iterator.next())
		{
			assertEquals("100000866964787_207711595934404", comment.getPost().getId().getId());
		}
	}
	
	@Test
	public	void	TestGetWallPostGreekStatusMessage()
	{
		Iterable<WallFacebookPostCreateDto> posts = handler.GetWallPosts(reader.GetGreekStatusMessage());
		String	message	=	null;
		for( WallFacebookPostCreateDto post : posts )
		{
			WallUserCreateDto user = post.getUser();
			_LOGGER.warn("User id: " + user.getId().getId()); 
			assertNotNull(user);
			message	=	( ( WallStatusMessageCreateDto )post ).getMessage();
			_LOGGER.warn( message );
			
		}
		assertEquals( "Το tabli είναι σαν την μπιρίμπα. Αν δεν έχεις καλό ταίρι πρέπει να έχεις καλό χέρι." , message );
	}
	
	@Test
	public void TestGetPublicPlaces()
	{
		Iterable< Place > places = handler.GetPublicPlaces( reader.GetPublicPlaces() );
		assertFalse( ( ( Set ) places ).isEmpty() );
		assertEquals( 25,  ( ( Set ) places).size() );	
	}
}
