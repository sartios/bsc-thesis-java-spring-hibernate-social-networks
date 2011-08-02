package com.sones.dao;

import java.util.List;

import javassist.tools.rmi.ObjectNotFoundException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Feeds.FacebookFeedList;

public class FeedDao extends AbstractDao{
	
	public FeedDao(){
		super();
	}
	
	public void saveOrUpdate(Feed feed) throws DataAccessLayerException{
		super.saveOrUpdate(feed);
	}
	
	public void delete(Feed feed) throws DataAccessLayerException{
		try{
			super.delete(feed);
		}
		catch (DataAccessLayerException e) {
			System.out.print("There is association with keyword");
		}
	}
	
	public Feed find(String id){
		try{
			return (Feed) find(Feed.class,id);
		}
		catch(NullPointerException ex){
			
		}
		return null;
	}
	
/*	*//**
	 * Puts all the feeds into a list and return it.
	 * @return FeedList
	 * @throws DataAccessLayerException
	 *//*
	public FeedList findAll() throws DataAccessLayerException{
		List feeds = findAll(Feed.class);
		FeedList list = new FeedList();
		for(int i=0;i<feeds.size();i++){
			list.addFeed((Feed)feeds.get(i));
		}
		return list;
	}
	
	*//**
	 * Find the feeds that has more comments than a boundary.
	 * @param boundary
	 * @return FeedList
	 *//*
	public FeedList findFeedsWithMoreCommentsThan(final int boundary){
		
		FeedList list = new FeedList();
		try{
			startOperation();
			Query query = session.createQuery("from Feed where numberOfComments_>=:limit");
			query.setParameter("limit", boundary);
			List feeds = query.list();
			tx.commit();
			for(int i=0;i<feeds.size();i++){
				list.addFeed((Feed)feeds.get(i));
			}
		}
		catch(HibernateException e){
			tx.rollback();
		}
		finally{
			HibernateUtil.close(session);
		}
		return list;
	}*/
	
	
	/**
	 * Find the feeds from a specific user
	 */
	public FacebookFeedList findUserFeeds(final String userID){
		
		FacebookFeedList userFeeds = new FacebookFeedList();
		try{
			startOperation();
			Query query = session.createQuery("from Feed where fromId_>=:user");
			query.setParameter("user", userID);
			List feeds = query.list();
			tx.commit();
			userFeeds.setUserID(userID);
			for(int i=0;i<feeds.size();i++){
				userFeeds.setFeed(((Feed) feeds.get(i)));
			}
		}
		catch(HibernateException e){
			tx.rollback();
		}
		finally{
			HibernateUtil.close(session);
		}
		return userFeeds;
	}
	
	/**
	 * Saves feed list
	 */
	public void saveUserFeeds(final FacebookFeedList feeds){
		
		
		int size = feeds.getFeeds_().size();
		for(int i=0;i<size;i++){
			saveOrUpdate(feeds.getFeed(i));
		}
		
	}
	
	/**
	 * Returns the creation date of most recent user's feed
	 * @param userID
	 * @return feed's date
	 */
	public String findMostRecentUserFeedDate(final String userID){
		String date="";
		try{
			startOperation();
			Query query = session.createQuery("select max(createdTime_) from Feed where fromId_=:user");
			query.setParameter("user", userID);
			date= (String) query.list().get(0);
			tx.commit();
		}
		catch(HibernateException ex){
			
		}
		return date;
	}
	
	/**
	 * Deletes the given feed list and handles the exceptions
	 * @param feeds
	 */
	public void deleteFeedList(final FacebookFeedList feeds){
		if(null!=feeds){
			try{
				for(int feedIndex=0;feedIndex<feeds.getSize();feedIndex++){
					this.delete(feeds.getFeed(feedIndex));
				}
			}
			catch (HibernateException e) {
				// TODO: handle exception
			}
		}
	}
	
	/**
	 * Finds the feeds between 2 dates
	 * @param startingDate
	 * @param endingDate
	 * @return feed list
	 */
	public FacebookFeedList findFeedsBetween(final String startingDate,final String endingDate){
		FacebookFeedList feeds = new FacebookFeedList();
		if((null!=startingDate)&&(null!=endingDate)){
			try{
				startOperation();
				Query query = session.createQuery("from Feed where createdTime_ between '"+startingDate+"' and '"+endingDate+"'");
				List feedsFromDB = query.list();
				tx.commit();
				System.out.println(feedsFromDB.size());

				feeds.setFeedsFromList(feedsFromDB);
			}
			catch(HibernateException ex){
				
			}
		}
		return feeds;
	}
	
	/**
	 * Return the number of the feeds into the database table
	 */
	public long getNumberOfFeeds(){
		long numberOfFeeds=-1;
		try{
			startOperation();
			Query query = session.createQuery("select count(*) from Feed");
			numberOfFeeds=Long.parseLong(query.list().get(0).toString());
			tx.commit();
		}
		catch(HibernateException ex){
			
		}
		return numberOfFeeds;
	}
	
}
