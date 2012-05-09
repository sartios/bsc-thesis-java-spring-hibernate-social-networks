package com.sones.usermanager.logic;

import com.sones.sharedDto.usermanager.ApplicationUserViewDto;

public interface IApplicationUserManagerService
{
	boolean createApplicationUser(String name, String username, String password);
	
	ApplicationUserViewDto login(String username, String password);
	
	boolean deleteApplicationUser(String username, String password);
}
