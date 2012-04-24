package com.sones.facebook.keywordSearcher.dao;

import java.util.Collection;
import java.util.Date;

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
	public KeywordSearch getLastKeywordSearchByAppUser( ApplicationUser appUser );
	
	/**
	 * Returns the keyword searches that had happened after date and until now
	 * and belongs to the application user.
	 * @param date the date after which the searches had happened.
	 * @param appUser the application user of the searches.
	 * @return Keyword searches
	 * @throws IllegalArgumentException if date or application user is null.
	 */
	public Collection<KeywordSearch> getAfterDateByAppUser( Date date, ApplicationUser appUser);
}
