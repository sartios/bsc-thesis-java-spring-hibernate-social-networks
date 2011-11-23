package com.sones.businessLogic.GeoNames;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocation;
import com.sones.businessLogic.GeoNames.Locations.IGeoNamesLocations;

public class GeoNamesClientTest
{

	@Test
	public	void	GetLocation_NotNullLocationsOnRealLocationInput_Test()
	{
		IGeoNamesClient	client	=	new	GeoNamesClient();
		String locationName	=	"sarti";
		IGeoNamesLocations	locations	=	client.GetLocations(locationName);
		Assert.assertNotNull(locations);
	}
	
	@Test
	public	void	GetLocation_NotNullLocationsOnBulkInputs_Test()
	{
		IGeoNamesClient	client	=	new	GeoNamesClient();
		String locationName	=	"something";
		IGeoNamesLocations	locations	=	client.GetLocations(locationName);
		Assert.assertNotNull(locations);
	}
	
	@Test
	public	void	GetLocation_EqualLocationNameOnRealLocationInput_Test()
	{
		IGeoNamesClient	client	=	new	GeoNamesClient();
		String locationName	=	"sarti";
		IGeoNamesLocations	locations	=	client.GetLocations(locationName);
		Iterator< IGeoNamesLocation >	iterator	=	locations.GetIterator();
		IGeoNamesLocation	location	=	iterator.next();
		Assert.assertEquals(locationName, location.GetLocationName() );
	}
	
	@Test
	public	void	GetLocation_ThereAreNotLocationsOnBulkInput_Test()
	{
		IGeoNamesClient	client	=	new	GeoNamesClient();
		String locationName	=	"something";
		IGeoNamesLocations	locations	=	client.GetLocations(locationName);
		Iterator< IGeoNamesLocation >	iterator	=	locations.GetIterator();
		Assert.assertFalse( iterator.hasNext() );
	}
}
