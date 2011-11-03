package com.sones.dao.Facebook.Feed.Comments;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;

public interface IFacebookCommentDao 
{
	public	boolean	SaveComment( final IFacebookComment comment );
	public	boolean	SaveComments( final IFacebookComments comments );
	
	public	IFacebookComment	GetComment( final String commentID );
	
	public	IFacebookComments	GetComments( final String feedID );
}
