
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


/**
 * This class is responsible for twitter functions. Uses the twitter4j
 *  @see  twitter4j.*
 */
public class TwitterHandler {

    // Twitter connection object. Provided by library
    Twitter twitter;

    // The list with sources from which we'll collect data
    List<String> sources;

    // A variable to parametrize the query of searching in Twitter
    Paging paging;

    // This var will contain the most recent id of the publish
    long maxId=1;


    /**
     * Constructor with sources
     * @param Sources
     */
    TwitterHandler(List<String> Sources){

        // Instanciate the twitter object
        twitter = new TwitterFactory().getInstance();
        
        // Instanciate the sources
        sources=Sources;
        paging=new Paging();
    }

    /**
     * Constructor with sources and a twitter object
     * @param Sources
     * @param twit
     */
    TwitterHandler(List<String> Sources,Twitter twit){
        twitter = twit;
        sources=Sources;
        paging=new Paging();
    }

    /**
     * Works like the {@link #setPagingAt(Date aDate)} with the difference that
     * sets the date to NOW.
     */
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

    /**
     * Set the page into the given date, so twitter will collect data after this
     * date. Twitter and the twitter4j doesn't understand the searching using
     * dates, except "yyyy-MM-dd". It works with IDs. So it searches for bigger or smaller IDs.
     * @param aDate
     */
    public void setPagingAt(Date aDate){
        try {
            // How Twitter understands the date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // In this var we set the queries
            Query query = new Query();

            // The results we fetch from query
            QueryResult querRes;

            // We set the query to search for the keyword "a"
            query.setQuery("a");

            // We also set to search until the given date
            query.setUntil(sdf.format(aDate));

            // We fetch the results of the query
            querRes = twitter.search(query);

            // We get an ID which published sooner to the given date
            paging.setSinceId(this.bringClosestIdAt(aDate,querRes.getTweets()));
        } catch (TwitterException ex) {
            System.out.println(ex.getMessage());
        }
   }

    /**
     * It gets a Date and a List of tweets and returns the ID which was published
     * soonest to the date.
     * @param aDate "yyyy-MM-dd"
     * @param tweets the result of the method twitter.search(query)
     * @return ID
     */
    private long bringClosestIdAt(Date aDate,List<Tweet> tweets){

        long aDateInMls=aDate.getTime();
        long minDiference;
        long diference;
        long closestId;

        // We define the sooner positive (abs) distance from the 1st publish.
        minDiference=Math.abs(aDateInMls-tweets.get(0).getCreatedAt().getTime());

        // We get the ID from the 1st tweet
        closestId=tweets.get(0).getId();

        // For every tweet, calculate the positive distance and compare to find the smallest
        for(int i=1;i<tweets.size();i++){
            diference=Math.abs(aDateInMls-tweets.get(i).getCreatedAt().getTime());
            if(diference<minDiference){
                minDiference=diference;
                closestId=tweets.get(i).getId();
           }
       }
        return closestId;
   }

 
   /**
    * Reads the data from the sources, puts them in a map and return it
    * @return feeds from Twitter
    */
    public Map<String,FeedObjects.FeedObject> readSources(){

        // The feeds
        Map<String,FeedObjects.FeedObject> feedMap = new HashMap<String,FeedObjects.FeedObject>();

        /**
         * A list of Twitter Response which is a data interface that indicates
         * that rate limit status is available.
         */
        ResponseList responseList;

        /**
         * A data interface which represents the status of a user.
         */
        Status aStatus;
        
        FeedObjects.StatusObject aFeed;

        for(int i=0;i<sources.size();i++){
            try{
                // The id of the latest publish
                long SourceMaxId=1;

                // Gets the sooner posts of a user
                responseList=twitter.getUserTimeline(sources.get(i), paging);

                // For every status
                for(int j=0;j<responseList.size();j++){
                    
                    // We get the status
                    aStatus=(Status) responseList.get(j);
                    if(j==0){
                        SourceMaxId=aStatus.getId();
                    }
                    // We get the ID of the status
                    String id = String.valueOf(aStatus.getId());
                    // We create a feed status object
                    aFeed=(StatusObject) FeedObjects.feedFactory.getFeedClass("status",id);
                    // from twitter
                    aFeed.setSiteSource("twitter");
                    // we extract the message of this post
                    aFeed.setMessage(aStatus.getText());
                    // the name of the publisher
                    aFeed.setFromName(aStatus.getUser().getScreenName());
                    // his id
                    aFeed.setFromId(String.valueOf(aStatus.getUser().getId()));
                    // and the creation time
                    aFeed.setCreatedTime(aStatus.getCreatedAt());
                    // we add into the map
                    feedMap.put(id, aFeed);
                }

                // Check if a post if sooner than the post we already have read.
                if(SourceMaxId>maxId){
                    maxId=SourceMaxId;
                }
            }catch(Exception e){System.out.println(e.getMessage());}
        }

        // We set this parameter so we don't read again this data.
        paging.setSinceId(maxId);
        return feedMap;
    }
}
