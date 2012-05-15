package com.sones.facebook.source.selector.logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;
import com.sones.sharedDto.facebook.source.selector.SourceViewDto;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.downloader.dao.IFacebookFriendDao;
import com.sones.facebook.downloader.model.FacebookFriend;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.placemanager.dao.IPlaceDao;
import com.sones.facebook.placemanager.model.Place;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.facebook.usermanager.model.ApplicationUserSourceId;

public class FacebookSourceManagerService implements IFacebookSourceManagerService
{
	private final Logger _LOGGER;
	private IApplicationUserDao appUserDao;
	private IApplicationUserSourceDao appUserSourceDao;
	private IFacebookFriendDao friendDao;
	private IPlaceDao placeDao;
	private ISourceDao sourceDao;
	private ISourceTypeDao typeDao;

	private FacebookSourceManagerService()
	{
		_LOGGER = Logger.getLogger(FacebookSourceManagerService.class);
	}
	
	/**
	 * @param appUserDao
	 * @param appUserSourceDao
	 * @param friendDao
	 * @param placeDao
	 */
	public FacebookSourceManagerService(IApplicationUserDao appUserDao,
			IApplicationUserSourceDao appUserSourceDao,
			IFacebookFriendDao friendDao, IPlaceDao placeDao, ISourceDao sourceDao,
			ISourceTypeDao typeDao)
	{
		_LOGGER = Logger.getLogger(FacebookSourceManagerService.class);
		this.appUserDao = appUserDao;
		this.appUserSourceDao = appUserSourceDao;
		this.friendDao = friendDao;
		this.placeDao = placeDao;
		this.sourceDao = sourceDao;
		this.typeDao = typeDao;
	}

	@Override
	public void deleteSelectedSources(String applicationUserId, Iterable<SourceCreateDto> sourcesDto) 
	{
		ApplicationUser appUser = appUserDao.GetById(applicationUserId);
		if(appUser == null)
		{
			throw new NullPointerException("There is not a user with that id");
		}
		
		for(SourceCreateDto sourceDto : sourcesDto)
		{
			String sourceId = sourceDto.getSourceId();
			Source source = sourceDao.GetById(sourceId);
			if(source == null)
			{
				throw new NullPointerException("Source is null");
			}
				
			ApplicationUserSourceId id = new ApplicationUserSourceId(source, appUser);
			ApplicationUserSource appUserSource = appUserSourceDao.GetById(id);
			if(appUserSource != null)
			{
				appUserSourceDao.Delete(appUserSource);
			}
		}
	}

	@Override
	public Iterable<SourceViewDto> getSelectedSources(String applicationUserId) 
	{
		ApplicationUser appUser = appUserDao.GetById(applicationUserId);
		if(appUser == null)
		{
			throw new NullPointerException("There is not a user with that id");
		}
		
		Iterable<ApplicationUserSource> appUserSources = appUserSourceDao.getApplicationUserSourcesByUser(appUser);
		Set<SourceViewDto> sourcesDto = new HashSet<SourceViewDto>();
		for(ApplicationUserSource appUserSource : appUserSources)
		{
			Source source = appUserSource.getId().getSource();
			String sourceId = source.getId();
			String sourceType = source.getType().getType();
			String sourceName = getSourceName(source);
			SourceViewDto sourceDto = new SourceViewDto(applicationUserId, sourceId, sourceName, sourceType);
			sourcesDto.add(sourceDto);
		}
		return sourcesDto;
	}

	private String getSourceName(Source source) 
	{
		SourceType tmpType = source.getType();
		SourceType type = typeDao.GetById(tmpType.getId());
		String id = source.getId();
		String name = null;
		if(type.getType().equals("User"))
		{
			FacebookFriend friend = friendDao.GetById(id);
			if(friend != null)
			{
				name = friend.getName();
			}
		}
		else if(type.getType().equals("Place"))
		{
			Place place = placeDao.GetById(id);
			name = place.getName();
		}
		return name;
	}
	
	

}
