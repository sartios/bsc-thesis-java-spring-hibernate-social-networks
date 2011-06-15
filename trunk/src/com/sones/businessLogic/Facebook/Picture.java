package com.sones.businessLogic.Facebook;


public class Picture extends Feed{
	
	private String caption_;
	private String url_;
	private String message_;
	
	public Picture(){
		super();
	}
	
	public Picture(final String caption,final String feedId,final String userId){
		super(feedId, userId);
		caption_ = caption;
	}

	public String getCaption() {
		return caption_;
	}

	public void setCaption(final String caption) {
		caption_ = caption;
	}
	
	public String getUrl(){
		return url_;
	}
	
	public void setUrl(final String url){
		url_ = url;
	}
	
	public void setMessage(final String message){
		message_ = message;
	}
	
	public String getMessage(){
		return message_;
	}
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		
		if(this.caption_.contains(keyword)){
			keywordExists = true;
		}
		if(this.message_.contains(keyword)){
			keywordExists = true;
		}
		
		return keywordExists;
	}
	
}
