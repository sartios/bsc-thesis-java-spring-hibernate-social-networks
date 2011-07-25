package com.sones.businessLogic.Facebook;

import com.sones.businessLogic.KeywordManager.KeywordList;

/**
 * This class is a facebook user. Every facebook user
 * has a unique ID in facebook's world. And through
 * this ID, can have feeds, wall and all the services
 * that facebook provides.
 * 
 * @author Sartios
 *
 */
public class FacebookUser {

	/**
	 * The ID that user has in facebook
	 */
	private String facebookUserID_;
	
	/**
	 * The ID that user has in our application
	 */
	private String applicationUserID_;
	
	/**
	 * User's feed list. There are not the feeds that he creates, but the feeds that
	 * he downloads for proceeding search.
	 */
	private FeedList feeds_;
	
	/**
	 * The keyword list that he created
	 */
	private KeywordList keywords_;
	
	
	public FacebookUser(){
	}
	
	/**
	 * Constructor Set the {@link #facebookUserID_} and {@link #applicationUserID_}
	 * @param facebookID the ID of the user in facebook
	 * @param applicationUserID the ID of the user in the application
	 */
	public FacebookUser(final String facebookID,final String applicationUserID){
		setFacebookUserID_(facebookID);
		setApplicationUserID_(applicationUserID);
	}
	


	/**
	 * Sets the {@link #facebookUserID_} to userID, if it's not null
	 * @param facebookID
	 */
	public void setFacebookUserID_(String facebookID) {
		if(null!=facebookID){
			this.facebookUserID_ = facebookID;
		}
		else{
			this.facebookUserID_=new String("");
		}
	}

	/**
	 * @return {@link #facebookUserID_}
	 */
	public String getFacebookUserID_() {
		return facebookUserID_;
	}

	/**
	 * Sets the {@link #feeds_} to feeds if they aren't null. The given feeds,
	 * must belong to nobody or to the user. Otherwise can't be set.
	 * @param feeds_
	 */
	public void setFeeds_(final FeedList feeds) {
		if(null!=feeds){
			if((feeds.getUserID().equals(this.facebookUserID_))){
				this.feeds_=feeds;
			}
			else if(feeds.getUserID().equals("")){
				this.feeds_=feeds;
				this.feeds_.setUserID(this.facebookUserID_);
			}
			else{
				buildFeedsIfNeeded();
			}
		}
		else{
			buildFeedsIfNeeded();
		}
	}
	
	/**
	 * If the feeds are null, creates and empty list for the user
	 */
	private void buildFeedsIfNeeded(){
		if(null==feeds_){
			feeds_=new FeedList();
			feeds_.setUserID(facebookUserID_);
		}
	}

	/**
	 * @return {@link #feeds_}
	 */
	public FeedList getFeeds_() {
		return feeds_;
	}

	/**
	 * Sets the {@link #applicationUserID_} to the param, if it's not null
	 * @param applicationUserID
	 */
	public void setApplicationUserID_(String applicationUserID) {
		if(null!=applicationUserID){
			this.applicationUserID_ = applicationUserID;
		}
		else{
			this.applicationUserID_="";
		}
	}

	/**
	 * @return the user's application ID
	 */
	public String getApplicationUserID_() {
		return applicationUserID_;
	}

	/**
	 * Sets the user keyword list with the given keyword list
	 * @param keywords
	 */
	public void setKeywords_(KeywordList keywords) {
		if(null!=keywords){
			if(keywords.belongToUser(this.applicationUserID_)==true){
				this.keywords_ = keywords;
			}
			else if(keywords.belongToNobody()){
				keywords.setApplicationUserID_(this.applicationUserID_);
				this.keywords_=keywords;
			}
			else if(keywords.belongToUser(this.applicationUserID_)==false){
				this.createEmptyKeywordList();
			}
		}
		else if(null==this.keywords_){
			createEmptyKeywordList();
		}
	}
	
	/**
	 * Creates empty keyword list if the {@link #keywords_} is empty
	 */
	private void createEmptyKeywordList(){
		if(null==this.keywords_){
			this.keywords_=new KeywordList();
			this.keywords_.setApplicationUserID_(applicationUserID_);
		}
	}

	/**
	 * Returns the keyword list of the user
	 * @return user's keyword list
	 */
	public KeywordList getKeywords_() {
		return keywords_;
	}

}
