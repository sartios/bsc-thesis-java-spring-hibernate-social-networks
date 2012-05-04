package com.sones.facebook.keyword.result.logic;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;
import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.result.FacebookResultViewDto;
import com.sones.usermanager.model.ApplicationUser;

public class FaceboookPostKeywordResultService implements IFaceboookPostKeywordResultService
{
	private Map<String, FacebookResultViewDto> results;
	private IFacebookPostKeywordResultDao resultDao;
	private IStatusMessageDao statusDao;
	private IVideoDao videoDao;
	private ILinkDao linkDao;
	private INoteDao noteDao;
	private ICheckinDao checkinDao;
	private IPhotoDao photoDao;
	private final Logger _LOGGER;
	
	public FaceboookPostKeywordResultService(IFacebookPostKeywordResultDao resultDao, IStatusMessageDao statusDao,
			IVideoDao videoDao, ILinkDao linkDao, INoteDao noteDao, ICheckinDao checkinDao, IPhotoDao photoDao)
	{
		_LOGGER = Logger.getLogger( FaceboookPostKeywordResultService.class );
		this.resultDao = resultDao;
		this.statusDao = statusDao;
		this.videoDao = videoDao;
		this.linkDao = linkDao;
		this.noteDao = noteDao;
		this.checkinDao = checkinDao;
		this.photoDao = photoDao;
	}
	
	@Override
	public List<FacebookResultViewDto> getFacebookKeywordResults(ApplicationUser appUser) 
	{
		_LOGGER.warn("Call for application user: " + appUser.getId() );
		initResults();
		Iterable<FacebookPostKeywordResult> dbresults = resultDao.GetByApplicationUser(appUser);
		for(FacebookPostKeywordResult result : dbresults)
		{
			_LOGGER.warn( "Result id: " + result.getId() );
			FacebookPost post = result.getPost();
			String id = post.getId();

			if( results.containsKey( id ) == true )
			{
				_LOGGER.warn( "The result already exists." );
				FacebookResultViewDto resultDto = results.get( id );
				Keyword keyword = result.getKeyword();
				String keywordValue = keyword.getValue();
				resultDto.addKeywordValue(keywordValue);
				_LOGGER.warn( "Update the result with id: " + resultDto.getId().getId() );
				results.put(id,resultDto);
			}
			else
			{
				_LOGGER.warn( "New result." );
				FacebookResultViewDto resultDto = new FacebookResultViewDto();
				
				Keyword keyword = result.getKeyword();
				resultDto.addKeywordValue(keyword.getValue());
				
				String description = createDescription(post);
				resultDto.setDescripton(description);
				
				String type = findType(post);
				resultDto.setType(type);
				
				FacebookPostIdDto idDto = new FacebookPostIdDto();
				idDto.setId( id );
				resultDto.setId(idDto);
				
				results.put(id,resultDto);
			}
		}
		Set<String> ids = results.keySet();
		List<FacebookResultViewDto> resultViewDtos = new LinkedList<FacebookResultViewDto>();
		for(String key : ids)
		{
			FacebookResultViewDto viewDto = results.get(key);
			_LOGGER.warn("Adding result for key: " + key );
			resultViewDtos.add(viewDto);
		}
		return resultViewDtos;
	}

	private String findType(FacebookPost post) 
	{
		String type;
		String id = post.getId();
		_LOGGER.warn("Find type for post with id: " + id );
		if( statusDao.GetById( id ) != null )
		{
			type = "StatusMessage";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		if( videoDao.GetById( id ) != null )
		{
			type = "Video";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		if( linkDao.GetById( id ) != null )
		{
			type = "Link";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		if( noteDao.GetById( id ) != null )
		{
			type = "Note";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		if( checkinDao.GetById( id ) != null )
		{
			type = "Checkin";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		if( photoDao.GetById( id ) != null )
		{
			type = "Photo";
			_LOGGER.warn("Type: "+ type);
			return type;
		}
		type = new String("Unknown feed type");
		return type;
	}

	private String createDescription(FacebookPost post) 
	{
		String description;
		String id = post.getId();
		
		StatusMessage statusMessage = statusDao.GetById( id );
		if( statusMessage != null )
		{
			description = statusMessage.getMessage();
			return description;
		}
	
		Link link = linkDao.GetById( id );
		if( link != null )
		{
			description = link.getDescription();
			return description;
		}
		
		Note note = noteDao.GetById( id );
		if( note != null )
		{
			description = note.getMessage();
			return description;
		}
		
		Checkin checkin = checkinDao.GetById( id );
		if( checkin != null )
		{
			description = checkin.getMessage();
			return description;
		}
		
		Photo photo = photoDao.GetById( id );
		if( photo != null )
		{
			description = photo.getName();
			return description;
		}
		
		Video video = videoDao.GetById( id );
		if( photo != null )
		{
			description = video.getName();
			return description;
		}
		
		description = new String("Unknown description");
		return description;
	}

	private void initResults()
	{
		results = new HashMap<String, FacebookResultViewDto>();
	}
}
