package com.sones.businessLogic.Facebook.JSON;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testingUtilities.FeedReader;

import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public class FacebookJSONHandlerForCompleteFeedsTest {

	private FacebookJSONHandlerForCompleteFeeds handler;
	
	@Before
	public void setUp(){
		handler = new FacebookJSONHandlerForCompleteFeeds();
	}
	
	@After
	public void tearDown(){
		handler = null;
	}
	
	@Test
	public void getFeed_StatusFromFile_Test(){
		String feedID = "100000866964787_206852752686955";
		String statusMessage = (new FeedReader()).getFacebookStatusMessage();
		Feed feed = handler.getFeed(statusMessage);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_VideoFromFile_Test(){
		String feedID = "100000866964787_211949868835890";
		String video = (new FeedReader()).getFacebookVideo();
		Feed feed = handler.getFeed(video);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_LinkFromFile_Test(){
		String feedID = "100000866964787_120642554686319";
		String link = (new FeedReader()).getFacebookLink();
		Feed feed = handler.getFeed(link);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_PhotoFromFile_Test(){
		String feedID = "100000866964787_207711595934404";
		String photo = (new FeedReader()).getFacebookPhoto();
		Feed feed = handler.getFeed(photo);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFriends_FriendsFromFile_Test(){
		String friendsString = (new FeedReader()).getFacebookFriends();
		FacebookFriendList friends = handler.getFriends(friendsString);
		assertFalse(friends.getFriendList().isEmpty());
	}
	
	@Test
	public void getGroups_GroupsFromFile_Test(){
		String groupsString = (new FeedReader()).getFacebookGroups();
		FacebookGroupList groups = handler.getGroups(groupsString);
		assertFalse(groups.getGroups().isEmpty());
	}
	
	@Test
	public void getFeeds_WallFromFile_Test(){
		String userWall = (new FeedReader()).getFacebookWall();
		Set feeds = handler.getFeeds(userWall);
		assertFalse(feeds.isEmpty());
	}
	
	@Test
	public void getFacebookUser_FacebookFriendFromFile_Test(){
		String facebookUser = (new FeedReader()).getFacebookUser();
		FacebookFriend friend = handler.getFacebookUser(facebookUser);
		assertEquals("100000866964787", friend.getId());
	}
	
	@Test
	public void getAllComments_CommentsFromFile_ListSize_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals(comments.getSize(), 2);
	}
	
	@Test
	public void getAllComments_CommentsFromFile_Ids_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getId_(), "100000866964787_206852752686955_2922754");
		assertEquals((comments.getComment(1)).getId_(), "100000866964787_206852752686955_2926662");
	}
	
	@Test
	public void getAllComments_CommentsFromFile_Message_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getMessage(), "Oppp arxisame??? :P");
		assertEquals((comments.getComment(1)).getMessage(), "xaxaxa nai nai! Douleuw to demo twra ki etsi !");
	}
	
	@Test
	public void getAllComments_CommentsFromFile_FeedID_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getFeedId_(), "100000866964787_206852752686955");
		assertEquals((comments.getComment(1)).getFeedId_(), "100000866964787_206852752686955");
	}
	
	@Test
	public void getComment_CommentsFromFile_Id_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getId_(), "100000866964787_206852752686955_2926662");
	}
	
	@Test
	public void getComment_CommentsFromFile_Message_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getMessage(), "xaxaxa nai nai! Douleuw to demo twra ki etsi !");
	}
	
	@Test
	public void getComment_CommentsFromFile_FeedId_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getFeedId_(), "100000866964787_206852752686955");
	}
	
	@Test
	public void getFeed_IncludingItsComments_FeedFromFile_Test(){
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Feed feed = handler.getFeed(feedInJson);
		assertTrue(feed.getComments_().getSize()==2);
	}
	
	@Test
	public void getFeeds_FeedsContainConcreteComments_Test(){
		FacebookFeedList feeds=new FacebookFeedList();
		feeds.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		assertNotNull(feeds.getFeed(1).getComments_().getComment(0).getId_());
	}
}
