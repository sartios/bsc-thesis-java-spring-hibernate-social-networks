package com.sones.keyword.creator.logic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.keyword.creator.logic.IKeywordCreatorService;

public class KeywordCreatorServiceTester 
{
	private IKeywordCreatorService service;
	private IKeywordDao keywordDao;
	
	public KeywordCreatorServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordCreator/spring-keywordcreator-logic.xml");
		service = (IKeywordCreatorService) context.getBean("keywordCreatorService");
		keywordDao = (IKeywordDao) context.getBean("keywordDao");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createKeywordShouldThrowOnNullKeyword()
	{
		service.createKeyword(null);
	}
	
	@Test
	public void createKeywordShouldWorkCorrectlyWhenKeywordExists()
	{
		String value = "something";
		Keyword keyword = new Keyword(value);
		service.createKeyword(value);
		service.createKeyword(value);
		service.createKeyword(value);
		assertNotNull(keywordDao.getByValue(keyword));
	}
}
