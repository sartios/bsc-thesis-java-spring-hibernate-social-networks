package com.sones.facebook.saver.wall.feed;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

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
	private	DozerBeanMapper mapper;
	private	IStatusMessageSaver statusSaver;
	private	IVideoSaver videoSaver;
	private	ILinkSaver linkSaver;
	private	IPhotoSaver photoSaver;
	private	INoteSaver noteSaver;
	
	public FacebookWallPostSaver()
	{
		_LOGGER = Logger.getLogger(FacebookWallPostSaver.class);
		
		List< String >	mappings	=	new	ArrayList< String >();
		mappings.add( "Dozer/facebook/GraphApi/Wall/CreateWallMapper.xml" );
		mapper	=	new	DozerBeanMapper( mappings );

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
			_LOGGER.error( "Status message id:" + status.getId() );
			//statusSaver = (StatusMessageSaver)context.getBean("statusSaver");
			statusSaver.Save(status);
		}
		else if(postDto.getType().equals("Video"))
		{
			_LOGGER.warn("Saving video");

			WallVideoCreateDto videoDto = new WallVideoCreateDto();
			videoDto = (WallVideoCreateDto)postDto;
			Video video = new Video();
			mapper.map(videoDto, video);
			//videoSaver = (VideoSaver)context.getBean("videoSaver");
			videoSaver.Save(video);
		}
		else if(postDto.getType().equals("Link"))
		{
			_LOGGER.warn("Saving link");
			WallLinkCreateDto lintDto = new WallLinkCreateDto();
			lintDto =	(WallLinkCreateDto)postDto;
			Link link = new Link();
			mapper.map(lintDto, link);
			//linkSaver = (LinkSaver)context.getBean("linkSaver");
			linkSaver.Save(link);
		}
		else if(postDto.getType().equals("Photo"))
		{
			_LOGGER.warn("Saving photo");
			WallPhotoCreateDto photoDto = new WallPhotoCreateDto();
			photoDto =	(WallPhotoCreateDto)postDto;
			Photo photo = new Photo();
			mapper.map(photoDto, photo);
			//photoSaver = (PhotoSaver)context.getBean("photoSaver");
			photoSaver.Save(photo);
		}
		else if(postDto.getType().equals("Note"))
		{
			_LOGGER.warn("Saving note");
			WallNoteCreateDto noteDto = new WallNoteCreateDto();
			noteDto =	(WallNoteCreateDto)postDto;
			Note note = new Note();
			mapper.map(noteDto, note);
			//noteSaver = (NoteSaver)context.getBean("noteSaver");
			noteSaver.Save(note);
		}
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		if( this.mapper == null )
		{
			this.mapper = mapper;
		}
	}

	/**
	 * @return the statusSaver
	 */
	public IStatusMessageSaver getStatusSaver() {
		return statusSaver;
	}

	/**
	 * @param statusSaver the statusSaver to set
	 */
	public void setStatusSaver(IStatusMessageSaver statusSaver) {
		this.statusSaver = statusSaver;
	}

	/**
	 * @return the videoSaver
	 */
	public IVideoSaver getVideoSaver() {
		return videoSaver;
	}

	/**
	 * @param videoSaver the videoSaver to set
	 */
	public void setVideoSaver(IVideoSaver videoSaver) {
		this.videoSaver = videoSaver;
	}

	/**
	 * @return the linkSaver
	 */
	public ILinkSaver getLinkSaver() {
		return linkSaver;
	}

	/**
	 * @param linkSaver the linkSaver to set
	 */
	public void setLinkSaver(ILinkSaver linkSaver) {
		this.linkSaver = linkSaver;
	}

	/**
	 * @return the photoSaver
	 */
	public IPhotoSaver getPhotoSaver() {
		return photoSaver;
	}

	/**
	 * @param photoSaver the photoSaver to set
	 */
	public void setPhotoSaver(IPhotoSaver photoSaver) {
		this.photoSaver = photoSaver;
	}

	/**
	 * @return the noteSaver
	 */
	public INoteSaver getNoteSaver() {
		return noteSaver;
	}

	/**
	 * @param noteSaver the noteSaver to set
	 */
	public void setNoteSaver(INoteSaver noteSaver) {
		this.noteSaver = noteSaver;
	}

}
