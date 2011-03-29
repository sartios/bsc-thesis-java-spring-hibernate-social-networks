package com.sones.businessLogic;

public class Note extends Feed{

	private String subject_;
	private String message_;
	
	
	public Note(){
		super();
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
		subject_ = subject;
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
		else if(this.subject_.contains(keyword)){
			keywordExists = true;
		}
		return keywordExists;
		
	}
	
}
