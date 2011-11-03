package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface ISearcher
{
	public	boolean	Search();
	public	void	SetApplicationUser( final IApplicationUserID	userID );
}
