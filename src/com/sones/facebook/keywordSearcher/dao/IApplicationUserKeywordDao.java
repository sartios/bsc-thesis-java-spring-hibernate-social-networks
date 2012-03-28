package com.sones.facebook.keywordSearcher.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeywordId;
import com.sones.userManager.model.ApplicationUser;

public interface IApplicationUserKeywordDao extends  IGenericDao<ApplicationUserKeyword, ApplicationUserKeywordId>
{
	/**
	 * Returns the {@link ApplicationUserKeyword}s for the specified user
	 * @param appUser
	 * @return the {@link ApplicationUserKeyword}s for the specified user
	 * @throws IllegalArgumentException if application user is null.
	 */
	public	Iterable<ApplicationUserKeyword>	getByApplicationUser( ApplicationUser appUser );
}
