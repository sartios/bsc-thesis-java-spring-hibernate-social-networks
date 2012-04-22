package com.sones.facebook.dao.hibernate.feed;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.annotations.FetchMode;
import org.hibernate.cfg.CreateKeySecondPass;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;

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
		
		Session	session	=	GetSession();
		Criteria	criteria	=	session.createCriteria( SourceFacebookPost.class )
			.add( Restrictions.eq( "id.source", source ) )
			.createCriteria("download")
			.add( Restrictions.eq( "date", date ) );
		return null;
	}
	
	@Override
	public Iterable<SourceFacebookPost> GetBySourceType(SourceType type) 
	{
		checkNullability(type, "Source type can't be null");
		if(type.getId() == null)
		{
			_LOGGER.error( "Source type id can't be null" );
			throw new IllegalArgumentException( "Source type id can't be null" );
		}
		Session session = GetSession();
		
		Criteria sourceCr = session.createCriteria( Source.class )
			.add( Restrictions.eq("type", type) );
		List<Source> sources = sourceCr.list();
		
		List<SourceFacebookPost> posts = new ArrayList<SourceFacebookPost>();
		Criteria criteria = session.createCriteria( SourceFacebookPost.class )
			.add( Restrictions.in("id.source", sources) );
		List<SourceFacebookPost> results = criteria.list();
		posts.addAll(results);
		session.close();
		return posts;
	}

	@Override
	public Iterable<SourceFacebookPost> GetBySources(Collection<Source> sources) 
	{
		checkNullability(sources, "Sources can't be null");
		Session session = GetSession();
		List<SourceFacebookPost> posts = new ArrayList<SourceFacebookPost>();
		Criteria criteria = session.createCriteria( SourceFacebookPost.class )
			.add( Restrictions.in("id.source", sources) );
		List<SourceFacebookPost> results = criteria.list();
		posts.addAll(results);
		session.close();
		return posts;
	}

	@Override
	public Iterable<SourceFacebookPost> GetInSourcesAndInPosts(Collection<Source> sources, Collection<FacebookPost> posts) 
	{
		checkNullability(sources, "Sources can't be null");
		checkNullability(posts, "Posts can't be null");
		
		Session session = GetSession();
		Criteria criteria = session.createCriteria( SourceFacebookPost.class )
			.add( Restrictions.in("id.source", sources) )
			.add( Restrictions.in("id.post", posts));
		List results = (List<SourceFacebookPost>) criteria.list();
		session.close();
		
		return results;
	}
	
	private void checkNullability( Object object, String message )
	{
		if( object == null )
		{
			_LOGGER.error( message );
			throw new IllegalArgumentException();
		}
	}
	
	private Session GetSession()
	{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		return session;
	}
}
