

package proof;


import java.util.Calendar;
import java.util.HashMap;


import java.util.Map;
import java.util.TimeZone;
import proof.Searcher.keywordInfo;

/**
 * This class is the kernel of the application. Selects the informations
 * from the social networks and then searching in them for the keywords.
 * In addition, calculates the statistics
 */
public class monitorEngine {

    // Users settings
    private monitorSettings settings;

    // The feeds which collected
    private Map<String,FeedObjects.FeedObject> FeedMap= new HashMap<String,FeedObjects.FeedObject>();

    // Contains the informations from the previous searches
    private Map<String,Map<String,FeedObjects.FeedObject>> resultsMap = new HashMap<String,Map<String,FeedObjects.FeedObject>>();

    // Contains the statistics for the keywords and their combos
    Map<String,keywordInfo> keywordStats = new HashMap<String,keywordInfo>();

    // The time of the last search
    private  long lastCollect=0;

    // The amount of searches
    private int searchesPerformed=0;

    // Searches for keywords into the informations
    Searcher searcher;
    TwitterHandler twitterHandler;


public monitorEngine(monitorSettings set){
    settings=set;

    // If the user has set a time for the first search, then we set this time as
    // data collecting time
    if(set.firstSearch){
        lastCollect=set.firstSearchDate.getTime()/1000;
    }

    // We define the keywords into searcher
    try{
        searcher=new Searcher(settings.keys);
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    searcher.setSearchComments(settings.searchComments);

    // If the user has been authenticated
    if(settings.authTwitter==true){

        // The we create a twitter handler, passing the twitter sources & the twitter object
        twitterHandler = new TwitterHandler(settings.getTwitterSources(),settings.twitterInstance);
    }
    else{
        // If she hasn't use authentication, then we create a twitter handler only with the sources
        twitterHandler = new TwitterHandler(settings.getTwitterSources());
    }
    // We define the date of first search
    if(settings.firstSearch==true){
        if(settings.twitterSearchFromNow==true){
            twitterHandler.setPagingNow();
        }
        else{
            twitterHandler.setPagingAt(settings.firstSearchDate);
        }
    }

}

/**
 * Performs the searching
 * @return
 */
public Map<String,Map<String,FeedObjects.FeedObject>> performSearch(){

    // Clears the previous feeds
    FeedMap.clear();
    // Collects the data from facebook
    this.collectFbSources();
    // Collects the date from twitter
    FeedMap.putAll(twitterHandler.readSources());

    // Search for the keywords in the data that we already have collect
    this.searchSources();
    searchesPerformed++;

    // Save statistics
    keywordStats=searcher.getKeywordStats();
    return resultsMap;

}

public Map<String,keywordInfo> getKeywordStats(){
    return keywordStats;
}


/**
 * Collects the data from Facebook
 */
public void collectFbSources(){
    queryGenerator timeQuery = new queryGenerator();
    
    // We read the current time
    Calendar date =  Calendar.getInstance(TimeZone.getTimeZone("UTC+02:00"));
    if(lastCollect!=0){
        // By adding the last collection time into this class, the rest class
        // doesn't reads the same data that had read last time
        timeQuery.addSinceQuery(lastCollect);
    }
    /**
     * For each Facebook source it collects the data.
     * Then, a JSON handler parses the data and return them as classes 
     */
    for(int i=0;i<settings.fbSources.length;i++){
        FeedMap.putAll(JsonHandler.getFeed(RestHandler.getWall(timeQuery.getQuery()+settings.fbToken,settings.fbSources[i])));
    }
    // Update the last search time
    lastCollect=date.getTimeInMillis()/1000;


}

public void searchSources(){
        
    try{      
        resultsMap.putAll(searcher.searchFeeds(FeedMap));
    }catch(Exception monitorException){
        System.out.println(monitorException.getMessage());
    }
}


public String getNotifications(){

    String notification="";

    // It becomes true if a notification occured
    boolean notifyOcured=false;

    // If the user wants to get notifications in every new search
    if(notifyOcured=settings.notSettings.notifyEveryNewSearch==true){
        for(int i=0;i<settings.keys.size();i++){
            
            // Gets the keyword
            String keyword= settings.keys.get(i).getKeyword();

            // Prepares the notification string with the keyword and the found number of the specific keyword
            notification+="Keyword :"+keyword+" Found"+String.valueOf(keywordStats.get(keyword).getLastTimeFound())+" Times"+"\n";
        }
    }

    //  If the user want's to get notification every time that a keyword found more than the threshold
    if(settings.notSettings.notifyWhenExceedThreshold==true){

        /**
         * For every keyword we read the threshold and we compare it
         * with the number of found. When is bigger, (threshold < numOfFounds),
         * the user gets a notification
         */
        for(int i=0;i<settings.keys.size();i++){
            String keyword=settings.keys.get(i).getKeyword();
            int threshold=settings.keys.get(i).getThreshold(),timesFound=keywordStats.get(keyword).getLastTimeFound();
            if(timesFound>threshold){
                notifyOcured=true;
                notification+="Keyword: \""+keyword+"\" Exceeded threshold (threshold was: "+threshold+" and found: "+timesFound+" times)\n";
            }
        }
    }

    /**
     * It finds the average of results found in a the given minutes. Then,
     * a portion % of threshold is defined.
     * In continue, we calculate threshold * (above portion)
     */
    if(settings.notSettings.notifyExeedAveragePM==true){

        // For each keyword
        for(int i=0;i<settings.keys.size();i++){
            
            // Get keyword
            String keyword=settings.keys.get(i).getKeyword();

            float averageTimesFound;
            float perGivenMinutesFound;
            float perMinuteFound;
            float modifiedThreshold;

            float thresholdPercent=(float)(settings.notSettings.perMinutePercent * 0.01);
            int threshold=settings.keys.get(i).getThreshold();

            averageTimesFound=keywordStats.get(keyword).getAverageTimesFound();
            perMinuteFound=averageTimesFound/((float)settings.timerInterval/60000);

            modifiedThreshold= (threshold*(thresholdPercent));
            perGivenMinutesFound=perMinuteFound*settings.notSettings.perMinuteExeed;

            if(perGivenMinutesFound>modifiedThreshold){
                 notifyOcured=true;
                 notification+="Keyword: \""+keyword+"\" Exceeded per minutes threshold found: "+perGivenMinutesFound+" Per "+settings.notSettings.perMinuteExeed+" Minutes";
            }
        }
    }

    // The average of thresholds from all the keywords is being calcuated.
    // The average of the results founds is being calculated.
    // A positive or negative addition is being occured to threshold average
    // and in the end we compare it with the results average.
    
    // We get a notification if avg(threshold) < avg(results)

    if(settings.notSettings.notifyExeedOveralThresholds==true){
        int totalThreshold=0;
        int totalFoundTimes=0;
        float thresholdDiference;
        float modifiedThreshold;

        // For every keyword
        for(int i=0;i<settings.keys.size();i++){

            // Get the keyword
            String keyword=settings.keys.get(i).getKeyword();
            
            // Get the threshold for that keyword and add it into the overall threshold
            totalThreshold+=settings.keys.get(i).getThreshold();

            // Get the number of times found
            totalFoundTimes+=keywordStats.get(keyword).getLastTimeFound();
        }

        // Get threshold diviation into %
        thresholdDiference=(float) (settings.notSettings.overalThresholdExeed * 0.01);

        // Add it into the overall threshold
        modifiedThreshold=totalThreshold+(totalThreshold*thresholdDiference);
        if(totalFoundTimes>modifiedThreshold){
            notifyOcured=true;
            notification+="Global threshold exceeded (threshold was: "+modifiedThreshold+" and found overal: "+totalFoundTimes+" results";
        }

    }

    /**
     * If the user chooses this option then a threshold some times bigger than
     * all the other thresholds is defined. When the number of times where the
     * keyword found, is bigger than this threshold, the user gets a notification.
     */
    if(settings.notSettings.lifetimeExceed==true){

        // For each keyword
        for(int i=0;i<settings.keys.size();i++){

            // Get keyword
            String keyword=settings.keys.get(i).getKeyword();

            // Get threshold
            int threshold=settings.keys.get(i).getThreshold();

            // New threshold
            float modifiedThreshold=threshold*settings.notSettings.lifetimeExceedTimes;

            if(keywordStats.get(keyword).getTotalTimesFound()>modifiedThreshold){
                notifyOcured=true;
                notification+="Keyword: \""+keyword+"\" Exceeded lifetime threshold found: "+keywordStats.get(keyword).getTotalTimesFound()+" times overall";
            }

        }

    }

    if(notifyOcured==true){
        return notification;
    }
    else{
        return null;
    }

}


    

}
