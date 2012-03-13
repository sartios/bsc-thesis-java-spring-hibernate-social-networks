package com.sones.facebook.graphApi.FacebookRestHandler;

/**
 * Provides calls to graph API restful web service. 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookRestHandler 
{
	/**
	 * Calls the service that provides the user wall.
	 * @param sourceId
	 * @param token the access token.
	 * @return json string.
	 * @throws IllegalArgumentException when {@code sourceId} or {@code token} is null or empty.
	 */
	public	String	GetWall(String sourceId, String token);
}
