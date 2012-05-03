package com.sones.facebook.post.logic;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.dao.IGenericDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.view.posts.LinkViewDto;
import com.sones.sharedDto.facebook.view.posts.NoteViewDto;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;
import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public class FacebookPostViewServiceTester 
{
	private IFacebookPostViewService service;
	private IStatusMessageDao statusDao;
	private INoteDao noteDao;
	private ILinkDao linkDao;
	private IVideoDao videoDao;
	private IPhotoDao photoDao;
	
	public FacebookPostViewServiceTester()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("FacebookViewPosts/spring-facebookpostview-logic-nu.xml");
		service = (IFacebookPostViewService) context.getBean("facebookPostViewService");
		statusDao = (IStatusMessageDao) context.getBean("statusDao");
		noteDao = (INoteDao) context.getBean("noteDao");
		linkDao = (ILinkDao) context.getBean("linkDao");
		videoDao = (IVideoDao) context.getBean("videoDao");
		photoDao = (IPhotoDao) context.getBean("photoDao");
	}
	
	@Test
	public void testGetStatusMessageWorkCorrectlyNoComments()
	{
		StatusMessage status = new StatusMessage();
		String statusId = generateRandomValue(9).toString();
		status.setId(statusId);
		status.setMessage("Status message");
		saveIfNotExists(status, statusId, statusDao);
		StatusMessageViewDto statusViewDto = service.getStatusMessage(statusId);
		assertEquals(status.getMessage(),statusViewDto.getMessage());
	}
	
	@Test
	public void testGetLinkWorkCorrectlyNoComments()
	{
		Link link = new Link();
		String linkId = generateRandomValue(9).toString();
		link.setId(linkId);
		link.setDescription("Link description");
		link.setIcon("Link icon");
		link.setLink("Link link url");
		link.setMessage("Link message");
		link.setName("Link name");
		link.setPicture("Link picture");
		saveIfNotExists(link, linkId, linkDao);
		LinkViewDto linkViewDto = service.getLink(linkId);
		assertEquals(link.getDescription(),linkViewDto.getDescription());
		assertEquals(link.getIcon(),linkViewDto.getIcon());
		assertEquals(link.getLink(),linkViewDto.getLink());
		assertEquals(link.getMessage(),linkViewDto.getMessage());
		assertEquals(link.getName(),linkViewDto.getName());
		assertEquals(link.getPicture(),linkViewDto.getPicture());
	}
	
	@Test
	public void testGetVideoWorkCorrectlyNoComments()
	{
		Video video = new Video();
		String videoId = generateRandomValue(9).toString();
		video.setId(videoId);
		video.setDescription("Video description");
		video.setName("Video name");
		video.setPicture("Video picture");
		video.setSource("Video source");
		saveIfNotExists(video, videoId, videoDao);
		VideoViewDto videoViewDto = service.getVideo(videoId);
		assertEquals(video.getDescription(),videoViewDto.getDescription());
		assertEquals(video.getName(),videoViewDto.getName());
		assertEquals(video.getPicture(),videoViewDto.getPicture());
		assertEquals(video.getSource(),videoViewDto.getSource());
	}
	
	@Test
	public void testGetPhotoWorkCorrectlyNoComments()
	{
		Photo photo = new Photo();
		String photoId = generateRandomValue(9).toString();
		photo.setId(photoId);
		photo.setIcon("Photo icon");
		photo.setLink("Photo link");
		photo.setName("Photo name");
		photo.setPicture("Photo picture");
		saveIfNotExists(photo, photoId, photoDao);
		PhotoViewDto photoViewDto = service.getPhoto(photoId);
		assertEquals(photo.getIcon(),photoViewDto.getIcon());
		assertEquals(photo.getLink(),photoViewDto.getLink());
		assertEquals(photo.getName(),photoViewDto.getName());
		assertEquals(photo.getPicture(),photoViewDto.getPicture());
	}
	
	@Test
	public void testGetNoteWorkCorrectlyNoComments()
	{
		Note note = new Note();
		String noteId = generateRandomValue(9).toString();
		note.setId(noteId);
		note.setMessage("Note message");
		saveIfNotExists(note, noteId, noteDao);
		NoteViewDto noteViewDto = service.getNote(noteId);
		assertEquals(note.getMessage(),noteViewDto.getMessage());
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
