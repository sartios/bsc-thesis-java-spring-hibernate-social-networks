

package proof;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class provides methods about the time. It can format the time.
 */
public class timeHandler {
    

    /**
     * It reads a Date in the Facebook format and returns it in a Java Calendar
     * in Greek Time Zone. An example for a facebook date is :
     *
     *              2010-04-15T17:37:03+0000
     * which is:
     *
     * Year = 2010
     * Month = 04
     * Day = 15
     * Hour = 17
     * Minute = 37
     * Second = 03
     * Time zone = 0000
     * @param aDate
     * @return Date
     */
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
