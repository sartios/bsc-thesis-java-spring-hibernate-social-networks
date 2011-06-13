package com.sones.businessLogic;

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
	private Set<String> comments_;
	
	public Feed(){
		
	}
	public Feed(final String feedId,final String userId,final int numOfCom){
		id_ = feedId;
		fromId_ = userId;
		numberOfComments_ = numOfCom;
	}
	
	public Feed(final String feedId,final String userId){
		id_ = feedId;
		fromId_ = userId;
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
		id_ = id;
	}

	public String getFromId_() {
		return fromId_;
	}

	public void setFromId_(String fromId) {
		fromId_ = fromId;
	}

	public int getNumberOfComments_() {
		return numberOfComments_;
	}

	public void setNumberOfComments_(int numberOfComments) {
		numberOfComments_ = numberOfComments;
	}

	public String getCreatedTime_() {
		return createdTime_;
	}

	public void setCreatedTime_(String createdTime) {
		createdTime_ = createdTime;
	}

	public Set<String> getComments_() {
		return comments_;
	}

	public void setComments_(Set<String> comments) {
		comments_ = comments;
	}
	
	
	public int getNumberOfLikes_() {
		return numberOfLikes_;
	}
	
	
	public void setNumberOfLikes_(int numberOfLikes) {
		numberOfLikes_ = numberOfLikes;
	}
	
	public boolean find(final String keyword){
		return false;
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
		this.type_ = type;
	}
	public String getType() {
		return type_;
	}
}
