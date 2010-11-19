

package proof;


public class RestHandler {
static String result="",link;

       public static String getWall(String token,String id)
       {
        
       return  doGet(link="https://graph.facebook.com/"+id+"/feed?"+token);
       }
  

       public static String getFriends(String token)
       {
        return doGet(link="https://graph.facebook.com/me/friends?"+token);
       }

       public static String getNews(String token){
       return doGet("https://graph.facebook.com/me/home?"+token);
       }

       public static String getPagesUserLikes(String token){
       return doGet("https://graph.facebook.com/me/likes?"+token);
       }

       public static String searchForUser(String token,String username){
       return doGet("https://graph.facebook.com/search?q="+username+"&type=user&"+token);
       
       }

       public static String searchForEvent(String token,String event){
        return doGet("https://graph.facebook.com/search?q="+event+"&type=event&"+token);
       }
      public static String searchForPage(String token,String page){
        return doGet("https://graph.facebook.com/search?q="+page+"&type=page&"+token);
       }
       public static String searchForGroup(String token,String group){
        return doGet("https://graph.facebook.com/search?q="+group+"&type=group&"+token);
       }
       public static String searchPublicPosts(String token,String keyword){
           return doGet("https://graph.facebook.com/search?q="+keyword+"&type=post&"+token);
       }

      
        private static String doGet(String link)
        {
        StringBuffer content = new StringBuffer();
        PageHandler.getPage(link,content);
        result=content.toString();
        return result;
        }

}
