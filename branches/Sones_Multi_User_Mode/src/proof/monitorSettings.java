

package proof;

import java.util.Date;
import java.util.List;
import twitter4j.Twitter;

public class monitorSettings {
    public List<FeedObjects.keywordObject> keys;
    public String[] fbSources;
    public List<String> twitterSources;
    public int timerInterval;
    public boolean firstSearch,searchComments,twitterSearchFromNow=false,searchTwitter,authTwitter;
    public Date firstSearchDate;
    public String fbToken;
    public Twitter twitterInstance;
    notificationSettings notSettings=new notificationSettings();



    public class notificationSettings{
         public boolean notifyEveryNewSearch,notifyWhenExceedThreshold,notifyExeedAveragePM,notifyExeedOveralThresholds,lifetimeExceed;
         public int perMinuteExeed,perMinutePercent,overalThresholdExeed;
         public float lifetimeExceedTimes;
    }






    public void setFbSources(String [] src){
        fbSources=src;
    }
    public void setTwitterSource(List<String> src){
        twitterSources=src;
    }
    public String[] getFbSources(){
        return fbSources;
    }
    public List<String> getTwitterSources(){
        return twitterSources;
    }

    public void setKeywords(List<FeedObjects.keywordObject> keywords){
        keys=keywords;
    }
    public List<FeedObjects.keywordObject> getKeywords(){
        return keys;
    }


}
