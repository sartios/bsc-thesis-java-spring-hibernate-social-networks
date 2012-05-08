package com.sones.facebook.controller.posts;

import com.sones.facebook.post.logic.IFacebookPostViewService;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public class VideoController 
{
	private IFacebookPostViewService service;
	private VideoViewDto postDto;
	
	public VideoController( IFacebookPostViewService service )
	{
		this.service = service;
	}
	
	public void findPost(String id)
	{
		postDto = service.getVideo(id);
	}
	
	public VideoViewDto getStatusMessage()
	{
		return postDto;
	}
}
