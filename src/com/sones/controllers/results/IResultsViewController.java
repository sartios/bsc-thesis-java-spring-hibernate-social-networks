package com.sones.controllers.results;

import java.util.List;

import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;

/**
 * Provides methods for handling the information that Result View needs.
 * 
 * @author Savramis Sartios
 *
 */
public interface IResultsViewController
{
	/**
	 * Returns the results of keyword searching for a specific user.
	 * If application user is null then it must informs the user.
	 * @param userID - user for which we want to get results.
	 * @return Results of keywords searching.
	 */
	public	List<IResultViewModel>	GetKeywordSearchingResultList( final IApplicationUserID	userID );
	
	public	IViewModelResults	GetKeywordSearchingResults( final IApplicationUserID	userID );
	
	public	void	ShowFeed(final String feedID, final String feedType);
}
