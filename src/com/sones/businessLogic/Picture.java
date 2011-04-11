package com.sones.businessLogic;

public class Picture extends Feed{
	
	private String caption_;
	
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
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		
		if(this.caption_.contains(keyword)){
			keywordExists = true;
		}
		
		return keywordExists;
	}
	
}
