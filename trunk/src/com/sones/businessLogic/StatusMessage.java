package com.sones.businessLogic;

public class StatusMessage extends Feed{
	private String message_;
	
	public StatusMessage(){
		super();
	}

	public StatusMessage(final String message,final String feedId,final String userId){
		super(feedId, userId);
		message_ = message;
	}
	
	public String getMessage() {
		return message_;
	}

	public void setMessage(final String message) {
		message_ = message;
	}
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		try{
			if(this.message_.contains(keyword)){
				keywordExists = true;
			}
		}
		catch(NullPointerException ex){
			System.out.println("Null keyword");
		}
		return keywordExists;
	}
}
