package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.util.JDBCExceptionReporter;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Keyword;
import com.sones.businessLogic.KeywordList;

public class KeywordDao extends AbstractDao{

	public KeywordDao(){
		super();
	}
	
	public void saveOrUpdate(Keyword keyword){
		try{
			super.saveOrUpdate(keyword);
			
/*			System.out.println("Object " +keyword.getValue()+" saved!");
*/
		}
		catch(Exception ex){}
	}
	
	public void delete(Keyword keyword){
		super.delete(keyword);
	}
	
	public Keyword find(final long keywordID){
		return (Keyword)super.find(Keyword.class, keywordID);
	}
	
	public List findAll(){
		return super.findAll(Keyword.class);
	}
	
	public KeywordList getKeywordList(){
		KeywordList keywords_ = new KeywordList();
		List keys = this.findAll();
		for(int i=0;i<keys.size();i++){
			Keyword keyword = (Keyword) keys.get(i);
			keywords_.addKeyword(keyword);
		}
		return keywords_;
	}
	
	/**
	 * The feeds of a keyword is a lazy relation. The session is closing after
	 * it executes the question and we can't take them. That's why when we
	 * need them, we will call this method.
	 * 
	 * @param id of the keyword
	 * @return keyword with feeds
	 */
	public Keyword getKeywordWithFeeds(final String keywordID){
		Keyword keyword = null;
		try{
			startOperation();
			keyword = (Keyword) session.get(Keyword.class, keywordID);
			tx.commit();
		}
		catch (HibernateException e) {
			handleException(e);
		}
		finally{
			HibernateUtil.close(session);
		}
		return keyword;
	}
}
