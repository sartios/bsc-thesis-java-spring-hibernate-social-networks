package com.sones.facebook.controller.sources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.publicsource.logic.IPublicSourceRetrieverService;
import com.sones.facebook.source.selector.logic.IFacebookSourceSelectorService;
import com.sones.sharedDto.facebook.source.selector.PublicPlaceViewDto;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;

public class FacebookPlaceSourceSelectorController
{
	private Map<String, PublicPlaceViewDto> places;
	private IPublicSourceRetrieverService retrieverService;
	private IFacebookSourceSelectorService selectorService;
	
	public FacebookPlaceSourceSelectorController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("PublicSources/spring-publicplaceretriever-logic.xml",
				"PrivateSourceSelector/spring-private-source-selector-service.xml");
		places = new HashMap<String, PublicPlaceViewDto>();
		retrieverService = (IPublicSourceRetrieverService) context.getBean("publicSourceRetrieverService");
		selectorService = (IFacebookSourceSelectorService) context.getBean("privateSourceSelectorService");
	}
	
	public Iterable<String> getCriteria()
	{
		Iterable<String> criterias = retrieverService.GetPlaceCriteria();
		return criterias;
	}
	
	public Iterable<String> showPlaces(String criterion)
	{
		Iterable<PublicPlaceViewDto> tmpPlaces = retrieverService.GetPublicPlaces(criterion);
		List<String> placeNames = new ArrayList<String>();
		for(PublicPlaceViewDto dto : tmpPlaces)
		{
			String placeName = dto.getName();
			placeNames.add(placeName);
			
			places.put(placeName, dto);
		}
		return placeNames;
	}
	
	public void saveSources(Iterable<String> names, String applicationUserId)
	{
		Set<SourceCreateDto> sourceDtos = new HashSet<SourceCreateDto>();
		for(String name : names)
		{
			PublicPlaceViewDto placeDto = places.get(name);
			String sourceId = placeDto.getId();
			String sourceType = "Place";
			SourceCreateDto sourceDto = new SourceCreateDto(applicationUserId, sourceId, sourceType);
			sourceDtos.add(sourceDto);
		}
		selectorService.saveSources(sourceDtos);
	}
	
	
}
