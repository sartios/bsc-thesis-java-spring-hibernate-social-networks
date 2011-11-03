package com.sones.businessLogic.Facebook.Rest;


import org.apache.log4j.Logger;

import com.sones.buisinessLogic.Facebook.UserManager.IFacebookToken;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.DateManager.IFacebookDate;
import com.sones.businessLogic.Facebook.Rest.IFacebookRestHandler;
import com.sones.businessLogic.Http.PageHandler;


/**
 * This class implements the Graph Facebook Api.
 * This API is based in REST technology, one call for each data.
 * It needs a <b>token</b> to work.
 * 
 * <h2>How to get a token</h2>
 * 
 * Facebook uses <href src="http://tools.ietf.org/html/draft-ietf-oauth-v2-10">OAuth 2.0 Protocol </a>
 * for authentication and authorization. When a user authorizes your application it get access to user
 * facebook ID. By default, you have access only to the public data of user. If you want to have
 * access to more, you must ask for <href src="http://developers.facebook.com/docs/authentication/permissions"> extended permissions </a>.
 * 
 * <h2>Desktop Authorization</h2>
 *
 * Facebook's OAuth implementation does not include explicit desktop applicaiton support.
 * The basic authentication process for desktop app is :
 * <ul>
 * <li>Register your app to get an app ID and secret, <i>client_id</i> and <i>client_secret</i></li>
 * <li>Go to https://graph.facebook.com/oauth/authorize?
 * client_id=<b>Your app's client ID</b>&
 * redirect_uri=http://www.facebook.com/connect/login_success.html&
 * type=user_agent&
 * display=popup
 * </li>
 * <li>
 * After the user authorizes your app, he gets a URI like this :
 * http://www,facebook.com/connect.login_success.html#acces_token=...&expires_in=...
 * </li>
 * <li>
 * And in the end we can fetch data from graph by using :
 * https://graph.facebook.com/me?access_token=...
 * </li>
 * </ul>
 * 
 * 
 * @author Sartios
 *
 */
public class FacebookRestHandler implements IFacebookRestHandler{
	
	private	Logger	_logger;
	
	/**
	 * No arguments constructor
	 */
	public FacebookRestHandler()
	{
		_logger	=	Logger.getLogger( FacebookRestHandler.class );
	}	

	/**
     * Gets the friends of the current user. We can only get friends
     * from the user to whom belongs the token.
     * 
     * @param token
     * @return friends of the user
     */
	public String getFriendList(final String token){
		String jsonString=null;
		if(null!=token){
			jsonString=doGet("https://graph.facebook.com/me/friends/&"+token);
		}
		return jsonString;

		
	}
	
	/**
	 * Gets the groups that a user is member
	 * @param user ID
	 * @param token
	 * @return groups
	 */
	public String getGroupList(String userID, String token) {
		String jsonString="";
		if((null!=userID)&&(null!=token)){
			jsonString = doGet("https://graph.facebook.com/"+userID+"/groups/?"+token);
		}
		return jsonString;

	}

	
	/**
	 * Connects to the internet and downloads the feeds' ID of the specifing userID
	 * @param token
	 * @param userID
	 * @return 
	 */
	public String getFeeds(final String ID,final String TOKEN){
		String jsonString="";
		if((null!=ID)&&(null!=TOKEN)){
			jsonString=doGet("https://graph.facebook.com/"+ID+"/feed&"+TOKEN);
		}
		return jsonString;
	}
	
	private String doGet(final String link){
		StringBuffer content = new StringBuffer();
		PageHandler  handler = new PageHandler();
		handler.getPage(link,content);
		return content.toString();
	}
	
	public String getFeed(final String feedID,final String token) {
		String jsonString=null;
		if((null!=feedID)&&(null!=token)){
			jsonString=doGet("https://graph.facebook.com/"+feedID+"?"+token);
		}
		return jsonString;
	}
	
	public String getFacebookUser(final String userID,final String token){
		String jsonString=null;
		if(null!=userID){
			jsonString=doGet("https://graph.facebook.com/"+userID+"?"+token);
		}
		return jsonString;
	}


	/**
	 * This method makes a Facebook Graph Api call for getting posts from user's wall.
	 * The special thing in this method is that we can give a date and take the feeds
	 * after this date.
	
	 * @param facebookUserID ID of the user
	 * @param facebookToken application user's token
	 * @param facebookDate since when
	 * 
	 * @return FeedList
	 */
	@Override
	public	String	GetFeedsSinceDate( final IFacebookUserID facebookUserID, final	IFacebookToken facebookToken, final	IFacebookDate facebookDate)
	{
		_logger.warn( " Downloading feeds" );
		String	results=doGet("https://graph.facebook.com/"+facebookUserID.GetValue()+"/feed&since="+facebookDate.GetDateValue()+"?"+facebookToken.GetValue());
		if( true == results.isEmpty() )
		{
			_logger.error( " Facebook rest call returned empty string " );
		}
		return results;
	}	
}
