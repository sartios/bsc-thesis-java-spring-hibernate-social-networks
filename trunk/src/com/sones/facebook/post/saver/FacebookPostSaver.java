package com.sones.facebook.post.saver;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.dao.DataAccessException;

import com.sones.dao.exception.DataAccessLayerException;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.dao.source.IUserDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.model.source.User;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;

public class FacebookPostSaver
{
	private	IUserDao userDao;
	private	ISourceFacebookPostDao sourceFacebookPostDao;
	private	ISourceDao sourceDao;
	private	ISourceTypeDao sourceTypeDao;
	private	ICommentDao commentDao;
	private	IFacebookPostDao facebookPostDao;
	private	DozerBeanMapper mapper;
	private	final Logger _LOGGER;
	
	public FacebookPostSaver()
	{
		_LOGGER = Logger.getLogger(FacebookPostSaver.class);
	}
	
	public boolean SavePost(WallSourceFacebookPostCreateDto post)// throws Exception 
	{
		SourceFacebookPost sourceFacebookPost = mapper.map(post, SourceFacebookPost.class);
		
		if(post == null)
		{
			throw new IllegalArgumentException("Post can't be null!");
		}
		
		if( sourceFacebookPost == null )
		{
			throw new NullPointerException("Source facebook post can't be null!");
		}
		
		User user = sourceFacebookPost.getId().getPost().getUser();
		if(user == null)
		{
			throw new NullPointerException("User can't be null.");
		}
		
		FacebookPost facebookPost = sourceFacebookPost.getId().getPost();
		if(facebookPost == null)
		{
			throw new NullPointerException("Facebook post can't be null.");
		}
		
		Iterable<Comment> comments = facebookPost.getComments();
		Source source = sourceFacebookPost.getId().getSource();
		SourceType tmpSourceType = source.getType();
		
		SourceType sourceType;
		try
		{
			sourceType = sourceTypeDao.GetByType(tmpSourceType);
		}
		catch (NullPointerException e)
		{
			throw new IllegalArgumentException("The type "+tmpSourceType.getType()+" doesn't exist.");
		}
		source.setType(sourceType);
		
		try
		{
			userDao.SaveOrUpdate(user);
		}
		catch (DataAccessException e)
		{
			_LOGGER.error("Unable to save or update the user id: "+user.getId());
		}
		
		try
		{
			facebookPostDao.SaveOrUpdate(facebookPost);
		}
		catch (DataAccessException e)	
		{
			_LOGGER.error("Unable to save or update the facebook post id: "+facebookPost.getId());
		}
		
		try
		{
			sourceDao.SaveOrUpdate(source);
		}
		catch (DataAccessException e) 
		{
			_LOGGER.error("Unable to save or update the facebook post id: "+source.getId());
		}
		
		try
		{
			sourceFacebookPostDao.SaveOrUpdate(sourceFacebookPost);
		}
		catch (DataAccessException e)
		{
			throw new DataAccessLayerException("Source facebook post from user id: " +sourceFacebookPost.getId().getSource().getId()
					+" and post id: "+sourceFacebookPost.getId().getPost().getId()
					+" can't be saved!");
		}
		return	true;
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

	/**
	 * @param sourceDao the sourceDao to set
	 */
	public void setSourceDao(ISourceDao sourceDao) {
		this.sourceDao = sourceDao;
	}

	/**
	 * @return the sourceDao
	 */
	public ISourceDao getSourceDao() {
		return sourceDao;
	}

	/**
	 * @param sourceTypeDao the sourceTypeDao to set
	 */
	public void setSourceTypeDao(ISourceTypeDao sourceTypeDao) {
		this.sourceTypeDao = sourceTypeDao;
	}

	/**
	 * @return the sourceTypeDao
	 */
	public ISourceTypeDao getSourceTypeDao() {
		return sourceTypeDao;
	}

	/**
	 * @param commentDao the commentDao to set
	 */
	public void setCommentDao(ICommentDao commentDao) {
		this.commentDao = commentDao;
	}

	/**
	 * @return the commentDao
	 */
	public ICommentDao getCommentDao() {
		return commentDao;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * @param facebookPostDao the facebookPostDao to set
	 */
	public void setFacebookPostDao(IFacebookPostDao facebookPostDao) {
		this.facebookPostDao = facebookPostDao;
	}

	/**
	 * @return the facebookPostDao
	 */
	public IFacebookPostDao getFacebookPostDao() {
		return facebookPostDao;
	}
	
	
}
