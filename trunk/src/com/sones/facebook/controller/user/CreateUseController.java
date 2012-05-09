package com.sones.facebook.controller.user;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import com.sones.usermanager.logic.IApplicationUserManagerService;

public class CreateUseController 
{
	private IApplicationUserManagerService userManagerService;
	private ApplicationUserViewDto userDto;
	
	public CreateUseController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("UsermanagerService/spring-usermanager-service.xml");
		userManagerService = (IApplicationUserManagerService) context.getBean("applicationUserManagerService");
	}
	
	public void createNewUser(String name, String username, String password)
	{
		userManagerService.createApplicationUser(name, username, password);
	}
	
	public boolean login(String username, String password)
	{
		userDto = userManagerService.login(username, password);
		if(userDto != null)
		{
			return true;
		}
		return false;
	}
	
	public ApplicationUserViewDto getUserDto()
	{
		return userDto;
	}
}
