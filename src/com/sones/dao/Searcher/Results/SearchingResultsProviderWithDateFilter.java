package com.sones.dao.Searcher.Results;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.Searcher.Results.ResultViewModel;
import com.sones.businessLogic.Searcher.Results.ViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class SearchingResultsProviderWithDateFilter	extends	AbstractDao	implements	ISearchingResultProviderWithDateFilter
{

	private	ISearchingResultProvider	_resultProvider;
	private	XMLConfiguration	_config;
	private	Logger	_logger;
	
	public	SearchingResultsProviderWithDateFilter()
	{
		super();
		_resultProvider	=	new	SearchingResultsProvider();
		_logger	=	Logger.getLogger(SearchingResultsProviderWithDateFilter.class);
		try
		{
			_config	=	new	XMLConfiguration("config/SqlScripts/UserResults/user_keyword_results2.xml");
		}
		catch (ConfigurationException e)
		{
			_logger.error(e.getMessage());
		}
	}
	
	@Override
	public IViewModelResults GetViewModelResults(IApplicationUserID userID,	String fromDate, String toDate) 
	{
		IViewModelResults	results	=	new	ViewModelResults();
		List<IResultViewModel>	list	=	GetKeywordSearchingResults(userID,fromDate,toDate);
		for(IResultViewModel result : list)
		{
			results.AddResult(result);
		}
		return	results;
	}

	@Override
	public List<IResultViewModel> GetKeywordSearchingResults(IApplicationUserID userID) 
	{
		return	_resultProvider.GetKeywordSearchingResults(userID);
	}

	@Override
	public IViewModelResults GetViewModelResults(IApplicationUserID userID) 
	{
		return	_resultProvider.GetViewModelResults(userID);
	}
	
	private	List<IResultViewModel>	GetStatusMessages(IApplicationUserID userID, String from, String to)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.status") );
			query=SetQueryParameters(query, userID, from, to);
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
			_logger.warn("Status Results: "+list.size());
			results.addAll( GetResultsFromObjects(list) );
		}
		catch( HibernateException ex )
		{
			_logger.error(ex.getMessage());
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	results;
	}
	
	private	List<IResultViewModel>	GetLinks(IApplicationUserID userID, String from, String to)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.link") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query=SetQueryParameters(query, userID, from, to);
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
			_logger.warn("Link Results: "+list.size());
			results.addAll( GetResultsFromObjects(list) );
		}
		catch( HibernateException ex )
		{
			_logger.error(ex.getMessage());
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	results;
	}
	
	private	List<IResultViewModel>	GetVideos(IApplicationUserID userID, String from, String to)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.video") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query=SetQueryParameters(query, userID, from, to);
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
			_logger.warn("Video Results: "+list.size());
			results.addAll( GetResultsFromObjects(list) );
		}
		catch( HibernateException ex )
		{
			_logger.error(ex.getMessage());
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	results;
	}
	
	private	List<IResultViewModel>	GetPictures(IApplicationUserID userID, String from, String to)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.picture") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query=SetQueryParameters(query, userID, from, to);
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
			_logger.warn("Picture Results: "+list.size());
			results.addAll( GetResultsFromObjects(list) );
		}
		catch( HibernateException ex )
		{
			_logger.error(ex.getMessage());
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	results;
	}
	
	private	List<IResultViewModel>	GetNotes(IApplicationUserID userID, String from, String to)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.note") );
			query=SetQueryParameters(query, userID, from, to);
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
			_logger.warn("Note Results: "+list.size());
			results.addAll( GetResultsFromObjects(list) );
		}
		catch( HibernateException ex )
		{
			_logger.error(ex.getMessage());
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	results;
	}
	
	private	List<IResultViewModel>	GetResultsFromObjects(List<Object[]> objects)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		if( null != objects )
		{
			if( objects.size() > 0)
			{
				for (Object[] object : objects) 
				{
					String	keyword	=	object[_config.getInt("columns.keyword.index")].toString();
					String	feedID	=	object[_config.getInt("columns.feedID.index")].toString();
					String	type	=	object[_config.getInt("columns.type.index")].toString();
					String	content	=	object[_config.getInt("columns.content.index")].toString();
					String	date	=	object[_config.getInt("columns.date.index")].toString();
					IResultViewModel	result	=	new	ResultViewModel();
					((ResultViewModel)result).SetFeedID(feedID);
					((ResultViewModel)result).AddKeyword(keyword);
					((ResultViewModel)result).SetType(type);
					((ResultViewModel)result).SetContent(content);
					results.add(result);
				}	
			}
		}
		return	results;
	}
	
	private	Query	SetQueryParameters(Query query, IApplicationUserID userID, String from, String to)
	{
		Query	tmp	=	query;
		tmp.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
		tmp.setParameter(_config.getString("parameters.from"), from );
		tmp.setParameter(_config.getString("parameters.to"), to );
		return	tmp;
	}
	
	public List<IResultViewModel> GetKeywordSearchingResults(IApplicationUserID userID, String from, String to) 
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		if( null != userID)
		{
			results.addAll(GetStatusMessages( userID, from, to ) );
			results.addAll(GetLinks( userID, from, to ) );
			results.addAll(GetVideos( userID, from, to ) );
			results.addAll(GetPictures( userID, from, to ) );
			results.addAll(GetNotes( userID, from, to ) );
		}
		return	results;
	}

}
