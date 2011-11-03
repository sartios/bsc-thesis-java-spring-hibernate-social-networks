package com.sones.dao.Facebook.Sources;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.buisinessLogic.Facebook.UserManager.FacebookUserID;
import com.sones.buisinessLogic.Facebook.UserManager.IFacebookUserID;
import com.sones.businessLogic.Facebook.FeedSources.FacebookSources;
import com.sones.businessLogic.Facebook.FeedSources.IFacebookSources;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class UserFacebookSourcesDao extends AbstractDao implements IUserFacebookSourcesDao
{

	private	PropertiesConfiguration	_config;
	private	Logger	_logger;
	
	public	UserFacebookSourcesDao()
	{
		super();
		_logger	=	Logger.getLogger( UserFacebookSourcesDao.class );
		
		String	file	=	"config/SqlScripts/UserFacebookSourcesSqlScripts/user_facebook_sources_sql_scripts.properties";
		try 
		{
			_config	=	new	PropertiesConfiguration(file);
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}
	
	@Override
	public IFacebookSources GetApplicationUserSources(IApplicationUserID userID) 
	{
		IFacebookSources	sources	=	new	FacebookSources();
		if( null != userID)
		{
			try
			{
				startOperation();
				Query	query	=	session.createSQLQuery( _config.getString( "User.Defined.Sources" ) );
				query.setParameter( "userID", userID.GetValue() );
				List	sourceObjects	=	query.list();
				tx.commit();
				sources	=	GetSources( sourceObjects );
				
			}
			catch (HibernateException e) 
			{
				tx.rollback();
				_logger.error( e.getMessage() );
				_logger.error( e.getCause() );
			}
			finally
			{
				HibernateUtil.close( session );
			}
		}
		else
		{
			_logger.error( " You must specify a real user. User is null." );
		}
		return	sources;
	}
	
	/**
	 * Generates sources from object list which is a result of database query execution
	 * @param sourceObjects
	 * @return {@link IFacebookSources}
	 */
	private	IFacebookSources	GetSources( final List sourceObjects )
	{
		IFacebookSources	sources	=	new	FacebookSources();
		if( null != sourceObjects )
		{
			Iterator	source	=	sourceObjects.iterator();
			for( ; source.hasNext(); )
			{
				IFacebookUserID	userID	=	new	FacebookUserID();
				( ( FacebookUserID )userID).SetUserID( source.next().toString() );
				sources.AddSource( userID );
			}
		}
		if( true == sources.IsEmpty() )
		{
			_logger.warn( " Application user has not defined sources or Application user does not exist." );
		}
		return	sources;
	}

}
