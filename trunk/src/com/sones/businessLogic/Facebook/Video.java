package com.sones.businessLogic.Facebook;

public class Video extends Feed{

	private String caption_;
	
	public Video(){
		super();
	}
	
	public Video(final String caption,final String feedId,final String userId){
		super(feedId, userId);
		caption_ = caption;
	}

	public String getCaption() {
		return caption_;
	}

	public void setCaption(final String caption) {
		caption_ = caption;
	}
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		
		if(this.caption_.contains(keyword)){
			keywordExists = true;
		}
		
		return keywordExists;
	}
}
