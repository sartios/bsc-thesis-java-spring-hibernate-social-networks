package com.sones.facebook.tokenmanager.dao;

import com.sones.dao.IGenericDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.usermanager.model.ApplicationUser;

/**
 * Provides methods for accessing {@link FacebookAccount} model.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookAccountDao	extends	IGenericDao<FacebookAccount, String>
{

	FacebookAccount getByApplicationUser(ApplicationUser appUser);

}
