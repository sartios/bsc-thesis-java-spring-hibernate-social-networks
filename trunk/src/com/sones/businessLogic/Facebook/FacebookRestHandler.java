package com.sones.businessLogic.Facebook;


import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedFactory;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Http.PageHandler;
import com.sones.dao.TokenDao;

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
	
	private FacebookToken token_;
	
	public FacebookRestHandler(){
		TokenDao dao = new TokenDao();
		token_=(FacebookToken) dao.findAll().get(0);
	}
	
/*	*//**
	 * Gets the news feed
	 * @param token
	 * @param id
	 * @return feeds of the news feed page
	 *//*
	public FeedList getNewsFeeds(final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/me/home/?"+TOKEN);
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getWallFeeds(jsonString);
	}

    *//**
     * Gets the wall of the user with the id who has the access token
     * @param token
     * @param id
     * @return feeds of the wall
     *//*
	public FeedList getWall(final String ID, final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/"+ID+"/feed/?until=now&"+TOKEN);
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getWallFeeds(jsonString);
	}
	
    */
	/**
     * Gets the friends of the current user. It doesn't work by using the ID.
     * It loads from the database the access token and calls the graph api rest address.
     * The returned string is passed to a json handler,extracts the friends and puts them
     * into a list. The list is returned.
     * 
     * @return friends of the user
     */
	public FacebookFriendList getFriendList(){
		TokenDao dao = new TokenDao();
		FacebookToken token = (FacebookToken)dao.findAll().get(0);
		String jsonString=doGet("https://graph.facebook.com/me/friends/&"+token.getToken());
		FacebookJsonHandler jsonHanlder = new FacebookJsonHandler();
		return jsonHanlder.getFriends(jsonString);
	}
	
	/**
	 * Gets the groups that a user is member
	 * @param user ID
	 * @param token
	 * @return groups
	 */
	public FacebookGroupList getGroups(final String ID){
		TokenDao dao = new TokenDao();
		FacebookToken token = (FacebookToken)dao.findAll().get(0);
		String jsonString = doGet("https://graph.facebook.com/"+ID+"/groups/?"+token.getToken());
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		return jsonHandler.getGroups(jsonString);
	}
	
	/**
	 * Connects to the internet and downloads the feed with the specified ID
	 * @param ID
	 * @param TOKEN
	 * @return feed
	 *//*
	public Feed getFeed(final String ID,final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/"+ID+"?"+TOKEN);
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		return jsonHandler.getFeed(jsonString);
	}*/
	
	/**
	 * Connects to the internet and downloads the feeds' ID of the specifing userID
	 * @param token
	 * @param userID
	 * @return 
	 */
	public FeedList getFeeds(final String ID,final String TOKEN){
		String jsonString=doGet("https://graph.facebook.com/"+ID+"/feed&"+TOKEN);
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		FeedList feeds = new FeedList(ID, jsonHandler.getFeeds(jsonString));
		return feeds;
	}
	
	private String doGet(final String link){
		StringBuffer content = new StringBuffer();
		PageHandler  handler = new PageHandler();
		handler.getPage(link,content);
		return content.toString();
	}

	public Feed getFeed(final String FEED_ID,final String TOKEN) {
		String jsonString=doGet("https://graph.facebook.com/"+FEED_ID+"?"+TOKEN);
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		return jsonHandler.getFeed(jsonString);
		
	}
	
	public Feed getFeed(final String FEED_ID) {
		String jsonString=doGet("https://graph.facebook.com/"+FEED_ID+"?"+token_.getToken());
		FacebookJsonHandler jsonHandler = new FacebookJsonHandler();
		return jsonHandler.getFeed(jsonString);
		
	}
	
}
