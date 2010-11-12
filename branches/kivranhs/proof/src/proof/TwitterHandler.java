
package proof;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import proof.FeedObjects.StatusObject;
import twitter4j.*;

public class TwitterHandler {
Twitter twitter;
List<String> sources;
Paging paging;
long maxId=1;



        TwitterHandler(List<String> Sources){
             
           twitter = new TwitterFactory().getInstance();
           sources=Sources;
           paging=new Paging();
         

        }
            TwitterHandler(List<String> Sources,Twitter twit){

           twitter = twit;
           sources=Sources;
           paging=new Paging();


        }

       public void setPagingNow(){
        try {
            Query query = new Query();
            QueryResult querRes;
            query.setQuery("a");
            querRes = twitter.search(query);
            paging.setSinceId(querRes.getMaxId());
        } catch (TwitterException ex) {
            System.out.println(ex.getMessage());
        }
       }

       public void setPagingAt(Date aDate){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Query query = new Query();
            QueryResult querRes;
            query.setQuery("a");
            query.setUntil(sdf.format(aDate));
            querRes = twitter.search(query);
            paging.setSinceId(this.bringClosestIdAt(aDate,querRes.getTweets()));
        } catch (TwitterException ex) {
            System.out.println(ex.getMessage());
        }

       }

       private long bringClosestIdAt(Date aDate,List<Tweet> tweets){

           long aDateInMls=aDate.getTime(),minDiference,diference,closestId;

           minDiference=Math.abs(aDateInMls-tweets.get(0).getCreatedAt().getTime());
           closestId=tweets.get(0).getId();
           for(int i=1;i<tweets.size();i++){
               diference=Math.abs(aDateInMls-tweets.get(i).getCreatedAt().getTime());
               if(diference<minDiference){
                 minDiference=diference;
                 closestId=tweets.get(i).getId();
               }

           }

          return closestId;

       }

 

        public Map<String,FeedObjects.FeedObject> readSources(){
            Map<String,FeedObjects.FeedObject> feedMap = new HashMap<String,FeedObjects.FeedObject>();
            ResponseList responseList;
            Status aStatus;
            FeedObjects.StatusObject aFeed;
            

            for(int i=0;i<sources.size();i++){
                try{                  
                long SourceMaxId=1;
                    responseList=twitter.getUserTimeline(sources.get(i), paging);

                    for(int j=0;j<responseList.size();j++){
                        aStatus=(Status) responseList.get(j);
                        if(j==0){
                        SourceMaxId=aStatus.getId();
                        }
                        String id = String.valueOf(aStatus.getId());
                        aFeed=(StatusObject) FeedObjects.feedFactory.getFeedClass("status",id);
                        aFeed.setSiteSource("twitter");
                        aFeed.setMessage(aStatus.getText());
                        aFeed.setFromName(aStatus.getUser().getScreenName());
                        aFeed.setFromId(String.valueOf(aStatus.getUser().getId()));
                        aFeed.setCreatedTime(aStatus.getCreatedAt());
                        feedMap.put(id, aFeed);
                    }
                  if(SourceMaxId>maxId){
                      maxId=SourceMaxId;
                  }


                }catch(Exception e){System.out.println(e.getMessage());}
            }

            paging.setSinceId(maxId);
            return feedMap;

        }






}
