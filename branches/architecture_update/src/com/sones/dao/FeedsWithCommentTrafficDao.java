package com.sones.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.FeedsWithCommentTraffic;

public class FeedsWithCommentTrafficDao extends AbstractDao{
	
	public FeedsWithCommentTrafficDao(){
		super();
	}
	
	public void saveOrUpdate(final FeedsWithCommentTraffic feeds){
		try{
			super.saveOrUpdate(feeds);
		}
		catch(Exception ex){}
	}
	
	public void delete(final FeedsWithCommentTraffic feeds){
		super.delete(feeds);
	}
	
	/**
	 * This method returns the FeedsWithCommentTraffic object without the feeds
	 * @param listID
	 * @return
	 */
	public FeedsWithCommentTraffic find(final long listID){
		return (FeedsWithCommentTraffic)super.find(FeedsWithCommentTraffic.class, listID);
	}
	
	/**
	 * This method returns a list of FeedsWithCommentTraffic objects without their feeds
	 * @param listID
	 * @return
	 */
	public List<FeedsWithCommentTraffic> findAll(){
		return super.findAll(FeedsWithCommentTraffic.class);
	}
	
	/**
	 * Deletes everything from the two tables.
	 */
	public void deleteAll(){
		List<FeedsWithCommentTraffic> list = this.findAll();
		for(int i=0;i<list.size();i++){
			this.delete(list.get(i));
		}
	}
	
	/**
	 * Get a list with their feeds
	 */
	public Map findFeedsOfTheList(final long listID){
		Map feeds=new HashMap<String, String>();
		try{
			startOperation();
			Query query = session.createQuery("from FeedsWithCommentTraffic where id=:listID_");
			query.setParameter("listID_", listID);
			feeds = ((FeedsWithCommentTraffic) query.list().get(0)).getFeeds();
			if(feeds.isEmpty()){}

		}
		catch(HibernateException ex){
			handleException(ex);
		}
		finally{
			//if(!feeds.isEmpty()){System.out.println("Feeds are not empty");}
			HibernateUtil.close(session);
			//if(!feeds.isEmpty()){System.out.println("Feeds are not empty");}
		}
		//if(!feeds.isEmpty()){System.out.println("Feeds are not empty");}

		return feeds;
	}
	
	/**
	 * Get a list with their feeds
	 */
	public FeedsWithCommentTraffic findListWithFeeds(final long listID){
		FeedsWithCommentTraffic list=null;
		try{
			startOperation();
			Query query = session.createQuery("from FeedsWithCommentTraffic where id=:listID_");
			query.setParameter("listID_", listID);
			list = ((FeedsWithCommentTraffic) query.list().get(0));
			if(list.getFeeds().isEmpty()){}
		}
		catch(HibernateException ex){
			handleException(ex);
		}
		finally{
			HibernateUtil.close(session);
		}

		return list;
	}
	
	/**
	 * Finds the dates of lists which contains feeds
	 */
	@SuppressWarnings("unchecked")
	public List<String> getSearchingDatesWhereResultsFound(){
		List<String> searchingDates = new ArrayList<String>();
		List<FeedsWithCommentTraffic> list=null;
		try{
			startOperation();
			Query query = session.createQuery("from FeedsWithCommentTraffic where size(feeds)>0");
			list =  query.list();
			if(list.isEmpty()){}
			else{
				for(int i=0;i<list.size();i++){
					searchingDates.add(((list.get(i))).getDate());
				}
			}
			
		}
		catch(HibernateException ex){
			handleException(ex);
		}
		finally{
			HibernateUtil.close(session);
		}
		return searchingDates;

	}
	
	/**
	 * Finds the list specified by time
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFeedListByTime(final String selectedDate){
		List<String> feeds = new ArrayList<String>();
		FeedsWithCommentTraffic list=null;
		try{
			startOperation();
			Query query = session.createQuery("from FeedsWithCommentTraffic where date=:date_");
			query.setParameter("date_", selectedDate);
			list = ((FeedsWithCommentTraffic) query.list().get(0));
			if(list.getFeeds().isEmpty()){}
			else{
				feeds = this.getFeedsIntoList(list);
			}
		}
		catch(HibernateException ex){
			handleException(ex);
		}
		finally{
			HibernateUtil.close(session);
		}
		return feeds;//searchingDates;

	}
	
	private List<String> getFeedsIntoList(final FeedsWithCommentTraffic list){
		Set<String> keys = list.getFeeds().keySet();
		return new ArrayList<String>(keys);
	}
}
