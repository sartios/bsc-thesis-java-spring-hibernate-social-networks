

package proof;


import java.util.Calendar;
import java.util.HashMap;


import java.util.Map;
import java.util.TimeZone;
import proof.Searcher.keywordInfo;

public class monitorEngine {

private monitorSettings settings;
private Map<String,FeedObjects.FeedObject> FeedMap= new HashMap<String,FeedObjects.FeedObject>();
private Map<String,Map<String,FeedObjects.FeedObject>> resultsMap = new HashMap<String,Map<String,FeedObjects.FeedObject>>();
Map<String,keywordInfo> keywordStats = new HashMap<String,keywordInfo>();

private  long lastCollect=0;
private int searchesPerformed=0;
Searcher searcher;
TwitterHandler twitterHandler;


public monitorEngine(monitorSettings set){
    settings=set;
    if(set.firstSearch){
        lastCollect=set.firstSearchDate.getTime()/1000;
    }
    try{
    searcher=new Searcher(settings.keys);
    }catch(Exception e){System.out.println(e.getMessage());}
    searcher.setSearchComments(settings.searchComments);

    if(settings.authTwitter==true){
    twitterHandler = new TwitterHandler(settings.getTwitterSources(),settings.twitterInstance);
    }
    else{
        twitterHandler = new TwitterHandler(settings.getTwitterSources());
    }
    if(settings.firstSearch==true){
        if(settings.twitterSearchFromNow==true){
        twitterHandler.setPagingNow();
        }
        else{
        twitterHandler.setPagingAt(settings.firstSearchDate);
        }
    }

}
public Map<String,Map<String,FeedObjects.FeedObject>> performSearch(){

    FeedMap.clear();
    this.collectFbSources();
    FeedMap.putAll(twitterHandler.readSources());
    this.searchSources();
    searchesPerformed++;
    keywordStats=searcher.getKeywordStats();
    return resultsMap;

}

public Map<String,keywordInfo> getKeywordStats(){
    return keywordStats;
}



 public void collectFbSources(){
       


        queryGenerator timeQuery = new queryGenerator();
        Calendar date =  Calendar.getInstance(TimeZone.getTimeZone("UTC+02:00"));
        if(lastCollect!=0){
        timeQuery.addSinceQuery(lastCollect);

        }
        for(int i=0;i<settings.fbSources.length;i++)
        {
         FeedMap.putAll(JsonHandler.getFeed(RestHandler.getWall(timeQuery.getQuery()+settings.fbToken,settings.fbSources[i])));
        }
        lastCollect=date.getTimeInMillis()/1000;


    }



  public void searchSources(){
        
        try{      
        resultsMap.putAll(searcher.searchFeeds(FeedMap));
        }catch(Exception monitorException){System.out.println(monitorException.getMessage());}

        
  }

  public String getNotifications(){
      String notification="";
        boolean notifyOcured=false;
        if(notifyOcured=settings.notSettings.notifyEveryNewSearch==true){
         for(int i=0;i<settings.keys.size();i++){
            String keyword= settings.keys.get(i).getKeyword();

            notification+="Keyword :"+keyword+" Found"+String.valueOf(keywordStats.get(keyword).getLastTimeFound())+" Times"+"\n";
        }
        }

        if(settings.notSettings.notifyWhenExceedThreshold==true){
             for(int i=0;i<settings.keys.size();i++){
                 String keyword=settings.keys.get(i).getKeyword();
                 int threshold=settings.keys.get(i).getThreshold(),timesFound=keywordStats.get(keyword).getLastTimeFound();
                 if(timesFound>threshold){
                 notifyOcured=true;
                 notification+="Keyword: \""+keyword+"\" Exceeded threshold (threshold was: "+threshold+" and found: "+timesFound+" times)\n";
                 }
             }
        }

        if(settings.notSettings.notifyExeedAveragePM==true){
            for(int i=0;i<settings.keys.size();i++){
                String keyword=settings.keys.get(i).getKeyword();
                float averageTimesFound,perGivenMinutesFound,perMinuteFound,modifiedThreshold,thresholdPercent=(float)(settings.notSettings.perMinutePercent * 0.01);
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

        if(settings.notSettings.notifyExeedOveralThresholds==true){
            int totalThreshold=0,totalFoundTimes=0;
            float thresholdDiference,modifiedThreshold;
            for(int i=0;i<settings.keys.size();i++){
                String keyword=settings.keys.get(i).getKeyword();
                totalThreshold+=settings.keys.get(i).getThreshold();
                totalFoundTimes+=keywordStats.get(keyword).getLastTimeFound();
             }

          thresholdDiference=(float) (settings.notSettings.overalThresholdExeed * 0.01);

          modifiedThreshold=totalThreshold+(totalThreshold*thresholdDiference);
            if(totalFoundTimes>modifiedThreshold){
                notifyOcured=true;
                notification+="Global threshold exceeded (threshold was: "+modifiedThreshold+" and found overal: "+totalFoundTimes+" results";
            }

        }
        if(settings.notSettings.lifetimeExceed==true){
            for(int i=0;i<settings.keys.size();i++){
              String keyword=settings.keys.get(i).getKeyword();
              int threshold=settings.keys.get(i).getThreshold();
              float modifiedThreshold=threshold*settings.notSettings.lifetimeExceedTimes;

              if(keywordStats.get(keyword).getTotalTimesFound()>modifiedThreshold){
                 notifyOcured=true;
                 notification+="Keyword: \""+keyword+"\" Exceeded lifetime threshold found: "+keywordStats.get(keyword).getTotalTimesFound()+" times overall";
              }

            }

        }
        if(notifyOcured==true){
        return notification;}
        else
        {return null;}

  }


    

}
