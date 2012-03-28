package com.sones.facebook.graphApi.GraphApiHandler;

import java.util.Date;

import com.sones.facebook.model.source.Source;
import com.sones.facebook.tokenmanager.model.FacebookToken;
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
	
	/**
	 * Downloads the facebook feeds for the specified user and only those that were created after the date.
	 * @param source
	 * @param token
	 * @param date
	 * @return
	 */
	public	Iterable<WallFacebookPostCreateDto>	GetWallPostsAfterDate(Source source, FacebookToken token, Date date);	
}
