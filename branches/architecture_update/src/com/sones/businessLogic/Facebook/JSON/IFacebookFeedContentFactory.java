package com.sones.businessLogic.Facebook.JSON;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;

public interface IFacebookFeedContentFactory {
	
	public	IFacebookFeedContent	GetFeedContent(  final Object feedObject  );
}
