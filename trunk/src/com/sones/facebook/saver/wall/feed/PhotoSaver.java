package com.sones.facebook.saver.wall.feed;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.comment.Comment;

public class PhotoSaver implements IPhotoSaver
{
	private	final Logger _LOGGER;
	private	IPhotoDao photoDao;
	private	ICommentSaver	commentSaver;
	
	public PhotoSaver()
	{
		_LOGGER = Logger.getLogger(PhotoSaver.class);
	}
	
	@Override
	public void Save(Photo photo) 
	{
		if( photo == null )
		{
			_LOGGER.error("Link object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			if( isAnExistingPhoto( photo ) == false )
			{
				if( hasComments( photo ) )
				{
					Set< Comment >	comments	=	photo.getComments();
					photo.setComments(null);
					photoDao.Save( photo );
					commentSaver.Save( comments );
				}
				else
				{
					photoDao.Save( photo );
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
	 * @param photoDao the photoDao to set
	 */
	public void setPhotoDao(IPhotoDao photoDao) {
		this.photoDao = photoDao;
	}

	/**
	 * @return the photoDao
	 */
	public IPhotoDao getPhotoDao() {
		return photoDao;
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
	
	private	boolean	isAnExistingPhoto( Photo photo )
	{
		Photo	dbPhoto	=	photoDao.GetById( photo.getId() );
		if( dbPhoto == null )
		{
			return	false;
		}
		return	true;
	}
	
	private	boolean	hasComments( Photo photo )
	{
		Set< Comment >	comments	=	photo.getComments();
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
