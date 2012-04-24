package com.sones.facebook.keywordSearcher.dao;

import java.util.Collection;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.usermanager.model.ApplicationUser;

public interface IFacebookPostKeywordResultDao extends IGenericDao<FacebookPostKeywordResult, String>
{

	public Iterable<FacebookPostKeywordResult> GetByApplicationUser( ApplicationUser appUser );
	
	/**
	 * Returns the results by the keyword search.
	 * @param search the keyword search to filter
	 * @return keyword search results
	 */
	public Collection<FacebookPostKeywordResult> getByKeywordSearch( KeywordSearch search );

}
