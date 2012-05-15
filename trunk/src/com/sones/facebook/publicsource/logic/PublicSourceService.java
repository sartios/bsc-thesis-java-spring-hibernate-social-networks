package com.sones.facebook.publicsource.logic;

import org.apache.log4j.Logger;

import com.sones.facebook.graphApi.GraphApiHandler.IFacebookGraphApiHandler;
import com.sones.facebook.placemanager.dao.IPlaceCategoryDao;
import com.sones.facebook.placemanager.dao.IPlaceCriteriaDao;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceCategory;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.publicsource.dao.ICriteriaDao;
import com.sones.facebook.publicsource.model.Criteria;
import com.sones.facebook.tokenmanager.dao.IFacebookTokenDao;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class PublicSourceService implements IPublicSourceService
{
	/**
	 * The class logger.
	 */
	private final Logger _LOGGER;
	
	/**
	 * The facebook graph api handler.
	 */
	private	IFacebookGraphApiHandler	graphApiHandler;
	
	/**
	 * The dao for {@link Place} model.
	 */
	private IPlaceDao placeDao;
	
	/**
	 * The dao for {@link PlaceCategory} model.
	 */
	private	IPlaceCategoryDao placeCategoryDao;
	
	/**
	 * The dao for {@link PlaceCriteria} model.
	 */
	private	IPlaceCriteriaDao placeCriteriaDao;
	
	/**
	 * The dao for {@link Criteria} model.
	 */
	private	ICriteriaDao criteriaDao;
	
	private IApplicationUserDao appUserDao;
	
	private IFacebookTokenDao tokenDao;
		
	/**
	 * Initializes the object.
	 */
	public	PublicSourceService()
	{
		_LOGGER = Logger.getLogger( PublicSourceService.class );
	}
	
		/**
	 * @param graphApiHandler
	 * @param placeDao
	 * @param placeCategoryDao
	 * @param placeCriteriaDao
	 * @param criteriaDao
	 * @param appUserDao
	 * @param tokenDao
	 */
	public PublicSourceService(IFacebookGraphApiHandler graphApiHandler,
			IPlaceDao placeDao, IPlaceCategoryDao placeCategoryDao,
			IPlaceCriteriaDao placeCriteriaDao, ICriteriaDao criteriaDao,
			IApplicationUserDao appUserDao, IFacebookTokenDao tokenDao) 
	{
		_LOGGER = Logger.getLogger( PublicSourceService.class );
		this.graphApiHandler = graphApiHandler;
		this.placeDao = placeDao;
		this.placeCategoryDao = placeCategoryDao;
		this.placeCriteriaDao = placeCriteriaDao;
		this.criteriaDao = criteriaDao;
		this.appUserDao = appUserDao;
		this.tokenDao = tokenDao;
	}

		/** {@inheritDoc} */
	@Override
	public void DownloadPublicPlaces(Iterable<String> criteriaValues, String appUserID, Iterable<String> cities, String radical) 
	{
		ApplicationUser appUser = appUserDao.GetById(appUserID);
		CheckNullability(appUser, "Application user can't be null.");
		
		CheckNullability(criteriaValues, "Criteria values can't be null.");
		
		FacebookToken token = tokenDao.GetByApplicationUser(appUser);
		CheckNullability(token, "Application user has not facebook token.");

		for( String criteriaValue : criteriaValues )
		{
			Criteria criteria = new Criteria(criteriaValue);
			for( String city : cities)
			{
				Iterable< Place > places = graphApiHandler.GetPublicPlaces( criteria, token, city, radical );
				SavePlacesWithCriteria( places , criteriaValue );
			}
		}
	}
	
	private void SavePlacesWithCriteria(Iterable<Place> places,
				String criteriaValue) {
		for( Place place : places )
		{
			SavePlaceAndReturn( place );
			Criteria dbCriteria = SaveCriteriaAndReturn( criteriaValue );
			PlaceCriteria placeCriteria = new PlaceCriteria(place, dbCriteria);
			if( placeCriteriaDao.GetById( placeCriteria.getId() ) == null )
			{
				placeCriteriaDao.Save( placeCriteria );
			}
		}			
		}

	private Criteria SaveCriteriaAndReturn(String criteriaValue) {
		Criteria model = criteriaDao.GetByValue( criteriaValue );
		if( model == null )
		{
			String id = criteriaDao.GetRowCount().toString();
			model = new Criteria( criteriaValue );
			model.setId( id );
			criteriaDao.Save( model );
		}
		return model;
	}

	/** {@inheritDoc} */
	@Override
	public void DownloadPublicPlaces(Iterable<Criteria> criterias, FacebookToken token, ApplicationUser appUser) 
	{
		CheckNullability(appUser, "Application user can't be null.");
		CheckNullability(token, "Token can't be null.");
		CheckNullability(criterias, "Criterias can't be null.");
		
		for( Criteria criteria : criterias )
		{
			Iterable< Place > places = graphApiHandler.GetPublicPlaces( criteria, token );
			SavePlacesWithCriteria( places , criteria );
		}
		
	}

	/**
	 * Checks if the object is null
	 * @param object the object to check
	 * @param message the message to throw
	 * @throws IllegalArgumentException if the object is null.
	 */
	private	void	CheckNullability( Object object , String message )
	{
		if( object == null )
		{
			_LOGGER.error( message );
			throw	new	IllegalArgumentException( message );
		}
	}
	
	/**
	 * Saves the places with the criteria that was search and it was found.
	 * @param places the places that were retrieved.
	 * @param criteria the criteria that was searched.
	 */
	private void SavePlacesWithCriteria( Iterable<Place> places, Criteria criteria )
	{
		for( Place place : places )
		{
			SavePlaceAndReturn( place );
			Criteria dbCriteria = SaveCriteriaAndReturn( criteria );
			PlaceCriteria placeCriteria = new PlaceCriteria(place, dbCriteria);
			if( placeCriteriaDao.GetById( placeCriteria.getId() ) == null )
			{
				placeCriteriaDao.Save( placeCriteria );
			}
		}
	}

	/**
	 * Saves and returns the criteria.
	 * @param criteria the criteria to be saved.
	 * @return the criteria
	 */
	private Criteria SaveCriteriaAndReturn(Criteria criteria) 
	{
		Criteria model = criteriaDao.GetByValue( criteria.getValue() );
		if( model == null )
		{
			String id = criteriaDao.GetRowCount().toString();
			model = new Criteria( criteria.getValue() );
			model.setId( id );
			criteriaDao.Save( model );
		}
		return model;
	}

	/**
	 * Saves and returns the place.
	 * @param place the place to be saved.
	 * @return the place.
	 */
	private Place SavePlaceAndReturn(Place place) 
	{
		Place currentPlace = place;
		PlaceCategory category = GetCategory( place );
		if( IsPlaceExisted( place ) == false )
		{
			currentPlace.setCategory( category );
			placeDao.Save( currentPlace );
		}
		return place;
	}

	/**
	 * Checks if the place exists into the database and returns true if it does.
	 * @param place the place to check its existence.
	 * @return true if the place exists into the database.
	 */
	private boolean IsPlaceExisted(Place place) 
	{
		if( placeDao.GetById( place.getId() ) == null )
		{
			return false;
		}
		return true;
	}

	/**
	 * Checks if the category value exists into the database and returns the category with its id.
	 * @param place the place from which the category will be retrieved.s
	 * @return the category of the place.
	 */
	private PlaceCategory GetCategory( Place place )
	{
		PlaceCategory tmpCategory = place.getCategory();
		PlaceCategory category = placeCategoryDao.GetByDescription( tmpCategory.getDescription() );
		if( category == null )
		{
			Number rowCountNumber = placeCategoryDao.GetRowCount();
			String id = rowCountNumber.toString();
			tmpCategory.setId( id );
			placeCategoryDao.Save( tmpCategory );
			category = tmpCategory;
		}
		return	category;
	}
}
