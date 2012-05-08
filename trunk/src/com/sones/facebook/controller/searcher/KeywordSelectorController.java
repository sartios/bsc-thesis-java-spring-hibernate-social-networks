package com.sones.facebook.controller.searcher;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.keyword.creator.logic.IKeywordCreatorService;
import com.sones.keyword.selector.logic.IKeywordSelectorService;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSelectorController 
{
	private IKeywordCreatorService creatorService;
	private IKeywordSelectorService selectorService;
	
	public KeywordSelectorController()
	{
		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("KeywordCreator/spring-keywordcreator-logic.xml","KeywordSelector/spring-keywordselector-logic.xml");
		creatorService = (IKeywordCreatorService) context.getBean("keywordCreatorService");
		selectorService = (IKeywordSelectorService) context.getBean("keywordSelectorService");
	}
	
	public	Set<String> getKeywords()
	{
		Set<String> keywords = new HashSet<String>();
		for(Keyword keyword : creatorService.getKeywords())
		{
			keywords.add(keyword.getValue());
		}
		return keywords;
	}
	
	public void submitSelectedKeywords(ApplicationUser appUser, Set<String> keywords)
	{
		for(String value : keywords)
		{
			Keyword keyword = new Keyword(value);
			selectorService.selectKeyword(appUser, keyword);
		}
	}
}
