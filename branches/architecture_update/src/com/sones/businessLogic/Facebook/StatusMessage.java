package com.sones.businessLogic.Facebook;

public class StatusMessage extends Feed{
	private String message_;
	
	public StatusMessage(){
		super();
		message_=new String();
	}

	public StatusMessage(final String message,final String feedId,final String userId){
		super(feedId, userId);
		message_ = message;
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
		try{
			if(existsInMessage(keyword)){
				keywordExists = true;
			}
			if(super.keywordExistsInComments(keyword)){
				keywordExists=true;
			}
		}
		catch(NullPointerException ex){
			System.out.println("Null keyword");
		}
		return keywordExists;
	}
	
	private boolean existsInMessage(final String keyword){
		return message_.toLowerCase().contains(keyword.toLowerCase());
	}
}
