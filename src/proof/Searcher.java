

package proof;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Map.Entry;
import proof.FeedObjects.StatusObject;
import proof.FeedObjects.comboObject;
import proof.FeedObjects.keywordObject;

public class Searcher {

boolean searchComments=false;
int totalResultsFound=0,lastResultsFound=0;
int totalSearches=0;
List<FeedObjects.keywordObject> keywordList;
Map<String,keywordInfo> keywordsInfos = new HashMap<String,keywordInfo>();


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


public Map searchFeeds(Map<String,FeedObjects.FeedObject> feedMap) throws IOException{
    Map<String,Map<String,FeedObjects.FeedObject>> resultsMap = new HashMap<String,Map<String,FeedObjects.FeedObject>>();

    this.resetLastTimesFound();

    totalSearches++;
    for(int j=0;j<keywordList.size();j++){
    resultsMap.put(keywordList.get(j).getKeyword(), new HashMap<String,FeedObjects.FeedObject>());
    }
 

    if(!keywordList.isEmpty()){

    
    Iterator mapIterator = feedMap.entrySet().iterator();
    FeedObjects.FeedObject aFeed;
    
    boolean triggerFound;

        for(int i=0;i<feedMap.size();i++){
            triggerFound=false;
            Map.Entry entry = (Map.Entry) mapIterator.next();
            aFeed = (FeedObjects.FeedObject) entry.getValue();
            String word;
            

            for(int cnt=0;cnt<keywordList.size();cnt++){
             boolean isCombo;             
             isCombo= keywordList.get(cnt).isCombo();

                if(isCombo==true){
                    List<String> keywords;
                    List<Boolean> keyFound = new ArrayList<Boolean>();
                    boolean mode;
                    FeedObjects.comboObject comboObj=(comboObject) keywordList.get(cnt);
                    keywords = comboObj.getWordList();
                    word=comboObj.getKeyword();
                    mode= comboObj.getMode();

                    for(int k=0;k<keywords.size();k++){
                        String comboKey=keywords.get(k);
                        keyFound.add(k,checkFeed(aFeed,comboKey));
                    }
                    if(mode==true){
                        triggerFound=true;
                        for(int l=0;l<keyFound.size();l++){
                            if(keyFound.get(l)==false){
                                triggerFound=false;
                            }
                        }
                    }else{
                          for(int l=0;l<keyFound.size();l++){
                            if(keyFound.get(l)==true){
                                triggerFound=true;
                            }
                        }
                    }


                }else{
                 FeedObjects.keywordObject keyObj = keywordList.get(cnt);
                 word = keyObj.getKeyword();
                 triggerFound=checkFeed(aFeed,word);
                }
                            
            if(triggerFound==true){
            resultsMap.get(word).put(aFeed.getId(), aFeed);
            keywordsInfos.get(word).increseTimesFound();
            }

      }
}
    }
return resultsMap;
}


private boolean checkFeed(FeedObjects.FeedObject aFeed,String word){
    boolean found=false;
    String content;
    Map<String,FeedObjects.StatusObject> commentMap=null;
    content=aFeed.getMessage();
               found=safeCheck(content,word);
                if(found==false)
                {content=aFeed.getName();
                 found=safeCheck(content,word);
                }
               if(found==false){
                   content=aFeed.getDescription();
                  found=safeCheck(content,word);
              }
              if(found==false){
                  content=aFeed.getCaption();
                  found=safeCheck(content,word);
              }
               if(found==false){
                   content=aFeed.getSubject();
                     found=safeCheck(content,word);
              }
            if((found==false)&&(searchComments==true)){
             if(aFeed.getComments()!=null){
               commentMap = aFeed.getComments();
                Iterator commentIterator = commentMap.entrySet().iterator();
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