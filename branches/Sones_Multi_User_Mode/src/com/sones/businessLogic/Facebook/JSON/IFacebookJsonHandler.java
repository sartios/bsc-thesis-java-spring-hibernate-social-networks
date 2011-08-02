package com.sones.businessLogic.Facebook.JSON;

import java.util.Set;

import com.sones.businessLogic.Facebook.Comment;
import com.sones.businessLogic.Facebook.CommentList;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public interface IFacebookJsonHandler{
	public FacebookFriendList getFriends(final String jsonString);	
	public FacebookGroupList getGroups(final String jsonString);
	public Set<Feed> getFeeds(final String jsonString);
	public Feed getFeed(final String jsonString);	
	public FacebookFriend getFacebookUser(final String jsonString);
	public CommentList getAllComments(final String jsonString);
	public Comment getComment(final String jsonString,int index);
}
