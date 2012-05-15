package com.sones.facebook.downloader.logic;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.sones.usermanager.model.ApplicationUser;

public class PostDownloadingTimerTask extends TimerTask
{
	private final Logger _LOGGER;
	private IFacebookDownloaderService service;
	private ApplicationUser appUser;
	
	private PostDownloadingTimerTask()
	{
		_LOGGER = Logger.getLogger(PostDownloadingTimerTask.class);
	}
	
	public PostDownloadingTimerTask(IFacebookDownloaderService service)
	{
		_LOGGER = Logger.getLogger(PostDownloadingTimerTask.class);
		this.service = service;
	}
	
	/**
	 * @return the appUser
	 */
	public ApplicationUser getAppUser() {
		return appUser;
	}

	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(ApplicationUser appUser) {
		this.appUser = appUser;
	}

	@Override
	public void run() 
	{
		_LOGGER.warn("Downloading...");
		service.DownloadWallPosts(appUser);
	}
}
