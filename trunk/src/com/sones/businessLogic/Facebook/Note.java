package com.sones.businessLogic.Facebook;

public class Note extends Feed{

	private String subject_;
	private String message_;
	
	
	public Note(){
		super();
		subject_=new String();
		message_=new String();
	}
	
	public Note(final String subject,final String message,final String feedId,final String userId){
		super(feedId, userId);
		subject_ = subject;
		message_ = message;
	}

	public String getSubject() {
		return subject_;
	}

	public void setSubject(final String subject) {
		if(null!=subject){
			subject_ = subject;
		}
		else if(null==subject){
			subject_="";
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
		else if(existsInSubject(keyword)){
			keywordExists = true;
		}
		else if(super.keywordExistsInComments(keyword)){
			keywordExists=true;
		}
		return keywordExists;
		
	}
	
	private boolean existsInMessage(final String keyword){
		return this.message_.toLowerCase().contains(keyword.toLowerCase());
	}
	
	private boolean existsInSubject(final String keyword){
		return this.subject_.toLowerCase().contains(keyword.toLowerCase());
	}
}
