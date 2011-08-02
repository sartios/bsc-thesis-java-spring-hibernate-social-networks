package com.sones.dao;

import java.util.ArrayList;
import java.util.List;

import com.exceptions.DataAccessLayerException;
import com.sones.businessLogic.Facebook.FacebookSearchingList;
import com.sones.businessLogic.Facebook.Source.FacebookFriend;
import com.sones.businessLogic.Facebook.Source.FacebookGroup;

public class FacebookSearchingListDao extends AbstractDao{


	public void saveOrUpdate(FacebookSearchingList sources) throws DataAccessLayerException{
		
	}
	
	public void delete(FacebookSearchingList sources) throws DataAccessLayerException{
		
	}
	
	public FacebookSearchingList find(String id){
		return null;
	}
	
	public List findNames(final FacebookSearchingList sources){
		List<String> names = new ArrayList<String>();
		FacebookFriendDao friendDao = new FacebookFriendDao();
		FacebookGroupDao groupDao = new FacebookGroupDao();
		FacebookFriend friend;
		FacebookGroup group;
		String id;
		
		while(null!=(id=sources.getID())){
			if(null!=(friend = friendDao.find(id))){
				names.add(friend.getName());
			}
			else if(null!=(group = groupDao.find(id))){
				names.add(group.getName());
			}
		}
		return names;
	}
}
