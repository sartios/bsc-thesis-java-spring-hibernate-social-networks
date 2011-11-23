package com.sones.businessLogic.Facebook.API;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeeds;

/**
 * Parser for facebook API call results
 * 
 * @author Sartios
 *
 */
public interface IFacebookAPIResultParser {

	/**
	 * Parses facebook API call results
	 * @param facebookAPICallResults comes from API calls
	 * @return Facebook feeds
	 */
	public	IFacebookFeeds	ParseFacebookFeeds( final String facebookAPICallResults );
}
