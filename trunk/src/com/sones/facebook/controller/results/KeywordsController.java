package com.sones.facebook.controller.results;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.keywordSearcher.logic.KeywordRetrieverService;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordsController 
{
	private KeywordRetrieverService service;
	
	public KeywordsController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordRetriever/spring-keywordretriever-service.xml");
		service = (KeywordRetrieverService) context.getBean("keywordRetrieverService");
	}
	
	public String[] getColumnNames()
	{
		String[] columnNames = {"Keyword value","Number of appears"};
		return columnNames;
	}
	
	public Map<String, Long> getKeywordResults(ApplicationUser appUser,int timeOfFounds, int time)
	{
		Map<String, Long> results = new HashMap<String, Long>();
		int negativeValue = time * (-1);
		System.out.println("Before " + negativeValue + " minutes");
		Map<Keyword, Long> serviceResults = service.getKeywords(appUser,timeOfFounds, negativeValue);
		Set<Keyword> keywords = serviceResults.keySet();
		System.out.println("Results: "+keywords.size());
		for(Keyword keyword : keywords)
		{
			Long numOfAppear = serviceResults.get(keyword);
			System.out.println("Found: "+numOfAppear.longValue());
			results.put(keyword.getValue(), numOfAppear);
		}
		return results;
	}
}
