package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.util.JDBCExceptionReporter;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keyword_old;
import com.sones.businessLogic.KeywordManager.KeywordList;

/**
 * This class is responsible for persisting Keywords to database
 * @author Sartios
 *
 */
public class KeywordDao extends AbstractDao{

	public KeywordDao(){
		super();
	}
	
	/**
	 * If the keyword is valid and doesn't exist into database, this method saves it to database
	 * @param keyword
	 * @return true if saving completed with success
	 */
	public boolean saveOrUpdate(Keyword keyword){
		if(keywordIsValid(keyword)){
			return super.saveOrUpdate(keyword);
		}
		return false;
	}
	
	/**
	 * Deletes a keyword from the database if the keyword is not null
	 * @param keyword
	 * @return true if the deletion completed with success
	 */
	public boolean delete(Keyword keyword){
		if(null!=keyword){
			return super.delete(keyword);
		}
		return false;
	}
	
	/**
	 * Finds a keyword by his ID
	 * @param keywordID
	 * @return
	 */
	public Keyword getKeywordByID(final String keywordID){
		if(null!=keywordID){
			return (Keyword)super.find(Keyword.class, keywordID);
		}
		return null;
	}
	
	/**
	 * Check if keyword has not empty ID and value. If it does then it's valid
	 * @param keyword
	 * @return true if is valid
	 */
	private boolean keywordIsValid(final Keyword keyword){
		if(null!=keyword){
			if((keyword.getKeywordID_().isEmpty()==false)&&(keyword.getValue_().isEmpty()==false)){
				return true;
			}
		}
		return false;
	}

}
