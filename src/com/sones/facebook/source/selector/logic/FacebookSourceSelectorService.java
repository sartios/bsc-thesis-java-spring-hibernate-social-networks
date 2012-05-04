package com.sones.facebook.source.selector.logic;

import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;
import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;

public class FacebookSourceSelectorService implements IFacebookSourceSelectorService
{
	private IApplicationUserDao appUserDao;
	private ISourceTypeDao sourceTypeDao;
	private ISourceDao sourceDao;
	private IApplicationUserSourceDao appUserSourceDao;
	private SourceType type = null;
	
	private FacebookSourceSelectorService()
	{}
	
	public FacebookSourceSelectorService(IApplicationUserDao appUserDao, ISourceTypeDao sourceTypeDao,
			ISourceDao sourceDao, IApplicationUserSourceDao appUserSourceDao)
	{
		this.appUserDao = appUserDao;
		this.sourceTypeDao = sourceTypeDao;
		this.sourceDao = sourceDao;
		this.appUserSourceDao = appUserSourceDao;
	}
	
	@Override
	public void saveSources(Iterable<SourceCreateDto> sourceCreateDtos)
	{
		SourceCreateDto sourceDto = sourceCreateDtos.iterator().next();
		String appUserId = sourceDto.getApplicationUserId();
		ApplicationUser appUser = appUserDao.GetById(appUserId);
		
		for(SourceCreateDto sourceCreateDto : sourceCreateDtos)
		{
			String sourceId = sourceCreateDto.getSourceId();
			Source source = sourceDao.GetById(sourceId);
			if(source == null)
			{
				saveSource(sourceCreateDto);
				source = new Source();
			}
			source.setId(sourceId);
			source.setType(type);
			ApplicationUserSource appUserSource = new ApplicationUserSource(source, appUser);
			saveIfDoesNotExist(appUserSource,appUserSource.getId(),appUserSourceDao);			
		}
	}
	
	private void saveSource(SourceCreateDto sourceCreateDto)
	{
		SourceType type = new SourceType(sourceCreateDto.getSourceType());
		SourceType sourceType = sourceTypeDao.GetByType(type);
		if(sourceType == null)
		{
			Number number = sourceTypeDao.GetRowCount();
			sourceType = new SourceType();
			sourceType.setId(number.toString());
			sourceType.setType(type.getType());
			sourceTypeDao.Save(sourceType);
		}
		this.type = sourceType;
		Source source = new Source();
		String sourceId = sourceCreateDto.getSourceId();
		source.setId(sourceId);
		source.setType(sourceType);
		saveIfDoesNotExist(source,source.getId(),sourceDao);
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
}
