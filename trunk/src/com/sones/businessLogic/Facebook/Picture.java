package com.sones.businessLogic.Facebook;


public class Picture extends Feed{
	
	private String caption_;
	private String url_;
	private String message_;
	
	public Picture(){
		super();
		caption_=new String();
		url_=new String();
		message_=new String();
	}
	
	public Picture(final String caption,final String feedId,final String userId){
		super(feedId, userId);
		caption_ = caption;
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
	
	public String getUrl(){
		return url_;
	}
	
	public void setUrl(final String url){
		if(null!=url){
			url_ = url;
		}
		else if(null==url){
			url_="";
		}
	}
	
	public void setMessage(final String message){
		if(null!=message){
			message_ = message;
		}
		else if(null==message){
			message_="";
		}
	}
	
	public String getMessage(){
		return message_;
	}
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		
		if(existsInMessage(keyword)){
			keywordExists = true;
		}
		if(existsInCaption(keyword)){
			keywordExists = true;
		}
		if(super.keywordExistsInComments(keyword)){
			keywordExists=true;
		}
		return keywordExists;
	}
	
	private boolean existsInCaption(final String keyword){
		return this.caption_.toLowerCase().contains(keyword.toLowerCase());
	}
	
	private boolean existsInMessage(final String keyword){
		return this.message_.toLowerCase().contains(keyword.toLowerCase());
	}
}
