package com.sones.dao.User.Facebook.Feed.Comments;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.ConstraintViolationException;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.persistance.HibernateUtil;
import com.sones.dao.AbstractDao;

public class UseFacebookCommentsDao	extends	AbstractDao implements	IUserFacebookCommentsDao
{
	private	PropertiesConfiguration	_config;
	private	Logger	_logger;
	
	public	UseFacebookCommentsDao()
	{
		super();
		super.startOperation();
		InitializeConfiguration();
		_logger	=	Logger.getLogger( UseFacebookCommentsDao.class );	
	}
	
	@Override
	public boolean SaveUserFacebookComments(String userID, List<String> commentIDs)
	{
		boolean	actionCompletedForAllComments	=	true;
		for( int index = 0; index < commentIDs.size(); index++ )
		{
			boolean isSaved = SaveUserComment(userID, commentIDs.get( index ) );
			if( false == isSaved)
			{
				actionCompletedForAllComments	=	false;
			}
		}	
		return	actionCompletedForAllComments;
	}
	
	public	String	GetInsertSqlString()
	{
		return	_config.getString( "Insert.Comments" );
	}

	public	void	InitializeConfiguration()
	{
		try 
		{
			String	fileName	=	"config/SqlScripts/UserFacebookCommentsSqlScripts/user_facebook_comments_sql_queries.properties";
			_config	=	new	PropertiesConfiguration(fileName);
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( "user_facebook_comments_sql_queries.properties file doesn't exist" );
		}

	}

	@Override
	public boolean SaveUserComment(String userID, String commentID) {
		startOperation();
		boolean	actionCompletedForAllComments	=	true;
		try
		{
			Query query=session.createSQLQuery( GetInsertSqlString() );
			query.setParameter("applicationUserID", userID);
			query.setParameter("commentID", commentID.toString() );
			query.executeUpdate();
			tx.commit();
		}
		catch (ConstraintViolationException constraintViolEx) 
		{
			_logger.error(constraintViolEx.getSQL());
			_logger.error(constraintViolEx.getCause());
			tx.rollback();
		}	
		finally
		{
			HibernateUtil.close(session);
		}

		return	actionCompletedForAllComments;
	}
}
