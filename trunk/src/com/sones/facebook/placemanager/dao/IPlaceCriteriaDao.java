package com.sones.facebook.placemanager.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.placemanager.model.PlaceCriteria;
import com.sones.facebook.placemanager.model.PlaceCriteriaId;
import com.sones.facebook.publicsource.model.Criteria;

public interface IPlaceCriteriaDao	extends	IGenericDao< PlaceCriteria , PlaceCriteriaId >
{

	Iterable<PlaceCriteria> GetByCriteria(Criteria criteria);

}
