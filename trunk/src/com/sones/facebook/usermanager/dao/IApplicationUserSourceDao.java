package com.sones.facebook.usermanager.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.facebook.usermanager.model.ApplicationUserSourceId;
import com.sones.usermanager.model.ApplicationUser;

public interface IApplicationUserSourceDao extends IGenericDao<ApplicationUserSource, ApplicationUserSourceId>
{
	/**
	 * Returns the application user's sources, searched by user.
	 * @param appUser
	 * @return Application user sources.
	 * @throws	IllegalArgumentException	if application user is null.
	 */
	public	Iterable<ApplicationUserSource>	getApplicationUserSourcesByUser( ApplicationUser appUser);
}
