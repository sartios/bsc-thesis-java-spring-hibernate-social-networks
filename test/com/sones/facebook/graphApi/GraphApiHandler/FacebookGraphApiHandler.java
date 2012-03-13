package com.sones.facebook.graphApi.GraphApiHandler;

import org.apache.log4j.Logger;

import com.sones.FacebookJsonHandler.IFacebookJsonHandler;
import com.sones.facebook.graphApi.FacebookRestHandler.IFacebookRestHandler;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public class FacebookGraphApiHandler implements IFacebookGraphApiHandler
{

	private	final	Logger _logger = Logger.getLogger(FacebookGraphApiHandler.class);
	
	/**
	 * The restful web service handler.
	 */
	private	IFacebookRestHandler restHandler;

	/**
	 * The json string parser.
	 */
	private	IFacebookJsonHandler jsonHandler;
	
	public FacebookGraphApiHandler()
	{
		
	}
	
	@Override
	public Iterable<WallFacebookPostCreateDto> GetWallPosts(String sourceId,
			String token) {

		if(null == sourceId)
		{
			_logger.error("Source id can't be null");
			throw new IllegalArgumentException("The source id is null");
		}
		if(sourceId.isEmpty())
		{
			_logger.error("Source id can't be empty");
			throw new IllegalArgumentException("The source id is empty");
		}
		if(null==token)
		{
			_logger.error("Token can't be null");
			throw new IllegalArgumentException("The token is null");
		}
		if(token.isEmpty())
		{
			_logger.error("Token can't be empty");
			throw new IllegalArgumentException("The token is empty");
		}
		String jsonString	=	restHandler.GetWall(sourceId, token);
		return	jsonHandler.GetWallPosts(jsonString);
	}

	/**
	 * @param restHandler the restHandler to set
	 */
	public void setRestHandler(IFacebookRestHandler restHandler) {
		this.restHandler = restHandler;
	}

	/**
	 * @return the restHandler
	 */
	public IFacebookRestHandler getRestHandler() {
		return restHandler;
	}

	/**
	 * @param jsonHandler the jsonHandler to set
	 */
	public void setJsonHandler(IFacebookJsonHandler jsonHandler) {
		this.jsonHandler = jsonHandler;
	}

	/**
	 * @return the jsonHandler
	 */
	public IFacebookJsonHandler getJsonHandler() {
		return jsonHandler;
	}

}
