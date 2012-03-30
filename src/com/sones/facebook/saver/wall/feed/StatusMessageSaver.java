package com.sones.facebook.saver.wall.feed;

import java.util.Set;


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
			if( isAnExistingStatusMessage( statusMessage ) == false )
			{
				if( hasComments( statusMessage ) )
				{
					Set< Comment >	comments	=	statusMessage.getComments();
					statusMessage.setComments(null);
					statusDao.Save( statusMessage );
					commentSaver.Save(comments);
				}
				else
				{
					statusDao.Save( statusMessage );
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
	
	private	boolean	isAnExistingStatusMessage( StatusMessage statusMessage )
	{
		StatusMessage	dbStatusMessage	=	statusDao.GetById( statusMessage.getId() );
		if( dbStatusMessage == null )
		{
			return	false;
		}
		return	true;
	}
	
	private	boolean	hasComments( StatusMessage statusMessage )
	{
		Set< Comment >	comments	=	statusMessage.getComments();
		if( comments == null )
		{
			return	false;
		}
		if( comments.size() == 0 )
		{
			return	false;
		}
		return	true;
	}
}
