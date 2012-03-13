package com.sones.facebook.post.saver.wall.feed;

import org.springframework.dao.DataAccessException;

import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.model.feed.FacebookPost;

public class FacebookPostSaver implements IFacebookPostSaver
{

	private	IFacebookPostDao postDao;
	
	@Override
	public void Save(FacebookPost post)
	{
		if( post == null )
		{
			throw new IllegalArgumentException("Post can't be null");
		}
		
		try
		{
			FacebookPost dbPost = postDao.GetById(post.getId());
			if( dbPost == null )
			{
				postDao.Save(post);
			}
		}
		catch(DataAccessException ex)
		{
			
		}
		
	}

	/**
	 * @param postDao the postDao to set
	 */
	public void setPostDao(IFacebookPostDao postDao) {
		this.postDao = postDao;
	}

	/**
	 * @return the postDao
	 */
	public IFacebookPostDao getPostDao() {
		return postDao;
	}

}
