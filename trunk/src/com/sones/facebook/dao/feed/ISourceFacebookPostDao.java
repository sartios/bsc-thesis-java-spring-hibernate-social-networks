package com.sones.facebook.dao.feed;

import java.util.Date;

import com.sones.dao.IGenericDao;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Source;

/**
 * Provides methods for accessing {@link SourceFacebookPost} class.
 * @author sartios.sones@gmail.com.
 *
 */
public interface ISourceFacebookPostDao extends IGenericDao<SourceFacebookPost, SourceFacebookPostId>
{
	/**
	 * Returns the source's facebook posts that where downloaded after the specified date.
	 * @param source for which to retrieve the posts.
	 * @param date the date that indicates the downloaded date limit.
	 * @return Source facebook posts.
	 * @throws	IllegalArgumentException	when source is null or date is null.
	 */
	public	Iterable<SourceFacebookPost>	getSourceFacebookPostsBySourceAndAfterDate( Source source, Date date );
}
