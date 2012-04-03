package com.sones.facebook.JsonHandler.Factory;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.placemanager.model.PlaceCategory;

public class PublicPlaceFactory implements IPublicPlaceFactory
{
	private	final Logger _LOGGER;
	
	public PublicPlaceFactory()
	{
		_LOGGER = Logger.getLogger( PublicPlaceFactory.class );
	}
	
	@Override
	public Place GetPublicPlace(DynaBean beanObject) 
	{
		Place place = null;
		if( beanObject != null )
		{
			String id = getProperty( beanObject, "id", "Error while extracting id" );
			String name = getProperty( beanObject, "name", "Error while extracting name" );
			String category = getProperty( beanObject, "category", "Error while extracting category" );
//			String street = getLocationProperty( beanObject, "street", "Error while extracting street" );
//			String city = getLocationProperty( beanObject, "city", "Error while extracting city" );
//			String country = getLocationProperty( beanObject, "country", "Error while extracting country" );
//			String zip = getLocationProperty( beanObject, "zip", "Error while extracting zip" );
			String latitude = getLocationProperty( beanObject, "latitude", "Error while extracting latitude" );
			String longitude = getLocationProperty( beanObject, "longitude", "Error while extracting longitude" );

			place = new Place();
			place.setId( id );
			place.setCategory( new PlaceCategory( category ) );
			place.setLatitude( latitude );
			place.setLongitude( longitude );
			place.setName( name );
		}
		return place;
	}
	
	private String getProperty( DynaBean beanObject , String propertyName , String message )
	{
		String property = null;
		try
		{
			property = beanObject.get( propertyName ).toString();
		}
		catch(MorphException ex)
		{
			_LOGGER.error( message + "\n" + ex.getLocalizedMessage() );
		}
		return property;
	}
	
	private String getLocationProperty( DynaBean beanObject , String propertyName , String message )
	{
		String property = null;
		try
		{
			property = ( ( DynaBean )( beanObject.get( "location" ) ) ).get( propertyName ).toString();
		}
		catch(MorphException ex)
		{
			_LOGGER.error( message + "\n" + ex.getLocalizedMessage() );
		}
		return property;
	}

}
