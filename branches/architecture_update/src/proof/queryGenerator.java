package proof;

/**
 * This class contains a string and formats it so it can be paste next to a token.
 * The purpose is the execution of some queries.
 */
public class queryGenerator {

    private String queryString="";

    /**
     * Gets a UNIX timestamp and creates a query for reading data after this time
     * @param timeInSec
     */
    public void addSinceQuery(long timeInSec){
        queryString=queryString+"since="+timeInSec+"&";
    }

    /**
     * Gets a UNIX timestamp and creates a query for reading data until this time
     * @param timeInSec
     */
    public void addUntilQuery(long timeInSec){
        queryString=queryString+"until="+timeInSec+"&";
    }

    /**
     * Creates a limit on the number of publishes that will return
     * @param limit
     */
    public void addLimitQuery(int limit){
        queryString=queryString+"limit="+limit+"&";
    }

    /**
     * Creates a string for searching the keyword into publishes
     * @param word
     */
    public void addKeywordQuery(String word){
        queryString=queryString+"q="+word+"&";
    }

    /**
     * Gets different field names which include them on search
     * @param field
     */
    public void addFieldQuery(String field){
        queryString=queryString+"fields="+field+"&";

    }

    /**
     * Clears the query string
     */
    public void cleanQuery(){
        queryString="";
    }

    /**
     * @return query in REST format
     */
    public String getQuery(){
        return queryString;
    }



}
