package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keyword_old;
import com.sones.businessLogic.KeywordManager.KeywordList;

/**
 * This class is responsible for saving application's users' keywords
 * into database and retrieve them. 
 * 
 * @author Sartios
 *
 */
public class KeywordListDao extends AbstractDao{

	/**
	 * Saves user's keywords
	 */
	public boolean saveUserKeywordList(final KeywordList keywords){
		boolean savingCompleted = false;
		if((null!=keywords)){
			String applcationUserID = keywords.getApplicationsUserID_();
			if(applicationUserIsValid(applcationUserID)){
				for(int i=0;i<keywords.getSize();i++){
					Keyword keyword = keywords.getKeyword(i);
					try{
						this.saveUsersKeyword(applcationUserID, keyword);
						savingCompleted=true;
					}
					catch(HibernateException hibEx){					
						tx.rollback();
						savingCompleted=false;
					}
					finally{
						HibernateUtil.close(session);
					}
				}
			}
		}
		return savingCompleted;
	}
	
	/**
	 * Saves user's keywords
	 */
	public boolean deleteUserKeywordList(final KeywordList keywords){
		boolean deletionCompleted = false;
		if((null!=keywords)){
			String applcationUserID = keywords.getApplicationsUserID_();
			if(applicationUserIsValid(applcationUserID)){
				for(int i=0;i<keywords.getSize();i++){
					Keyword keyword = keywords.getKeyword(i);
					try{
						this.deleteUsersKeyword(applcationUserID, keyword);
						deletionCompleted=true;
					}
					catch(HibernateException hibEx){					
						tx.rollback();
						deletionCompleted=false;
					}
					finally{
						HibernateUtil.close(session);
					}
				}
			}
		}
		return deletionCompleted;
	}
	
	/**
	 * Finds user's keywords
	 * @param applicationUserID
	 * @return KeywordList
	 */
	public KeywordList getUserKeywordsByUserID(final String applicationUserID){
		KeywordList keywords = null;
		if(this.applicationUserIsValid(applicationUserID)){
			List keywordListFromDB = this.execureQueryForGettingUserKeywordsByUserID(applicationUserID);
			if(keywordListFromDB.isEmpty()==false){
				keywords = this.getKeywordList(keywordListFromDB, applicationUserID);
			}
		}
		return keywords;
	}
	
	/**
	 * Creates the query for finding user's keywords and executes it
	 * @param applicationUserID
	 * @return KeywordList
	 */
	private List execureQueryForGettingUserKeywordsByUserID(final String applicationUserID){
		List keywords=null;
		if(this.applicationUserIsValid(applicationUserID)){
			try{
				startOperation();
				Query query = getQueryForGettingUserKeywordsByUserID(applicationUserID);
				keywords= query.list();
			}
			catch (HibernateException e) {
			}
		}
		return keywords;
	}
	
	/**
	 * Return a keyword list from an Object List which is the return of the {@link #getQueryForGettingUserKeywordsByUserID(String)}
	 * statement, when it executes
	 * @param List
	 * @param application user
	 * @return KeywordList
	 */
	private KeywordList getKeywordList(final List keywords,final String applicationUser){
		KeywordList keywordList = null;
		if((null!=keywords)&&this.applicationUserIsValid(applicationUser)){
			keywordList = new KeywordList();
			keywordList.setApplicationUserID_(applicationUser);
			for(int i=0;i<keywords.size();i++){
				keywordList.addKeyword(this.getKeyword(((Object[])keywords.get(i))));
			}
		}
		return keywordList;
	}
	
	/**
	 * Extracts the keyword ID from an Object[] keyword
	 * @param Object[]
	 * @return keywordID
	 */
	private String getKeywordID(final Object[] keyword){
		return keyword[0].toString();
	}
	
	/**
	 * Extracts the keyword value from an Object[] keyword
	 * @param Object[]
	 * @return keywordValue
	 */
	private String getKeywordValue(final Object[] keyword){
		return keyword[1].toString();
	}
	
	/**
	 * Creates a keyword from an Object[] keyword
	 * @param Object[]
	 * @return Keyword
	 */
	private Keyword getKeyword(final Object[] keyword){
		Keyword keyword_ = new Keyword();
		keyword_.setKeywordID_(this.getKeywordID(keyword));
		keyword_.setValue_(this.getKeywordValue(keyword));
		return keyword_;
	}
	
	
	/**
	 * Returns the query for getting user keywords by application user ID
	 * @param applicationUserID
	 * @return Query
	 */
	private Query getQueryForGettingUserKeywordsByUserID(final String applicationUserID){
		String sqlQuery = "SELECT "+
								"KEYWORD_LIST.KEYWORD_ID as KEYWORD_ID," +
								"KEYWORDS.KEYWORD_VALUE  as KEYWORD_VALUE " +
							"FROM "+
								"users_create_keywords AS KEYWORD_LIST " +
							"INNER JOIN "+
								"keywords AS KEYWORDS " +
							"WHERE "+
								"KEYWORD_LIST.APPLICATION_USER_ID=:appUser";
		Query query = session.createSQLQuery(sqlQuery);
		query.setParameter("appUser", applicationUserID);
		return query;
	}
	
	/**
	 * Creates the query for Inserting the data into users_create_keywords and executes it
	 * @param appUserID
	 * @param keyword
	 */
	private void saveUsersKeyword(final String appUserID,final Keyword keyword){
		if(userAndKeywordAreValid(appUserID,keyword)){
			KeywordDao dao = new KeywordDao();
			dao.saveOrUpdate(keyword);
			dao=null;
			startOperation();
			Query query = session.createSQLQuery("INSERT INTO users_create_keywords VALUES(:applicationUserID,:keywordID)");
			query.setParameter("applicationUserID",appUserID);
			query.setParameter("keywordID", keyword.getKeywordID_());
			query.executeUpdate();
			tx.commit();
		}
	}
	
	/**
	 * Creates the query for Deleting the data from users_create_keywords and executes it
	 * @param appUserID
	 * @param keyword
	 */
	private void deleteUsersKeyword(final String appUserID,final Keyword keyword){
		if(userAndKeywordAreValid(appUserID,keyword)){
			startOperation();
			Query query = session.createSQLQuery("DELETE FROM users_create_keywords WHERE APPLICATION_USER_ID=:applicationUserID and KEYWORD_ID=:keywordID");
			query.setParameter("applicationUserID",appUserID);
			query.setParameter("keywordID", keyword.getKeywordID_());
			query.executeUpdate();
			tx.commit();
		}
	}
	
	/**
	 * Checks if the user and the keyword are valid
	 * @return true if are valid
	 */
	public boolean userAndKeywordAreValid(final String appUserID,final Keyword keyword){
		if((null!=appUserID)&&(null!=keyword)){
			if((applicationUserIsValid(appUserID))&&(keywordIsValid(keyword))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the application user is valid
	 * @param applicationUser
	 * @return true if she is valid
	 */
	private boolean applicationUserIsValid(final String appUser){
		if(false==appUser.isEmpty()){
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if the keyword is valid
	 * @param keyword
	 * @return true if it is valid
	 */
	private boolean keywordIsValid(final Keyword keyword){
		if((false==keyword.getKeywordID_().isEmpty())&&(false==keyword.getValue_().isEmpty())){
			return true;
		}
		return false;
	}
}
