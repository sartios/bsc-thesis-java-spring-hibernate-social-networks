

package proof;
import net.sf.json.*;
import net.sf.ezmorph.*;
import java.util.*;
import org.apache.commons.beanutils.*;
import java.util.Map;
import java.util.HashMap;
import proof.FeedObjects.LinkObject;
import proof.FeedObjects.NoteObject;
import proof.FeedObjects.StatusObject;
import proof.FeedObjects.VideoObject;
import proof.FeedObjects.entityObject;

/**
 * Includes functionality for decode JSON format files.
 * Uses DynaBeans and Json-lib
 *
 * <b><i>DynaBean</i></b>
 *
 * DynaBean is a Java object that supports properties whose names and data types,
 * as well as values, maybe dynamically modified.
 * @see <a href src="http://commons.apache.org/beanutils/api/org/apache/commons/beanutils/DynaBean.html">DynaBean</as>
 *
 * <b><i>Json-lib</i></b>
 *
 * Json-lib is a java library for transforming beans, maps, collections, java arrays
 * and XML to JSON and back again to beans and DynaBeans
 * @see <a href src="http://json-lib.sourceforge.net/apidocs/net/sf/json/JSON.html">JSON Library</a>
 */
public class JsonHandler {

    /**
     * Gets a json string as parameter, reads and extracts the users' id.
     * @param responseList JSON format string
     * @return ids of users
     */
    public static String[] ExtractIdsFromRest(String responseList) {
        try{
            /**
             * JSON array. The external form is a string wrapped in square brackets
             * with commas between the values. The internal form is an object
             * having get() and opt() method for accessing the value by index
             * and put() method for adding or replacing values.
             */
            JSONArray jsonArray;
            DynaBean dynaBean;
        
            /**
             * JSON object is an unordered collection of name/value pairs.
             * The external form is a string wrapped in curly braces with colons
             * between the names and the values, and commas between the values
             * and names.
             * {
             *  "name" : "Couper"
             * }
             * The internal form is an object having get() and opt() methods
             * for accessing the values by name, and put() methods
             * for adding or replacing values by name.
             *
             * We create a JSONObject from the return of REST call.
             */
            JSONObject obj= JSONObject.fromObject(responseList);

            // Return the array of JSON document with the name <u>data</u>
            jsonArray = obj.getJSONArray("data");
            int i=0,size[];

            // Get the size of the jsonArray
            size = JSONArray.getDimensions(jsonArray);

            // Here is were the id's is going to be saved
            String[] stringArray = new String[size[0]];

            // Each element of the array, do it a DynaBean object
            for(i=0;i<size[0];i++){

                // Creates a bean from the json object, without a specific class
                dynaBean = (DynaBean) JSONObject.toBean( jsonArray.getJSONObject(i));

                // Save the id into an array
                stringArray[i] =dynaBean.get("id").toString();
             }
    
            return stringArray;
        }
        catch(Exception e){
            return null;
        }
    }


    /**
     * This method reads from a JSON format string, the name and the id of the users.
     * Then it extracts them on an array and returns it.
     * @param responseList
     * @return array of names and users' id
     */
    public static entityObject[] ExtractEntitysFromRest(String responseList) {
    try{
        // This 2 objects are used also in the method above.
        JSONArray jsonArray;
        DynaBean dynaBean;


            JSONObject obj= JSONObject.fromObject(responseList);
            jsonArray = obj.getJSONArray("data");
            int i=0,size[];
            size = JSONArray.getDimensions(jsonArray);
            entityObject[]  entityArray = new entityObject[size[0]];

            for(i=0;i<size[0];i++){
                entityArray[i]=new entityObject();
            }

            String name,id;
          
            for(i=0;i<size[0];i++){

                dynaBean = (DynaBean) JSONObject.toBean( jsonArray.getJSONObject(i));
                id=dynaBean.get("id").toString();
                
                name=dynaBean.get("name").toString();

                // Should be object.
                entityArray[i].setId(id);
                entityArray[i].setName(name);
                   
             }
            return entityArray;
    }catch(Exception e){return null;}
 
    }

    /**
     * Extracts the feeds from a JSON document and creates FeedObjects
     * @param content
     * @return feeds
     */
    public static Map<String,FeedObjects.FeedObject> getFeed (String content){
        
        
        JSONArray jsonArray;
        DynaBean dynaBean;

        // Our feeds
        Map<String,FeedObjects.FeedObject> map = new HashMap<String,FeedObjects.FeedObject>();
        
        try{
            boolean empty=true;

            // Create a JSONObject from the JSON document
            JSONObject obj= JSONObject.fromObject(content);

            // Create the array from the document.
            jsonArray = obj.getJSONArray("data");
            int i=0,size[];
            size = JSONArray.getDimensions(jsonArray);
     

            for(i=0;i<size[0];i++){
                empty=true;

                try{
                    // Feed's type
                   String type;
                   // Feed's id
                   String id;
                   // Feed's creation time
                   String ctime;
                   // Feed's update time
                   String utime;
                   // Feed's publisher id
                   String fromId;
                   // Feed's publisher name
                   String fromName;

                   // A feed
                   FeedObjects.FeedObject aFeed;

                   // We get the object i from the JSONArray into a DynaBean
                   dynaBean = (DynaBean) JSONObject.toBean( jsonArray.getJSONObject(i));

                   // Extract the type
                   type = dynaBean.get("type").toString();
                   // Extract the id
                   id = dynaBean.get("id").toString();
                   // Extract the created time
                   ctime = dynaBean.get("created_time").toString();
                   // Extract the updated time
                   utime = dynaBean.get("updated_time").toString();
                   // Extract the publisher's id
                   fromId = reCaster(dynaBean.get("from")).get("id").toString();
                   // Extract the publisher's name
                   fromName = reCaster(dynaBean.get("from")).get("name").toString();

                   // Get the specific feed object
                   aFeed = FeedObjects.feedFactory.getFeedClass(type, id);
                   aFeed.setFromId(fromId);
                   aFeed.setFromName(fromName);
                   aFeed.setCreatedTime(timeHandler.convertFbToDate(ctime).getTime());
                   aFeed.setUpdatedTime(utime);
                   aFeed.setSiteSource("facebook");

                   try{

                       // It's not sure if a feed includes comments. So we try to get comments
                       aFeed.setComments(getComments(reCaster(dynaBean.get("comments"))));
                       empty=false;
                   }
                   catch(MorphException commentException){ }

                    /****** S T A T U S ******/
                   /*********************/
                   if(type.equals("status")){
                       try{
                           // Create a status object
                           FeedObjects.StatusObject statusClass;
                           // Cast the initial feed
                           statusClass = (StatusObject) aFeed;
                           // Set the message of the status
                           statusClass.setMessage(dynaBean.get("message").toString());
                           // Put it into the map
                           map.put(id, statusClass);
                       }catch(MorphException statusException){ }
                   }

                    /****** L I N K ******/
                   /*********************/
                   else if(type.equals("link")){
                       try{
                           // Create a link feed object
                           FeedObjects.LinkObject linkClass;
                           // Cast the initial feed
                           linkClass = (LinkObject) aFeed;

                           /****** M E S S A G E ******/
                           try{
                               // Get the message of the link
                               linkClass.setMessage(dynaBean.get("message").toString());
                               empty=false;
                           }catch(MorphException Secondary){ }

                            /****** N A M E  ******/
                           try{
                               linkClass.setMessage(dynaBean.get("name").toString());
                               empty=false;
                           }catch(MorphException Secondary){}

                            /****** C A P T I O N ******/
                           try{
                               linkClass.setCaption(dynaBean.get("caption").toString());
                               empty=false;
                           }catch(MorphException Secondary){}

                           /****** L I N K ******/
                           try{
                               linkClass.setLink(dynaBean.get("link").toString());
                           }catch(MorphException Secondary){}

                           /****** D E S C R I P T I O N ******/
                           try{
                               linkClass.setDescription(dynaBean.get("description").toString());
                               empty=false;
                           }catch(MorphException Secondary){}

                           /****** P I C T U R E ******/
                           try{
                               linkClass.setPicture(dynaBean.get("picture").toString());
                           }catch(MorphException Secondary){}

                           if(empty==false){
                               map.put(id, linkClass);
                           }
                       }catch(MorphException baseLinkException){ }
                   }
                    /****** V I D E O ******/
                   /***********************/
                   else if(type.equals("video")){
                       try{
                           FeedObjects.VideoObject videoClass;
                           videoClass = (VideoObject) aFeed;

                           /****** M E S S A G E ******/
                           try{
                                videoClass.setMessage(dynaBean.get("message").toString());
                                empty=false;
                           }catch(MorphException Secondary){}

                           /****** D E S C R I P T I O N ******/
                           try{
                               videoClass.setDescription(dynaBean.get("description").toString());
                               empty=false;
                           }catch(MorphException Secondary){}
                           
                           /****** S O U R C E ******/
                           try{
                               videoClass.setScourse(dynaBean.get("source").toString());
                           }catch(MorphException Secondary){ }

                           /****** L I N K ******/
                           try{
                               videoClass.setLink(dynaBean.get("link").toString());
                           }catch(MorphException Secondary){ }

                           /****** C A P T I O N ******/
                           try{
                               videoClass.setCaption(dynaBean.get("caption").toString());
                               empty=false;
                           }catch(MorphException Secondary){ }
                           
                           /****** N A M E ******/
                           try{
                              videoClass.setName(dynaBean.get("name").toString());
                              empty=false;
                           }catch(MorphException Secondary){ }
                           if(empty==false){
                               map.put(id, videoClass);}
                       }
                   catch(MorphException VideoException){}
               }

               /****** N O T E ******/
              /*********************/
               else if(type.equals("note")){

                   /****** M E S S A G E ******/
                   try{
                       FeedObjects.NoteObject noteClass;
                       noteClass = (NoteObject) aFeed;
                       noteClass.setMessage(dynaBean.get("message").toString());

                       /****** S U B J E C T ******/
                       try{
                           noteClass.setSubject(dynaBean.get("subject").toString());
                       }catch(MorphException Secondary){ }
                       map.put(id,noteClass);
                   }catch(Exception noteException){}
               }
   
            }catch(Exception FeedException){}
             }
                }catch(Exception e){System.out.println(e.getMessage());}
        return map;
        
    }


    /**
     * Gets a DynaObject usually with comments. Extracts this comments and return
     * them in a map
     * @param aDynaBean
     * @return map with comments
     */
    public static Map getComments(DynaBean aDynaBean){
        ArrayList aList;
        Object[] commentArray;

        // The comments
        Map<String,FeedObjects.StatusObject> commentMap = new HashMap<String,FeedObjects.StatusObject>();

        try{
            aList = (ArrayList) aDynaBean.get("data");
            commentArray=aList.toArray();
            int i=0,size=aList.size();
            for(i=0;i<size;i++){
                String id;
                aDynaBean = (DynaBean) commentArray[i];
                id = aDynaBean.get("id").toString();
                FeedObjects.StatusObject aComment = new FeedObjects.StatusObject("comment",id);
                aComment.setMessage(aDynaBean.get("message").toString());
                aComment.setCreatedTime(timeHandler.convertFbToDate(aDynaBean.get("created_time").toString()).getTime());
                aComment.setFromId(reCaster(aDynaBean.get("from")).get("id").toString());
                aComment.setFromName(reCaster(aDynaBean.get("from")).get("name").toString());
                aComment.setSiteSource("facebook");
                commentMap.put(id, aComment);
            }
        }catch(Exception e){}

        return commentMap;


    }

    /**
     * Casts an Object to DynaBean
     * @param aBean
     * @return DynaBean
     */
    public static DynaBean reCaster(Object aBean){
            DynaBean aDynaBean = (DynaBean) aBean;
            return aDynaBean;

    }

    /**
     * Sometimes the REST responds are coming to more than one page. This
     * method returns the previous page
     * @param content
     * @return JSON string with the previous page
     */
    public static String getPreviusPaging(String content){
        JSONObject obj= JSONObject.fromObject(content);
        DynaBean dynaBean;

        JSONObject obj2 = obj.getJSONObject("paging");
        dynaBean = (DynaBean) JSONObject.toBean(obj2);
               return dynaBean.get("previous").toString();
    }

     /**
     * Sometimes the REST responds are coming to more than one page. This
     * method returns the next page
     * @param content
     * @return JSON string with the next page
     */
    public static String getNextPaging(String content){
        JSONObject obj= JSONObject.fromObject(content);
        DynaBean dynaBean;

        JSONObject obj2 = obj.getJSONObject("paging");
        dynaBean = (DynaBean) JSONObject.toBean(obj2);
               return dynaBean.get("next").toString();
    }

}
