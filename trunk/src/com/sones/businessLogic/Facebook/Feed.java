package com.sones.businessLogic.Facebook;

import java.util.HashSet;
import java.util.Set;

public class Feed {
	
	private String id_;
	private String fromId_;
	private int numberOfComments_;
	private String createdTime_;
	private int numberOfLikes_;
	private String type_;
	
	/**
	 * Comments' id for this feed
	 */
	private CommentList comments_;
	
	public Feed(){
		id_=new String();
		fromId_=new String();
		createdTime_=new String();
		type_=new String();
		comments_=new CommentList();
	}
	
	public Feed(final String feedId,final String userId,final int numOfCom){
		id_ = feedId;
		fromId_ = userId;
		numberOfComments_ = numOfCom;
		createdTime_=new String();
		type_=new String();
		comments_=new CommentList();
	}
	
	public Feed(final String feedId,final String userId){
		id_ = feedId;
		fromId_ = userId;
		createdTime_=new String();
		type_=new String();
		comments_=new CommentList();
		
	}
	
	public Feed(final Feed feed){
		id_ = feed.getId_();
		fromId_ = feed.getFromId_();
		numberOfComments_ = feed.getNumberOfComments_();
		createdTime_ = feed.getCreatedTime_();
	}

	public String getId_() {
		return id_;
	}

	public void setId_(String id) {
		if(null!=id){
			id_ = id;
		}
		else{
			id_="";
		}
	}

	public String getFromId_() {
		return fromId_;
	}

	public void setFromId_(String fromId) {
		if(null!=fromId){
			fromId_ = fromId;
		}
		else if(null==fromId){
			fromId_="";
		}
	}

	public int getNumberOfComments_() {
		return numberOfComments_;
	}

	public void setNumberOfComments_(int numberOfComments) {
		if(numberOfComments>0){
			numberOfComments_ = numberOfComments;
		}
		else if(numberOfComments<=0){
			numberOfComments_=0;
		}
	
	}

	public String getCreatedTime_() {
		return createdTime_;
	}

	public void setCreatedTime_(String createdTime) {
		if(null!=createdTime){
			createdTime_ = createdTime;
		}
		else if(null==createdTime){
			createdTime_="";
		}
	}

	public CommentList getComments_() {
		return comments_;
	}

	public void setComments_(CommentList comments) {
		if(null!=comments){
			comments_ = comments;
		}
	}
	
	
	public int getNumberOfLikes_() {
		return numberOfLikes_;
	}
	
	
	public void setNumberOfLikes_(int numberOfLikes) {
		if(numberOfLikes>0){
			numberOfLikes_ = numberOfLikes;
		}
		else if(numberOfLikes<0){
			numberOfLikes_=0;
		}
	}
	
	public boolean find(final String keyword){
		if(this.keywordExistsInComments(keyword)){
			return true;
		}
		return false;
	}
	
	public boolean keywordExistsInComments(final String keyword){
		return comments_.contain(keyword);
	}
	
	public boolean equals(Object aThat){
		if ( this == aThat ){
			return true;
		}
	//	if ( !(aThat instanceof Feed) ){
	//		return false;
	//	}
		Feed that = (Feed)aThat;
		return id_.equals(that.getId_().toString());
	}
	
	public int hashCode(){
		return id_.hashCode();
	}
	
	public void setType(final String type) {
		if(null!=type){
			this.type_ = type;
		}
		else if(null==type){
			this.type_="";
		}
	}
	public String getType() {
		return type_;
	}
}
