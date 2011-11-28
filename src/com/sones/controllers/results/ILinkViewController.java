package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.ILinkContent;

public interface ILinkViewController 
{
	public	ILinkContent	GetLink( final String feedID);
}
