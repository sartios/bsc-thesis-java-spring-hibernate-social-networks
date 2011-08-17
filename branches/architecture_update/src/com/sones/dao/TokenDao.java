package com.sones.dao;

import java.util.List;

import com.exceptions.DataAccessLayerException;
import com.sones.businessLogic.Facebook.FacebookToken;

public class TokenDao extends AbstractDao{
	
	public void saveOrUpdate(FacebookToken token) throws DataAccessLayerException{
		super.saveOrUpdate(token);
	}
	
	public void delete(FacebookToken token) throws DataAccessLayerException{
		super.delete(token);
	}
	
	public FacebookToken find(String id){
		return (FacebookToken) find(FacebookToken.class,id);
	}
	
	public List findAll() throws DataAccessLayerException{
		return findAll(FacebookToken.class);
	}
}
