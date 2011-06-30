

package proof;
import java.util.Map;

public class TaskHandler {

    private String Token;
    static private TaskHandler instance;
    private TaskHandler(String token){
        Token=token;
    }
    public static TaskHandler getInstance(String token){
        if(instance== null){
        instance = new TaskHandler(token);
        }
        return instance;
    }

    public void saveFriendFeeds()throws Exception{
          int i;
          String[] friendlist;
          friendlist=JsonHandler.ExtractIdsFromRest(RestHandler.getFriends(Token));

           for(i=0;i<friendlist.length;i++){
                FileHandler.writeFile("results/friendfeeds/"+friendlist[i],RestHandler.getWall(Token,friendlist[i]));
           }
          FileHandler.writeFile("results/friendfeeds/friendlist",friendlist);
    }
    public void saveSiteFeeds()throws Exception{
           int i;
           String[] sitelist;
           sitelist=JsonHandler.ExtractIdsFromRest(RestHandler.getPagesUserLikes(Token));
                   
           for(i=0;i<sitelist.length;i++){
            FileHandler.writeFile("results/sitefeeds/"+sitelist[i],RestHandler.getWall(Token,sitelist[i]));

           }
           FileHandler.writeFile("results/sitefeeds/sitelist",sitelist);
    }



    public void saveNews() throws Exception{
        FileHandler.writeFile("results/news",RestHandler.getNews(Token));
    }


    public Map getNews(){
        String result;
        result=RestHandler.getNews(Token);
        return JsonHandler.getFeed(result);


    }

    public Map loadFeedsFromFiles(){
    String[] siteList=null,friendList=null;
    Map<String,FeedObjects.FeedObject> localFeedMap=null;
            try{
    localFeedMap=JsonHandler.getFeed(FileHandler.readFile("results/news"));
    }catch(Exception fileException){ }
    for(int i=0;i<siteList.length;i++){
        try{
        siteList = FileHandler.getTableFromFile("results/sitefeeds/sitelist");
        localFeedMap.putAll(JsonHandler.getFeed(FileHandler.readFile("results/sitefeeds/"+siteList[i])));
        }catch(Exception fileException){ }
        }
      for(int i=0;i<friendList.length;i++){
            try{
          friendList = FileHandler.getTableFromFile("results/friendfeeds/friendlist");
          localFeedMap.putAll(JsonHandler.getFeed(FileHandler.readFile("results/friendfeeds/"+friendList[i])));
            }catch(Exception fileException){  }
      }

            
    return localFeedMap;
    }







}
