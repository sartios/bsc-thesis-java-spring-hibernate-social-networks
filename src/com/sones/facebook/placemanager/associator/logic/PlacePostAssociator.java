package com.sones.facebook.placemanager.associator.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IPagePostDao;
import com.sones.facebook.dao.feed.ISourceFacebookPostDao;
import com.sones.facebook.dao.source.IPageDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.downloader.model.FacebookPostDownloadId;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.PagePost;
import com.sones.facebook.model.feed.SourceFacebookPost;
import com.sones.facebook.model.feed.SourceFacebookPostId;
import com.sones.facebook.model.source.Page;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.placemanager.associator.exception.NoSuchSourceType;
import com.sones.usermanager.model.ApplicationUser;

public class PlacePostAssociator implements IPlacePostAssociator
{
	/**
	 * The class logger.
	 */
	private final Logger _LOGGER;
	private IFacebookPostDownloadDao facebookPostDownloadDao;
	private IFacebookDownloadDao facebookDownloadDao;
	private ISourceFacebookPostDao sourceFacebookPostDao;
	private ISourceDao sourceDao;
	private ISourceTypeDao sourceTypeDao;
	private IPageDao pageDao;
	private IPagePostDao pagePostDao;
	
	/**
	 * Initiates the object.
	 */
	public PlacePostAssociator()
	{
		_LOGGER = Logger.getLogger(PlacePostAssociator.class);
	}
	
	@Override
	public void AssociatePlaceWithPosts(ApplicationUser appUser)
	{
		if(appUser == null)
		{
			_LOGGER.error("The application user can't be null.");
			throw new IllegalArgumentException("The application user can't be null.");
		}
		Date date = null;
		Iterable<FacebookDownload> downloads = facebookDownloadDao.GetByAppUserAfterDate(appUser, date);
		Collection<FacebookDownload> collection = GetFacebookDownloadCollection(downloads);
		Iterable<FacebookPostDownload> facebookPostDownloads = facebookPostDownloadDao.GetInDownloads(collection);
		Iterable<FacebookPost> facebookPosts = GetFacebookPosts(facebookPostDownloads);
		SourceType sourceType = new SourceType("Place");
		if(sourceType == null)
		{
			_LOGGER.error("The source type doesn't exist.");
			throw new NoSuchSourceType("The source type doesn't exist.");
		}
		SourceType type = sourceTypeDao.GetByType(sourceType);
		Iterable<Source> sources = sourceDao.GetByType(type);
		Collection<FacebookPost> postResults = GetFacebookPostCollection(facebookPosts);
		Collection<Source> sourceResults = GetSourceCollection(sources);
		Iterable<SourceFacebookPost> results = sourceFacebookPostDao.GetInSourcesAndInPosts(sourceResults, postResults);
		SaveResults(results);
	}
	
	/**
	 * Saves the {@link PagePost}.
	 * @param pagePosts the page's posts.
	 */
	private void SaveResults(Iterable<SourceFacebookPost> pagePosts)
	{
		Iterable<PagePost> posts = GetPagePosts(pagePosts);
		for(PagePost post : posts)
		{
			SaveIfNotExists(post, post.getId(), pagePostDao);
		}
	}
	
	/**
	 * Returns the page posts from the source posts.
	 * @param sourcePosts the source posts.
	 */
	private Iterable<PagePost> GetPagePosts(Iterable<SourceFacebookPost> sourcePosts)
	{
		Set<PagePost> pagePosts = new HashSet<PagePost>();
		for(SourceFacebookPost sourcePost : sourcePosts)
		{
			SourceFacebookPostId id = sourcePost.getId();
			Source source = id.getSource();
			Page page = GetPage(source);
			FacebookPost post = id.getPost();
			PagePost pagePost = GetPagePost(post, page);
			pagePosts.add(pagePost);
		}
		return pagePosts;
	}
	
	/**
	 * Returns the page post by providing the post and the page.
	 * @param post
	 * @param page
	 */
	private PagePost GetPagePost(FacebookPost post, Page page)
	{
		PagePost pagePost = new PagePost();
		pagePost.setId( post.getId() );
		pagePost.setPage(page);
		return pagePost;
	}
	
	/**
	 * Returns the page from source
	 * @param source
	 * @throws Exception
	 */
	private Page GetPage(Source source)
	{
		Page page = pageDao.GetById(source.getId());
		if(page == null)
		{
			_LOGGER.error("There is not a page with id: "+source.getId()+".");
		}
		return page;
	}
	
	/**
	 * Saves the model if it doesn't already exist.
	 * @param model
	 * @param id
	 * @param dao
	 */
	private void SaveIfNotExists(Object model, Object id,IGenericDao dao) 
	{
		if( dao.GetById(id) == null )
		{
			dao.Save(model);
		}
	}

	/**
	 * Converts a source iterable to a collection of sources.
	 * @param sources
	 * @return collection of sources.
	 */
	private Collection<Source> GetSourceCollection(Iterable<Source> sources) 
	{
		Collection<Source> collection = new ArrayList<Source>();
		for(Source source : sources)
		{
			collection.add(source);
		}
		return collection;
	}
	
	/**
	 * Converts a FacebookPost iterable to a collection of FacebookPost.
	 * @param facebookPosts
	 * @return collection of facebook Posts.
	 */
	private Collection<FacebookPost> GetFacebookPostCollection(Iterable<FacebookPost> facebookPosts)
	{
		Collection<FacebookPost> collection = new ArrayList<FacebookPost>();
		for(FacebookPost post : facebookPosts)
		{
			collection.add(post);
		}
		return collection;
	}

	/**
	 * Converts a facebook post downloads iterable to a collection of facebook post downloads.
	 * @param facebookPostDownloads
	 * @return collection of facebook post downloads.
	 */
	private Iterable<FacebookPost> GetFacebookPosts(Iterable<FacebookPostDownload> facebookPostDownloads) 
	{
		Set<FacebookPost> posts = new HashSet<FacebookPost>();
		for(FacebookPostDownload facebookPostDownload : facebookPostDownloads)
		{	
			FacebookPostDownloadId id = facebookPostDownload.getId();
			FacebookPost post = id.getPost();
			posts.add(post);
		}
		return posts;
	}

	/**
	 * Converts a facebook download iterable to a collection of facebook download.
	 * @param downloads
	 * @return collection of facebook download.
	 */
	private Collection<FacebookDownload> GetFacebookDownloadCollection(Iterable<FacebookDownload> downloads)
	{
		Collection<FacebookDownload> collection = new ArrayList<FacebookDownload>();
		for(FacebookDownload download : downloads)
		{
			collection.add(download);
		}
		return collection;
	}
}
