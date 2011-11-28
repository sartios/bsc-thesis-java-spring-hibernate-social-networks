package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.ILinkContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.dao.Facebook.Feed.Contents.IFacebookContentDao;
import com.sones.dao.Facebook.Feed.Contents.LinkContentDao;

public class LinkViewController	implements	ILinkViewController
{

	private	IFacebookContentDao	_dao;
	
	public	LinkViewController()
	{
		_dao	=	new	LinkContentDao();
	}
	
	@Override
	public ILinkContent GetLink(String feedID) 
	{
		ILinkContent	link	=	new LinkContent();
		if( null != feedID )
		{
			link = (ILinkContent) _dao.GetContent(feedID);
		}
		return	link;
	}

}
