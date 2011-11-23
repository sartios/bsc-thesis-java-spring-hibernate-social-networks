package com.sones.businessLogic.Facebook.JSON;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;

public interface IFacebookFeedCommentsFactory 
{
	public	IFacebookComments	GetComments( final Object feedObject );
}
