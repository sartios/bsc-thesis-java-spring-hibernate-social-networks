package com.sones.facebook.controller.posts;

import com.sones.facebook.post.logic.IFacebookPostViewService;
import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;

public class StatusMessageController 
{
	private IFacebookPostViewService service;
	private StatusMessageViewDto postDto;
	
	public StatusMessageController( IFacebookPostViewService service )
	{
		this.service = service;
	}
	
	public void findPost(String id)
	{
		postDto = service.getStatusMessage(id);
	}
	
	public StatusMessageViewDto getStatusMessage()
	{
		return postDto;
	}
}
