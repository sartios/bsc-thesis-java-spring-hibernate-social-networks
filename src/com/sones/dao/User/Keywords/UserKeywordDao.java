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
import com.sones.businessLogic.KeywordManager.IKeyword;
import com.sones.businessLogic.KeywordManager.IUserKeywordIDRetriever;
import com.sones.businessLogic.KeywordManager.IUserKeywordSaver;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;
import com.sones.dao.Keywords.IKeywordDao;
import com.sones.dao.Keywords.KeywordDao;

public class UserKeywordDao extends	AbstractDao implements	IUserKeywordIDRetriever, IUserKeywordSaver
{

	private	PropertiesConfiguration _config;
	private	Logger	_logger;
	private	IKeywordDao	_keywordDao;
	
	public	UserKeywordDao()
	{
		super();
		_logger	=	Logger.getLogger( UserKeywordDao.class );
		try 
		{
			_config	=	new	PropertiesConfiguration("config/SqlScripts/UserKeywords/user_keywords.properties");
		} 
		catch (ConfigurationException e) 
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
	

	@Override
	public boolean SaveKeyword(IApplicationUserID userID, String keywordValue) 
	{
		boolean	isSaved	=	false;
		if( (null != userID) && (null != keywordValue) )
		{
			IKeyword	keyword	=	GetKeywordDao().GetKeywordByValue(keywordValue);
			if( null == keyword )
			{
				GetKeywordDao().SaveKeyword( keywordValue );
				keyword	=	GetKeywordDao().GetKeywordByValue(keywordValue);
			}
			try
			{
				startOperation();
				Query	query	=	session.createSQLQuery( _config.getString( "insert.user.keywords" ) );
				query.setParameter(_config.getString( "user.parameter" ), userID.GetValue() );
				query.setParameter(_config.getString( "keyword.id.parameter" ), keyword.GetID() );
				query.executeUpdate();
				tx.commit();
				session.flush();
				isSaved	=	true;
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
		}
		return	 isSaved;
	}
	
	private	IKeywordDao	GetKeywordDao()
	{
		if ( null == _keywordDao )
		{
			_keywordDao	=	new	KeywordDao();
		}
		return	_keywordDao;
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
