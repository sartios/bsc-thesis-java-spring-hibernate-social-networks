

package proof;
import java.util.Calendar;
import java.util.TimeZone;


public class timeHandler {
    
    
    public static Calendar convertFbToDate(String aDate){
        Calendar date =  Calendar.getInstance(TimeZone.getTimeZone("UTC+02:00"));
        
        int aYear,aMonth,aDay,aHour,aMinute,aSecond;



       aYear=Integer.parseInt(aDate.substring(0,4));
       aMonth=Integer.parseInt(aDate.substring(5,7));
       aDay=Integer.parseInt(aDate.substring(8,10));
       aHour=Integer.parseInt(aDate.substring(11,13));
       aMinute=Integer.parseInt(aDate.substring(14,16));
       aSecond=Integer.parseInt(aDate.substring(17,19));
      
       date.set(aYear, aMonth-1, aDay, aHour, aMinute, aSecond);
     
        return date;
    }
    public static Calendar getCurrentTime(){

         Calendar date =  Calendar.getInstance(TimeZone.getTimeZone("UTC+02:00"));
         return date;
    }









}
