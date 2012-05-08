package com.sones.facebook.downloader.logic;

import org.springframework.dao.DataAccessException;

public interface IFacebookPostDownloaderManagerService 
{
	public void startDownloading(String facebookUserId) throws DataAccessException;
	public void stopDownloading();
	public void saveOptions(String facebookUserId, String interval);
}
