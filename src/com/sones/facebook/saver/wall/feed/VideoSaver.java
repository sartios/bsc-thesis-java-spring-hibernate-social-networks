package com.sones.facebook.saver.wall.feed;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.model.feed.Video;
import com.sones.facebook.model.feed.comment.Comment;

public class VideoSaver implements IVideoSaver
{
	private	final Logger _LOGGER;
	private	IVideoDao videoDao;
	private	ICommentSaver	commentSaver;
	
	public VideoSaver()
	{
		_LOGGER = Logger.getLogger(VideoSaver.class);
	}
	
	@Override
	public void Save(Video video) throws DataAccessException 
	{
		if( video == null )
		{
			_LOGGER.error("Video object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			if( videoExists(video) == false )
			{
				boolean	hadComments	=	hasComments( video );
				if( hadComments == true )
				{
					Set< Comment >	comments	=	video.getComments();
					video.setComments(null);
					videoDao.Save(video);
					commentSaver.Save(comments);
				}
				else if(  hadComments == false )
				{
					videoDao.Save( video );
				}
			}
		}
		catch (	DataAccessException e)
		{
			_LOGGER.error("Unable to save video object because");
			e.printStackTrace();
			
			throw e;
		}	
	}

	/**
	 * @param videoDao the videoDao to set
	 */
	public void setVideoDao(IVideoDao videoDao) {
		this.videoDao = videoDao;
	}

	/**
	 * @return the videoDao
	 */
	public IVideoDao getVideoDao() {
		return videoDao;
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
	
	private	boolean	videoExists( Video video )
	{
		String videoID = video.getId();
		Video dbVideo = videoDao.GetById(videoID);
		if(dbVideo != null)
		{
			_LOGGER.warn("Video already exists.");
			return	true;
		}
		return	false;
	}
	
	private	boolean	hasComments( Video video )
	{
		Set< Comment >	comments	=	video.getComments();
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
