package com.sones.facebook.saver;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.User;
import com.sones.facebook.saver.source.ISourceFacebookPostSaver;
import com.sones.facebook.saver.source.ISourceSaver;
import com.sones.facebook.saver.source.IUserSaver;
import com.sones.facebook.saver.wall.feed.IFacebookWallPostSaver;
import com.sones.sharedDto.exceptions.MapErrorException;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostIdDto;

public class FacebookPostSaverLogic	implements	IFacebookPostSaverLogic
{
	/**
	 * Logger of the class.
	 */
	private	final	Logger _LOGGER = Logger.getLogger(FacebookPostSaverLogic.class);
	
	/**
	 * Saves the posts
	 */
	private	IFacebookWallPostSaver wallPostSaver;
	
	/**
	 * Saves the post associated with the source.
	 */
	private	ISourceFacebookPostSaver sourceFacebookPostSaver;
	
	/**
	 * Saves the user who created the post.
	 */
	private	IUserSaver userSaver;
	
	/**
	 * Saves the source
	 */
	private	ISourceSaver sourceSaver;
	
	/**
	 * Mapps model objects to dto objects.
	 */
	private	DozerBeanMapper mapper;
	
	/**
	 * Initializes the object.
	 */
	public FacebookPostSaverLogic()
	{
		_LOGGER.warn("Initializing post saver logic.");
	}

	@Override
	public boolean saveWallPosts(Iterable<WallSourceFacebookPostCreateDto> posts)
	{
		if( posts == null )
		{
			_LOGGER.error("Posts can't be null!");
			throw new IllegalArgumentException("Posts can't be null");
		}
		if(posts.iterator().hasNext() == false )
		{
			_LOGGER.debug("Posts are empty.");
			return false;
		}
		Iterator<WallSourceFacebookPostCreateDto> iterator = posts.iterator();
		WallSourceFacebookPostCreateDto sourcePostDto = iterator.next();
		while(sourcePostDto != null)
		{
			SourceFacebookPost sourcePost =	new	SourceFacebookPost();
			mapper.map(sourcePostDto, sourcePost);
			
			SourceFacebookPostId	id	=	sourcePost.getId();
			verifySourcePostId( id );
			
			FacebookPost	post	=	id.getPost();
			verifyFacebookPost( post );
			
			User	user	=	post.getUser();
			verifyUser( user );
			userSaver.Save( user );
			
			Source source = id.getSource();
			verifySource( source );
			sourceSaver.Save( source );
			
			WallSourceFacebookPostIdDto	idDto	=	sourcePostDto.getId();
			wallPostSaver.SavePost( idDto.getPost() );
			sourceFacebookPostSaver.Save( sourcePost );
			if(iterator.hasNext())
			{
				sourcePostDto = iterator.next();
			}
			else
			{
				sourcePostDto = null;
			}
		}
		return true;
	}
	
	private void verifySource(Source source) 
	{
		if( source == null )
		{
			_LOGGER.error( "Mapped source is null. Error during mapping." );
			throw	new	MapErrorException( "Mapped source is null. Error during mapping." );
		}		
	}

	private void verifyUser(User user) 
	{
		if( user == null )
		{
			_LOGGER.error( "Mapped post's user is null. Error during mapping." );
			throw	new	MapErrorException( "Mapped post's user is null. Error during mapping." );
		}
		
	}

	private	void	verifySourcePostId( SourceFacebookPostId	id )
	{
		if( id == null )
		{
			_LOGGER.error( "Mapped source's post id is null. Error during mapping." );
			throw	new	MapErrorException( "Mapped source's post id is null. Error during mapping." );
		}
	}
	
	private	void	verifyFacebookPost( FacebookPost post )
	{
		if( post == null )
		{
			_LOGGER.error( "Mapped source's post is null. Error during mapping." );
			throw	new	MapErrorException( "Mapped source's post is null. Error during mapping." );
		}
	}

	/**
	 * @return the wallPostSaver
	 */
	public IFacebookWallPostSaver getWallPostSaver() {
		return wallPostSaver;
	}

	/**
	 * @param wallPostSaver the wallPostSaver to set
	 */
	public void setWallPostSaver(IFacebookWallPostSaver wallPostSaver) {
		this.wallPostSaver = wallPostSaver;
	}

	/**
	 * @return the sourceFacebookPostSaver
	 */
	public ISourceFacebookPostSaver getSourceFacebookPostSaver() {
		return sourceFacebookPostSaver;
	}

	/**
	 * @param sourceFacebookPostSaver the sourceFacebookPostSaver to set
	 */
	public void setSourceFacebookPostSaver(
			ISourceFacebookPostSaver sourceFacebookPostSaver) {
		this.sourceFacebookPostSaver = sourceFacebookPostSaver;
	}

	/**
	 * @return the userSaver
	 */
	public IUserSaver getUserSaver() {
		return userSaver;
	}

	/**
	 * @param userSaver the userSaver to set
	 */
	public void setUserSaver(IUserSaver userSaver) {
		this.userSaver = userSaver;
	}

	/**
	 * @return the sourceSaver
	 */
	public ISourceSaver getSourceSaver() {
		return sourceSaver;
	}

	/**
	 * @param sourceSaver the sourceSaver to set
	 */
	public void setSourceSaver(ISourceSaver sourceSaver) {
		this.sourceSaver = sourceSaver;
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}
	
}
