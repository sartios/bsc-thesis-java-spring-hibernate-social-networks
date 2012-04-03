package com.sones.facebook.JsonHandler.Factory;

import org.apache.commons.beanutils.DynaBean;

import com.sones.facebook.placemanager.model.Place;

public interface IPublicPlaceFactory 
{
	public Place GetPublicPlace( DynaBean beanObject );
}
