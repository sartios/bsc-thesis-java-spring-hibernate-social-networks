package com.sones.businessLogic.Facebook;

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
	
	/*public boolean equals(Object aThat){
		if ( this == aThat ){
			return true;
		}
		if ( (!(aThat instanceof StatusMessage))||(!(aThat instanceof Feed)) ){
			return false;
		}
		Feed that = (Feed)aThat;
		return super.getId_().equals(that.getId_());
	}
	
	public int hashCode(){
		return super.getId_().hashCode();
	}*/
}
