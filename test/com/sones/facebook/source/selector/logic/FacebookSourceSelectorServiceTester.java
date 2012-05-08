package com.sones.facebook.source.selector.logic;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.Source;
import com.sones.facebook.model.source.SourceType;
import com.sones.facebook.usermanager.dao.IApplicationUserSourceDao;
import com.sones.facebook.usermanager.model.ApplicationUserSource;
import com.sones.sharedDto.facebook.source.selector.SourceCreateDto;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class FacebookSourceSelectorServiceTester 
{
	private IFacebookSourceSelectorService service;
	private IApplicationUserDao appUserDao;
	private ISourceTypeDao sourceTypeDao;
	private ISourceDao sourceDao;
	private IApplicationUserSourceDao appUserSourceDao;
	private SecureRandom random;
	
	public FacebookSourceSelectorServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("PrivateSourceSelector/spring-private-source-selector-service.xml");
		service = (IFacebookSourceSelectorService) context.getBean("privateSourceSelectorService");
		appUserDao = (IApplicationUserDao) context.getBean("appUserDao");
		sourceTypeDao = (ISourceTypeDao) context.getBean("sourceTypeDao");
		sourceDao = (ISourceDao) context.getBean("sourceDao");
		appUserSourceDao = (IApplicationUserSourceDao) context.getBean("appUserSourceDao");
		random = new SecureRandom();
	}
	
	@Test
	public void saveSourcesShouldSaveSourceWhenTypeDoesNotExist()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("test1115");
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		String applicationUserId = appUser.getId();
		String sourceId = "aaa11122333444";
		String sourceType = "Super3";
		
		SourceType type = new SourceType(sourceType);
		
		SourceCreateDto sourceCreateDto = new SourceCreateDto(applicationUserId, sourceId, sourceType);
		List<SourceCreateDto> sourceCreateDtos = new ArrayList<SourceCreateDto>();
		sourceCreateDtos.add(sourceCreateDto);
		service.saveSources(sourceCreateDtos);
		
		SourceType dbtype = sourceTypeDao.GetByType(type);
		assertNotNull(dbtype);
		
		Source dbsource = sourceDao.GetById(sourceId);
		assertNotNull(dbsource);
		
		Iterable<ApplicationUserSource> appUserSources = appUserSourceDao.getApplicationUserSourcesByUser(appUser);
		
		ApplicationUserSource applicationUserSource = appUserSources.iterator().next();
		assertNotNull(applicationUserSource);
		
		deleteIfExists(applicationUserSource, applicationUserSource.getId(), appUserSourceDao);
		deleteIfExists(dbsource, dbsource.getId(), sourceDao);
		deleteIfExists(dbtype, dbtype.getId(), sourceTypeDao);
		deleteIfExists(appUser, appUser.getId(), appUserDao);
	}
	
	@Test
	public void saveSourcesShouldWorkInIteratorModeSameType()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("test1115");
		saveIfDoesNotExist(appUser,appUser.getId(),appUserDao);
		
		String applicationUserId = appUser.getId();
		List<String> sourceIds = new LinkedList<String>();
		
		BigInteger intevals = generateRandomNumber(4);
		for(int sourceIndex = 0; sourceIndex < intevals.intValue(); sourceIndex++)
		{
			String sourceId = generateRandomNumber(12).toString();
			sourceIds.add(sourceId);
		}
		
		String sourceType = "Super3";
		
		SourceType type = new SourceType(sourceType);
		List<SourceCreateDto> sourceCreateDtos = new ArrayList<SourceCreateDto>();
		for(String sourceId : sourceIds)
		{
			SourceCreateDto sourceCreateDto = new SourceCreateDto(applicationUserId, sourceId, sourceType);
			sourceCreateDtos.add(sourceCreateDto);
		}
		
		service.saveSources(sourceCreateDtos);
		
		SourceType dbtype = sourceTypeDao.GetByType(type);
		assertNotNull(dbtype);
		
		List<Source> sources = new LinkedList<Source>();
		for(String sourceId : sourceIds)
		{
			SourceCreateDto sourceCreateDto = new SourceCreateDto(applicationUserId, sourceId, sourceType);
			sourceCreateDtos.add(sourceCreateDto);
			Source dbsource = sourceDao.GetById(sourceId);
			assertNotNull(dbsource);
			sources.add(dbsource);
		}
		
		Iterable<ApplicationUserSource> appUserSources = appUserSourceDao.getApplicationUserSourcesByUser(appUser);
		
		ApplicationUserSource applicationUserSource = appUserSources.iterator().next();
		assertNotNull(applicationUserSource);
		
		for(ApplicationUserSource userSource : appUserSources)
		{
			deleteIfExists(userSource, userSource.getId(), appUserSourceDao);
		}
		
		for(Source dbsource : sources)
		{
			deleteIfExists(dbsource, dbsource.getId(), sourceDao);
		}
		deleteIfExists(dbtype, dbtype.getId(), sourceTypeDao);
		deleteIfExists(appUser, appUser.getId(), appUserDao);
	}
	
	private void saveIfDoesNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void deleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
	
	private BigInteger generateRandomNumber(int digits)
	{
		return new BigInteger(digits, random);
	}
}
