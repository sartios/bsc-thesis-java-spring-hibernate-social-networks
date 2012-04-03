package com.sones.facebook.publicsource.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.publicsource.model.Criteria;

/**
 * Provides methods for accessing {@link Criteria} model.
 * @author sartios.sones@gmail.com.
 *
 */
public interface ICriteriaDao	extends	IGenericDao< Criteria, String >
{
	/**
	 * Returns the criteria which has the value.
	 * @param value the value of a criteria.
	 * @return criteria.
	 * @throws IllegalArgumentException if the value is null.
	 */
	public Criteria GetByValue(String value);

}
