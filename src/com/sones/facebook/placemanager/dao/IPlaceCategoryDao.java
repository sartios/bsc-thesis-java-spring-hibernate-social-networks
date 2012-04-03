package com.sones.facebook.placemanager.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.placemanager.model.PlaceCategory;

public interface IPlaceCategoryDao	extends	IGenericDao< PlaceCategory, String >
{

	public PlaceCategory GetByDescription(String description);
	
}
