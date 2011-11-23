package com.sones.dao.CapitalWordsSpotter.Facebook;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComment;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.CapitalizedFacebookComments;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComment;
import com.sones.businessLogic.CapitalWordSpotter.CapitalizedFeeds.Searchable.Comments.ICapitalWordsSearchableComments;
import com.sones.dao.AbstractDao;

public class FacebookCommentsCapitalWordSpotterDao	extends	AbstractDao	implements	IFacebookCommentCapitalWordSpotterDao
{
	private	XMLConfiguration	_config;
	
	public	FacebookCommentsCapitalWordSpotterDao()
	{
		super();
		try 
		{
			_config	=	new	XMLConfiguration("config/SqlScripts/FeedsWithCapitalizedWords/Facebook/comment.xml");
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}

	public ICapitalWordsSearchableComments GetCommentsBetweenDates( String startDate, String endDate) 
	{
		ICapitalWordsSearchableComments	comments	=	null;
		startOperation();
		try
		{			
			System.out.println(_config.getString( "queries.get_comments" ));
			Query	query	=	session.createSQLQuery( _config.getString( "queries.get_comments" ) );
			query.setParameter(_config.getString( "parameters.start_date" ), startDate);
			query.setParameter(_config.getString( "parameters.end_date" ), endDate);
			List objects	=	query.list();
			tx.commit();
			session.flush();
			comments	=	GetCommentFeeds(objects);
		}
		catch (HibernateException ex)
		{
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	comments;
	}
	
	private	ICapitalWordsSearchableComments	GetCommentFeeds( List<Object[]> objects )
	{
		ICapitalWordsSearchableComments	comments	=	new	CapitalizedFacebookComments();
		Iterator<Object[]>	iterator	=	objects.iterator();
		while(iterator.hasNext())
		{
			System.out.println("Has next");
			ICapitalWordsSearchableComment	comment	=	new	CapitalizedFacebookComment();
			Object[]	object	=	iterator.next();
			comment.SetFeedID((String)object[ _config.getInt( "index.feedID" ) ]);
			System.out.println("Feed ID: " +(String)object[ _config.getInt( "index.feedID" ) ]);
			comment.SetMessage((String)object[ _config.getInt( "index.commentMessage" ) ]);
			System.out.println("Comment message: " +(String)object[ _config.getInt( "index.commentMessage" ) ]);
			comments.AddComment(comment);
		}
		System.out.println("Has not next");
		return	comments;
	}
}
