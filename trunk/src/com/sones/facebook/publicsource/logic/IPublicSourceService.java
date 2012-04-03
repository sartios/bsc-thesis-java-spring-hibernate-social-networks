package com.sones.facebook.publicsource.logic;

import com.sones.facebook.publicsource.model.Criteria;
import com.sones.facebook.tokenmanager.model.FacebookToken;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for downloading facebook public sources by providing criteria.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IPublicSourceService 
{
	/**
	 * Downloads the public places that agree on criteria.
	 * @param criterias the criterias to search for
	 * @param token the facebook accesss token.
	 * @param appUser the application user who requests the service.
	 * @throws IllegalArgumentException if any of the parameters is null.
	 */
	public void DownloadPublicPlaces( Iterable< Criteria > criterias, FacebookToken token, ApplicationUser appUser );
}
