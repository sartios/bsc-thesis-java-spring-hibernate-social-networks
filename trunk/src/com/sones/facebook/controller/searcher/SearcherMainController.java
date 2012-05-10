package com.sones.facebook.controller.searcher;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.gui.searcher.KeywordCreatorFrame;
import com.sones.facebook.gui.searcher.KeywordSelectorFrame;
import com.sones.facebook.keywordSearcher.logic.IKeywordSearcherService;
import com.sones.facebook.keywordSearcher.logic.KeywordSearcherService;
import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.usermanager.model.ApplicationUser;

public class SearcherMainController 
{
	private KeywordCreatorFrame creatorFrame;
	private KeywordSelectorFrame selectorFrame;
	private IKeywordSearcherService service;
	
	public SearcherMainController()
	{
		creatorFrame = new KeywordCreatorFrame();
		selectorFrame = new KeywordSelectorFrame();
		initializeService();
	}
	
	public void selectKeywords()
	{
		selectorFrame.show(true);
	}
	
	public void createKeywords()
	{
		creatorFrame.show(true);
	}
	
	public void search(String appUserID)
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId(appUserID);
		service.searchForKeywordsIntoAllFacebookPostTypes(appUser);
	}
	
	private void initializeService()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordSearcher/spring-keywordSearcher-service.xml");
		service = (KeywordSearcherService) context.getBean("keywordSearcherService");
		IDataRetriever checkinRet = (IDataRetriever) context.getBean("checkinDataRetriever");
		IDataRetriever statusRet = (IDataRetriever) context.getBean("statusMessageDataRetriever");
		IDataRetriever linkRet = (IDataRetriever) context.getBean("linkDataRetriever");
		IDataRetriever photoRet = (IDataRetriever) context.getBean("photoDataRetriever");
		IDataRetriever videoRet = (IDataRetriever) context.getBean("videoDataRetriever");
		IDataRetriever noteRet = (IDataRetriever) context.getBean("noteDataRetriever");
		service.addDataRetriever(checkinRet);
		service.addDataRetriever(statusRet);
		service.addDataRetriever(linkRet);
		service.addDataRetriever(photoRet);
		service.addDataRetriever(videoRet);
		service.addDataRetriever(noteRet);

	}
}
