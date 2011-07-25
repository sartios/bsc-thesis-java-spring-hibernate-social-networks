package com.sones.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sones.businessLogic.Facebook.FacebookUser;
import com.sones.businessLogic.Facebook.Feed;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keyword_old;

public class KeywordDaoTest {

	
	@Test
	public void saveOrUpdate_KeywordIsValid_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		String keywordValue = new String("keyword_value");
		keyword.setKeywordID_(keywordID);
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		boolean result = dao.saveOrUpdate(keyword);
		dao.delete(keyword);
		assertTrue(result);
	}
	
	@Test
	public void saveOrUpdate_KeywordHasEmptyID_Test(){
		Keyword keyword = new Keyword();
		String keywordValue = new String("keyword_value");
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		assertFalse(dao.saveOrUpdate(keyword));
	}
	
	@Test
	public void saveOrUpdate_KeywordHasEmptyValue_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_ID");
		keyword.setKeywordID_(keywordID);
		KeywordDao dao = new KeywordDao();
		assertFalse(dao.saveOrUpdate(keyword));
	}
	
	@Test
	public void delete_KeywordExists_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		String keywordValue = new String("keyword_value");
		keyword.setKeywordID_(keywordID);
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		dao.saveOrUpdate(keyword);
		boolean result=dao.delete(keyword);
		assertTrue(result);
	}
	
	@Test
	public void saveOrUpdate_KeywordIsNull_Test(){
		KeywordDao dao = new KeywordDao();
		boolean result=dao.saveOrUpdate(null);
		assertFalse(result);
	}
	
	@Test
	public void delete_KeywordDoesNotExist_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		String keywordValue = new String("keyword_value");
		keyword.setKeywordID_(keywordID);
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		boolean result=dao.delete(keyword);
		assertTrue(result);
	}
	
	@Test
	public void delete_KeywordHasBlankID_Test(){
		Keyword keyword = new Keyword();
		String keywordValue = new String("keyword_value");
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		boolean result=dao.delete(keyword);
		assertTrue(result);
	}
	
	@Test
	public void delete_KeywordHasBlankValue_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		keyword.setKeywordID_(keywordID);
		KeywordDao dao = new KeywordDao();
		boolean result=dao.delete(keyword);
		assertTrue(result);
	}
	
	@Test
	public void delete_KeywordIsNull_Test(){
		KeywordDao dao = new KeywordDao();
		boolean result=dao.delete(null);
		assertFalse(result);
	}
	
	@Test
	public void getKeywordByID_KeywordExists_Test(){
		Keyword keyword = new Keyword();
		String keywordID = new String("keyword_id");
		String keywordValue = new String("keyword_value");
		keyword.setKeywordID_(keywordID);
		keyword.setValue_(keywordValue);
		KeywordDao dao = new KeywordDao();
		dao.saveOrUpdate(keyword);
		Keyword keyFromDB = dao.getKeywordByID(keywordID);
		dao.delete(keyword);
		assertNotNull(keyFromDB);
	}
	
	@Test
	public void getKeywordByID_KeywordDoesNotExist_Test(){
		KeywordDao dao = new KeywordDao();
		Keyword keyFromDB = dao.getKeywordByID("xxCXXx");
		assertNull(keyFromDB);
	}
	
	@Test
	public void getKeywordByID_KeywordIDIsNull_Test(){
		KeywordDao dao = new KeywordDao();
		Keyword keyFromDB = dao.getKeywordByID(null);
		assertNull(keyFromDB);
	}

}
