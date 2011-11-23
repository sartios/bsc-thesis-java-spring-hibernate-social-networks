package com.sones.businessLogic.Facebook.API;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;
import com.sones.businessLogic.Facebook.JSON.FacebookJSONHandler;
import com.sones.businessLogic.Facebook.JSON.IFacebookJsonHandler;

public class FacebookJsonParser implements IFacebookAPIResultParser{

	private	IFacebookJsonHandler	_jsonHandler;
	
	public FacebookJsonParser()
	{
		JsonHandlerIsNull();
	}
	
	public	FacebookJsonParser( final	IFacebookJsonHandler	jsonHandler )
	{
		if( null == jsonHandler )
		{
			JsonHandlerIsNull();
		}
		_jsonHandler	=	jsonHandler;
	}
	
	@Override
	public IFacebookFeeds ParseFacebookFeeds(String facebookAPICallResults) {
		return	_jsonHandler.GetFeeds(facebookAPICallResults);
	}

	private	void	JsonHandlerIsNull()
	{
		String	exceptionMessage	=	"Json handler can't be null";
		if( null == _jsonHandler )
		{
			throw	new	NullPointerException(exceptionMessage);
		}
	}
}
