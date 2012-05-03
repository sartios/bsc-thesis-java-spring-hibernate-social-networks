package com.sones.facebook.dao.hibernate.feed.comment;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.comment.Comment;

public class HibernateCommentDao extends HibernateGenericDao<Comment, String> implements ICommentDao
{
	private final Logger _LOGGER;
	
	public HibernateCommentDao()
	{
		super(Comment.class);
		_LOGGER = Logger.getLogger( HibernateCommentDao.class );
	}

	@Override
	public Iterable<Comment> getByFacebookPost(FacebookPost post) 
	{
		checkNullability(post, "Post can't be null");
		
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Comment.class)
			.add( Restrictions.eq("post", post))
			.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<Comment> results = criteria.list();
		session.close();
		return results;
	}
	
	private void checkNullability(Object object, String message) throws IllegalArgumentException
	{
		if(object == null)
		{
			_LOGGER.error(message);
			throw new IllegalArgumentException(message);
		}
	}
}
