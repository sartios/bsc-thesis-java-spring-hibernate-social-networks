package com.sones.dao.User.Keywords;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.KeywordManager.IUserKeywordIDRetriever;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class UserKeywordDao extends	AbstractDao implements	IUserKeywordIDRetriever
{

	private	PropertiesConfiguration _config;
	private	Logger	_logger;
	
	public	UserKeywordDao()
	{
		super();
		_logger	=	Logger.getLogger( UserKeywordDao.class );
		try 
		{
			_config	=	new	PropertiesConfiguration("config/SqlScripts/UserKeywords/user_keywords.properties");
		} catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	@Override
	public	List< String >	GetKeywords( final IApplicationUserID userID ) 
	{
		List< String >	keywordIDs	=	null;
		
		try
		{
			startOperation();
			Query	query	=	session.createSQLQuery( _config.getString( "select.user.keywords" ) );
			query.setParameter(_config.getString( "user.parameter" ), userID.GetValue() );
			List list = query.list();
			keywordIDs	=	GetKeywordList( list );
			tx.commit();
		}
		catch ( HibernateException e )
		{
			tx.rollback();
			_logger.error( e.getMessage() );
		}
		finally
		{
			HibernateUtil.close( session );
		}
		return	keywordIDs;
	}
	
	private	List< String >	GetKeywordList( final List list )
	{
		List< String >	ids	=	new	ArrayList<String>();
		for( Iterator it = list.iterator(); it.hasNext(); )
		{
			ids.add( String.valueOf( it.next() ) );
		}
		return	ids;
	}
	
	
}
