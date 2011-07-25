package com.sones.businessLogic.KeywordManager;

import static org.junit.Assert.*;

import javax.swing.JEditorPane;

import org.junit.Test;

public class KeywordListTest {

	@Test
	public void addKeyword_ListDoesNotContainTheKeyword_Test(){
		Keyword keyword = new Keyword("HAHA");
		KeywordList keywords = new KeywordList();
		assertTrue(keywords.addKeyword(keyword));
	}
	
	@Test
	public void addKeyword_DuplicateKeyword_Test(){
		Keyword keyword = new Keyword("HAHA");
		Keyword duplicateKeyword = new Keyword("HAHA");
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(keyword);
		assertFalse(keywords.addKeyword(duplicateKeyword));
	}
	
	@Test
	public void addKeyword_KeywordIsNull_Test(){
		KeywordList keywords = new KeywordList();
		assertFalse(keywords.addKeyword(null));
	}
	
	@Test
	public void isEmpty_KeywordListIsEmpty_Test(){
		KeywordList keywords = new KeywordList();
		assertTrue(keywords.isEmpty());
	}
	
	@Test
	public void isEmpty_KeywordIsNotEmpty_Test(){
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(new Keyword("haha"));
		assertFalse(keywords.isEmpty());
	}
	
	@Test
	public void getKeyword_IndexIsInBounds_Test(){
		Keyword keyword1 = new Keyword("HAHA");
		Keyword keyword2 = new Keyword("houhou");
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(keyword1);
		keywords.addKeyword(keyword2);
		assertEquals(keywords.getKeyword(0), keyword1);
	}
	
	@Test
	public void getKeyword_IndexIsOutOfBounds_Test(){
		Keyword keyword1 = new Keyword("HAHA");
		Keyword keyword2 = new Keyword("houhou");
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(keyword1);
		keywords.addKeyword(keyword2);
		assertEquals(keywords.getKeyword(5), null);
	}
	
	@Test
	public void getKeyword_AskForNotSameKeyword_Test(){
		Keyword keyword1 = new Keyword("HAHA");
		Keyword keyword2 = new Keyword("houhou");
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(keyword1);
		keywords.addKeyword(keyword2);
		assertNotSame(keywords.getKeyword(1), keyword1);
	}
	
	@Test
	public void getKeyword_KeywordListIsEmpty_Test(){
		KeywordList keywords = new KeywordList();
		assertNull(keywords.getKeyword(5));
	}
	
	@Test
	public void getSize_KeywordListContainsTwoKeywords_Test(){
		Keyword keyword1 = new Keyword("HAHA");
		Keyword keyword2 = new Keyword("houhou");
		KeywordList keywords = new KeywordList();
		keywords.addKeyword(keyword1);
		keywords.addKeyword(keyword2);
		assertEquals(keywords.getSize(), 2);
	}
	
	@Test
	public void getSize_KeywordListIsEmpty_Test(){
		KeywordList keywords = new KeywordList();
		assertEquals(keywords.getSize(), 0);
	}
	
	@Test
	public void setApplicationUserID_UserIsNotEmpty_Test(){
		String userID = new String("1");
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(userID);
		assertEquals(keywords.getApplicationsUserID_(),userID);
	}
	
	@Test
	public void setApplicationUserID_ParamAndUserAreEmpty_Test(){
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(null);
		assertNotNull(keywords.getApplicationsUserID_());
	}
	
	@Test
	public void keywordsBelongToUser_KeywordsApplicationUserIsNull_Test(){
		KeywordList keywords = new KeywordList();
		assertFalse(keywords.belongToUser("1"));
	}
	
	@Test
	public void keywordsBelongToUser_KeywordsApplicationUserIsTheSameAsParameterUser_Test(){
		String applicationUserID = new String("1");
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(applicationUserID);
		assertTrue(keywords.belongToUser(applicationUserID));
	}
	
	@Test
	public void keywordsBelongToUser_ParameterUserIsNull_Test(){
		String applicationUserID = new String("1");
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(applicationUserID);
		assertFalse(keywords.belongToUser(null));
	}
	
	@Test
	public void keywordsBelongToNobody_ThereIsAUserForKeywords_Test(){
		String applicationUserID = new String("1");
		KeywordList keywords = new KeywordList();
		keywords.setApplicationUserID_(applicationUserID);
		assertFalse(keywords.belongToNobody());
	}
	
	@Test
	public void keywordsBelongToNobody_ThereIsNotAUserForKeywords_Test(){
		KeywordList keywords = new KeywordList();
		assertTrue(keywords.belongToNobody());
	}
}
