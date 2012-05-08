package com.sones.facebook.controller.downloader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.downloader.logic.IFacebookPostDownloaderManagerService;
import com.sones.sharedDto.exceptions.MapErrorException;

public class DownloaderMainFrameController 
{
	private IFacebookPostDownloaderManagerService service;
	
	
	public DownloaderMainFrameController()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-downloader-manager-service.xml");
		service = (IFacebookPostDownloaderManagerService) context.getBean("postDownloaderManagerService");
	}
	
	public void startDownloading(String facebookAccountId)
	{
		try
		{
			service.startDownloading(facebookAccountId);
		}
		catch (RuntimeException e) 
		{
		}
	}
}
