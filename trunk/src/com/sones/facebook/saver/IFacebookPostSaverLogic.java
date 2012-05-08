package com.sones.facebook.saver;

import org.springframework.dao.DataAccessException;

import com.sones.sharedDto.exceptions.MapErrorException;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallSourceFacebookPostCreateDto;

public interface IFacebookPostSaverLogic 
{
	public	boolean	saveWallPosts(Iterable<WallSourceFacebookPostCreateDto> posts) throws DataAccessException, IllegalArgumentException, MapErrorException ;
}
