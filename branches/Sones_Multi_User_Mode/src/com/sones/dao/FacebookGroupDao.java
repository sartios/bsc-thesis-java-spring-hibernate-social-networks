package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.Facebook.Source.FacebookGroup;
import com.sones.businessLogic.Facebook.Source.FacebookGroupList;

public class FacebookGroupDao extends AbstractDao {
	
	public void saveOrUpdate(FacebookGroup group) throws DataAccessLayerException{
		if(groupIsOk(group)){
			super.saveOrUpdate(group);
		}
	}
	
	public boolean delete(FacebookGroup group) throws DataAccessLayerException{
		if(groupIsOk(group)){
			return super.delete(group);
		}
		return false;
	}
	
	public FacebookGroup find(String id){
		if(null!=id){
			return (FacebookGroup) find(FacebookGroup.class,id);
		}
		return null;
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
		catch (IndexOutOfBoundsException indexOut) {
			group=null;
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
	
	/**
	 * Validate if the facebook friend is ok to be saved
	 * @param friend
	 * @return true if it's ok
	 */
	private boolean groupIsOk(final FacebookGroup group){
		boolean isOk=false;
		if(null!=group){
			if((false==group.getName().isEmpty())&&(false==group.getId().isEmpty())){
				isOk=true;
			}
		}
		return isOk;
	}
}
