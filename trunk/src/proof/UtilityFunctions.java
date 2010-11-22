

package proof;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class exists just for future adds
 */
public abstract interface UtilityFunctions {

public static class mapSort{

    /**
     * Get's a map of FeedObjects and sorts them
     * @param FeedObjects to be sorted
     * @return FeedObjects sorted
     */
    public static Map<String,FeedObjects.FeedObject> sortByValue(Map<String,FeedObjects.FeedObject> map) {
        // The map is being duplicated to a list
        List list = new LinkedList(map.entrySet());

        // Use the java lib sorting method we define what to compare
        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
              .compareTo(((Map.Entry) (o2)).getValue());
              }
         });

         // We add the sorted list's results into the map again
        Map result = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }


}


}
