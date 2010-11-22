

package proof;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


public abstract interface FeedObjects {

 /**
  * The factory of feeds.
  */
 public static class feedFactory{

     /**
      * Creates an object of the given type and sets the id
      * @param type
      * @param Id
      * @return FeedObject of the given type
      */
   public static FeedObject getFeedClass(String type,String Id){
       if(type.equals("status")){
           StatusObject statusClass = new StatusObject(type,Id);
           return statusClass;
       }
       else if(type.equals("link")){
            LinkObject linkClass = new LinkObject(type,Id);
            return linkClass;
       }
       else if(type.equals("video")){
            VideoObject videoClass = new VideoObject(type,Id);
            return videoClass;
       }
       else if(type.equals("note")){
           NoteObject noteClass = new NoteObject(type,Id);
           return noteClass;
       }

       else{
            return null;
       }
   }



 }

public abstract class FeedObject implements Comparable{


    private String id;
    private String type;
    private String updatedTime;
    private String fromId;
    private String fromName;
    private String siteSource;
    Date createdTime;
    Map comments=null;

    public void setSiteSource(String siteSrc){
        siteSource=siteSrc;
    }
    public String getSiteSource(){
        return siteSource;
    }
    public void setId(String Id){
        id=Id;
    }
    public void setType(String Type){
        type=Type;
    }
    public void setCreatedTime(Date cTime){
        createdTime = cTime;
    }
    public void setUpdatedTime(String uTime){
        updatedTime = uTime;
    }
    public String getId(){
        return id;
    }
    public String getUpdatedTime(){
        return updatedTime;
    }
    public Date getCreatedTime(){
        return createdTime;
    }
    public void setFromId(String fid){
        fromId=fid;
    }
    public String getFromId(){
        return fromId;
    }
    public void setFromName(String fname){
        fromName = fname;
    }
    public String getFromName(){
        return fromName;
    }
    public void setComments(Map map){
        comments = map;
    }
    public Map getComments(){
        return comments;
    }
   public String getType(){
       return type;
   }
   public abstract String getMessage();
   public abstract String getLink();
   public abstract String getName();
   public abstract String getCaption();
   public abstract String getDescription();
   public abstract String getSource();
   public abstract String getLength();
   public abstract String getPicture();
   public abstract String getSubject();

   /**
    * This object and the object that coming as parameter are being compared
    * by the date that created. The object is extending the Comparable @see java.lang.Comparable
    * so it must implements this method.
    * @param feed2
    * @return 0 when the dates are same
    * @throws ClassCastException if the two objects haven't the same class or dirived class
    */
    public int compareTo(Object feed2) throws ClassCastException {

    // The two objects must have the same class or dirived class
    // otherwise we throw an exception
    if (!(feed2 instanceof FeedObject)){
      throw new ClassCastException("FbFeed Object Expected");
    }
    // Creation date of the parameter feed
    Date feed2Date = ((FeedObject) feed2).getCreatedTime();

    // Creation date of this feed
    Date thisDate = this.getCreatedTime();

    // return thisDate.compareTo(feed2Date);
    return feed2Date.compareTo(thisDate);
  }

}


public class StatusObject extends FeedObject {
    String message;
    StatusObject(String Type,String id){
        this.setType(Type);
        this.setId(id);
        
            }
    public void setMessage(String Content){
        message=Content;
    }
    public String getMessage(){
        return message;
    }

        @Override
        public String getLink() {
               return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getCaption() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getSource() {
            return null;
        }

        @Override
        public String getLength() {
            return null;
        }

        @Override
        public String getPicture() {
            return null;
        }

        @Override
        public String getSubject() {
            return null;
        }
       
  
}



public class LinkObject extends FeedObject{
    String name,link,caption,picture,description,message;
      LinkObject(String Type,String id){
        this.setType(Type);
        this.setId(id);
    }
      public void setMessage(String Content){
          message=Content;
      }
      public String getMessage(){
          return message;
      }

      public void setLink(String Link){
        link=Link;
      }
      public String getLink(){
        return link;
      }
      public void setCaption(String Caption){
        caption=Caption;
      }
      public void setPicture(String pic){
        picture=pic;
      }
     
      public void setDescription(String desc){
          description=desc;
      }
      public void setName(String Name){
          name=Name;
      }
      public String getName(){
          return name;
      }
      public String getCaption(){
          return caption;
      }
      public String getDescription(){
          return description;
      }
      public String getPicture(){
           return picture;
      }

        @Override
        public String getSource() {
            return null;
        }

        @Override
        public String getLength() {
            return null;
        }

        @Override
        public String getSubject() {
            return null;
        }

}

 public class VideoObject extends FeedObject{
     String message,description,length,source,picture,link,caption,name;
     VideoObject(String Type,String id){
        this.setType(Type);
        this.setId(id);
    }
       public void setMessage(String Content){
           message=Content;
       }
       public String getMessage(){
           return message;
       }
       public void setDescription(String disc){
           description=disc;
       }
       public String getDescription(){
           return description;
       }
       public void setLength(String Length){
           length=Length;
       }
       public String getLength(){
            return length;
       }
       public void setScourse(String Source){
            source=Source;
       }
       public void setLink(String Link){
            link=Link;
       }
       public void setPicture(String pic){
           picture=pic;
       }
       public void setCaption(String Caption){
           caption=Caption;
       }
       public void setName(String Name){
           name=Name;
       }
        @Override
       public String getSource(){
           return source;
       }
       public String getLink(){
           return link;
       }
       public String getPicture(){
           return picture;
       }
       public String getCaption(){
           return caption;
       }
       public String getName(){
           return name;
       }

      

        @Override
        public String getSubject() {
            return null;
        }

  
 }

 public class NoteObject extends FeedObject{
     String message,subject;
     NoteObject(String Type,String Id){
         this.setId(Id);
         this.setType(Type);
     }
     public void setMessage(String Content){
         message=Content;
     }
     public void setSubject(String Subject){
         subject=Subject;
     }
     public String getMessage(){
         return message;
     }
     public String getSubject(){
         return subject;
     }

        @Override
        public String getLink() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public String getCaption() {
            return null;
        }

        @Override
        public String getDescription() {
            return null;
        }

        @Override
        public String getSource() {
            return null;
        }

        @Override
        public String getLength() {
            return null;
        }

        @Override
        public String getPicture() {
            return null;
        }

 }



public class entityObject{
   private String Id,Name;

  
    public String getName(){
    return Name;
    }
    public String getID(){
     return Id;
    }
    public void setName(String name){
        Name=name;
    }
    public void setId(String id){
        Id=id;
    }

}


/**
 * This class contains the keyword and the threshold
 */
public class keywordObject{
    protected String keyword;
    protected int threshold;
    boolean combo;
    keywordObject(){
        
    }
    keywordObject(String key,int thres){
        keyword=key;
        threshold=thres;
        combo=false;
    }
    
    public String getKeyword(){
        return keyword;
    }
    public int getThreshold(){
        return threshold;
    }
    public boolean isCombo(){
        return combo;
    }


    
}

/**
 * This class extends the keywordObject and provides the mechanism of combo objects
 */
public class comboObject extends keywordObject {

    // The combo
    private List<String> keyList;
    boolean mode=false;

    comboObject(String Name,int thres){
        keyList = new ArrayList<String>();
        this.keyword=Name;
        this.combo=true;
        this.threshold=thres;

    }

    public List<String> getWordList(){
        return keyList;
    }
    public void addWord(String word){
        keyList.add(word);
    }
    public void setTrueForAndFalseForOR(boolean Mode){
        mode=Mode;
    }
    public boolean getMode(){
        return mode;
    }
}

}

