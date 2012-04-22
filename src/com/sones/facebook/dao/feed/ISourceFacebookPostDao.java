package com.sones.facebook.dao.feed;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.sones.dao.IGenericDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;

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
	public Iterable<SourceFacebookPost> getSourceFacebookPostsBySourceAndAfterDate( Source source, Date date );
	
	/**
	 * Returns the source facebook posts that the source is the type of <code>type</code>.
	 * @param type the type of the source.
	 * @return source facebook posts
	 * @throws IllegalArgumentException if the type is null.
	 */
	public Iterable<SourceFacebookPost> GetBySourceType( SourceType type );
	
	/**
	 * Returns the source facebook posts that their source exists in sources.
	 * @param sources the sources to retrieve their source facebook posts.
	 * @return source facebook posts.
	 * @throws IllegalArgumentException if the sources are null.
	 */
	public Iterable<SourceFacebookPost> GetBySources( Collection<Source> sources );
	
	/**
	 * Returns the source facebook posts that the source exists in the source collection
	 * and the post exists in the post collection.
	 * @param sources the collection of sources.
	 * @param posts the collection of posts.
	 * @return source facebook posts.
	 * @throws IllegalArgumentException if sources or posts are null.
	 */
	public Iterable<SourceFacebookPost> GetInSourcesAndInPosts( Collection<Source> sources, Collection<FacebookPost> posts );

}
