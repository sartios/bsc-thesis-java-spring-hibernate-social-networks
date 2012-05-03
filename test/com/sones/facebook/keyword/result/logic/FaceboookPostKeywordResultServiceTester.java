package com.sones.facebook.keyword.result.logic;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.IFacebookPostDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.result.FacebookResultViewDto;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.usermanager.dao.IApplicationUserDao;


public class FaceboookPostKeywordResultServiceTester 
{
	private IApplicationUserDao appUserDao;
	private IStatusMessageDao statusMessageDao;
	private ILinkDao linkDao;
	private IVideoDao videoDao;
	private IPhotoDao photoDao;
	private IKeywordDao keywordDao;
	private Set<FacebookPost> facebookPosts;
	private IFacebookPostKeywordResultDao resultDao;
	private IFaceboookPostKeywordResultService service;
	private IFacebookPostDao postDao;
	
	public FaceboookPostKeywordResultServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookResults/spring-facebookresults-logic-nu.xml");
		appUserDao = (IApplicationUserDao) context.getBean("appUserDao");
		statusMessageDao = (IStatusMessageDao) context.getBean("statusMessageDao");
		linkDao = (ILinkDao) context.getBean("linkDao");
		videoDao = (IVideoDao) context.getBean("videoDao");
		photoDao = (IPhotoDao) context.getBean("photoDao");
		keywordDao = (IKeywordDao) context.getBean("keywordDao");
		resultDao = (IFacebookPostKeywordResultDao) context.getBean("resultDao");
		postDao = (IFacebookPostDao) context.getBean("postDao");
		service = (IFaceboookPostKeywordResultService) context.getBean("service");
	}
	
	@Before
	public void setUp()
	{
		facebookPosts = new HashSet<FacebookPost>();
	}
	
	@After
	public void tearDown()
	{
		facebookPosts = null;
	}
	
	@Test
	public void getFacebookKeywordResultsShouldWorkCorrectly()
	{
		SecureRandom random = new SecureRandom();
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId((new BigInteger(8, random)).toString());
		saveIfNotExists(appUser, appUser.getId(), appUserDao);
		
		List<StatusMessage> statusMessages = getStatusMessages();
		List<Video> videos = getVideos();
		List<Photo> photos = getPhotos();
		List<Link> links = getLinks();
		List<Keyword> keywords = getKeywords();
		List<FacebookPostKeywordResult> results = getResults(keywords, appUser);
		List<FacebookResultViewDto> resultsDto = service.getFacebookKeywordResults(appUser);
		assertEquals(resultsDto.size(), facebookPosts.size());
		
		for( FacebookPostKeywordResult result : results )
		{
			deleteIfExists(result, result.getId(), resultDao);
		}
		
		for( Keyword keyword : keywords )
		{
			deleteIfExists(keyword, keyword.getId(), keywordDao);
		}
		
		for( Link link : links )
		{
			deleteIfExists(link, link.getId(), linkDao);
		}
		
		for( Video video : videos )
		{
			deleteIfExists(video, video.getId(), videoDao);
		}
		
		for( StatusMessage status : statusMessages )
		{
			deleteIfExists(status, status.getId(), statusMessageDao);
		}
		
		for( Photo photo : photos )
		{
			deleteIfExists(photo, photo.getId(), photoDao);
		}
	}
	
	private List<FacebookPostKeywordResult> getResults( List<Keyword> keywords, ApplicationUser user)
	{
		List<FacebookPostKeywordResult> results = new ArrayList<FacebookPostKeywordResult>();
		for(FacebookPost post : facebookPosts)
		{
			for(Keyword keyword : keywords)
			{
				FacebookPostKeywordResult result = new FacebookPostKeywordResult();
				String id = generateRandomValue(10).toString();
				result.setId(id);
				result.setKeyword(keyword);
				result.setPost(post);
				result.setUser(user);
				saveIfNotExists(result, result.getId(), resultDao );
				results.add(result);
			}
		}
		return results;
	}
	
	private List<Keyword> getKeywords()
	{
		List<Keyword> keywords = new ArrayList<Keyword>();
		int randomValue = generateRandomValue(3).intValue();
		for(int postIndex = 0; postIndex < randomValue; postIndex++)
		{
			Keyword keyword = new Keyword();
			String id = generateRandomValue(8).toString();
			keyword.setId(id);
			String message = "Keyword " + id ;
			keyword.setValue(message);
			saveIfNotExists(keyword, keyword.getId(), keywordDao);
			keywords.add(keyword);
		}
		return keywords;
	}
	
	private List<StatusMessage> getStatusMessages() 
	{
		List<StatusMessage> posts = new ArrayList<StatusMessage>();
		int randomValue = generateRandomValue(3).intValue();
		for(int postIndex = 0; postIndex < randomValue; postIndex++)
		{
			StatusMessage post = new StatusMessage();
			String id = generateRandomValue(8).toString();
			post.setId(id);
			String message = "Status message " + id ;
			post.setMessage(message);
			boolean wasSaved = saveIfNotExists(post, post.getId(), postDao);
			if( wasSaved )
			{
				facebookPosts.add(post);
			}	
			posts.add(post);
		}
		return posts;
	}
	
	private List<Video> getVideos() 
	{
		List<Video> posts = new ArrayList<Video>();
		int randomValue = generateRandomValue(3).intValue();
		for(int postIndex = 0; postIndex < randomValue; postIndex++)
		{
			Video post = new Video();
			String id = generateRandomValue(8).toString();
			post.setId(id);
			String message = "Video message " + id ;
			post.setDescription(message);
			boolean wasSaved = saveIfNotExists(post, post.getId(), postDao);
			if( wasSaved )
			{
				facebookPosts.add(post);
			}	
			posts.add(post);
		}
		return posts;
	}
	
	private List<Photo> getPhotos() 
	{
		List<Photo> posts = new ArrayList<Photo>();
		int randomValue = generateRandomValue(3).intValue();
		for(int postIndex = 0; postIndex < randomValue; postIndex++)
		{
			Photo post = new Photo();
			String id = generateRandomValue(8).toString();
			post.setId(id);
			String message = "Photo message " + id ;
			post.setName(message);
			boolean wasSaved = saveIfNotExists(post, post.getId(), postDao);
			if( wasSaved )
			{
				facebookPosts.add(post);
			}	
			posts.add(post);
		}
		return posts;
	}
	
	private List<Link> getLinks() 
	{
		List<Link> posts = new ArrayList<Link>();
		int randomValue = generateRandomValue(3).intValue();
		for(int postIndex = 0; postIndex < randomValue; postIndex++)
		{
			Link post = new Link();
			String id = generateRandomValue(8).toString();
			post.setId(id);
			String message = "Photo message " + id ;
			post.setName(message);
			boolean wasSaved = saveIfNotExists(post, post.getId(), postDao);
			if( wasSaved )
			{
				facebookPosts.add(post);
			}
			posts.add(post);
		}
		return posts;
	}
	
	private BigInteger generateRandomValue(int digits)
	{
		SecureRandom random = new SecureRandom();
		return (new BigInteger(digits, random));
	}
	
	private boolean saveIfNotExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
			return true;
		}
		return false;
	}
	
	private void deleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
}
