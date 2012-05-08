package com.sones.facebook.controller.searcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.keyword.creator.logic.IKeywordCreatorService;

public class KeywordCreatorController 
{
	private IKeywordCreatorService service;
	
	public KeywordCreatorController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordCreator/spring-keywordcreator-logic.xml");
		service = (IKeywordCreatorService) context.getBean("keywordCreatorService");
	}
	
	public void createKeyword(String keyword)
	{
		service.createKeyword(keyword);
	}
}
