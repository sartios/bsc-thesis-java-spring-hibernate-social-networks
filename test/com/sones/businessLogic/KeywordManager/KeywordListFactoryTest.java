package com.sones.businessLogic.KeywordManager;

import org.junit.Test;
import static org.junit.Assert.*;

public class KeywordListFactoryTest {

	@Test
	public void getKeywordList_UserHasCreateKeywordsIntoDatabase_Test(){
		String userID="0001";
		assertNotNull(KeywordListFactory.getInstance().getKeywordList("database", userID));
	}
	
	@Test
	public void getKeywordList_UserHasNotCreateKeywordsIntoDatabase_Test(){
		String userID="00001";
		assertNull(KeywordListFactory.getInstance().getKeywordList("database", userID));
	}
	
	@Test
	public void getKeywordList_UserHasCreateKeywordsIntoDatabase_KeywordListContainTheKeywords_Test(){
		String userID="0001";
		KeywordList keywords=KeywordListFactory.getInstance().getKeywordList("database", userID);
		assertTrue(keywords.getSize()>0);
	}
}
