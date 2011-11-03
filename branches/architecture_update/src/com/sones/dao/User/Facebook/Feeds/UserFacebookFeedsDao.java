package com.sones.dao.User.Facebook.Feeds;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.dao.AbstractDao;

public class UserFacebookFeedsDao	extends AbstractDao	implements	IUserFacebookFeedsDao
{
	private	PropertiesConfiguration	_config;
	private	Logger	_logger;
	
	public	UserFacebookFeedsDao()
	{
		super();
		_logger	=	Logger.getLogger( UserFacebookFeedsDao.class );
		try 
		{
			_config	=	new	PropertiesConfiguration( "config/SqlScripts/UserFacebookFeedsSqlScripts/user_facebook_feeds_sql_scripts.properties" );
		}
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}
	}

	@Override
	public void SaveUserFacebookFeeds(String userID, List<String> feedIDs) {
		startOperation();
		try
		{
			for( int index = 0; index < feedIDs.size(); index++ )
			{
				String	sqlString	=	"INSERT INTO application_users_has_facebook_feeds "
										+" ( APPLICATION_USERS_PK_USER_ID, FACEBOOK_FEEDS_PK_FEED_ID ) "
										+" VALUES ( '"+ userID +"', '"+ feedIDs.get( index ) +"') ;";
					Query query=session.createSQLQuery( sqlString );
					query.executeUpdate();
			}
			tx.commit();
		}
		catch (HibernateException ex) 
		{
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
	}

	@Override
	public List<String> GetUserFacebookFeeds( final String userID ) 
	{
		List< String >	feeds	=	new	ArrayList< String >();
		if( null != userID )
		{
			startOperation();
			try
			{
				Query	query	=	session.createSQLQuery( _config.getString( "select.user.feeds" ) );
				query.setParameter( "applicationUserID", userID );
				List	list	=	query.list();
				feeds	=	GetFeedsFromDB( list );
				session.flush();
				tx.commit();
			}
			catch (HibernateException e) 
			{
				e.printStackTrace();
				_logger.error( e.getStackTrace() );
				_logger.error( e.getMessage() );
				tx.rollback();
			}
			finally
			{
				HibernateUtil.close( session );
			}
		}
		return feeds;
	}

	private List<String> GetFeedsFromDB(List list) 
	{
		List< String >	feeds	=	new	ArrayList< String >();
		for( Iterator< Object > iterator = list.iterator(); iterator.hasNext(); )
		{
			feeds.add( iterator.next().toString() );
		}
		return	feeds;
	}
}
