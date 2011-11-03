package com.sones.businessLogic.Facebook.JSON;


import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;

/**
 * Parses a json string from facebook API calls
 * and translates it into concrete application objects
 * 
 * @author Sartios
 *
 */
public interface IFacebookJsonHandler{

	/**
	 * Parses jsonString and translates it into application {@link IFacebookFeeds}
	 * @param jsonString
	 * @return {@link IFacebookFeeds} from json string
	 */
	public IFacebookFeeds GetFeeds( final String jsonString );
}
