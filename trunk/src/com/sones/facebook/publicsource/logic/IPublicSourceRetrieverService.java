package com.sones.facebook.publicsource.logic;

import com.sones.sharedDto.facebook.source.selector.PublicPlaceViewDto;

public interface IPublicSourceRetrieverService 
{
	public Iterable<PublicPlaceViewDto> GetPublicPlaces(String criteriaValue);
	public Iterable<String> GetPlaceCriteria();
}
