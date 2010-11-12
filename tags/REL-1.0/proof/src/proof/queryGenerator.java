package proof;


public class queryGenerator {

    private String queryString="";


    public void addSinceQuery(long timeInSec){
        queryString=queryString+"since="+timeInSec+"&";
    }

    public void addUntilQuery(long timeInSec){
        queryString=queryString+"until="+timeInSec+"&";
    }
    public void addLimitQuery(int limit){
        queryString=queryString+"limit="+limit+"&";
    }
    public void addKeywordQuery(String word){
        queryString=queryString+"q="+word+"&";
    }
    public void addFieldQuery(String field){
        queryString=queryString+"fields="+field+"&";

    }
    public void cleanQuery(){
        queryString="";
    }
    public String getQuery(){
        return queryString;
    }



}
