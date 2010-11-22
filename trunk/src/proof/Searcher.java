

package proof;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import proof.FeedObjects.StatusObject;
import proof.FeedObjects.comboObject;
import proof.FeedObjects.keywordObject;

/**
 * This class gets a collection of publishes and some keywords. Then try to find
 * each keyword into the collections. Keeps some statistics and return the results
 */
public class Searcher {

    // To search into comments or not
    boolean searchComments=false;

    int totalResultsFound=0;
    int lastResultsFound=0;
    int totalSearches=0;
   
    // The keywords list
    List<FeedObjects.keywordObject> keywordList;

    // Statistics about the keywords
    Map<String,keywordInfo> keywordsInfos = new HashMap<String,keywordInfo>();


    /**
     * Constructor which gets a list of keywords and initialize the 
     * internal list of keywords and the statistics for each keyword
     * @param keywords
     * @throws IOException
     */
    Searcher(List<keywordObject> keywords)throws IOException{
        keywordList = keywords;
         for(int i=0;i<keywordList.size();i++){
             keywordsInfos.put(keywordList.get(i).getKeyword(),new keywordInfo());
         }
    }




    public void setSearchComments(boolean value){
        searchComments=value;
    }



    public void searchFile (String keyword) throws IOException{
        String content;
        boolean contains=false;
        try{
           content= FileHandler.readFile("results/news");
           contains=content.contains(keyword);
        }catch(IOException fileException){ }
       System.out.println(contains);
    }


    /**
     * Searches the keywords into the feeds
     * @param feedMap
     * @return
     * @throws IOException
     */
    public Map searchFeeds(Map<String,FeedObjects.FeedObject> feedMap) throws IOException{

        /**
         *
         */
        Map<String,Map<String,FeedObjects.FeedObject>> resultsMap = new HashMap<String,Map<String,FeedObjects.FeedObject>>();

        // Clear the previous results
        this.resetLastTimesFound();
        // Increase the number of searches
        totalSearches++;

        // For each keyword, we create a map which will contain the search results
        for(int j=0;j<keywordList.size();j++){
            resultsMap.put(keywordList.get(j).getKeyword(), new HashMap<String,FeedObjects.FeedObject>());
        }

        // If we've some keywords
        if(!keywordList.isEmpty()){
            Iterator mapIterator = feedMap.entrySet().iterator();
            FeedObjects.FeedObject aFeed;
            boolean triggerFound;

            // For every pair in the feeds
            for(int i=0;i<feedMap.size();i++){
                triggerFound=false;

                // We get the pair
                Map.Entry entry = (Map.Entry) mapIterator.next();
                // We extract the text
                aFeed = (FeedObjects.FeedObject) entry.getValue();
                String word;

                // For each keyword
                for(int cnt=0;cnt<keywordList.size();cnt++){
                    boolean isCombo;
                    // Get the answer if is combo
                    isCombo= keywordList.get(cnt).isCombo();
                    // And if is combo
                    if(isCombo==true){
                        
                        List<String> keywords;
                        // List that we save if we found each keyword
                        List<Boolean> keyFound = new ArrayList<Boolean>();

                        // The mode  AND / OR
                        boolean mode;

                        // Get out the combo
                        FeedObjects.comboObject comboObj=(comboObject) keywordList.get(cnt);
                        // We extract the individual keywords of the combo
                        keywords = comboObj.getWordList();
                        // We get the name of the combo
                        word=comboObj.getKeyword();
                        // We also get the mode that connects the keywords
                        mode= comboObj.getMode();

                        // For each keyword
                        for(int k=0;k<keywords.size();k++){
                            // Get the k keyword of the combo
                            String comboKey=keywords.get(k);
                            // Add into the result list the result of search into the feed for the combo
                            keyFound.add(k,checkFeed(aFeed,comboKey));
                        }
                        // If we have choose the AND connector then we must search
                        // and check if both keywords found.
                        if(mode==true){
                            triggerFound=true;
                            for(int l=0;l<keyFound.size();l++){
                                if(keyFound.get(l)==false){
                                    triggerFound=false;
                                }
                            }
                            // If we choose OR, then we search to see if even one
                            // of the keywords found
                        }else{
                            for(int l=0;l<keyFound.size();l++){
                                if(keyFound.get(l)==true){
                                    triggerFound=true;
                                }
                            }
                        }
                        // If we don't have combo, but only one keyword
                        // then we check to see if the keyword exists
                    }else{
                        FeedObjects.keywordObject keyObj = keywordList.get(cnt);
                         word = keyObj.getKeyword();
                         triggerFound=checkFeed(aFeed,word);
                    }
                    // If the keyword exists into the feed, then we put the feed
                    // into the result map & update the statistic map
                    if(triggerFound==true){
                        resultsMap.get(word).put(aFeed.getId(), aFeed);
                        keywordsInfos.get(word).increseTimesFound();
                    }
              }
        }
    }
    return resultsMap;
}

    /**
     * This method searches into a feed to find the word.
     * @param aFeed
     * @param word
     * @return true if the word exists into the feed
     */
    private boolean checkFeed(FeedObjects.FeedObject aFeed,String word){

        boolean found=false;
        String content;
        Map<String,FeedObjects.StatusObject> commentMap=null;
        
        // The textual content of the feed
        content=aFeed.getMessage();

        // Checks with safty if the content includes word
        found=safeCheck(content,word);

        // If it doesn't include it
        if(found==false){
            // Then we get the name of the fees
            content=aFeed.getName();
            // and check again to see if the word exists into the name of the feed
            found=safeCheck(content,word);
        }
        // If it doesn't exists into the Feed name
        if(found==false){
            // Then we get the description
            content=aFeed.getDescription();
            // and check again to see if the word exists into the description of the feed
            found=safeCheck(content,word);
        }
         // If it doesn't exists into the Feed description
        if(found==false){
            // we get the Feeds caption
            content=aFeed.getCaption();
            // and we check again
            found=safeCheck(content,word);
        }
        //if doesn't exists into the feeds caption
        if(found==false){
            // we get the subject
            content=aFeed.getSubject();
            // and check again
            found=safeCheck(content,word);
        }
        // If we didn't find it but we want to search in the comments as well
        if((found==false)&&(searchComments==true)){
            // And comments exist
            if(aFeed.getComments()!=null){
                // Get the comments
                commentMap = aFeed.getComments();
                Iterator commentIterator = commentMap.entrySet().iterator();
                
                // And get the textual content of comments and check
                FeedObjects.StatusObject aComment;
                for(int j=0;j<commentMap.size();j++){
                    Map.Entry commentEntry = (Entry) commentIterator.next();
                    aComment=(StatusObject) commentEntry.getValue();
                    content=aComment.getMessage();
                    found=safeCheck(content,word);
                }
             }
         }
        return found;
    }

    private void resetLastTimesFound(){
        for(int i=0;i<keywordList.size();i++){
            keywordsInfos.get(keywordList.get(i).getKeyword()).resetTimesFoundLast();
        }
    }

    protected class keywordInfo{
        private int timesFoundTotal=0;
        private int timesFoundLast=0;

        public void increseTimesFound(){
            timesFoundTotal++;
            timesFoundLast++;
        }
        public void resetTimesFoundLast(){
            timesFoundLast=0;
        }
        public int getTotalTimesFound(){
            return timesFoundTotal;
        }
        public int getLastTimeFound(){
            return timesFoundLast;
        }
        public float getAverageTimesFound(){
            if(totalSearches!=0){
                DecimalFormat twoDForm = new DecimalFormat("#.##");
                float d=timesFoundTotal/totalSearches;
                return Float.valueOf(twoDForm.format(d));
            }
            else return 0;
        }
    }


    public boolean safeCheck(String content,String word){
        boolean found=false;
        if(content!=null){
            if(content.toLowerCase().contains(word)){
                found=true;
             }
        }
        return found;
    }

    public Map<String,keywordInfo> getKeywordStats(){
        return keywordsInfos;
    }



}