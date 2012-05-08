package com.sones.facebook.controller.posts;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.gui.posts.LinkFrame;
import com.sones.facebook.gui.posts.PhotoFrame;
import com.sones.facebook.gui.posts.StatusMessageFrame;
import com.sones.facebook.gui.posts.VideoFrame;
import com.sones.facebook.post.logic.IFacebookPostViewService;
import com.sones.sharedDto.facebook.view.posts.LinkViewDto;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;
import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public class FacebookPostController 
{
	private final Logger _LOGGER;
	private StatusMessageFrame statusFrame;
	private VideoFrame videoFrame;
	private LinkFrame linkFrame;
	private PhotoFrame photoFrame;
	
	private IFacebookPostViewService service;
	
	public FacebookPostController()
	{
		_LOGGER = Logger.getLogger(FacebookPostController.class);
		statusFrame = new StatusMessageFrame();
		videoFrame = new VideoFrame();
		linkFrame = new LinkFrame();
		photoFrame = new PhotoFrame();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookViewPosts/spring-facebookpostview-logic.xml");
		service = (IFacebookPostViewService) context.getBean( "facebookPostViewService" );
	}
	
	public void showStatusMessage(String id)
	{
		StatusMessageViewDto postDto = service.getStatusMessage(id);
		if(postDto == null )
		{
			_LOGGER.error("Status is null");
		}
		statusFrame.setPost(postDto);
		statusFrame.show(true);
	}
	
	public void showVideo(String id)
	{
		VideoViewDto postDto = service.getVideo(id);
		videoFrame.setPost(postDto);
		videoFrame.show(true);
	}

	public void showLink(String id)
	{
		LinkViewDto postDto = service.getLink(id);
		linkFrame.setPost(postDto);
		linkFrame.show(true);
	}
	
	public void showPhoto(String id)
	{
		PhotoViewDto postDto = service.getPhoto(id);
		photoFrame.setPost(postDto);
		photoFrame.show(true);
	}
}
