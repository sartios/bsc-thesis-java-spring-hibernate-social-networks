package com.sones.facebook.JsonHandler.Factory;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public interface IWallFeedFactory 
{
	public	WallFacebookPostCreateDto getPost(Object feedObject);
}
