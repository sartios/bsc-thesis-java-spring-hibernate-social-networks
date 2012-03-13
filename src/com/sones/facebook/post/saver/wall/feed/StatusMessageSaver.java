package com.sones.facebook.post.saver.wall.feed;

import java.sql.SQLException;
import java.util.Set;

import javax.swing.plaf.SliderUI;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.comment.Comment;

public class StatusMessageSaver implements IStatusMessageSaver
{
	private IStatusMessageDao statusDao;
	private	final Logger _LOGGER;
	private	ICommentSaver	commentSaver;
	
	public StatusMessageSaver()
	{
		_LOGGER = Logger.getLogger(StatusMessageSaver.class);
	}
	
	@Override
	public void Save(StatusMessage statusMessage) 
	{
		if( statusMessage == null )
		{
			_LOGGER.error("Status message can't be null!");
			throw new NullPointerException("Status message can't be null!");
		}
		try
		{
			StatusMessage dbStatusMessage = statusDao.GetById(statusMessage.getId());
			if( dbStatusMessage == null )
			{
				if( statusMessage.getComments().size() == 0 )
				{
					statusDao.Save(statusMessage);
				}
				else
				{
					Set<Comment> comments = statusMessage.getComments();
					statusMessage.setComments(null);
					statusDao.Save(statusMessage);
					if(comments == null)
					{
						_LOGGER.error("Comments are empty");
					}
					commentSaver.Save(comments);
				}
			}
		}
		catch ( DataAccessException e)
		{
			_LOGGER.error("Unable to save status message because "
					+e.getStackTrace().toString());
			//throw e;
		}
	}

	/**
	 * @param statusDao the statusDao to set
	 */
	public void setStatusDao(IStatusMessageDao statusDao) {
		this.statusDao = statusDao;
	}

	/**
	 * @return the statusDao
	 */
	public IStatusMessageDao getStatusDao() {
		return statusDao;
	}

	/**
	 * @param commentSaver the commentSaver to set
	 */
	public void setCommentSaver(ICommentSaver commentSaver) {
		this.commentSaver = commentSaver;
	}

	/**
	 * @return the commentSaver
	 */
	public ICommentSaver getCommentSaver() {
		return commentSaver;
	}
}
