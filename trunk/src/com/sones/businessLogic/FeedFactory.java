package com.sones.businessLogic;


import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;

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
		if(type.equals("link")){
			return getLink(object);
		}
		if(type.equals("photo")){
			return getPicture(object);
		}
		if(type.equals("video")){
			return getVideo(object);
		}
		return null;
	}
	
	private StatusMessage getStatus(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		StatusMessage feed = new StatusMessage(message,id,from_id);
		feed.setCreatedTime_(created_time);
		
		this.setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);
		
/*		List<Comment> comments=getComments(object,feed);
		
		if(comments!=null){
			feed.setComments(comments);
		}*/
		return feed;
	}
	
	private Note getNote(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String subject = object.get("subject").toString();
		Note feed = new Note(subject, message, id, from_id);
		feed.setCreatedTime_(created_time);
		
		this.setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);

		/*List<Comment> comments=getComments(object,feed);
		if(comments!=null){
			feed.setComments(comments);
		}*/
		return feed;
	}
	
	private Link getLink(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String name="";
		Link link = new Link(name,id,from_id);
		link.setCreatedTime_(created_time);
		
		try{
			name = object.get("name").toString();
		}
		catch(MorphException e){
			link.setName(name);
		}
		try{
			String message = object.get("message").toString();
			link.setMessage(message);
		}
		catch(MorphException ex){
			link.setMessage("");
		}		
		try{
			String caption = object.get("caption").toString();
			link.setCaption(caption);
		}
		catch(MorphException ex){
			link.setCaption("");
		}
		try{
			String description = object.get("description").toString();
			link.setDescription(description);
		}
		catch(MorphException ex){
			link.setDescription("");
		}
		
		this.setCommentNumber(link,object);
		this.setLikeNumber(link, object);

		
		return link;
	}
	
	private Picture getPicture(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String caption = "";
		try{
			caption = object.get("caption").toString();
		}
		catch (MorphException e) {
		}
		Picture picture = new Picture(caption, id, from_id);
		picture.setCreatedTime_(created_time);
		this.setCommentNumber(picture,object);
		this.setLikeNumber(picture, object);

		return picture;
	}
	
	private Video getVideo(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String caption = object.get("caption").toString();
		
		Video video= new Video(caption, id, from_id);
		video.setCreatedTime_(created_time);
		this.setCommentNumber(video,object);
		this.setLikeNumber(video, object);
		
		return video;
	}
	
	private void setCommentNumber(Feed feed,final DynaBean object){
		try{
			String numOfCom = ((DynaBean)(object.get("comments"))).get("count").toString();
			feed.setNumberOfComments_(Integer.parseInt(numOfCom));
		}
		catch(MorphException ex){	
			feed.setNumberOfComments_(0);
		}
	}
	
	private void setLikeNumber(Feed feed,final DynaBean object){
		try{
			String numberOfLikes = ((DynaBean)(object.get("likes"))).get("count").toString();
			feed.setNumberOfLikes_(Integer.parseInt(numberOfLikes));
		}
		catch(MorphException ex){
			feed.setNumberOfLikes_(0);
		}
	}
	
	public Feed getSimpleFeed(final DynaBean object){
		
	
		String id = object.get("id").toString();
		String created_time = object.get("created_time").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		Feed feed = new Feed(id,from_id);
		setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);
		feed.setCreatedTime_(created_time);
		return feed;
	}
}
