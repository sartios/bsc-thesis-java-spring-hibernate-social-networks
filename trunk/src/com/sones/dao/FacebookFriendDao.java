package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookFriendList;

public class FacebookFriendDao extends AbstractDao{

	public void saveOrUpdate(FacebookFriend friend) throws DataAccessLayerException{
		super.saveOrUpdate(friend);
	}
	
	public void delete(Feed feed) throws DataAccessLayerException{
		super.delete(feed);
	}
	
	public FacebookFriend find(String id){
		return (FacebookFriend) find(FacebookFriend.class,id);
	}
	
	public FacebookFriend findByName(final String name){
		
		FacebookFriend friend = null;
		
		try{
			startOperation();
			Query query = session.createQuery("from FacebookFriend where name=:name");
			query.setParameter("name", name);
			List friends = query.list();
			tx.commit();
			friend = (FacebookFriend)friends.get(0);
		}
		catch(HibernateException ex){
			tx.rollback();
		}
		finally{
			HibernateUtil.close(session);
		}
		
		return friend;
	}
	
	public FacebookFriendList findAll(){
		FacebookFriendList friends = new FacebookFriendList();
		List friendsFromDb = super.findAll(FacebookFriend.class);
		
		for(int i=0;i<friendsFromDb.size();i++){
			friends.addFriend((FacebookFriend) friendsFromDb.get(i));
		}
		return friends;
	}
}
