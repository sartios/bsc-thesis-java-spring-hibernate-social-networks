package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.Session;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Feed;
import com.sones.businessLogic.FeedList;
import com.sones.businessLogic.Status;

public class FeedDao extends AbstractDao{
	
	public FeedDao(){
		super();
	}
	
	public void saveOrUpdate(Feed feed) throws DataAccessLayerException{
		super.saveOrUpdate(feed);
	}
	
	public void delete(Feed feed) throws DataAccessLayerException{
		super.delete(feed);
	}
	
	public Feed find(String id){
		return (Feed) find(Feed.class,id);
	}
	
	/**
	 * Puts all the feeds into a list and return it.
	 * @return FeedList
	 * @throws DataAccessLayerException
	 */
	public FeedList findAll() throws DataAccessLayerException{
		List feeds = findAll(Feed.class);
		FeedList list = new FeedList();
		for(int i=0;i<feeds.size();i++){
			list.addFeed((Feed)feeds.get(i));
		}
		return list;
	}
	
	/**
	 * Find the feeds that has more comments than a boundary.
	 * @param boundary
	 * @return FeedList
	 */
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
	}
	
	
}
