package com.sones.dao.Searcher.Results;

import java.util.List;

import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * Provides methods for retrieving searching results.
 * 
 * @author Savramis	Sartios
 *
 */
public interface ISearchingResultProvider 
{
	public	List<IResultViewModel>	GetKeywordSearchingResults( final IApplicationUserID userID );
	public	IViewModelResults	GetViewModelResults( final IApplicationUserID userID );
}
