package com.sones.businessLogic.Facebook;

public interface IDownloadingTimer 
{
	public	void	StartDownloading();
	public	void	StopDownloading();
	
	public	void	SetDownloader( final IFacebookDownloader downloader );
	public	void	SetTime( final String time );
}
