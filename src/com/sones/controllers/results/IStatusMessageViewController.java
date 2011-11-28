package com.sones.controllers.results;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookStatusMessage;

public interface IStatusMessageViewController 
{
	public	IFacebookStatusMessage	GetStatusMessage( String feedID );
}
