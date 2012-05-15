package com.sones.geonames.logic;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import com.sones.sharedDto.geonames.CityViewDto;

public class CoordinateFinderService implements ICoordinateFinderService
{
	public CoordinateFinderService()
	{}
	
	@Override
	public CityViewDto getCity(String city, String username) throws Exception 
	{
		if(city.isEmpty() == true)
		{
			throw new IllegalArgumentException();
		}
		WebService.setUserName(username);
		ToponymSearchCriteria query = new ToponymSearchCriteria();
		query.setQ(city);
		ToponymSearchResult result = WebService.search(query);
		CityViewDto cityDto = null;
		for(Toponym toponym : result.getToponyms())
		{
			String longitude = String.valueOf( toponym.getLongitude() );
			String latitude = String.valueOf( toponym.getLatitude() );
			cityDto = new CityViewDto(latitude, longitude);
			break;
		}
		return cityDto;
	}

}
