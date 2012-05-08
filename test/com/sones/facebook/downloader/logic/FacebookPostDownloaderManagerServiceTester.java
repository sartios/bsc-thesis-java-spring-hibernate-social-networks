package com.sones.facebook.downloader.logic;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FacebookPostDownloaderManagerServiceTester 
{
	private final String FACEBOOK_ACCOUNT_ID = "100000866964787";
	private IFacebookPostDownloaderManagerService managerService;
	
	public FacebookPostDownloaderManagerServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookDownloader/spring-facebook-downloader-manager-service.xml");
		managerService = (IFacebookPostDownloaderManagerService) context.getBean("postDownloaderManagerService");
	}
	
	@Test
	public void startDownloadingCorrecltyWorkingWithValidValues()
	{
		managerService.saveOptions(FACEBOOK_ACCOUNT_ID, "10000");
		managerService.startDownloading(FACEBOOK_ACCOUNT_ID);
	}
}
