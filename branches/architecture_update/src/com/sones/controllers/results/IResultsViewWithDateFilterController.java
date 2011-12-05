package com.sones.controllers.results;

import java.util.List;

import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;

public interface IResultsViewWithDateFilterController	extends	IResultsViewController
{
	public	IViewModelResults	GetKeywordSearchingResults( final IApplicationUserID	userID, final String fromDate, final String toDate );
	public	List<IResultViewModel>	GetKeywordSearchingResultList( final IApplicationUserID	userID, final String fromDate, final String toDate );
}
