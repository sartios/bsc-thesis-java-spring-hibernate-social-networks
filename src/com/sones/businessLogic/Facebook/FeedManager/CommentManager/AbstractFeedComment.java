package com.sones.businessLogic.Facebook.FeedManager.CommentManager;

public abstract class AbstractFeedComment 
{
	private	String	_feedID;

	public void SetFeedID(String _feedID) {
		this._feedID = _feedID;
	}

	public String GetFeedID() {
		return _feedID;
	}
	
	
}
