package com.sones.facebook.saver.source;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.source.User;

public class UserSaver implements IUserSaver
{
	private	final	Logger _LOGGER;
	private	IUserDao userDao;
	
	public UserSaver()
	{
		_LOGGER	=	Logger.getLogger(UserSaver.class);
		_LOGGER.warn("Initializing...");
	}
	
	@Override
	public void Save(User user) 
	{
		if( user == null )
		{
			_LOGGER.error("User can't be null!");
			throw new NullPointerException("User can't be null!");
		}
		try
		{
			User dbUser = userDao.GetById(user.getId());
			if( dbUser == null )
			{
				userDao.Save(user);
			}
		}
		catch (DataAccessException e)
		{
			_LOGGER.error("Unable to save user id: "+user.getId());
		}
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * @return the userDao
	 */
	public IUserDao getUserDao() {
		return userDao;
	}

}
