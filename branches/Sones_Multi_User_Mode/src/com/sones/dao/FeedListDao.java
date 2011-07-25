package com.sones.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.cfg.FkSecondPass;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.FeedList;

/**
 * This class saves the feeds of a user with their comments in current time.
 * 
 * @author Sartios
 *
 */
public class FeedListDao extends AbstractDao{

	public FeedListDao(){
		super();
	}
	
	/**
	 * It saves the feeds of the user into FACEBOOK_USERS_download_FACEBOOK_FEEDS.
	 * Uses the FACEBOOK_USER_ID, the FEED_ID and the NUMBER_OF_COMMENTS.
	 * The feed must exist into FACEBOOK_FEEDS table. If it doesn't we save it first there
	 * and then in this table
	 * @param feeds of the user
	 * @return true if the feeds have saved
	 */
	public boolean saveUserFeeds(final FeedList feeds){
		boolean areSaved = false;
		String facebookUserID = feeds.getUserID();
		if(null!=feeds){
			for(int i=0;i<feeds.getSize();i++){
				Feed feed = feeds.getFeed(i);
				try{
					this.executeSaveQuery(facebookUserID, feed);
					areSaved = true;
				}
				catch (HibernateException e) {
					tx.rollback();
					FeedDao dao = new FeedDao();
					dao.saveOrUpdate(feed);
					this.executeSaveQuery(facebookUserID, feed);
					areSaved = true;
				}
				catch (Exception ex) {
					tx.rollback();
					areSaved = false;
				}
				finally{
					HibernateUtil.close(session);
				}
			}
		}
		return areSaved;
	}
	
	/**
	 * It creates the query for inserting the elements into the database and executing it.
	 * @param facebookUserID
	 * @param feed
	 */
	private void executeSaveQuery(String facebookUserID,Feed feed){
		startOperation();
		Query query = session.createSQLQuery("INSERT INTO facebook_users_download_facebook_feeds VALUES(:userID,:feedID,:numOfCom)");
		query.setParameter("userID",facebookUserID);
		query.setParameter("feedID", feed.getId_());
		query.setParameter("numOfCom",feed.getNumberOfComments_());
		query.executeUpdate();
		tx.commit();
	}
	
	/**
	 * Delete the feeds of a user. Which feeds will be deleted are specified by the 
	 * feed list
	 * 
	 * @param feeds
	 * @return true if the deletion was ok
	 */
		public boolean deleteUserFeeds(final FeedList feeds){
			boolean areDeleted = false;
			String facebookUserID = feeds.getUserID();
			if(null!=feeds){
				for(int i=0;i<feeds.getSize();i++){
					Feed feed = feeds.getFeed(i);
					try{
						this.executeDeleteQuery(facebookUserID, feed);
						areDeleted = true;
					}
					catch (HibernateException e) {
						areDeleted = false;
					}
					finally{
						HibernateUtil.close(session);
					}
				}
			}
			return areDeleted;
		}
	
	/**
	 * It creates the query for deleting the elements from the database and executing it.
	 * @param facebookUserID
	 * @param feed to be deleted
	 */
	private void executeDeleteQuery(String facebookUserID,Feed feed){
		startOperation();
		Query query=session.createSQLQuery("DELETE FROM facebook_users_download_facebook_feeds WHERE FACEBOOK_USER_ID=:userID and FACEBOOK_FEED_ID=:feedID");
		query.setParameter("userID",facebookUserID);
		query.setParameter("feedID", feed.getId_());
		query.executeUpdate();
		tx.commit();
	}
	
	/**
	 * It returns the user feeds
	 * @param userID of facebook user
	 * @return feedList of the user
	 */
	public FeedList findUserFeeds(final String userID){
		FeedList feeds = null;
		if(null!=userID){
			try{
				feeds=executeFindFeedListQuery(userID);
				feeds.setUserID(userID);
			}
			catch (HibernateException ex) {
				tx.rollback();
			}
			finally{
				HibernateUtil.close(session);
			}
		}
		return feeds;
	}
	
	
	/**
	 * It creates the query for deleting the elements from the database and executing it.
	 * @param facebookUserID
	 * @param feed to be deleted
	 */
	private FeedList executeFindFeedListQuery(String facebookUserID){
		startOperation();
		Query query=session.createSQLQuery("SELECT "+
												"F.FACEBOOK_FEED_ID     	as      FACEBOOK_FEED_ID,"+
												"L.NUMBER_OF_COMMENTS 		as     	NUMBER_OF_COMMENTS,"+
												"F.CREATOR_ID              	as      CREATOR_ID,"+
												"F.CREATION_TIME           	as      CREATION_TIME"+
											" FROM "+
													"facebook_users_download_facebook_feeds AS L "+
												"INNER JOIN "+
													"facebook_feeds AS F "+
												"ON L.FACEBOOK_FEED_ID = F.FACEBOOK_FEED_ID "+
											"WHERE "+
												"L.FACEBOOK_USER_ID = :userID");
		query.setParameter("userID",facebookUserID);
		List feeds=query.list();
		tx.commit();
		return this.getFeedList(feeds);
	}
	
	/**
	 * It creates the feed list from the {@link #executeFindFeedListQuery(String)} results
	 * @param feeds
	 * @return
	 */
	private FeedList getFeedList(final List feeds){
		FeedList feedList = new FeedList();
		if(null!=feeds){
			String feedID=new String();
			String creatorID=new String();
			String creationTime=new String();
			int numberOfComments;
			for(int i=0;i<feeds.size();i++){
				Object[] object = (Object[]) feeds.get(i);
				feedID=object[0].toString();
				numberOfComments=Integer.parseInt(object[1].toString());
				creatorID=object[2].toString();
				creationTime=object[3].toString();
				feedList.setFeed(new Feed(feedID,creatorID,numberOfComments,creationTime));
			}
		}
		return feedList;
	}
}
