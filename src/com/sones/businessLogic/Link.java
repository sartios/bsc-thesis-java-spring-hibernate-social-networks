package com.sones.businessLogic;


public class Link extends Feed{
	private String name_;
	private String caption_;
	private String description_;
	private String message_;
	private String photoURL_;
	private String linkURL_;
	
	public Link(){
		super();
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
		name_ = name;
	}

	public String getCaption() {
		return caption_;
	}

	public void setCaption(final String caption) {
		caption_ = caption;
	}

	public String getDescription() {
		return description_;
	}

	public void setDescription(final String description) {
		description_ = description;
	}

	public String getMessage() {
		return message_;
	}

	public void setMessage(final String message) {
		message_ = message;
	}
	
	public boolean find(final String keyword){
		boolean keywordExists = false;
		
		if(this.message_.contains(keyword)){
			keywordExists = true;
		}
		else if(this.caption_.contains(keyword)){
			keywordExists=true;
		}
		else if(this.description_.contains(keyword)){
			keywordExists=true;
		}
		else if(this.name_.contains(keyword)){
			keywordExists = true;
		}
		
		return keywordExists;
	}

	public void setPhotoURL(final String photoURL_) {
		this.photoURL_ = photoURL_;
	}

	public String getPhotoURL() {
		return photoURL_;
	}

	public void setLinkURL(final String linkURL) {
		this.linkURL_ = linkURL;
	}

	public String getLinkURL() {
		return linkURL_;
	}
	
}
