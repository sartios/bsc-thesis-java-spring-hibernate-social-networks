package com.sones.controllers.main;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface ISonesConsoleController 
{

	public	void	StartDownload();
	public	void	StopDownload();
	public	void	SetFacebookDownloadingInterval( final String interval);
	
	public	void	SetApplicationUser( final IApplicationUserID userID );
	
	public	void	AddKeyword( String keyword );
	public	void	AddLocation( String location );
	public	void	SearchForKeywords();
	public	void	SearchForLocations();
	public	void	SetSearchingInterval();
	
	public	void	ViewKeywordSearchingResults();
}
