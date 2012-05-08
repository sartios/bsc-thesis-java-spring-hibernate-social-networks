package com.sones.usermanager.dao;

import com.sones.dao.IGenericDao;
import com.sones.usermanager.model.ApplicationUserCredential;

public interface IApplicationUserCredentialDao extends IGenericDao<ApplicationUserCredential, String>
{
	public ApplicationUserCredential getByCredentials(ApplicationUserCredential appUserCredentials);
}
