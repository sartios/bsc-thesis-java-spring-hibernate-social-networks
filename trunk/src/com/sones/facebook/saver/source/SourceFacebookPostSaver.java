package com.sones.facebook.saver.source;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.dao.exception.DataAccessLayerException;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.model.feed.SourceFacebookPost;

public class SourceFacebookPostSaver implements ISourceFacebookPostSaver
{
	private	final Logger _LOGGER;
	private	ISourceFacebookPostDao sourceFacebookPostDao;
	
	public SourceFacebookPostSaver()
	{
		_LOGGER = Logger.getLogger(SourceFacebookPostSaver.class);
		_LOGGER.warn("Initializing...");
	}

	@Override
	public void Save(SourceFacebookPost post) 
	{
		if( post == null )
		{
			_LOGGER.error("Source's post can't be null!");
			throw new NullPointerException("Source's post can't be null!");
		}
		try
		{
			SourceFacebookPost	dbPost	=	sourceFacebookPostDao.GetById( post.getId() );
			if( dbPost == null )
			{
				sourceFacebookPostDao.Save( post );
			}
		}
		catch (DataAccessException e)
		{
			throw new DataAccessLayerException("Source facebook post from user id: " +post.getId().getSource().getId()
					+" and post id: "+post.getId().getPost().getId()
					+" can't be saved!");
		}
	}

	/**
	 * @param sourceFacebookPostDao the sourceFacebookPostDao to set
	 */
	public void setSourceFacebookPostDao(ISourceFacebookPostDao sourceFacebookPostDao) {
		this.sourceFacebookPostDao = sourceFacebookPostDao;
	}

	/**
	 * @return the sourceFacebookPostDao
	 */
	public ISourceFacebookPostDao getSourceFacebookPostDao() {
		return sourceFacebookPostDao;
	}

}
