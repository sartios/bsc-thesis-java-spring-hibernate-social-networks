package com.sones.facebook.saver;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;

public interface IFacebookPostSaverLogic 
{
	public	boolean	saveWallPosts(Iterable<WallSourceFacebookPostCreateDto> posts);
}
