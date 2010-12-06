package com.sones.businessLogic.Facebook;

import java.util.List;

import com.sones.businessLogic.Http.PageHandler;
import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;

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
public class FacebookRestHandler {
	
	/**
	 * Gets the news feed
	 * @param token
	 * @param id
	 * @return feeds of the news feed page
	 */
	public FeedList getNewsFeeds(final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/me/home/?"+TOKEN);
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getWallFeeds(jsonString);
	}

    /**
     * Gets the wall of the user with the id who has the access token
     * @param token
     * @param id
     * @return feeds of the wall
     */
	public FeedList getWall(final String ID, final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/"+ID+"/feed/?"+TOKEN);
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getWallFeeds(jsonString);
	}
	
    /**
     * Gets the wall feeds from all the user's friends
     * @param token
     * @param id
     * @return feeds of the friend's walls
     */
	public FacebookFriendList getFriendList(final String ID, final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/"+ID+"/friends/?"+TOKEN);
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getFriends(jsonString);
	}
	
	/**
	 * Gets the groups that a user is member
	 * @param user ID
	 * @param token
	 * @return groups
	 */
	public FacebookGroupList getGroups(final String ID, final String TOKEN){
		String jsonString = doGet("https://graph.facebook.com/"+ID+"/groups/?"+TOKEN);
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		return jsonHandler.getGroups(jsonString);
	}
	
	private String doGet(final String link){
		StringBuffer content = new StringBuffer();
		PageHandler  handler = new PageHandler();
		handler.getPage(link,content);
		return content.toString();
	}
}
