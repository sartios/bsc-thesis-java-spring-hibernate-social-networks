package com.sones.businessLogic.KeywordManager;

import com.sones.dao.KeywordListDao;

public class KeywordListFactory {

	private static KeywordListFactory instance_=null;
	
	private KeywordListFactory(){
	}
	
	public static KeywordListFactory getInstance(){
		if(null==instance_){
			instance_=new KeywordListFactory();
		}
		return instance_;
	}
	
	public KeywordList getKeywordList(final String from,final String userID){
		if(null!=from){
			if(from.equals("database")){
				return this.fromDatabase(userID);
			}
		}
		return null;
	}
	
	private KeywordList fromDatabase(final String userID){
		KeywordListDao dao = new KeywordListDao();
		return dao.getUserKeywordsByUserID(userID);
	}
}
