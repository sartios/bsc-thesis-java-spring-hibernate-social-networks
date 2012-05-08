package com.sones.facebook.saver.wall.feed;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.comment.Comment;

public class LinkSaver implements ILinkSaver
{
	private	final Logger _LOGGER;
	private	ILinkDao linkDao;
	private	ICommentSaver	commentSaver;
	
	public LinkSaver()
	{
		_LOGGER = Logger.getLogger(LinkSaver.class);
	}
	
	@Override
	public void Save(Link link) throws DataAccessException
	{
		if( link == null )
		{
			_LOGGER.error("Link object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			if( linkExists( link ) == false )
			{
				if( hasComments( link ) )
				{
					Set< Comment >	comments	=	link.getComments();
					link.setComments(null);
					linkDao.Save( link );
					commentSaver.Save(comments);
				}
				else
				{
					linkDao.Save( link );
				}
			}
		}
		catch (	DataAccessException e)
		{
			_LOGGER.error("Unable to save link object because "
					+e.getMessage().toString());
			throw e;
		}
		
	}
	/**
	 * @param linkDao the linkDao to set
	 */
	public void setLinkDao(ILinkDao linkDao) {
		this.linkDao = linkDao;
	}
	/**
	 * @return the linkDao
	 */
	public ILinkDao getLinkDao() {
		return linkDao;
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
	
	private	boolean	linkExists( Link link )
	{
		String linkId = link.getId();
		Link dbLink = linkDao.GetById(linkId);
		if( dbLink != null )
		{
			_LOGGER.warn("Link already exists.");
			return	true;
		}
		return	false;
	}
	
	private	boolean	hasComments( Link link )
	{
		Set< Comment >	comments	=	link.getComments();
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
