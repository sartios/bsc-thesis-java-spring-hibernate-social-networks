package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookStatusMessage;
import com.sones.businessLogic.Facebook.FeedManager.StatusMessageContent;
import com.sones.dao.Facebook.Feed.Contents.IFacebookContentDao;
import com.sones.dao.Facebook.Feed.Contents.StatusMessageContentDao;

public class StatusMessageViewController	implements	IStatusMessageViewController
{
	private	IFacebookContentDao	_dao;
	
	public	StatusMessageViewController()
	{
		_dao	=	new	StatusMessageContentDao();
	}

	@Override
	public IFacebookStatusMessage GetStatusMessage(String feedID)
	{
		IFacebookStatusMessage	statusMessage	=	new	StatusMessageContent();
		if( null != feedID )
		{
			statusMessage	=	(IFacebookStatusMessage) _dao.GetContent(feedID);
		}
		return	statusMessage;
	}

}
