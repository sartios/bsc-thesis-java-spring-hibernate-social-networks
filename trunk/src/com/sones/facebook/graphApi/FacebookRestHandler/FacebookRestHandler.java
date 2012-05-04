package com.sones.facebook.graphApi.FacebookRestHandler;

import org.apache.log4j.Logger;

import com.sones.facebook.graphApi.FacebookRestHandler.PageHandler;

public class FacebookRestHandler implements IFacebookRestHandler
{

	private	final	Logger _LOGGER = Logger.getLogger(FacebookRestHandler.class);
	
	public FacebookRestHandler()
	{
		
	}
	
	@Override
	public String GetWall(String sourceId, String token)
	{
		CheckSourceIdAndThrow( sourceId );
		CheckFacebookTokenAndThrow( token );
		return doGet( "https://graph.facebook.com/" + sourceId + "/feed?" + token );
	}

	@Override
	public String GetWall(String id, String value, String date)
	{
		CheckSourceIdAndThrow( id );
		CheckFacebookTokenAndThrow( value );
		CheckDateAndThrow( date );
		return doGet( "https://graph.facebook.com/" + id + "/feed?" + value + "&since=" + date );
	}
	
	
	@Override
	public String GetPublicPlaces(String criteria, String token) 
	{
		CheckNullOrEmpty( criteria , "Criteria can't be null or empty." );
		CheckNullOrEmpty( token , "Token can't be null or empty." );
		return doGet( "https://graph.facebook.com/search?q=" + criteria + "&type=place&" + token );
	}
	
	@Override
	public String GetFriends(String accountId, String token) 
	{
		CheckNullOrEmpty( accountId , "Account id can't be null or empty." );
		CheckNullOrEmpty( token , "Token can't be null or empty." );
		String jsonString = doGet( "https://graph.facebook.com/" + accountId + "/friends?" + token );
		return jsonString;
	}
	
	@Override
	public String GetFacebookAccount(String token)
	{
		CheckNullOrEmpty( token , "Token can't be null or empty." );
		String jsonString = doGet( "https://graph.facebook.com/fql?q=SELECT+uid,email,name+FROM+user+WHERE+uid=me()&" + token );
		return jsonString;
	}
	
	private void CheckDateAndThrow( String date ) 
	{
		if( date == null )
		{
			_LOGGER.error( "Date can't be null" );
			throw new IllegalArgumentException( "Date can't be null" );
		}
		if( date.isEmpty() )
		{
			_LOGGER.error( "Date can't be empty" );
			throw new IllegalArgumentException( "Date can't be empty" );
		}
	}

	private void CheckSourceIdAndThrow(String sourceId)
	{
		if(null == sourceId)
		{
			_LOGGER.error("Source id can't be null");
			throw new IllegalArgumentException("Source id is null");
		}
		if(sourceId.isEmpty())
		{
			_LOGGER.error("Source id can't be empty");
			throw new IllegalArgumentException("Source id is empty");
		}
	}
	

	private void CheckFacebookTokenAndThrow(String token) 
	{
		if(null == token)
		{
			_LOGGER.error("Token can't be null");
			throw new IllegalArgumentException("Token is null");
		}
		if(token.isEmpty())
		{
			_LOGGER.error("Token can't be empty");
			throw new IllegalArgumentException("Token is empty");
		}
	}
	
	private void CheckNullOrEmpty( String object, String message )
	{
		if( object == null )
		{
			_LOGGER.error( message );
			throw new IllegalArgumentException( message );
		}
		if( object.isEmpty() == true )
		{
			_LOGGER.error( message );
			throw new IllegalArgumentException( message );
		}
	}
	
	private String doGet(final String link){
		StringBuffer content = new StringBuffer();
		PageHandler  handler = new PageHandler();
		handler.getPage(link,content);
		return content.toString();
	}
}
