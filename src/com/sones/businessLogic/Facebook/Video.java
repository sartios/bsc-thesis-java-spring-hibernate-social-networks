package com.sones.businessLogic.Facebook;

public class Video extends Feed{

	private String caption_;
	
	public Video(){
		super();
		caption_=new String();
	}
	
	public Video(final String caption,final String feedId,final String userId){
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
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		
		if(existsInCaption(keyword)){
			keywordExists = true;
		}
		
		return keywordExists;
	}
	
	private boolean existsInCaption(final String keyword){
		return caption_.toLowerCase().contains(keyword.toLowerCase());
	}
}
