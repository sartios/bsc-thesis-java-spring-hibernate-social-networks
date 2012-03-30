package com.sones.facebook.dao.hibernate.feed;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Source;

public class HibernateSourceFacebookPostDao extends HibernateGenericDao<SourceFacebookPost,SourceFacebookPostId> implements ISourceFacebookPostDao
{
	private	final	Logger	_LOGGER;
	
	public HibernateSourceFacebookPostDao()
	{
		super(SourceFacebookPost.class);
		_LOGGER	=	Logger.getLogger( HibernateSourceFacebookPostDao.class );
	}

	@Override
	public Iterable<SourceFacebookPost> getSourceFacebookPostsBySourceAndAfterDate( Source source, Date date )
	{
		if( source == null )
		{
			_LOGGER.error( "Source can't be null!" );
			throw	new	IllegalArgumentException( "Source can't be null!" );
		}
		
		if( date == null )
		{
			_LOGGER.error( "Date can't be null!" );
			throw	new	IllegalArgumentException( "Date can't be null!" );
		}
		
		Session	session	=	getHibernateTemplate().getSessionFactory().openSession();
		Criteria	criteria	=	session.createCriteria( SourceFacebookPost.class )
			.add( Restrictions.eq( "id.source", source ) )
			.createCriteria("download")
			.add( Restrictions.eq( "date", date ) );
		return null;
	}
}
