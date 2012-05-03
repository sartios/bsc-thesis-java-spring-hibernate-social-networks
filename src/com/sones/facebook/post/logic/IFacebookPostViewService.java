package com.sones.facebook.post.logic;

import com.sones.sharedDto.facebook.view.posts.LinkViewDto;
import com.sones.sharedDto.facebook.view.posts.NoteViewDto;
import com.sones.sharedDto.facebook.view.posts.PhotoViewDto;
import com.sones.sharedDto.facebook.view.posts.StatusMessageViewDto;
import com.sones.sharedDto.facebook.view.posts.VideoViewDto;

public interface IFacebookPostViewService 
{
	public StatusMessageViewDto getStatusMessage(String id);
	public LinkViewDto getLink(String id);
	public VideoViewDto getVideo(String id);
	public PhotoViewDto getPhoto(String id);
	public NoteViewDto getNote(String id);
}
