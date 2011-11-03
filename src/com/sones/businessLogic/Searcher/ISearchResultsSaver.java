package com.sones.businessLogic.Searcher;

import com.sones.businessLogic.Searcher.Results.ISearchResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface ISearchResultsSaver 
{
	public	boolean	SaverResults( final IApplicationUserID userID, final ISearchResults results );
}
