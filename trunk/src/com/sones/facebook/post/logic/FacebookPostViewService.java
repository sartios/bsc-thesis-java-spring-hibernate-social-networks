package com.sones.facebook.post.logic;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

import com.sones.facebook.dao.feed.ICheckinDao;
import com.sones.facebook.dao.feed.ILinkDao;
import com.sones.facebook.dao.feed.INoteDao;
import com.sones.facebook.dao.feed.IPhotoDao;
import com.sones.facebook.dao.feed.IStatusMessageDao;
import com.sones.facebook.dao.feed.IVideoDao;
import com.sones.facebook.dao.feed.comment.ICommentDao;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.facebook.model.feed.Link;
import com.sones.facebook.model.feed.Note;
import com.sones.facebook.model.feed.Photo;
import com.sones.facebook.model.feed.StatusMessage;
import com.sones.facebook.model.feed.Video;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.sharedDto.facebook.view.posts.CommentViewDto;
import com.sones.sharedDto.facebook.view.posts.LinkViewDto;
import com.sones.sharedDto.facebook.view.posts.NoteViewDto;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;
import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public class FacebookPostViewService implements IFacebookPostViewService
{
	private final Logger _LOGGER; 

	private IStatusMessageDao statusDao;
	private IVideoDao videoDao;
	private ILinkDao linkDao;
	private INoteDao noteDao;
	private ICheckinDao checkinDao;
	private IPhotoDao photoDao;
	private ICommentDao commentDao;
	
	private DozerBeanMapper dozer;
	
	public FacebookPostViewService(IStatusMessageDao statusDao, IVideoDao videoDao, ILinkDao linkDao,
			INoteDao noteDao, ICheckinDao checkinDao, IPhotoDao photoDao, ICommentDao commentDao, DozerBeanMapper dozer)
	{
		_LOGGER = Logger.getLogger(FacebookPostViewService.class);
		this.statusDao = statusDao;
		this.videoDao = videoDao;
		this.linkDao = linkDao;
		this.noteDao = noteDao;
		this.checkinDao = checkinDao;
		this.photoDao = photoDao;
		this.commentDao = commentDao;
		this.dozer = dozer;
	}

	@Override
	public LinkViewDto getLink(String id) 
	{
		Link link = linkDao.GetById(id);
		checkNullability(link,"Link with id: " + id + " doesn't exist.");
		LinkViewDto linkViewDto = new LinkViewDto();
		dozer.map(link, linkViewDto);
		Set<CommentViewDto> comments = getComments(link);
		linkViewDto.setComments(comments);
		return linkViewDto;
	}

	@Override
	public NoteViewDto getNote(String id)
	{
		Note note = noteDao.GetById(id);
		checkNullability(note,"Note with id: " + id + " doesn't exist.");
		NoteViewDto noteViewDto = new NoteViewDto();
		dozer.map(note, noteViewDto);
		Set<CommentViewDto> comments = getComments(note);
		noteViewDto.setComments(comments);
		return noteViewDto;
	}

	@Override
	public PhotoViewDto getPhoto(String id) 
	{
		Photo photo = photoDao.GetById(id);
		checkNullability(photo,"Photo with id: " + id + " doesn't exist.");
		PhotoViewDto photoViewDto = new PhotoViewDto();
		dozer.map(photo, photoViewDto);
		Set<CommentViewDto> comments = getComments(photo);
		photoViewDto.setComments(comments);
		return photoViewDto;
	}

	@Override
	public StatusMessageViewDto getStatusMessage(String id)
	{
		StatusMessage statusMessage = statusDao.GetById(id);
		checkNullability(statusMessage,"Status message with id: " + id + " doesn't exist.");
		StatusMessageViewDto statusMessageViewDto = new StatusMessageViewDto();
		dozer.map(statusMessage, statusMessageViewDto);
		Set<CommentViewDto> comments = getComments(statusMessage);
		statusMessageViewDto.setComments(comments);
		return statusMessageViewDto;
	}

	@Override
	public VideoViewDto getVideo(String id)
	{
		Video video = videoDao.GetById(id);
		checkNullability(video,"Video with id: " + id + " doesn't exist.");
		VideoViewDto videoViewDto = new VideoViewDto();
		dozer.map(video, videoViewDto);
		Set<CommentViewDto> comments = getComments(video);
		videoViewDto.setComments(comments);
		return videoViewDto;
	}
	
	private Set<CommentViewDto> getComments(FacebookPost post)
	{
		_LOGGER.warn("Extracting comments");
		Iterable<Comment> comments = commentDao.getByFacebookPost(post);
		Set<CommentViewDto> commentsDto = new HashSet<CommentViewDto>();
		for(Comment comment : comments)
		{
			CommentViewDto commentViewDto = new CommentViewDto();
			dozer.map(comment, commentViewDto);
			commentsDto.add(commentViewDto);
		}
		return commentsDto;
	}
	
	private void checkNullability(Object object, String message) throws NullPointerException
	{
		if( object == null )
		{
			_LOGGER.error(message);
			throw new NullPointerException(message);
		}
	}

}
