package com.sones.facebook.controller.sources;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.publicsource.logic.IPublicSourceService;
import com.sones.geonames.logic.CoordinateFinderService;
import com.sones.geonames.logic.ICoordinateFinderService;
import com.sones.sharedDto.geonames.CityViewDto;

public class FacebookPlaceDownloaderController 
{
	private IPublicSourceService service;
	private ICoordinateFinderService cooredinateFinderService;
	
	public FacebookPlaceDownloaderController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("PublicSources/spring-publicplace-logic.xml");
		service = (IPublicSourceService) context.getBean("publicSourceDownloaderService");
		cooredinateFinderService = new CoordinateFinderService();
	}
	
	public void downloadPublicPlaces(Iterable<String> criteria, Iterable<String> cities, String radical, String appUserID)
	{
		Iterable<String> coordinates = getCoordinates(cities);
		service.DownloadPublicPlaces(criteria, appUserID, coordinates, radical);
	}
	
	private Iterable<String> getCoordinates(Iterable<String> cities)
	{
		Set<String> coordinates = new HashSet<String>();
		for(String city : cities)
		{
			CityViewDto cityDto;
			try {
				cityDto = cooredinateFinderService.getCity(city, "sartios");
			} catch (Exception e) {
				continue;
			}
			String coordinate = cityDto.getLatitude()+","+cityDto.getLongitude();
			coordinates.add(coordinate);
		}
		return coordinates;
	}
}
