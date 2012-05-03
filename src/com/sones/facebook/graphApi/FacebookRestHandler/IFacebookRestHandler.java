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

	/**
	 * Gets the wall feeds of the source that were published after the specified date.
	 * @param id
	 * @param value
	 * @param date
	 * @return wall feeds
	 */
	public	String	GetWall(String id, String value, String date);

	/**
	 * Returns a json string that contains the public places which agree on criteria.
	 * @param criteria the value of criteria to search
	 * @param token the value of the facebook token
	 * @throws IllegalArgumentException if criteria or token is empty.
	 */
	public String GetPublicPlaces(String criteria, String token);
	
	public String GetFriends(String accountId, String token);
}
