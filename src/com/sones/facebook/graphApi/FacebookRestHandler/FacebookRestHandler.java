package com.sones.facebook.graphApi.FacebookRestHandler;

import org.apache.log4j.Logger;

import com.sones.facebook.graphApi.FacebookRestHandler.PageHandler;

public class FacebookRestHandler implements IFacebookRestHandler
{

	private	final	Logger _logger = Logger.getLogger(FacebookRestHandler.class);
	
	public FacebookRestHandler()
	{
		
	}
	
	@Override
	public String GetWall(String sourceId, String token)
	{
		String jsonString="";
		if(null == sourceId)
		{
			_logger.error("Source id can't be null");
			throw new IllegalArgumentException("Source id is null");
		}
		if(sourceId.isEmpty())
		{
			_logger.error("Source id can't be empty");
			throw new IllegalArgumentException("Source id is empty");
		}
		if(null == token)
		{
			_logger.error("Token can't be null");
			throw new IllegalArgumentException("Token is null");
		}
		if(token.isEmpty())
		{
			_logger.error("Token can't be empty");
			throw new IllegalArgumentException("Token is empty");
		}
		return doGet("https://graph.facebook.com/"+sourceId+"/feed?"+token);
	}
	
	private String doGet(final String link){
		StringBuffer content = new StringBuffer();
		PageHandler  handler = new PageHandler();
		handler.getPage(link,content);
		return content.toString();
	}

}
