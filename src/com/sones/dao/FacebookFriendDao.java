package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookFriendList;

public class FacebookFriendDao extends AbstractDao{

	public void saveOrUpdate(FacebookFriend friend) throws DataAccessLayerException{
		if(friendIsOk(friend)){
			super.saveOrUpdate(friend);
		}
	}
	
	public boolean delete(FacebookFriend friend) throws DataAccessLayerException{
		if(this.friendIsOk(friend)){
			return super.delete(friend);
		}
		return false;
	}
	
	public FacebookFriend find(String id){
		if(null!=id){
			return (FacebookFriend) find(FacebookFriend.class,id);
		}
		return null;
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
		catch (IndexOutOfBoundsException indexOut) {
			friend=null;
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
	
	/**
	 * Validate if the facebook friend is ok to be saved
	 * @param friend
	 * @return true if it's ok
	 */
	private boolean friendIsOk(final FacebookFriend friend){
		boolean isOk=false;
		if(null!=friend){
			if((false==friend.getName().isEmpty())&&(false==friend.getId().isEmpty())){
				isOk=true;
			}
		}
		return isOk;
	}
}
