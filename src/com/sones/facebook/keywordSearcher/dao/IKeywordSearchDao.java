package com.sones.facebook.keywordSearcher.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for accessing {@link KeywordSearch} model.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IKeywordSearchDao extends IGenericDao<KeywordSearch, String>
{
	/**
	 * Returns the last keyword search of specified application user.
	 * @param appUser The application user.
	 * @return Keyword search
	 * @throws IllegalArgumentException if application user is null.
	 */
	public	KeywordSearch	getLastKeywordSearchByAppUser( ApplicationUser appUser );
}
