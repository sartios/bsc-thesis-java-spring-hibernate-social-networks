

package proof;

/**
 * This class implements the Graph API of Facebook.
 * It's very simple. One call for every different data.
 * To work, it needs a user token.
 *
 * <b><i>How to get this Token</i></b>
 *
 * Facebook uses <href src="http://tools.ietf.org/html/draft-ietf-oauth-v2-10">OAuth 2.0 Protocol </a>
 * for authentication and authorization.
 * When a user authorizes your application, your application gets access to the user's
 * Facebook ID. By default, your application can access all the general information
 * in a user's profile. If your app needs to access other parts of the user's profile
 * that maybe private, you must get <href src="http://developers.facebook.com/docs/authentication/permissions"> extended permissions </a>.
 *
 * <b>Desktop Authorization</b>
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
 */
public class RestHandler {
    static String result="",link;

    /**
     * Gets the wall of the user with the id who has the access token
     * @param token
     * @param id
     * @return
     */
    public static String getWall(String token,String id){
        return  doGet(link="https://graph.facebook.com/"+id+"/feed?"+token);
    }
  
    /**
     * Returns a JSON document for friends. For example,
     * <code>
     *  {
     *      "data" : [
     *          {
     *              "name" : "John Papadopoulos",
     *              "id"   : "0123456789"
     *          }
     *      ]
     *  }
     * </code>
     * @param token of the user
     * @return string in JSON format for friends
     */
    public static String getFriends(String token){
        return doGet(link="https://graph.facebook.com/me/friends?"+token);
   }

   /**
    * Returns a JSON document for feeds. An example,
    * {
    *   "data": [
    *   {
    *           "id": "0123456789_0123456789"
    *           "from":{
    *               "name"  :   "John Papadopoulos"
    *               "id"    :   "0123456789"
    *           },
    *           "message"   :   "This is a test"
    *           "actions"   :   [
    *               {   "name"  :   "Comment"
    *                   "link"  :   "http://www.facebook.com/0123456789/posts/1478523985"
    *               },
    *               {
    *                   "name"  :   "Like"
    *                   "link"  :   "http://www.facebook.com/0123456789/posts/1478523985
    *               }
    *           ],
    *           "type"  :   "status"
    *           "created_time": "2010-11-15T10:04:11+0000",
                "updated_time": "2010-11-15T10:04:11+0000"
    *       }
    *   ]
    * }
    * @param token
    * @return string in JSON format for news posted in wall
    */
   public static String getNews(String token){
       return doGet("https://graph.facebook.com/me/home?"+token);
   }

   /**
    * Returns a JSON document for Likes. An example
    * {
    *   "data"  :[
    *       {
    *           "name"          :   "Kick boxing Hellas"
    *           "category"      :   "Sports"
    *           "id"            :   "9876543210"
    *           "created_time"  :   "2010-10-24T20:53:36+0000"
    *       }
    *   ]
    * }
    * @param token
    * @return string in JSON format for pages that user likes
    */
   public static String getPagesUserLikes(String token){
       return doGet("https://graph.facebook.com/me/likes?"+token);
   }

   /**
    * Searches for the username
    * @param token
    * @param username
    * @return string in JSON format for founds with username
    */
   public static String searchForUser(String token,String username){
       return doGet("https://graph.facebook.com/search?q="+username+"&type=user&"+token);    
   }

   /**
    * Searches for events with the given event name
    * @param token
    * @param event
    * @return string in JSON format for the events
    */
   public static String searchForEvent(String token,String event){
        return doGet("https://graph.facebook.com/search?q="+event+"&type=event&"+token);
   }

   /**
    * Searches for pages using the given page word in their names
    * @param token
    * @param page
    * @return  string in JSON format for pages using the page word
    */
   public static String searchForPage(String token,String page){
        return doGet("https://graph.facebook.com/search?q="+page+"&type=page&"+token);
   }

    /**
    * Searches for goups using the given group word in their names
    * @param token
    * @param group
    * @return  string in JSON format for pages using the group word
    */
   public static String searchForGroup(String token,String group){
        return doGet("https://graph.facebook.com/search?q="+group+"&type=group&"+token);
   }

    /**
    * Searches for public posts using the given keyword in their names
    * @param token
    * @param page
    * @return  string in JSON format for the public posts
    */
   public static String searchPublicPosts(String token,String keyword){
           return doGet("https://graph.facebook.com/search?q="+keyword+"&type=post&"+token);
   }

    /**
     * Reads the content of the given link and returns it into a string
     * @param link
     * @return
     */
    private static String doGet(String link){
        StringBuffer content = new StringBuffer();
        PageHandler.getPage(link,content);
        result=content.toString();
        return result;
    }
}
