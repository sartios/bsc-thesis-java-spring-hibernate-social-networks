package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Feed;
import com.sones.businessLogic.Facebook.FacebookFriend;
import com.sones.businessLogic.Facebook.FacebookGroup;
import com.sones.businessLogic.Facebook.FacebookGroupList;

public class FacebookGroupDao extends AbstractDao {
	
	public void saveOrUpdate(FacebookGroup group) throws DataAccessLayerException{
		super.saveOrUpdate(group);
	}
	
	public void delete(Feed feed) throws DataAccessLayerException{
		super.delete(feed);
	}
	
	public FacebookGroup find(String id){
		return (FacebookGroup) find(FacebookGroup.class,id);
	}
	
	public FacebookGroup findByName(final String name){
		
		FacebookGroup group = null;
		
		try{
			startOperation();
			Query query = session.createQuery("from FacebookGroup where name=:name");
			query.setParameter("name", name);
			List groups = query.list();
			tx.commit();
			group = (FacebookGroup)groups.get(0);
		}
		catch(HibernateException ex){
			tx.rollback();
		}
		finally{
			HibernateUtil.close(session);
		}
		
		return group;
	}
	
	public FacebookGroupList findAll(){
		FacebookGroupList groups = new FacebookGroupList();
		List groupsFromDb = super.findAll(FacebookGroup.class);
		
		for(int i=0;i<groupsFromDb.size();i++){
			groups.addGroup((FacebookGroup) groupsFromDb.get(i));
		}
		return groups;
	}
}
