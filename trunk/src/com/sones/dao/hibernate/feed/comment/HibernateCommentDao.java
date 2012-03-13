package com.sones.facebook.dao.hibernate.feed.comment;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.model.feed.comment.Comment;

public class HibernateCommentDao extends HibernateGenericDao<Comment, String> implements ICommentDao
{
	public HibernateCommentDao()
	{
		super(Comment.class);
	}
}
