package com.sones.businessLogic.Facebook;


public class Link extends Feed{
	private String name_;
	private String caption_;
	private String description_;
	private String message_;
	private String photoURL_;
	private String linkURL_;
	
	public Link(){
		super();
		name_ = new String();
		caption_=new String();
		description_=new String();
		message_=new String();
		photoURL_=new String();
		linkURL_=new String();
	}
	
	public Link(final String name,final String caption, final String description, final String message,final String feedId,final String userId){
		super(feedId,userId);
		name_ = name;
		caption_ = caption;
		description_ = description;
		message_ = message;
	}
	
	public Link(final String name,final String feedId,final String userId){
		super(feedId,userId);
		name_ = name;
	}
	
	public String getName() {
		return name_;
	}

	public void setName(final String name) {
		if(null!=name){
			name_ = name;
		}
		else if(null==name){
			name_="";
		}
	}

	public String getCaption() {
		return caption_;
	}

	public void setCaption(final String caption) {
		if(null!=caption){
			caption_ = caption;
		}
		else if(null==caption){
			caption_="";
		}
	}

	public String getDescription() {
		return description_;
	}

	public void setDescription(final String description) {
		if(null!=description){
			description_ = description;
		}
		else if(null==description){
			description_="";
		}
	}

	public String getMessage() {
		return message_;
	}

	public void setMessage(final String message) {
		if(null!=message){
			message_ = message;
		}
		else if(null==message){
			message_="";
		}
	}
	
	public boolean find(final String keyword){
		boolean keywordExists = false;
		
		if(existsInMessage(keyword)){
			keywordExists = true;
		}
		else if(existsInCaption(keyword)){
			keywordExists=true;
		}
		else if(existsInDescription(keyword)){
			keywordExists=true;
		}
		else if(existsInName(keyword)){
			keywordExists = true;
		}
		else if(super.keywordExistsInComments(keyword)){
			keywordExists=true;
		}
		
		return keywordExists;
	}

	public void setPhotoURL(final String photoURL) {
		if(null!=photoURL){
			photoURL_ = photoURL;
		}
		else if(null==photoURL){
			photoURL_="";
		}
	}

	public String getPhotoURL() {
		return photoURL_;
	}

	public void setLinkURL(final String linkURL) {
		if(null!=linkURL){
			linkURL_ = linkURL;
		}
		else if(null==linkURL){
			linkURL_="";
		}	
	}

	public String getLinkURL() {
		return linkURL_;
	}
	
	private boolean existsInMessage(final String keyword){
		String linkMessage = this.message_;
		return linkMessage.toLowerCase().contains(keyword.toLowerCase());
	}
	
	private boolean existsInCaption(final String keyword){
		String linkCaption = this.caption_;
		return linkCaption.toLowerCase().contains(keyword.toLowerCase());
	}
	
	private boolean existsInDescription(final String keyword){
		String linkDescription = this.description_;
		return linkDescription.toLowerCase().contains(keyword.toLowerCase());
	}
	private boolean existsInName(final String keyword){
		String linkName = this.name_;
		return linkName.toLowerCase().contains(keyword.toLowerCase());
	}
	
}
