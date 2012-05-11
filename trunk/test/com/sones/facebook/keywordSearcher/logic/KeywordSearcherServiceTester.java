package com.sones.facebook.keywordSearcher.logic;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.downloader.dao.IFacebookDownloadDao;
import com.sones.facebook.downloader.dao.IFacebookPostDownloadDao;
import com.sones.facebook.downloader.model.FacebookDownload;
import com.sones.facebook.downloader.model.FacebookPostDownload;
import com.sones.facebook.keywordSearcher.dao.IApplicationUserKeywordDao;
import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.logic.retriever.ICheckinSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.ILinkSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.INoteSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IPhotoSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IStatusMessageSearchDataManager;
import com.sones.facebook.keywordSearcher.logic.retriever.IVideoSearchDataManager;
import com.sones.facebook.keywordSearcher.model.ApplicationUserKeyword;
import com.sones.facebook.keywordSearcher.model.Keyword;
import com.sones.facebook.model.feed.Checkin;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.usermanager.dao.IApplicationUserDao;
import com.sones.usermanager.model.ApplicationUser;

public class KeywordSearcherServiceTester 
{
	private IStatusMessageDao statusDao;
	private ICheckinDao checkinDao;
	private ILinkDao linkDao;
	private IPhotoDao photoDao;
	private IVideoDao videoDao;
	private INoteDao noteDao;
	private IApplicationUserDao appUserDao;
	private IFacebookDownloadDao downloadDao;
	private IFacebookPostDownloadDao postDownloadDao;
	private IKeywordDao keywordDao;
	private IApplicationUserKeywordDao appUserKeywordDao;
	private IKeywordSearcherService service;
	
	private ICheckinSearchDataManager checkinDataManager;
	private IStatusMessageSearchDataManager statusDataManager;
	private IVideoSearchDataManager videoDataManager;
	private ILinkSearchDataManager linkDataManager;
	private IPhotoSearchDataManager photoDataManager;
	private INoteSearchDataManager noteDataManager;
	
	public KeywordSearcherServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("KeywordSearcher/spring-facebook-combained-searcher-nu.xml");
		statusDao = (IStatusMessageDao) context.getBean("statusDao");
		checkinDao = (ICheckinDao) context.getBean("checkinDao");
		linkDao = (ILinkDao) context.getBean("linkDao");
		photoDao = (IPhotoDao) context.getBean("photoDao");
		videoDao = (IVideoDao) context.getBean("videoDao");
		noteDao = (INoteDao) context.getBean("noteDao");
		appUserDao = (IApplicationUserDao) context.getBean("appUserDao");
		downloadDao = (IFacebookDownloadDao) context.getBean("downloadDao");
		postDownloadDao = (IFacebookPostDownloadDao) context.getBean("postDownloadDao");
		keywordDao = (IKeywordDao) context.getBean("keywordDao");
		appUserKeywordDao = (IApplicationUserKeywordDao) context.getBean("appUserKeywordDao");
		checkinDataManager = (ICheckinSearchDataManager) context.getBean("checkinDataRetriever");
		statusDataManager = (IStatusMessageSearchDataManager) context.getBean("statusMessageDataRetriever");
		videoDataManager = (IVideoSearchDataManager) context.getBean("videoDataRetriever");
		linkDataManager = (ILinkSearchDataManager) context.getBean("linkDataRetriever");
		noteDataManager =  (INoteSearchDataManager) context.getBean("noteDataRetriever");
		photoDataManager =  (IPhotoSearchDataManager) context.getBean("photoDataRetriever");
		service = (IKeywordSearcherService) context.getBean("keywordSearcherService");
		service.addDataRetriever(checkinDataManager);
		service.addDataRetriever(linkDataManager);
		service.addDataRetriever(noteDataManager);
		service.addDataRetriever(photoDataManager);
		service.addDataRetriever(statusDataManager);
		service.addDataRetriever(videoDataManager);
		
	}
	
	@Test
	public void searchForKeywordsShouldWorkWithEveryPost()
	{
		StatusMessage statusMessage = getStatusMessage();
		saveIfDoesNotExist(statusMessage, statusMessage.getId(), statusDao);
		
		Checkin checkin = getCheckin();
		saveIfDoesNotExist(checkin, checkin.getId(), checkinDao);

		Link link = getLink();
		saveIfDoesNotExist(link, link.getId(), linkDao);

		Photo photo = getPhoto();
		saveIfDoesNotExist(photo, photo.getId(), photoDao);

		Video video = getVideo();
		saveIfDoesNotExist(video, video.getId(), videoDao);

		Note note = getNote();
		saveIfDoesNotExist(note, note.getId(), noteDao);
		
		ApplicationUser appUser = getAppUser();
		saveIfDoesNotExist(appUser, appUser.getId(), appUserDao);

		FacebookDownload download = getDownload(appUser);
		saveIfDoesNotExist(download, download.getId(), downloadDao);

		FacebookPostDownload postDownload1 = new FacebookPostDownload(statusMessage, download);
		saveIfDoesNotExist(postDownload1, postDownload1.getId(), postDownloadDao);

		FacebookPostDownload postDownload2 = new FacebookPostDownload(checkin, download);
		saveIfDoesNotExist(postDownload2, postDownload2.getId(), postDownloadDao);

		FacebookPostDownload postDownload3 = new FacebookPostDownload(link, download);
		saveIfDoesNotExist(postDownload3, postDownload3.getId(), postDownloadDao);

		FacebookPostDownload postDownload4 = new FacebookPostDownload(photo, download);
		saveIfDoesNotExist(postDownload4, postDownload4.getId(), postDownloadDao);

		FacebookPostDownload postDownload5 = new FacebookPostDownload(video, download);
		saveIfDoesNotExist(postDownload5, postDownload5.getId(), postDownloadDao);

		FacebookPostDownload postDownload6 = new FacebookPostDownload(note, download);
		saveIfDoesNotExist(postDownload6, postDownload6.getId(), postDownloadDao);

		Keyword keyword = new Keyword();
		keyword.setId("1");
		keyword.setValue("message");
		saveIfDoesNotExist(keyword, keyword.getId(), keywordDao);
		
		ApplicationUserKeyword appUserKeyword = new ApplicationUserKeyword(keyword, appUser);
		saveIfDoesNotExist(appUserKeyword, appUserKeyword.getId(), appUserKeywordDao);

		service.searchForKeywordsIntoAllFacebookPostTypes(appUser);
		
	}
	
	private ApplicationUser getAppUser()
	{
		ApplicationUser appUser = new ApplicationUser();
		appUser.setId("AppUserID");
		return appUser;
	}
	
	private FacebookDownload getDownload(ApplicationUser appUser)
	{
		FacebookDownload download = new FacebookDownload();
		download.setId("1");
		download.setDate(Calendar.getInstance().getTime());
		download.setAppUser(appUser);
		return download;
	}
	
	private Note getNote()
	{
		Note note = new Note();
		note.setId("NotID");
		note.setMessage("Note message");
		return note;
	}
	
	private Video getVideo()
	{
		Video video = new Video();
		video.setId("VideoID");
		video.setDescription("Video description");
		video.setName("Video name");
		return video;
	}
	
	private Photo getPhoto()
	{
		Photo photo = new Photo();
		photo.setId("PhotoID");
		photo.setName("Photo name");
		return photo;
	}
	
	private StatusMessage getStatusMessage()
	{
		StatusMessage statusMessage = new StatusMessage();
		statusMessage.setId("statusMessage");
		statusMessage.setMessage("Status message message");
		return statusMessage;
	}
	
	private Checkin getCheckin()
	{
		Checkin checkin = new Checkin();
		checkin.setId("CheckinID");
		checkin.setMessage("Checkin message");
		return checkin;
	}
	
	private Link getLink()
	{
		Link link = new Link();
		link.setId("LinkID");
		link.setDescription("Link description");
		link.setMessage("Link message");
		link.setName("Link name");
		return link;
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
}
