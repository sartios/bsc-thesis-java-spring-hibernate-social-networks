package com.sones.businessLogic.Facebook;

import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

import testingUtilities.FeedReader;


import static org.junit.Assert.*;


public class FacebookJsonHandlerTest {
	
	@Test
	public void getFeed_StatusFromFile_Test(){
		String feedID = "100000866964787_206852752686955";
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String statusMessage = (new FeedReader()).getFacebookStatusMessage();
		Feed feed = handler.getFeed(statusMessage);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_VideoFromFile_Test(){
		String feedID = "100000866964787_211949868835890";
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String video = (new FeedReader()).getFacebookVideo();
		Feed feed = handler.getFeed(video);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_LinkFromFile_Test(){
		String feedID = "100000866964787_120642554686319";
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String link = (new FeedReader()).getFacebookLink();
		Feed feed = handler.getFeed(link);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFeed_PhotoFromFile_Test(){
		String feedID = "100000866964787_207711595934404";
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String photo = (new FeedReader()).getFacebookPhoto();
		Feed feed = handler.getFeed(photo);
		assertEquals(feed.getId_(), feedID);
	}
	
	@Test
	public void getFriends_FriendsFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String friendsString = (new FeedReader()).getFacebookFriends();
		FacebookFriendList friends = handler.getFriends(friendsString);
		assertFalse(friends.getFriendList().isEmpty());
	}
	
	@Test
	public void getGroups_GroupsFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String groupsString = (new FeedReader()).getFacebookGroups();
		FacebookGroupList groups = handler.getGroups(groupsString);
		assertFalse(groups.getGroups().isEmpty());
	}
	
	@Test
	public void getFeeds_WallFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String userWall = (new FeedReader()).getFacebookWall();
		Set feeds = handler.getFeeds(userWall);
		assertFalse(feeds.isEmpty());
	}
	
	@Test
	public void getFacebookUser_FacebookFriendFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String facebookUser = (new FeedReader()).getFacebookUser();
		FacebookFriend friend = handler.getFacebookUser(facebookUser);
		assertEquals("100000866964787", friend.getId());
	}
	
	@Test
	public void getAllComments_CommentsFromFile_ListSize_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals(comments.getSize(), 2);
	}
	
	@Test
	public void getAllComments_CommentsFromFile_Ids_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getId_(), "100000866964787_206852752686955_2922754");
		assertEquals((comments.getComment(1)).getId_(), "100000866964787_206852752686955_2926662");
	}
	
	@Test
	public void getAllComments_CommentsFromFile_Message_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getMessage(), "Oppp arxisame??? :P");
		assertEquals((comments.getComment(1)).getMessage(), "xaxaxa nai nai! Douleuw to demo twra ki etsi !");
	}
	
	@Test
	public void getAllComments_CommentsFromFile_FeedID_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		CommentList comments = handler.getAllComments(feedInJson);
		assertEquals((comments.getComment(0)).getFeedId_(), "100000866964787_206852752686955");
		assertEquals((comments.getComment(1)).getFeedId_(), "100000866964787_206852752686955");
	}
	
	@Test
	public void getComment_CommentsFromFile_Id_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getId_(), "100000866964787_206852752686955_2926662");
	}
	
	@Test
	public void getComment_CommentsFromFile_Message_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getMessage(), "xaxaxa nai nai! Douleuw to demo twra ki etsi !");
	}
	
	@Test
	public void getComment_CommentsFromFile_FeedId_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Comment comment = handler.getComment(feedInJson,1);
		assertEquals(comment.getFeedId_(), "100000866964787_206852752686955");
	}
	
	@Test
	public void getFeed_IncludingItsComments_FeedFromFile_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		String feedInJson = (new FeedReader()).getFacebookStatusMessage();
		Feed feed = handler.getFeed(feedInJson);
		assertTrue(feed.getComments_().getSize()==2);
	}
	
	@Test
	public void getFeeds_FeedsContainConcreteComments_Test(){
		FacebookJsonHandler handler = new FacebookJsonHandler();
		FacebookFeedList feeds=new FacebookFeedList();
		feeds.setFeeds(handler.getFeeds(new FeedReader().getFacebookWall()));
		assertNotNull(feeds.getFeed(1).getComments_().getComment(0).getId_());
	}
}
