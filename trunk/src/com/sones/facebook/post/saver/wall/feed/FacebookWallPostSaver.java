package com.sones.facebook.post.saver.wall.feed;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallLinkCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallNoteCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallPhotoCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallStatusMessageCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallVideoCreateDto;

public class FacebookWallPostSaver implements	IFacebookWallPostSaver
{
	private	final Logger _LOGGER;
	private	ApplicationContext context;
	private	DozerBeanMapper mapper;
	private	IStatusMessageSaver statusSaver;
	private	IVideoSaver videoSaver;
	private	ILinkSaver linkSaver;
	private	IPhotoSaver photoSaver;
	private	INoteSaver noteSaver;
	
	public FacebookWallPostSaver()
	{
		_LOGGER = Logger.getLogger(FacebookWallPostSaver.class);
		context = new ClassPathXmlApplicationContext("spring-facebook-wall-saver.xml");
		mapper = (DozerBeanMapper) context.getBean("dozerMapper");
		_LOGGER.warn("Initializing...");
//		statusSaver = (StatusMessageSaver)context.getBean("statusSaver");
//		videoSaver = (VideoSaver)context.getBean("videoSaver");
//		linkSaver = (LinkSaver)context.getBean("linkSaver");
//		photoSaver = (PhotoSaver)context.getBean("photoSaver");
//		noteSaver = (NoteSaver)context.getBean("noteSaver");
	}

	@Override
	public void SavePost(WallFacebookPostCreateDto postDto) 
	{
		if( postDto == null )
		{
			_LOGGER.error("Post dto can't be null");
			throw new NullPointerException("Post dto can't be null");
		}
		if(postDto.getType().equals("StatusMessage"))
		{
			_LOGGER.warn("Saving status message");
			WallStatusMessageCreateDto statusDto = new WallStatusMessageCreateDto();
			statusDto = (WallStatusMessageCreateDto)postDto;
			StatusMessage status = new StatusMessage();
			mapper.map(statusDto, status);
			statusSaver = (StatusMessageSaver)context.getBean("statusSaver");
			statusSaver.Save(status);
		}
		else if(postDto.getType().equals("Video"))
		{
			_LOGGER.warn("Saving video");

			WallVideoCreateDto videoDto = new WallVideoCreateDto();
			videoDto = (WallVideoCreateDto)postDto;
			Video video = new Video();
			mapper.map(videoDto, video);
			videoSaver = (VideoSaver)context.getBean("videoSaver");
			videoSaver.Save(video);
		}
		else if(postDto.getType().equals("Link"))
		{
			_LOGGER.warn("Saving link");
			WallLinkCreateDto lintDto = new WallLinkCreateDto();
			lintDto =	(WallLinkCreateDto)postDto;
			Link link = new Link();
			mapper.map(lintDto, link);
			linkSaver = (LinkSaver)context.getBean("linkSaver");
			linkSaver.Save(link);
		}
		else if(postDto.getType().equals("Photo"))
		{
			_LOGGER.warn("Saving photo");
			WallPhotoCreateDto photoDto = new WallPhotoCreateDto();
			photoDto =	(WallPhotoCreateDto)postDto;
			Photo photo = new Photo();
			mapper.map(photoDto, photo);
			photoSaver = (PhotoSaver)context.getBean("photoSaver");
			photoSaver.Save(photo);
		}
		else if(postDto.getType().equals("Note"))
		{
			_LOGGER.warn("Saving note");
			WallNoteCreateDto noteDto = new WallNoteCreateDto();
			noteDto =	(WallNoteCreateDto)postDto;
			Note note = new Note();
			mapper.map(noteDto, note);
			noteSaver = (NoteSaver)context.getBean("noteSaver");
			noteSaver.Save(note);
		}
	}

}
