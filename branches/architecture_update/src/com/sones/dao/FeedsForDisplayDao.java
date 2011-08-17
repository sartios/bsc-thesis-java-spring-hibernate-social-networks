package com.sones.dao;

import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FeedsForDisplayDao extends AbstractDao{

	public FeedsForDisplayDao(){
		super();
		super.startOperation();
	}
	
	public void saveFeeds(final Set<String> feeds,final String type){
		int size = feeds.size();
		for(int i=0;i<size;i++){
			String feed_id =(String) feeds.toArray()[i];
			System.out.println(feed_id);
			Query query=session.createSQLQuery("INSERT INTO displayable_feeds (FEED_ID,TYPE) VALUES ('"+feed_id+"','"+type+"');");
			query.executeUpdate();
		}
	}
}
