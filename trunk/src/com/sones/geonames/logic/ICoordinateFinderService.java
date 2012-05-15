package com.sones.geonames.logic;

import com.sones.sharedDto.geonames.CityViewDto;

public interface ICoordinateFinderService 
{
	public CityViewDto getCity(String city, String username) throws Exception;
}
