package com.sones.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.KeywordList;

import static org.junit.Assert.*;


public class KeywordListDaoTest {
	
	private KeywordListDao keywordListDao;
	private KeywordList keywordList;
	
	@Before
	public void setUp(){
		keywordListDao = new KeywordListDao();
		this.saveKeyword();
	}
	
	@After
	public void tearDown(){
		keywordListDao=null;
		keywordList=null;
		//this.deleteKeyword();
	}

	@Test
	/**
	 * When we try to save a keyword list that is valid, the statement should pass.
	 */
	public void saveUserKeywordList_KeywordAndUserExistIntoDatabase_Test(){
		boolean result = keywordListDao.saveUserKeywordList(this.getValidKeywordList());
		assertTrue(result);
	}
	
	@Test
	/**
	 * When we try to save the same keyword for another user, the statement should pass.
	 */
	public void saveUserKeywordList_AnotherUserWithTheSameKeyword_Test(){
		KeywordList keywords = this.getValidKeywordList();
		keywords.setApplicationUserID_("0002");
		boolean result = keywordListDao.saveUserKeywordList(keywords);
		deleteKeywordList();
		assertTrue(result);
	}
	
	@Test
	/**
	 * When we try to delete a keyword list that has not user, the statement should fail.
	 */
	public void saveUserKeywordList_KeywordListHasNoUser_Test(){
		KeywordList keywords=getKeywordListWithoutApplicationUser();
		assertFalse(keywordListDao.saveUserKeywordList(keywords));
	}
	
	@Test
	/**
	 * When we try to save a keyword list that is empty of keywords, the statement should fail.
	 */
	public void saveUserKeywordList_KeywordListHasNoKeywords_Test(){
		KeywordList keywords = getKeywordListWithoutKeywords();
		assertFalse(keywordListDao.saveUserKeywordList(keywords));
	}
	
	@Test
	/**
	 * When we try to save a keyword list that is null, the statement should fail.
	 */
	public void saveUserKeywordList_KeywordListIsNull_Test(){
		assertFalse(keywordListDao.saveUserKeywordList(null));
	}
	
	@Test
	/**
	 * When we try to delete a keyword list that is has a user and keywords, the statement should pass.
	 */
	public void deleteUserKeywordList_KeywordListIsValid_Test(){
		assertTrue(keywordListDao.deleteUserKeywordList(this.getValidKeywordList()));
	}
	
	@Test
	/**
	 * When we try to delete a keyword list that has not user, the statement should fail.
	 */
	public void deleteUserKeywordList_KeywordListHasNoUser_Test(){
		assertFalse(keywordListDao.deleteUserKeywordList(this.getKeywordListWithoutApplicationUser()));
	}
	
	@Test
	/**
	 * When we try to delete a keyword list that is no keywords, the statement should fail.
	 */
	public void deleteUserKeywordList_KeywordListHasNotKeywords_Test(){
		assertFalse(keywordListDao.deleteUserKeywordList(this.getKeywordListWithoutKeywords()));
	}
	
	@Test
	/**
	 * When we try to delete a keyword list that is null, the statement should fail.
	 */
	public void deleteUserKeywordList_KeywordListIsNull_Test(){
		assertFalse(keywordListDao.deleteUserKeywordList(null));
	}
	
	@Test
	/**
	 * We have save for a user the keyword list which contains one keyword. When we receive
	 * this keyword list, must not be null
	 */
	public void getUserKeywordsByUserID_UserAndKeywordsExist_KeywordListNotNull_Test(){
		saveValidKeywordList();
		KeywordList keywords = keywordListDao.getUserKeywordsByUserID(getApplcationUser());
		deleteKeywordList();
		assertNotNull(keywords);
	}
	
	@Test
	/**
	 * We have save for a user the keyword list which contains one keyword. When we receive
	 * this keyword list, we must get the correct keyword ID
	 */
	public void getUserKeywordsByUserID_UserAndKeywordsExist_KeywordIDIsCorrect_Test(){
		saveValidKeywordList();
		KeywordList keywords = keywordListDao.getUserKeywordsByUserID(getApplcationUser());
		deleteKeywordList();
		assertEquals(keywords.getKeyword().getKeywordID_(),(this.getKeyword()).getKeywordID_());
	}
	
	@Test
	/**
	 * We have save for a user the keyword list which contains one keyword. When we receive
	 * this keyword list, we must get the correct keyword value
	 */
	public void getUserKeywordsByUserID_UserAndKeywordsExist_KeywordValueIsCorrect_Test(){
		saveValidKeywordList();
		KeywordList keywords = keywordListDao.getUserKeywordsByUserID(getApplcationUser());
		deleteKeywordList();
		assertEquals(keywords.getKeyword().getValue_(),(this.getKeyword()).getValue_());
	}
	
	@Test
	/**
	 * The user who's keyword list we want to receive, doesn't exist as a valid application
	 * user. So we should receive an empty, null object.
	 */
	public void getUserKeywordsByUserID_UserDoesNotExist_KeywordListNull_Test(){
		saveValidKeywordList();
		KeywordList keywords = keywordListDao.getUserKeywordsByUserID("xxxx");
		deleteKeywordList();
		assertNull(keywords);
	}
	
	@Test
	/**
	 * There is a valid user who has not keywords. When we try to get his keyword list
	 * we should receive an empty object.
	 */
	public void getUserKeywordsByUserID_UserExistsButHasNotKeywords_KeywordListNull_Test(){
		KeywordList keywords = keywordListDao.getUserKeywordsByUserID(getApplcationUser());
		assertNull(keywords);
	}
	
	/**
	 * Returns a valid keyword list
	 * @return valid keyword list
	 */
	private KeywordList getValidKeywordList(){
		buildIfNeeded();
		keywordList.addKeyword(this.getKeyword());
		keywordList.setApplicationUserID_(this.getApplcationUser());
		return keywordList;
	}
	
	/**
	 * Returns a valid keyword
	 * @return a valid keyword
	 */
	private Keyword getKeyword(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		String keywordValue = new String("keyword_value");
		keyword.setKeywordID_(keywordID);
		keyword.setValue_(keywordValue);
		return keyword;
	}
	
	/**
	 * Returns a valid application user
	 * @return valid application user
	 */
	private String getApplcationUser(){
		
		return new String("0001");
	}
	
	/**
	 * Save the keyword to database
	 */
	private void saveKeyword(){
		KeywordDao dao = new KeywordDao();
		dao.saveOrUpdate(this.getKeyword());
		dao=null;
	}
	
	/**
	 * Deletes the keyword from the database
	 */
	private void deleteKeyword(){
		KeywordDao dao = new KeywordDao();
		dao.delete(this.getKeyword());
		dao=null;
	}
	
	/**
	 * Returns the keyword list which has not application user
	 * @return keyword list with empty application user
	 */
	private KeywordList getKeywordListWithoutApplicationUser(){
		buildIfNeeded();
		keywordList.addKeyword(this.getKeyword());
		keywordList.setApplicationUserID_("");
		return keywordList;
	}
	
	/**
	 * Returns the keyword list without keywords
	 * @return keyword list empty of keywords
	 */
	private KeywordList getKeywordListWithoutKeywords(){
		buildIfNeeded();
		keywordList.setApplicationUserID_(this.getApplcationUser());
		return keywordList;
	}
	
	/**
	 * Build the keyword list if it's necessary
	 */
	private void buildIfNeeded(){
		if(null==keywordList){
			keywordList = new KeywordList();
		}
	}
	
	/**
	 * Deletes the valid keyword list from the database
	 */
	private void deleteKeywordList(){
		keywordListDao.deleteUserKeywordList(this.keywordList);
	}
	
	/**
	 * Saves the valid keyword list to database
	 */
	private void saveValidKeywordList(){
		KeywordListDao keywordsDao = new KeywordListDao();
		keywordsDao.saveUserKeywordList(this.getValidKeywordList());
	}

}
