package com.sones.facebook.controller.searcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.gui.searcher.KeywordCreatorFrame;
import com.sones.facebook.gui.searcher.KeywordSelectorFrame;
import com.sones.facebook.keywordSearcher.logic.IKeywordSearcherManagerService;
import com.sones.facebook.keywordSearcher.logic.IKeywordSearcherService;
import com.sones.facebook.keywordSearcher.logic.KeywordSearcherService;
import com.sones.facebook.keywordSearcher.logic.keyword.retriever.IApplicationUserKeywordRetriever;
import com.sones.facebook.keywordSearcher.logic.retriever.IDataRetriever;
import com.sones.sharedDto.facebook.keywordSearcher.KeywordSearchDto;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import com.sones.usermanager.model.ApplicationUser;

public class SearcherMainController 
{
	private KeywordCreatorFrame creatorFrame;
	private KeywordSelectorFrame selectorFrame;
	private IKeywordSearcherService searcherService;
	private IKeywordSearcherManagerService managerService;
	private IApplicationUserKeywordRetriever keywordRetriever;
	
	public SearcherMainController()
	{
		creatorFrame = new KeywordCreatorFrame();
		selectorFrame = new KeywordSelectorFrame();
		initializeService();
	}
	
	public void selectKeywords(ApplicationUserViewDto userDto)
	{
		selectorFrame.setUserDto(userDto);
		selectorFrame.show(true);
	}
	
	public void createKeywords()
	{
		creatorFrame.show(true);
	}
	
	public void search(String appUserID)
	{
		Date date = new Date(0);
		managerService.startKeywordSearch(appUserID, date);
	}
	
	private void initializeService()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordSearcher/spring-keywordSearcher-service.xml", "KeywordSearcher/spring-searcher-manager-service.xml");
		searcherService = (KeywordSearcherService) context.getBean("keywordSearcherService");
		IDataRetriever checkinRet = (IDataRetriever) context.getBean("checkinDataRetriever");
		IDataRetriever statusRet = (IDataRetriever) context.getBean("statusMessageDataRetriever");
		IDataRetriever linkRet = (IDataRetriever) context.getBean("linkDataRetriever");
		IDataRetriever photoRet = (IDataRetriever) context.getBean("photoDataRetriever");
		IDataRetriever videoRet = (IDataRetriever) context.getBean("videoDataRetriever");
		IDataRetriever noteRet = (IDataRetriever) context.getBean("noteDataRetriever");
		searcherService.addDataRetriever(checkinRet);
		searcherService.addDataRetriever(statusRet);
		searcherService.addDataRetriever(linkRet);
		searcherService.addDataRetriever(photoRet);
		searcherService.addDataRetriever(videoRet);
		searcherService.addDataRetriever(noteRet);
		managerService = (IKeywordSearcherManagerService) context.getBean("keywordSearcherManagerService");
		managerService.setSearchService(searcherService);
		keywordRetriever = (IApplicationUserKeywordRetriever) context.getBean("keywordRetriever");
	}

	public IKeywordSearcherManagerService getService() {
		return managerService;
	}

	public Iterable<String> getKeywords(String applicationUserId) 
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId(applicationUserId);
		Iterable<KeywordSearchDto> keywords = keywordRetriever.getApplicationUserKeywords(appUser);
		Set<String> keywordValues = new HashSet<String>();
		for(KeywordSearchDto keyword : keywords)
		{
			String value = keyword.getValue();
			keywordValues.add(value);
		}
		return keywordValues;
	}

	public String getOptions(String appUserId) 
	{
		return managerService.getSearchInterval(appUserId);
	}
}
