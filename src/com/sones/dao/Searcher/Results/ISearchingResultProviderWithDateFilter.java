package com.sones.dao.Searcher.Results;

import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface ISearchingResultProviderWithDateFilter	extends	ISearchingResultProvider
{
	public	IViewModelResults	GetViewModelResults( final IApplicationUserID userID , final String fromDate, final String toDate );
}
