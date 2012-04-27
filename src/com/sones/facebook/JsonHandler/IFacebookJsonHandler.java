package com.sones.facebook.JsonHandler;

import com.sones.facebook.placemanager.model.Place;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

public interface IFacebookJsonHandler
{
	public	Iterable<WallFacebookPostCreateDto>	GetWallPosts(String jsonString);
	
	/**
	 * Returns the places that exist into jsonString
	 * @param jsonString the json string that contains the places.
	 */
	public	Iterable< Place >	GetPublicPlaces( String jsonString );
}