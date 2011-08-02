package com.sones.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.KeywordManager.Keyword;

/**
 * This class is responsible for persisting Keyword searching results
 * 
 * @author Sartios
 *
 */
public class KeywordSearchResultDao extends AbstractDao{
	
	/**
	 * Saves the feed list of a keyword, which comes as a result from a keyword searching
	 */
	public boolean saveKeywordSearchingResults(final Keyword keyword){
		boolean savingSucceed = false;
		if(keywordHasAValidKeywordList(keyword)){
			int numberOfFeeds = keyword.getFeeds_().getSize();
			String facebookUserID = keyword.getFeeds_().getUserID();
			String keywordID = keyword.getKeywordID_();
			for(int i=0;i<numberOfFeeds;i++){
				String feedID = keyword.getFeeds_().getFeedID(i);
				savingSucceed=saveKeywordSearchingResultsIntoDatabase(facebookUserID,keywordID,feedID);
			}
		}
		return savingSucceed;
	}
	
	/**
	 * Deletes the feed list of a keyword, which is a result from keyword searching
	 */
	public boolean deleteKeywordSearchingResults(final Keyword keyword){
		boolean deletingSucceed = false;
		if(keywordHasAValidKeywordList(keyword)){
			int numberOfFeeds = keyword.getFeeds_().getSize();
			String facebookUserID = keyword.getFeeds_().getUserID();
			String keywordID = keyword.getKeywordID_();
			for(int i=0;i<numberOfFeeds;i++){
				String feedID = keyword.getFeeds_().getFeedID(i);
				deletingSucceed=deleteKeywordSearchingResultsFromDatabase(facebookUserID,keywordID,feedID);
			}
		}
		return deletingSucceed;
	}
	
	/**
	 * Delete the results of keyword searching for a user's keyword 
	 * @param facebookUserID
	 * @param keywordID
	 * @param feedID 
	 */
	private boolean deleteKeywordSearchingResultsFromDatabase(final String facebookUserID,final String keywordID,final String feedID){
		boolean deletingSucceed = false;
		if(facebookUserIsValid(facebookUserID)){
			if(keywordIDIsValid(keywordID)){
				if(feedIDIsValid(feedID)){
					try{
						startOperation();
						Query query = getDeleteKeywordSearchingResultsFromDatabaseQuery();
						query.setParameter("keyword", keywordID);
						query.setParameter("feed", feedID);
						query.setParameter("facebookUser", facebookUserID);
						query.executeUpdate();
						tx.commit();
						deletingSucceed =true;
					}
					catch (HibernateException e) {
						tx.rollback();
					}
					finally{
						HibernateUtil.close(session);
					}
				}
			}
		}
		return deletingSucceed;
	}
	
	
	/**
	 * The values we must bind are :
	 * keyword
	 * feed
	 * facebookUser
	 * @return Query for deleting data from keywords_exist_into_facebook_feeds_from_facebook_users
	 */
	private Query getDeleteKeywordSearchingResultsFromDatabaseQuery(){
		String sqlString = "DELETE "+
							"FROM "+
								"keywords_exist_into_facebook_feeds_from_facebook_users "+
							"WHERE "+
								"KEYWORD_ID=:keyword and " +
								"FACEBOOK_FEED_ID=:feed and " +
								"FACEBOOK_USER_ID=:facebookUser";
		return session.createSQLQuery(sqlString);
	}
	
	/**
	 * Save into database the result of a search. The data its saves are :
	 * facebookUserID => keywordID => feedID, which means that for the facebookUserID
	 * who had create the keywordID we found a feedID which contains the value of keywordID
	 * 
	 * @param facebookUserID
	 * @param keywordID
	 * @param feedID 
	 */
	private boolean saveKeywordSearchingResultsIntoDatabase(final String facebookUserID,final String keywordID,final String feedID){
		boolean savingSucceed = false;
		if(facebookUserIsValid(facebookUserID)){
			if(keywordIDIsValid(keywordID)){
				if(feedIDIsValid(feedID)){
					try{
						startOperation();
						Query query = getSaveKeywordSearchingResultsIntoDatabaseQuery();
						query.setParameter("keyword", keywordID);
						query.setParameter("feed", feedID);
						query.setParameter("facebookUser", facebookUserID);
						query.executeUpdate();
						tx.commit();
						savingSucceed =true;
					}
					catch (HibernateException e) {
						tx.rollback();
					}
					finally{
						HibernateUtil.close(session);
					}
				}
			}
		}
		return savingSucceed;
	}
	
	/**
	 * The values we must bind are :
	 * keyword
	 * feed
	 * facebookUser
	 * @return Query for inserting data into keywords_exist_into_facebook_feeds_from_facebook_users
	 */
	private Query getSaveKeywordSearchingResultsIntoDatabaseQuery(){
		String sqlString = "INSERT INTO keywords_exist_into_facebook_feeds_from_facebook_users VALUES (:keyword,:feed,:facebookUser)";
		return session.createSQLQuery(sqlString);
	}
	
	/**
	 * @return true if feed is valid
	 */
	private boolean feedIDIsValid(final String feedID){
		if(null!=feedID){
			if(feedID.isEmpty()==false){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return true if keyword is valid
	 */
	private boolean keywordIDIsValid(final String keywordID){
		if(null!=keywordID){
			if(keywordID.isEmpty()==false){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return true if facebook user is valid
	 */
	private boolean facebookUserIsValid(final String facebookUserID){
		if(null!=facebookUserID){
			if(facebookUserID.isEmpty()==false){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if the keyword has a valid keyword list
	 */
	private boolean keywordHasAValidKeywordList(final Keyword keyword){
		if(null!=keyword){
			if((feedListBelongsToAUser(keyword))){
				if(feedListContainsFeeds(keyword)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Check if the feed list belongs to a user
	 */
	private boolean feedListBelongsToAUser(final Keyword keyword){
		if(keyword.getFeeds_().getUserID().isEmpty()==false){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the keyword's feed list contain feeds
	 */
	private boolean feedListContainsFeeds(final Keyword keyword){
		if(keyword.getFeeds_().isEmpty()==false){
			return true;
		}
		return false;
	}
	

}
