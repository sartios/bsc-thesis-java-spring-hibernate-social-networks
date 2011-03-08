package com.sones.businessLogic;

import java.util.ArrayList;
import java.util.List;

import net.sf.ezmorph.MorphException;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.DynaBean;

import com.sones.businessLogic.Facebook.FacebookFriend;

public class FeedFactory {
	
	private static FeedFactory instance_ = null;
	
	private FeedFactory(){	
	}
	
	public static FeedFactory getInstance(){
		if(instance_==null){
			instance_ = new FeedFactory();
		}
		return instance_;
	}
	
	public Feed getFeed(final DynaBean object){
		String type = object.get("type").toString();
		if(type.equals("status")){
			return getStatus(object);
		}
		if(type.equals("note")){
			return getNote(object);
		}
		return null;
	}
	
	private Status getStatus(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String from_name = ((DynaBean)(object.get("from"))).get("name").toString();
		FacebookFriend from = new FacebookFriend(from_name, from_id);
		Status feed = new Status(id,from,created_time,message);
		List<Comment> comments=getComments(object,feed);
		if(comments!=null){
			feed.setComments(comments);
		}
		return feed;
	}
	
	private Note getNote(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String from_name = ((DynaBean)(object.get("from"))).get("name").toString();
		FacebookFriend from = new FacebookFriend(from_name, from_id);
		String subject = object.get("subject").toString();
		Note feed = new Note(id,from,created_time,subject,message);
		List<Comment> comments=getComments(object,feed);
		if(comments!=null){
			feed.setComments(comments);
		}
		return feed;
	}
	
	/**
	 * Extracts the comments from the feed and returns them in list
	 * If the feed doesn't have comments then the comments property doesn't exist.
	 * It also sets the number of comments to the feed object.
	 * And the JSONObject.get(property) throws MorphException
	 * @param object
	 * @param feed
	 * @return comments or null if feed doesn't have comments
	 */
	public List<Comment> getComments(final DynaBean object,Feed feed){
		try{
			DynaBean tmp = (DynaBean)object.get("comments");
			feed.setNumberOfComments(Integer.parseInt(tmp.get("count").toString()));
			ArrayList aList=((ArrayList)tmp.get("data"));
			List<Comment> feedComments = new ArrayList<Comment>();
			Object[] commentArray = aList.toArray();
			int numberOfComments =  aList.size();
			
			for(int i=0;i<numberOfComments;i++){
				tmp = (DynaBean)commentArray[i];
				String ID = tmp.get("id").toString();
				String MESSAGE = tmp.get("message").toString();
				String FROM_ID = ((DynaBean)tmp.get("from")).get("id").toString();
				String FROM_NAME = ((DynaBean)tmp.get("from")).get("name").toString();
				String CREATED_TIME = tmp.get("created_time").toString();
				FacebookFriend FROM = new FacebookFriend(FROM_NAME, FROM_ID);
				feedComments.add(new Comment(ID,FROM, CREATED_TIME, MESSAGE));
			}
			return feedComments;
        }
		catch (MorphException e) {}
		return null;
	}
	
}
