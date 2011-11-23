package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;

public interface IFacebookFeed {

	public	IFacebookFeedContent	GetContent();
	public	String	GetID();
	public	IFacebookComments	GetComments();
}
