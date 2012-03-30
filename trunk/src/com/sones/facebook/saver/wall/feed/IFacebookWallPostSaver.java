package com.sones.facebook.saver.wall.feed;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public interface IFacebookWallPostSaver 
{
	public	void	SavePost(WallFacebookPostCreateDto postDto);
	
}
