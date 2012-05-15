package com.sones.facebook.controller.sources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.source.selector.logic.IFacebookSourceManagerService;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;
import com.sones.sharedDto.facebook.source.selector.SourceViewDto;

public class FacebookSourceManagerController 
{
	private IFacebookSourceManagerService service;
	private Map<String, SourceViewDto> sourceMap;
	
	public FacebookSourceManagerController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("PrivateSourceSelector/spring-facebook-source-manager-service.xml");
		service = (IFacebookSourceManagerService) context.getBean("sourceManagerService");
		sourceMap = new HashMap<String, SourceViewDto>();
	}
	
	public Iterable<String> getSelectedSources(String applicationUserId)
	{
		Set<String> sourceNames = new HashSet<String>();
		Iterable<SourceViewDto> sourcesDto = service.getSelectedSources(applicationUserId);
		for(SourceViewDto sourceDto : sourcesDto)
		{
			String sourceName = sourceDto.getSourceName();
			System.out.println(sourceName);
			sourceNames.add(sourceName);
			sourceMap.put(sourceName, sourceDto);
		}
		return sourceNames;
	}
	
	public void updateDatabase(Iterable<String> sources, String applicationUserId)
	{
		Set<SourceCreateDto> sourcesDto = new HashSet<SourceCreateDto>();
		for(String source : sources)
		{
			SourceViewDto sourceViewDto = sourceMap.get(source);
			String sourceId = sourceViewDto.getSourceId();
			String sourceType = sourceViewDto.getSourceType();
			SourceCreateDto sourceCreateDto = new SourceCreateDto(applicationUserId, sourceId, sourceType);
			sourcesDto.add(sourceCreateDto);
		}
		service.deleteSelectedSources(applicationUserId, sourcesDto);
	}
}
