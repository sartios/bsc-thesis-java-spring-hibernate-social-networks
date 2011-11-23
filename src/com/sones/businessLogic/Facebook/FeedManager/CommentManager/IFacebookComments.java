package com.sones.businessLogic.Facebook.FeedManager.CommentManager;

import java.util.Iterator;
import java.util.List;

public interface IFacebookComments {

	public	boolean	AddComment( IFacebookComment comment );
	public 	Iterator< IFacebookComment >	GetIterator();
	public	boolean	IsEmpty();
	public	int	GetSize();
	public	List< String >	GetCommentIDs();
}
