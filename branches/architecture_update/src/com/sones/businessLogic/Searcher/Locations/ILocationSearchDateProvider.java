package com.sones.businessLogic.Searcher.Locations;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface ILocationSearchDateProvider 
{
	public	String	GetDateOfLastSearch( IApplicationUserID	userID );

	public void SaveDateOfCurrentSearch( String now, IApplicationUserID	userID );
}
