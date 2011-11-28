package com.sones.controllers.results;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;

import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.Searcher.Results.ViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.Searcher.Results.ISearchingResultProvider;
import com.sones.dao.Searcher.Results.SearchingResultsProvider;
import com.sones.ui.results.LinkView;
import com.sones.ui.results.StatusMessageView;

public class ResultsViewController	implements	IResultsViewController
{

	private	ISearchingResultProvider	_resultProvider;
	
	public	ResultsViewController()
	{
		_resultProvider	=	new	SearchingResultsProvider();
	}
	
	@Override
	public List<IResultViewModel> GetKeywordSearchingResultList( IApplicationUserID userID)
	{
		if( null != userID )
		{
			return	_resultProvider.GetKeywordSearchingResults(userID);
		}
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		return	results;
	}

	@Override
	public IViewModelResults GetKeywordSearchingResults(IApplicationUserID userID) 
	{
		if( null != userID )
		{
			return	_resultProvider.GetViewModelResults(userID);
		}
		IViewModelResults	results	=	new	ViewModelResults();
		return	results;
	}

	@Override
	public void ShowFeed(String feedID, String feedType) 
	{
		PropertiesConfiguration	_config	=	null;
		try
		{
			_config	=	new	PropertiesConfiguration("config/Facebook/Content/Facebook.Content.properties");
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		if( feedType.equals(_config.getString("StatusMessage.Type")))
		{
			StatusMessageView	status	=	new	StatusMessageView();
			status.SetFeedID(feedID);
			status.show(true);
		}
		else if( feedType.equals(_config.getString("Link.Type")))
		{
			LinkView	link	=	new	LinkView();
			link.SetFeedID(feedID);
			link.show(true);
		}
		else if( feedType.equals(_config.getString("Note.Type")))
		{
			
		}
		else if( feedType.equals(_config.getString("Picture.Type")))
		{
			
		}
		else if( feedType.equals(_config.getString("Video.Type")))
		{
			
		}
	}
	
}
