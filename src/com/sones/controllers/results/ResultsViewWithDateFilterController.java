package com.sones.controllers.results;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.omg.CORBA._PolicyStub;

import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.Searcher.Results.ViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.controllers.results.calendar.EditBoxCalendarController;
import com.sones.controllers.results.calendar.ICalendarController;
import com.sones.dao.Searcher.Results.ISearchingResultProviderWithDateFilter;
import com.sones.dao.Searcher.Results.SearchingResultsProviderWithDateFilter;
import com.sones.ui.results.LinkView;
import com.sones.ui.results.PictureView;
import com.sones.ui.results.StatusMessageView;

public class ResultsViewWithDateFilterController	implements	IResultsViewWithDateFilterController
{
	
	private	IResultsViewController	_controller;
	private ISearchingResultProviderWithDateFilter _resultProvider;
	private	ICalendarController	_calendarController;
	
	public	ResultsViewWithDateFilterController()
	{
		_controller	=	new	ResultsViewController();
		_resultProvider	=	new SearchingResultsProviderWithDateFilter();
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
			PictureView	picture	=	new	PictureView();
			picture.SetFeedID(feedID);
			picture.show(true);
		}
		else if( feedType.equals(_config.getString("Video.Type")))
		{
			
		}
	}

	@Override
	public String GetEndDate() 
	{
		if( null != _calendarController )
		{
			return	_calendarController.GetEndDate();
		}
		return	"";
	}

	@Override
	public String GetFromDate() 
	{
		if( null != _calendarController )
		{
			return	_calendarController.GetFromDate();
		}
		return	"";
	}

	@Override
	public void ShowCalendar(JFrame owner) 
	{
		if( null == _calendarController )
		{
			_calendarController	=	new	EditBoxCalendarController(owner);
		}
		_calendarController.ShowCalendarDialog();
	}

	@Override
	public List<IResultViewModel> GetKeywordSearchingResultList(
			IApplicationUserID userID, String fromDate, String toDate) {
		return null;
	}

	@Override
	public IViewModelResults GetKeywordSearchingResults(
			IApplicationUserID userID, String fromDate, String toDate) {
		// TODO Auto-generated method stub
		return _resultProvider.GetViewModelResults(userID, fromDate, toDate);
	}	
}
