package com.sones.facebook.keywordSearcher.logic.retriever;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sones.facebook.dao.source.IApplicationUserSourceDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;
import com.sones.facebook.keywordSearcher.model.KeywordSearch;
import com.sones.facebook.model.source.ApplicationUserSource;
import com.sones.facebook.model.source.Source;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.CheckinSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.LinkSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.NoteSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.PhotoSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.StatusMessageSearchDto;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.VideoSearchDto;
import com.sones.userManager.model.ApplicationUser;

public class KeywordSearcherDataManager	implements IKeywordSearcherDataManager
{
	private	final	Logger	_LOGGER;
	private	IApplicationUserSourceDao	appUserSourcesDao;
	private	IKeywordSearchDao	keywordSearchDao;
	
	public KeywordSearcherDataManager()
	{
		_LOGGER	=	Logger.getLogger(KeywordSearcherDataManager.class);
	}

	@Override
	public Iterable<CheckinSearchDto> getCheckinForSearch( ApplicationUser appUser )
	{
		Iterable<ApplicationUserSource> appUserSources = appUserSourcesDao.getApplicationUserSourcesByUser( appUser );
		KeywordSearch	search = keywordSearchDao.getLastKeywordSearchByAppUser( appUser );
		Date date = search.getDate();
		Set<CheckinSearchDto> posts = new HashSet<CheckinSearchDto>();
		for( ApplicationUserSource appUserSource : appUserSources )
		{
			Source source = appUserSource.getId().getSource();
			
		}
		return null;
	}

	@Override
	public Iterable<LinkSearchDto> getLinkForSearch(ApplicationUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<NoteSearchDto> getNoteForSearch(ApplicationUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PhotoSearchDto> getPhotoForSearch(ApplicationUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<StatusMessageSearchDto> getStatusMessagesForSearch(
			ApplicationUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<VideoSearchDto> getVideoForSearch(ApplicationUser appUser) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param appUserSourcesDao the appUserSourcesDao to set
	 */
	public void setAppUserSourcesDao(IApplicationUserSourceDao appUserSourcesDao) {
		this.appUserSourcesDao = appUserSourcesDao;
	}

	/**
	 * @return the appUserSourcesDao
	 */
	public IApplicationUserSourceDao getAppUserSourcesDao() {
		return appUserSourcesDao;
	}
}
