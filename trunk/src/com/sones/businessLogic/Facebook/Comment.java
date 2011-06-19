package com.sones.businessLogic.Facebook;

public class Comment {
	private String id_;
	private String feedId_;
	private String message_;
	
	public Comment(){
		id_=new String();
		feedId_=new String();
		message_=new String();
	}
	
	public Comment(final String id,final String message,final String feedId){
		id_=new String();
		feedId_=new String();
		message_=new String();
		this.setId_(id);
		this.setMessage(message);
		this.setFeedId_(feedId);
	}

	public String getId_() {
		return id_;
	}

	public void setId_(final String id) {
		if(null!=id){
			id_ = id;
		}
		else{
			id_="";
		}
	}

	public String getFeedId_() {
		return feedId_;
	}

	public void setFeedId_(final String feedId) {
		if(null!=feedId){
			feedId_ = feedId;
		}
		else{
			feedId_="";
		}	
	}
	
	public void setMessage(final String message){
		if(null!=message){
			message_ = message;
		}
		else{
			message_="";
		}	
	}
	
	public String getMessage(){
		return message_;
	}
	
	public boolean find(final String keyword){
		
		boolean keywordExists = false;
		try{
			if(existsInMessage(keyword)){
				keywordExists = true;
			}
		}
		catch(NullPointerException ex){
			keywordExists = false;
		}
		return keywordExists;
	}
	
	private boolean existsInMessage(final String keyword){
		return message_.toLowerCase().contains(keyword.toLowerCase());
	}
	
	public boolean equals(Object aThat){
		if ( this == aThat ){
			return true;
		}
	//	if ( !(aThat instanceof Feed) ){
	//		return false;
	//	}
		Comment that = (Comment)aThat;
		return id_.equals(that.getId_().toString());
	}
	
	public int hashCode(){
		return id_.hashCode();
	}
	
}
