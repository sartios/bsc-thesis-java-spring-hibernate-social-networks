package com.sones.businessLogic.Facebook.FeedManager.CommentManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FacebookComments implements IFacebookComments
{
	private	List< IFacebookComment > 	_comments;
	
	public	FacebookComments()
	{
		_comments	=	new	ArrayList< IFacebookComment >();
	}

	@Override
	public boolean AddComment(IFacebookComment comment)
	{
		if( null != comment )
		{
			_comments.add( comment );
			return	true;
		}
		return	false;
	}

	@Override
	public Iterator<IFacebookComment> GetIterator() {
		return	_comments.iterator();
	}

	@Override
	public int GetSize() {
		return	_comments.size();
	}

	@Override
	public boolean IsEmpty() {
		return	_comments.isEmpty();
	}

	@Override
	public List<String> GetCommentIDs() {
		List< String > commentIDs	=	new	ArrayList< String >();
		Iterator< IFacebookComment > comment = GetIterator();
		for(;comment.hasNext();)
		{
			commentIDs.add( comment.next().GetID() );
		}
		return	commentIDs;
	}
	
	
}
