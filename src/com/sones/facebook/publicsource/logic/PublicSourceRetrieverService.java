package com.sones.facebook.publicsource.logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.dao.exception.DataAccessLayerException;
import com.sones.facebook.placemanager.dao.IPlaceCriteriaDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.sharedDto.facebook.source.selector.PublicPlaceViewDto;

public class PublicSourceRetrieverService implements IPublicSourceRetrieverService
{
	private final Logger _LOGGER;
	private ICriteriaDao criteriaDao;
	private IPlaceCriteriaDao placeCriteriaDao;
	
	private PublicSourceRetrieverService()
	{
		_LOGGER = Logger.getLogger(PublicSourceRetrieverService.class);
	}
	
	public PublicSourceRetrieverService(ICriteriaDao criteriaDao, IPlaceCriteriaDao placeCriteriaDao)
	{
		_LOGGER = Logger.getLogger(PublicSourceRetrieverService.class);
		this.criteriaDao = criteriaDao;
		this.placeCriteriaDao = placeCriteriaDao;
	}

	@Override
	public Iterable<PublicPlaceViewDto> GetPublicPlaces(String criteriaValue) 
	{
		if(criteriaValue.isEmpty() == true)
		{
			_LOGGER.error("Criteria value can't be empty!");
			throw new IllegalArgumentException("Criteria value can't be empty!");
		}
		if(criteriaValue == null)
		{
			_LOGGER.error("Criteria value can't be null!");
			throw new IllegalArgumentException("Criteria value can't be null!");
		}
		
		Criteria criteria = criteriaDao.GetByValue(criteriaValue);
		if(criteria == null)
		{
			_LOGGER.error("Criteria doesn't exist");
			throw new DataAccessLayerException("Criteria doesn't exist");
		}
		Iterable<PlaceCriteria> placeCriterias = placeCriteriaDao.GetByCriteria(criteria);
		Set<PublicPlaceViewDto> placesDto = new HashSet<PublicPlaceViewDto>();
		for(PlaceCriteria placeCriteria : placeCriterias)
		{
			Place place = placeCriteria.getId().getPlace();
			String placeID = place.getId();
			String placeName = place.getName();
			
			Criteria crit = placeCriteria.getId().getCriteria();
			String criteriaName = crit.getValue();
			
			PublicPlaceViewDto dto = new PublicPlaceViewDto(placeID,placeName,criteriaName);
			placesDto.add(dto);
		}
		
		return placesDto;
	}

	@Override
	public Iterable<String> GetPlaceCriteria() 
	{
		Iterable<Criteria> criterias = criteriaDao.GetAll();
		Set<String> values = new HashSet<String>();
		for(Criteria criteria : criterias)
		{
			values.add( criteria.getValue() );
		}
		return values;
	}
	
	
}
