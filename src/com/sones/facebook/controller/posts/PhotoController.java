package com.sones.facebook.controller.posts;

import com.sones.facebook.post.logic.IFacebookPostViewService;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;

public class PhotoController 
{
	private IFacebookPostViewService service;
	private PhotoViewDto postDto;
	
	public PhotoController( IFacebookPostViewService service )
	{
		this.service = service;
	}
	
	public void findPost(String id)
	{
		postDto = service.getPhoto(id);
	}
	
	public PhotoViewDto getStatusMessage()
	{
		return postDto;
	}
}
