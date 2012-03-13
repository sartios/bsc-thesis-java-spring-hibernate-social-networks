package com.sones.facebook.post.saver.wall.feed;

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
	public void Save(Link link) 
	{
		if( link == null )
		{
			_LOGGER.error("Link object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			Link dbLink = linkDao.GetById(link.getId());
			if( dbLink == null )
			{
				if( link.getComments().size() == 0 )
				{
					linkDao.Save(link);
				}
				else
				{
					Set<Comment> comments = link.getComments();
					link.setComments(null);
					linkDao.Save(link);
					if(comments == null)
					{
						_LOGGER.error("Comments are empty");
					}
					commentSaver.Save(comments);
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
	
}
