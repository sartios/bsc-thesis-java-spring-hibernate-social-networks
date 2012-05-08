package com.sones.facebook.saver.wall.feed;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;

public class CheckinSaver implements ICheckinSaver
{
	private	final Logger _LOGGER;
	private	ICheckinDao checkinDao;
	private IPlaceDao placeDao;
	private	ICommentSaver	commentSaver;
	
	public CheckinSaver(ICheckinDao checkinDao, ICommentSaver commentSaver, IPlaceDao placeDao)
	{
		_LOGGER = Logger.getLogger(CheckinSaver.class);
		this.checkinDao = checkinDao;
		this.commentSaver = commentSaver;
		this.placeDao = placeDao;
	}
	
	@Override
	public void Save(Checkin checkin) throws DataAccessException
	{
		if( checkin == null )
		{
			_LOGGER.error("Link object can't be null!");
			throw new NullPointerException("Link object can't be null!");
		}
		try
		{
			if( checkinExists( checkin ) == false )
			{
				Place place = checkin.getPlace();
				if(placeDao.GetById(place.getId()) == null)
				{
					placeDao.Save(place);
				}
				if( hasComments( checkin ) )
				{
					Set< Comment >	comments	=	checkin.getComments();
					checkin.setComments(null);
					checkinDao.Save( checkin );
					commentSaver.Save( comments );
				}
				else
				{
					checkinDao.Save( checkin );
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
	
	private	boolean	checkinExists( Checkin checkin )
	{
		String checkinID = checkin.getId();
		Checkin dbcheckin	=	checkinDao.GetById( checkinID );
		if( dbcheckin != null )
		{
			_LOGGER.warn("Checkin already exists.");
			return	true;
		}
		return	false;
	}
	
	private	boolean	hasComments( Checkin checkin )
	{
		Set<Comment> comments = checkin.getComments();
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
