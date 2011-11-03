package com.sones.dao.Searcher.Results;

import java.util.Iterator;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Searcher.ISearchResultsSaver;
import com.sones.businessLogic.Searcher.Results.IResult;
import com.sones.businessLogic.Searcher.Results.ISearchResults;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class ResultsDao	extends AbstractDao implements	ISearchResultsSaver
{
	private	Logger	_logger;
	private	PropertiesConfiguration	_config;
	
	public	ResultsDao()
	{
		super();
		_logger	=	Logger.getLogger( ResultsDao.class );
		try 
		{
			_config	=	new	PropertiesConfiguration( "config/SqlScripts/UserResults/user_results.properties" );
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	public	boolean	InsertResult( final IPersistableResult result )
	{
		boolean	isSaved;
		startOperation();
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString( "insert.result" ) );
			query.setParameter( _config.getString( "user.parameter" ), result.GetUserID().toString() );
			query.setParameter( _config.getString( "feed.parameter" ), result.GetFeedID().toString() );
			query.setParameter( _config.getString( "keyword.parameter" ), result.GetKeywordID().toString() );
			
			_logger.warn("Saving entry: \n");
			_logger.warn("User: "+result.GetUserID()+" feed: "+result.GetFeedID()+" keyword: "+result.GetKeywordID() );
			_logger.warn( query.getQueryString() );
			query.executeUpdate();
			tx.commit();
			isSaved	=	true;
		}
		catch ( HibernateException  e )
		{
			_logger.error( e.getMessage() );
			tx.rollback();
			isSaved	=	false;
		}
		finally
		{
			HibernateUtil.close( session );
		}
		return	isSaved;
	}

	@Override
	public boolean SaverResults( final IApplicationUserID userID, final ISearchResults results) 
	{
		Iterator< IResult >	iterator	=	results.GetIterator();
		for( ; iterator.hasNext(); )
		{
			IResult	result	=	iterator.next();
			( ( IPersistableResult )result ).SetUserID( userID.GetValue() );
			InsertResult( (IPersistableResult) result );
		}
		return false;
	}
	
	
}
