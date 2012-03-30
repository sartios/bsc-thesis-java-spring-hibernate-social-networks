package com.sones.facebook.graphApi.GraphApiHandler;

import java.util.Date;

import org.apache.log4j.Logger;

import com.sones.facebook.datemanager.logic.IFacebookDateManager;
import com.sones.facebook.graphApi.FacebookRestHandler.IFacebookRestHandler;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.facebook.JsonHandler.IFacebookJsonHandler;
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
	
	/**
	 * The facebook date manager
	 */
	private	IFacebookDateManager	dateManager;
	
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
	
	@Override
	public Iterable< WallFacebookPostCreateDto > GetWallPostsAfterDate(Source source, FacebookToken token, Date date) 
	{
		if( source == null )
		{
			_logger.error("Source can't be null");
			throw new IllegalArgumentException("The source is null");
		}
		if(token == null )
		{
			_logger.error("Token can't be null");
			throw new IllegalArgumentException("The token is null");
		}
		if( date == null )
		{
			_logger.error("Date can't be null");
			throw new IllegalArgumentException("Date can't be null");
		}
		String	dateInGraphApiFormat	=	dateManager.getDateForGraphApiSearch(date);
		String	jsonString	=	restHandler.GetWall(source.getId(), token.getValue(), dateInGraphApiFormat );
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

	/**
	 * @return the dateManager
	 */
	public IFacebookDateManager getDateManager() {
		return dateManager;
	}

	/**
	 * @param dateManager the dateManager to set
	 */
	public void setDateManager(IFacebookDateManager dateManager) {
		this.dateManager = dateManager;
	}

}
