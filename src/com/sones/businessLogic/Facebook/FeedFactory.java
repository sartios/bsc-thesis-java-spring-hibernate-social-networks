package com.sones.businessLogic.Facebook;


import java.util.ArrayList;
 
import net.sf.ezmorph.MorphException;
import net.sf.json.JSONException;

import org.apache.commons.beanutils.DynaBean;

import com.sones.utilities.DateConverter;

public class FeedFactory {
	
	private static FeedFactory instance_ = null;
	private String type = new String();
	private DateConverter dateConverter;
	
	private FeedFactory(){
		dateConverter=new DateConverter();
	}
	
	public static FeedFactory getInstance(){
		if(instance_==null){
			instance_ = new FeedFactory();
		}
		return instance_;
	}
	
	public Feed getFeed(final DynaBean object){
		if(null!=object){
			type = object.get("type").toString();
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
		}
		return new Feed("","",0);
	}
	
	private StatusMessage getStatus(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		StatusMessage feed = new StatusMessage(message,id,from_id);
		feed.setCreatedTime_(created_time);
		feed.setType(type);
		
		this.setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);
		feed.setComments_(this.getComments(object));
		
/*		List<Comment> comments=getComments(object,feed);
		
		if(comments!=null){
			feed.setComments(comments);
		}*/
		return feed;
	}
	
	private Note getNote(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String message = object.get("message").toString();
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String subject = object.get("subject").toString();
		Note feed = new Note(subject, message, id, from_id);
		feed.setCreatedTime_(created_time);
		feed.setType(type);
		this.setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);
		feed.setComments_(this.getComments(object));

		/*List<Comment> comments=getComments(object,feed);
		if(comments!=null){
			feed.setComments(comments);
		}*/
		return feed;
	}
	
	private Link getLink(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String name="";
		Link link = new Link(name,id,from_id);
		link.setCreatedTime_(created_time);
		String url ="";
		String linkURL="";
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
		try{
			url=object.get("picture").toString();
		}
		catch (MorphException ex) {
		}
		try{
			linkURL=object.get("link").toString();
		}
		catch (MorphException ex) {
		}
		link.setLinkURL(linkURL);
		link.setPhotoURL(url);
		link.setType(type);
		this.setCommentNumber(link,object);
		this.setLikeNumber(link, object);
		link.setComments_(this.getComments(object));

		
		return link;
	}
	
	private Picture getPicture(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String caption = "";
		String url="";
		String message="";
		try{
			caption = object.get("caption").toString();
		}
		catch (MorphException e) {
		}
		try{
			url = object.get("picture").toString();
		}
		catch (MorphException e) {
		}
		try{
			message = object.get("message").toString();
		}
		catch (MorphException e) {
		}
		Picture picture = new Picture(caption, id, from_id);
		picture.setCreatedTime_(created_time);
		picture.setUrl(url);
		picture.setMessage(message);
		picture.setType(type);
		picture.setComments_(this.getComments(object));

		this.setCommentNumber(picture,object);
		this.setLikeNumber(picture, object);

		return picture;
	}
	
	private Video getVideo(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		String caption = object.get("caption").toString();
		
		Video video= new Video(caption, id, from_id);
		video.setCreatedTime_(created_time);
		video.setType(type);

		this.setCommentNumber(video,object);
		this.setLikeNumber(video, object);
		video.setComments_(this.getComments(object));
		
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
	
	/**
	 * Creates a simple feed which contains 
	 * feedID ,
	 * creatorID ,
	 * createdTime in unix timestamp,
	 * number of comments,
	 * number of likes
	 * 
	 * @param object
	 * @return Feed with id, creator id, creation time, number of comments and number of likes
	 */
	public Feed getSimpleFeed(final DynaBean object){
		String id = object.get("id").toString();
		String created_time = getCreationDateInUnixTimestamp(object);
		String from_id = ((DynaBean)(object.get("from"))).get("id").toString();
		Feed feed = new Feed(id,from_id);
		setCommentNumber(feed,object);
		this.setLikeNumber(feed, object);
		feed.setCreatedTime_(created_time);
		//feed.setComments_(this.getComments(object));
		return feed;
	}
	
	/**
	 * Return the creation date of a feed in unix time stamp.
	 * @param object 
	 * @return unix time stamp
	 */
	public String getCreationDateInUnixTimestamp(final DynaBean object){
		String created_time = object.get("created_time").toString();
		long date = dateConverter.getUnixTimestamp(created_time);
		return String.valueOf(date);
	}
	
	
	public CommentList getComments(final DynaBean object){
		CommentList comments = new CommentList();
		try{
			String feedID = object.get("id").toString();
			ArrayList list = (ArrayList) ((DynaBean)object.get("comments")).get("data");
			for(int i=0;i<list.size();i++){
				Comment comment = new Comment();
				comment.setId_(((DynaBean)list.get(i)).get("id").toString());
				comment.setMessage(((DynaBean)list.get(i)).get("message").toString());
				comment.setFeedId_(feedID);
				comments.addComment(comment);
			}
		}	
		catch (JSONException ex) {			
		}
		catch (MorphException noComments) {
		}
		return comments;
	}
}
