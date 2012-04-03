package com.sones.facebook.graphApi.GraphApiHandler;

import java.util.Date;

import org.apache.log4j.Logger;

import com.sones.facebook.datemanager.logic.IFacebookDateManager;
import com.sones.facebook.graphApi.FacebookRestHandler.IFacebookRestHandler;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.publicsource.model.Criteria;
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

		CheckNullability(sourceId,"Source id can't be null");
		CheckNullability(token,"Token can't be null");
		
		if(sourceId.isEmpty())
		{
			_logger.error("Source id can't be empty");
			throw new IllegalArgumentException("The source id is empty");
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
		CheckNullability(source,"Source can't be null");
		CheckNullability(token,"Token can't be null");
		CheckNullability(date,"Date can't be null");
		String	dateInGraphApiFormat	=	dateManager.getDateForGraphApiSearch(date);
		String	jsonString	=	restHandler.GetWall(source.getId(), token.getValue(), dateInGraphApiFormat );
		return	jsonHandler.GetWallPosts(jsonString);
	}
	
	@Override
	public Iterable<Place> GetPublicPlaces( Criteria criteria, FacebookToken token ) 
	{
		CheckNullability(criteria, "Criteria can't be null");
		CheckNullability(token, "Facebook token can't be null");
		String jsonString = restHandler.GetPublicPlaces( criteria.getValue() , token.getValue() );
		Iterable<Place> places = jsonHandler.GetPublicPlaces( jsonString );
		return places;
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
	
	/**
	 * Checks if the object is null.
	 * @param object the object to check if it is null.
	 * @param message the message to throw
	 * @throws IllegalArgumentException if the object is null.
	 */
	private	void	CheckNullability( Object object, String message )
	{
		if( object == null )
		{
			_logger.error( message );
			throw new IllegalArgumentException( message );
		}
	}

}
