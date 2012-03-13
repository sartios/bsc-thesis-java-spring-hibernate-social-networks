package com.sones.facebook.post.saver;

import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.post.saver.source.ISourceFacebookPostSaver;
import com.sones.facebook.post.saver.source.ISourceSaver;
import com.sones.facebook.post.saver.source.IUserSaver;
import com.sones.facebook.post.saver.source.SourceFacebookPostSaver;
import com.sones.facebook.post.saver.source.SourceSaver;
import com.sones.facebook.post.saver.source.UserSaver;
import com.sones.facebook.post.saver.wall.feed.FacebookWallPostSaver;
import com.sones.facebook.post.saver.wall.feed.IFacebookWallPostSaver;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;

public class FacebookPostSaverLogic	implements	IFacebookPostSaverLogic
{
	private	final	Logger _LOGGER = Logger.getLogger(FacebookPostSaverLogic.class);
	
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
		
		_LOGGER.info("Initialize context");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-facebook-downloader.xml");

		IUserSaver userSaver = (UserSaver)context.getBean("userSaver");
		ISourceSaver sourceSaver = (SourceSaver)context.getBean("sourceSaver");
		ISourceFacebookPostSaver sourceFacebookPostSaver = (SourceFacebookPostSaver)context.getBean("sourceFacebookPostSaver");
		IFacebookWallPostSaver wallPostSaver = new  FacebookWallPostSaver();
		Iterator<WallSourceFacebookPostCreateDto> iterator = posts.iterator();
		DozerBeanMapper mapper = (DozerBeanMapper) context.getBean("dozerMapper");
		WallSourceFacebookPostCreateDto postDto = iterator.next();
		while(postDto != null)
		{
			System.out.println("Saving posts..");
			SourceFacebookPost post = mapper.map(postDto, SourceFacebookPost.class);
			userSaver.Save(post.getId().getPost().getUser());
			Source source = post.getId().getSource();
			sourceSaver.Save(source);
			wallPostSaver.SavePost(postDto.getId().getPost());
			sourceFacebookPostSaver.Save(post);
			if(iterator.hasNext())
			{
				postDto = iterator.next();
			}
			else
			{
				postDto = null;
			}
		}
		return true;
	}
	
}
