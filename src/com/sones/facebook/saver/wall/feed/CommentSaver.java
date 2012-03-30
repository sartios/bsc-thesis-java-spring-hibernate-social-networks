package com.sones.facebook.saver.wall.feed;


import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.User;
import com.sones.facebook.saver.source.IUserSaver;

public class CommentSaver	implements	ICommentSaver
{
	private	final Logger _LOGGER;
	private ICommentDao	commentDao;
	private	IUserSaver	userSaver;
	
	public CommentSaver()
	{
		_LOGGER = Logger.getLogger(CommentSaver.class);
	}
	
	@Override
	public void Save(Iterable<Comment> comments)
	{
		if( comments == null )
		{
			_LOGGER.error("Comments can't be null");
			throw new IllegalArgumentException("Comments can't be null");
		}
		try
		{
			for (Comment comment : comments) 
			{
				Comment dbComment = commentDao.GetById(comment.getId());
				if( dbComment == null )
				{
					User user = comment.getUser();
					_LOGGER.warn("Saving comment user id: "+user.getId());
					userSaver.Save(user);
					_LOGGER.warn("Saving comment");
					commentDao.Save(comment);
				}
			}
		}
		catch (DataAccessException e)
		{
			_LOGGER.error("Unable to save comments");
		}
	}

	/**
	 * @param commentDao the commentDao to set
	 */
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * @return the commentDao
	 */
	public ICommentDao getCommentDao() {
		return commentDao;
	}

	/**
	 * @param userSaver the userSaver to set
	 */
	public void setUserSaver(IUserSaver userSaver) {
		this.userSaver = userSaver;
	}

	/**
	 * @return the userSaver
	 */
	public IUserSaver getUserSaver() {
		return userSaver;
	}
}
