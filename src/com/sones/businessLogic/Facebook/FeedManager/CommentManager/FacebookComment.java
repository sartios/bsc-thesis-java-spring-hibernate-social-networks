package com.sones.businessLogic.Facebook.FeedManager.CommentManager;

public class FacebookComment	extends	AbstractFeedComment	implements	IFacebookComment
{
	private	String	_commentId;
	private	String	_creatorId;
	private	String	_message;
	
	public	FacebookComment()
	{
		
	}

	public void SetCommentId(String commentId) {
		if( null != commentId )
		{
			_commentId = commentId;
		}
	}

	public String GetCommentId() {
		return _commentId;
	}

	public void SetMessage(String message) {
		if( null != message )
		{
			_message = message;
		}
	}

	public String GetMessage() {
		return _message;
	}

	public void SetCreatorId(String creatorId) {
		if( null != creatorId)
		{
			_creatorId = creatorId;
		}
	}

	public String GetCreatorId() {
		return _creatorId;
	}

	@Override
	public String GetContent() {
		return	GetMessage();
	}

	@Override
	public String GetID() 
	{
		return	GetCommentId();
	}
}
