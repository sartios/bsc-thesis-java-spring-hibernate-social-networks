package com.sones.facebook.controller.downloader;

import com.sones.facebook.downloader.logic.IFacebookPostDownloaderManagerService;

public class IntervalController
{
	private IFacebookPostDownloaderManagerService service;
	
	public IntervalController()
	{}
	
	public void saveOption(String facebookUserId, String interval)
	{
		service.saveOptions(facebookUserId, interval);
	}
	
	public void setService(IFacebookPostDownloaderManagerService service)
	{
		this.service = service;
	}
}
