package com.sones.usermanager.logic;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.sharedDto.usermanager.ApplicationUserViewDto;
import com.sones.usermanager.dao.IApplicationUserCredentialDao;
import com.sones.usermanager.dao.IApplicationUserDao;

public class ApplicationUserManagerServiceTester
{
	private IApplicationUserManagerService service;
	private final String name = "name";
	private final String username = "username";
	private final String password = "password";
	private IApplicationUserCredentialDao credentialDao;
	private IApplicationUserDao userDao;
	
	public ApplicationUserManagerServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("UsermanagerService/spring-usermanager-service.xml");
		service = (IApplicationUserManagerService) context.getBean("applicationUserManagerService");
		credentialDao = (IApplicationUserCredentialDao) context.getBean("credentialDao");
		userDao = (IApplicationUserDao) context.getBean("appUserDao");
	}
	
	@Test
	public void createApplicationUserShouldWorkOnValidValues()
	{
		Boolean isCreated = service.createApplicationUser(name, username, password);
		assertTrue(isCreated);
		service.deleteApplicationUser(username, password);
	}
	
	@Test
	public void loginShouldWorkOnValidValues()
	{
		service.createApplicationUser(name, username, password);
		ApplicationUserViewDto userDto = service.login(username, password);
		assertNotNull(userDto);
		service.deleteApplicationUser(username, password);
	}
}
