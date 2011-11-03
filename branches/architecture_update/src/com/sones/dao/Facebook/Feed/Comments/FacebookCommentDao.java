package com.sones.dao.Facebook.Feed.Comments;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.dao.AbstractDao;

public class FacebookCommentDao	extends	AbstractDao	implements	IFacebookCommentDao
{
	private	Logger	_logger;
	private	PropertiesConfiguration _config;
	
	public	FacebookCommentDao()
	{
		super();
		_logger	=	Logger.getLogger( FacebookCommentDao.class );
		try 
		{
			_config	=	new	PropertiesConfiguration( "config/SqlScripts/FacebookCommentsSqlScripts/facebook_comments_sql_scripts.properties" );
		} 
		catch (ConfigurationException e) 
		{
			_logger.error( e.getMessage() );
		}

	}
	
	@Override
	public boolean SaveComment( final IFacebookComment comment ) {
		boolean commentIsSaved	=	super.saveOrUpdate( comment );
		if( false == commentIsSaved)
		{
			_logger.error( "Comment can't be saved" );
		}
		return	commentIsSaved;
	}

	@Override
	public boolean SaveComments( final IFacebookComments comments) {
		
		boolean	areSaved	=	true;
		try
		{
			Iterator< IFacebookComment >	comment	=	comments.GetIterator();
			for( ; comment.hasNext(); )
			{
				SaveComment( comment.next() );
			}
		}
		catch (HibernateException e) 
		{
			areSaved	=	false;
		}
		return areSaved;
	}

	@Override
	public IFacebookComments GetComments(String feedID)
	{
		startOperation();
		IFacebookComments	comments	=	null;
		try
		{
			Query	query	=	session.createSQLQuery( _config.getString( "comments.for.specific.feeds" ) );
			query.setParameter( "feedID", feedID );
			List	objComments	=	query.list();
			tx.commit();
			comments	=	GetComments( objComments );
			
		}
		catch (HibernateException e) 
		{
			_logger.error( e.getMessage() );
		}
		finally
		{
			HibernateUtil.close( session );
		}
		return	comments;
	}
	
	@Override
	public IFacebookComment GetComment(String commentID) 
	{
		return	(IFacebookComment) super.find( FacebookComment.class , commentID );
	}
	
	private	IFacebookComments	GetComments( final List objComments )
	{
		IFacebookComments	comments	=	new	FacebookComments();
		if( false == objComments.isEmpty() )
		{
			Iterator< Object[] >	iterator	=	objComments.iterator();
			for( ; iterator.hasNext(); )
			{
				Object[]	object	=	iterator.next();
				IFacebookComment	comment	=	new FacebookComment(); 
				( (FacebookComment)comment ).SetCommentId( object[0].toString() );
				( (FacebookComment)comment ).SetMessage( object[1].toString() );
				( (FacebookComment)comment ).SetFeedID( object[2].toString() );
				( (FacebookComment)comment ).SetCreatorId( object[3].toString() );
				comments.AddComment( comment );
			}
		}
		return	comments;
	}


}
