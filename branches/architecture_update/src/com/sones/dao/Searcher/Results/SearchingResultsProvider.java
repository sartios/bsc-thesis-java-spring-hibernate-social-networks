package com.sones.dao.Searcher.Results;

import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.READER;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.omg.stub.java.rmi._Remote_Stub;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Searcher.Results.IResultViewModel;
import com.sones.businessLogic.Searcher.Results.IViewModelResults;
import com.sones.businessLogic.Searcher.Results.ResultViewModel;
import com.sones.businessLogic.Searcher.Results.ViewModelResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class SearchingResultsProvider	extends	AbstractDao	implements	ISearchingResultProvider
{
	
	private	Logger	_logger;
	private	XMLConfiguration	_config;
	
	public	SearchingResultsProvider()
	{
		super();
		_logger	=	Logger.getLogger(SearchingResultsProvider.class);
		try 
		{
			_config	=	new	XMLConfiguration("config/SqlScripts/UserResults/user_keyword_results.xml");
		} 
		catch (ConfigurationException e) 
		{
			_logger.error(e.getMessage());
		}
	}
	
	@Override
	public List<IResultViewModel> GetKeywordSearchingResults(IApplicationUserID userID) 
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		if( null != userID)
		{
			results.addAll(GetStatusMessages( userID ) );
			results.addAll(GetLinks( userID ) );
			results.addAll(GetVideos( userID ) );
			results.addAll(GetPictures( userID ) );
			results.addAll(GetNotes( userID ) );
		}
		return	results;
	}

	private	List<IResultViewModel>	GetStatusMessages(IApplicationUserID userID)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.status") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
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
	
	private	List<IResultViewModel>	GetLinks(IApplicationUserID userID)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.link") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query.setParameter("userID", userID.GetValue() );
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
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
	
	private	List<IResultViewModel>	GetVideos(IApplicationUserID userID)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.video") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query.setParameter("userID", userID.GetValue() );
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
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
	
	private	List<IResultViewModel>	GetPictures(IApplicationUserID userID)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.picture") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			query.setParameter("userID", userID.GetValue() );
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
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
	
	private	List<IResultViewModel>	GetNotes(IApplicationUserID userID)
	{
		List<IResultViewModel>	results	=	new	ArrayList<IResultViewModel>();
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString("selectQuery.note") );
			query.setParameter(_config.getString("parameters.user.id"), userID.GetValue() );
			_logger.warn(query.getQueryString());
			List	list	=	query.list();
			tx.commit();
			session.flush();
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

	@Override
	public IViewModelResults GetViewModelResults(IApplicationUserID userID) 
	{
		IViewModelResults	results	=	new	ViewModelResults();
		List<IResultViewModel>	list	=	GetKeywordSearchingResults(userID);
		for(IResultViewModel result : list)
		{
			results.AddResult(result);
		}
		return	results;
	}	
}
