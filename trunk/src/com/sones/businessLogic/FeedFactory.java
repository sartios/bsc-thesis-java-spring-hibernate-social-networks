package com.sones.businessLogic;

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
		return new Status(id,from,created_time,message);
	}
	
	private Note getNote(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String from_name = ((DynaBean)(object.get("from"))).get("name").toString();
		FacebookFriend from = new FacebookFriend(from_name, from_id);
		String subject = object.get("subject").toString();
		return new Note(id,from,created_time,subject,message);
	}
	
}
