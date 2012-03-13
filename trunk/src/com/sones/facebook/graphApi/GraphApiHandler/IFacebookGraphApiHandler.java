package com.sones.facebook.graphApi.GraphApiHandler;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;

/**
 * Provides methods that call graph api services.
 * @author sartios.sones@gmail.com.
 *
 */
public interface IFacebookGraphApiHandler
{
	/**
	 * Calls the graph api wall service and return the {@code sourceId} wall feeds.
	 * @param sourceId
	 * @param token
	 * @return wall feeds
	 * @throws IllegalArgumentException if {@code sourceId} or {@code token} is null or empty.
	 */
	public	Iterable<WallFacebookPostCreateDto>	GetWallPosts(String sourceId, String token);
}
