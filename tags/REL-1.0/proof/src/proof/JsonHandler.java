

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


public class JsonHandler {


    public static String[] ExtractIdsFromRest(String responseList) {
        try{
        JSONArray jsonArray;
        DynaBean dynaBean;
        

            JSONObject obj= JSONObject.fromObject(responseList);
            jsonArray = obj.getJSONArray("data");
            int i=0,size[];
            size = JSONArray.getDimensions(jsonArray);
            String[] stringArray = new String[size[0]];


            for(i=0;i<size[0];i++){

                dynaBean = (DynaBean) JSONObject.toBean( jsonArray.getJSONObject(i));
                stringArray[i] =dynaBean.get("id").toString();
                              
             }
    
    return stringArray;
        }catch(Exception e){return null; }
    }
    public static entityObject[] ExtractEntitysFromRest(String responseList) {
    try{
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
                entityArray[i].setId(id);
                entityArray[i].setName(name);
                   
             }
            return entityArray;
    }catch(Exception e){return null;}
 
    }

    
    public static Map<String,FeedObjects.FeedObject> getFeed (String content){
        
        
                JSONArray jsonArray;
                DynaBean dynaBean;
                Map<String,FeedObjects.FeedObject> map = new HashMap<String,FeedObjects.FeedObject>();
                try{
                boolean empty=true;
            JSONObject obj= JSONObject.fromObject(content);
            jsonArray = obj.getJSONArray("data");
            int i=0,size[];
            size = JSONArray.getDimensions(jsonArray);
     
     
            for(i=0;i<size[0];i++){
                empty=true;
            try{
            String type,id,ctime,utime,fromId,fromName;
            FeedObjects.FeedObject aFeed;
               dynaBean = (DynaBean) JSONObject.toBean( jsonArray.getJSONObject(i));

               
               type = dynaBean.get("type").toString();
               id = dynaBean.get("id").toString();
               ctime = dynaBean.get("created_time").toString();
               utime = dynaBean.get("updated_time").toString();
               fromId = reCaster(dynaBean.get("from")).get("id").toString();
               fromName = reCaster(dynaBean.get("from")).get("name").toString();
            

                      


               aFeed = FeedObjects.feedFactory.getFeedClass(type, id);

               aFeed.setFromId(fromId);
               aFeed.setFromName(fromName);
               aFeed.setCreatedTime(timeHandler.convertFbToDate(ctime).getTime());
               aFeed.setUpdatedTime(utime);
               aFeed.setSiteSource("facebook");
               try{

                   aFeed.setComments(getComments(reCaster(dynaBean.get("comments"))));
                   empty=false;

               }
                    catch(MorphException commentException){ }

               if(type.equals("status")){
                   try
                   {
                   FeedObjects.StatusObject statusClass;
                   statusClass = (StatusObject) aFeed;
                   statusClass.setMessage(dynaBean.get("message").toString());
                   map.put(id, statusClass);
                   }catch(MorphException statusException){ }
               }
               else if(type.equals("link")){
                   
                   try
                   {
                   FeedObjects.LinkObject linkClass;
                   linkClass = (LinkObject) aFeed;
                   try{
                   linkClass.setMessage(dynaBean.get("message").toString());
                   empty=false;
                   }catch(MorphException Secondary){ }
                   try{
                   linkClass.setMessage(dynaBean.get("name").toString());
                   empty=false;
                   }catch(MorphException Secondary){}
                   try{
                       linkClass.setCaption(dynaBean.get("caption").toString());
                       empty=false;
                   }catch(MorphException Secondary){}
                   try{
                       linkClass.setLink(dynaBean.get("link").toString());
                   }catch(MorphException Secondary){}
                   try{
                       linkClass.setDescription(dynaBean.get("description").toString());
                       empty=false;
                   }catch(MorphException Secondary){}
                   try{
                       linkClass.setPicture(dynaBean.get("picture").toString());
                   }catch(MorphException Secondary){}

                   if(empty==false){
                   map.put(id, linkClass);
                   }
                   }catch(MorphException baseLinkException){ }
               }
               else if(type.equals("video")){
                   
                   try
                   {
                   FeedObjects.VideoObject videoClass;
                   videoClass = (VideoObject) aFeed;
                   try{
                        videoClass.setMessage(dynaBean.get("message").toString());
                        empty=false;
                   }catch(MorphException Secondary){}
                   try{
                       videoClass.setDescription(dynaBean.get("discription").toString());
                       empty=false;
                   }catch(MorphException Secondary){}
                   try{
                       videoClass.setScourse(dynaBean.get("source").toString());
                   }catch(MorphException Secondary){ }
                   try{
                       videoClass.setLink(dynaBean.get("link").toString());
                   }catch(MorphException Secondary){ }
                   try{
                       videoClass.setCaption(dynaBean.get("caption").toString());
                       empty=false;
                   }catch(MorphException Secondary){ }
                   try{
                      videoClass.setName(dynaBean.get("name").toString());
                      empty=false;
                   }catch(MorphException Secondary){ }

                   if(empty==false){
                   map.put(id, videoClass);}
                   }
                   catch(MorphException VideoException){}
               }
               else if(type.equals("note")){
                   try{
                       FeedObjects.NoteObject noteClass;
                       noteClass = (NoteObject) aFeed;
                       noteClass.setMessage(dynaBean.get("message").toString());
                                           
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

    public static Map getComments(DynaBean aDynaBean){
        ArrayList aList;
      Object[] commentArray;
        
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
    public static DynaBean reCaster(Object aBean){
            DynaBean aDynaBean = (DynaBean) aBean;
            return aDynaBean;

    }
    public static String getPreviusPaging(String content){
        JSONObject obj= JSONObject.fromObject(content);
        DynaBean dynaBean;

        JSONObject obj2 = obj.getJSONObject("paging");
        dynaBean = (DynaBean) JSONObject.toBean(obj2);
               return dynaBean.get("previous").toString();
    }
    public static String getNextPaging(String content){
        JSONObject obj= JSONObject.fromObject(content);
        DynaBean dynaBean;

        JSONObject obj2 = obj.getJSONObject("paging");
        dynaBean = (DynaBean) JSONObject.toBean(obj2);
               return dynaBean.get("next").toString();
    }

}
