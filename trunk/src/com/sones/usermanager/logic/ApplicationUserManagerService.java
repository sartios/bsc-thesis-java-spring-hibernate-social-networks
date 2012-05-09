package com.sones.usermanager.logic;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import com.sones.usermanager.dao.IApplicationUserCredentialDao;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.usermanager.model.ApplicationUserCredential;

public class ApplicationUserManagerService implements IApplicationUserManagerService
{
	private final Logger _LOGGER;
	private IApplicationUserCredentialDao credentialDao;
	private IApplicationUserDao appUserDao;
	private IFacebookAccountDao accountDao;
	
	private ApplicationUserManagerService()
	{
		_LOGGER = Logger.getLogger(ApplicationUserManagerService.class);
	}
	
	public ApplicationUserManagerService(IApplicationUserCredentialDao credentialDao, 
			IApplicationUserDao appUserDao, IFacebookAccountDao accountDao)
	{
		_LOGGER = Logger.getLogger(ApplicationUserManagerService.class);
		this.credentialDao = credentialDao;
		this.appUserDao = appUserDao;
		this.accountDao = accountDao;
	}
	
	@Override
	public boolean createApplicationUser(String name, String username, String password)
	{
		try
		{
			Number applicationUserId = appUserDao.GetRowCount();
			ApplicationUser applicationUser = new ApplicationUser();
			applicationUser.setId( applicationUserId.toString() );
			appUserDao.Save( applicationUser );
			
			ApplicationUserCredential credential = new ApplicationUserCredential(name, username, password, applicationUser);
			Number credentialId = appUserDao.GetRowCount();
			credential.setId( credentialId.toString() );
			credentialDao.Save( credential );
		}
		catch(DataAccessException ex)
		{
			_LOGGER.error("The creation of new user: " + username + " rised an error.");
			return false;
		}
		return true;
	}

	@Override
	public ApplicationUserViewDto login(String username, String password)
	{
		ApplicationUserCredential credential = new ApplicationUserCredential();
		credential.setUsername(username);
		credential.setPassword(password);
		
		ApplicationUserCredential result = credentialDao.getByCredentials(credential);
	
		ApplicationUserViewDto userDto = null;
		if(result != null)
		{
			ApplicationUser appUser = result.getAppUser();
			FacebookAccount account = accountDao.getByApplicationUser(appUser);
			userDto = new ApplicationUserViewDto();
			userDto.setName( result.getName() );
			userDto.setUserID( appUser.getId() );
			if(account != null)
			{
				userDto.setAccountID( account.getId() );
			}
		}
		return userDto;
	}

	@Override
	public boolean deleteApplicationUser(String username, String password)
	{
		ApplicationUserCredential credential = new ApplicationUserCredential();
		credential.setUsername(username);
		credential.setPassword(password);
		
		ApplicationUserCredential result = credentialDao.getByCredentials(credential);
		ApplicationUser appUser = result.getAppUser();
		
		try
		{
			credentialDao.Delete(result);
			appUserDao.Delete(appUser);
		}
		catch(DataAccessException ex)
		{
			_LOGGER.error("The user couldn't be deleted.");
			return false;
		}
		return true;
	}

}
