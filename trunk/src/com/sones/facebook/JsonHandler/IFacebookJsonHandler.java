package com.sones.facebook.JsonHandler;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public interface IFacebookJsonHandler
{
	public	Iterable<WallFacebookPostCreateDto>	GetWallPosts(String jsonString);
}
