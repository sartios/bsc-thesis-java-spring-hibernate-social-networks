package com.sones.facebook.JsonHandler.Factory;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.sharedDto.facebook.GraphApi.Wall.WallCheckinCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallFacebookPostCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallLinkCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallNoteCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallPhotoCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallPlaceCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallStatusMessageCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallUserCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallVideoCreateDto;
import com.sones.sharedDto.facebook.GraphApi.Wall.WallCommentCreateDto;
import com.sones.sharedDto.facebook.feed.CommentIdDto;
import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;
import com.sones.sharedDto.facebook.place.PlaceIdDto;
import com.sones.sharedDto.facebook.source.UserIdDto;

public class WallFeedFactory implements IWallFeedFactory
{

	private	String	_type;
	private	final Logger _LOGGER;
	
	public WallFeedFactory()
	{
		_LOGGER	= Logger.getLogger(WallFeedFactory.class);
	}
	
	@Override
	public WallFacebookPostCreateDto getPost(Object feedObject) 
	{
		WallFacebookPostCreateDto	post	=	null;
		
		if( null != feedObject)
		{
			DynaBean	feedContent	=	( DynaBean )feedObject;
			_type	=	feedContent.get( "type" ).toString();
			
			if( _type.equals( "status" ) )
			{
				post	=	GetStatusContent( feedContent );
			}
			if( _type.equals( "note" ) )
			{
				post	=	GetNoteContent( feedContent );
			}
			if( _type.equals( "link" ) )
			{
				post	=	GetLinkContent( feedContent );
			}
			if( _type.equals( "photo" ) )
			{
				post	=	GetPictureContent( feedContent );
			}
			if( _type.equals( "video" ) )
			{
				post	=	GetVideoContent( feedContent );
			}
			if( _type.equals("checkin"))
			{
				post	=	GetCheckin( feedContent );
			}
		}
		return	post;
	}
	
	private	WallCheckinCreateDto	GetCheckin( final DynaBean object )
	{
		_LOGGER.warn("Creating checkin object");
		WallCheckinCreateDto checkin = new WallCheckinCreateDto();
		String message = GetMessage(object);
		Set<WallCommentCreateDto> comments = getComments(object);
		Date date = getDate(object);
		WallUserCreateDto userId = getUser(object);
		FacebookPostIdDto postId = getId(object);
		WallPlaceCreateDto place = new WallPlaceCreateDto();
		try
		{
			String id = ( ( DynaBean )( object.get( "place" ) ) ).get( "id" ).toString() ;
			String name = "" ;
			String latitude = "" ;
			String longitude = "" ;
			try
			{
				name = ( ( DynaBean )( object.get( "place" ) ) ).get( "name" ).toString() ;
			}
			catch(MorphException ex)
			{
			}
			try
			{
				latitude = ( ( DynaBean )( object.get( "place" ) ) ).get( "latitude" ).toString() ;
			}
			catch(MorphException ex)
			{
			}
			try
			{
				longitude = ( ( DynaBean )( object.get( "place" ) ) ).get( "longitude" ).toString() ;
			}
			catch(MorphException ex)
			{	
			}
			PlaceIdDto placeId = new PlaceIdDto();
			placeId.setId(id);
			place.setId(placeId);
			place.setLatitude(latitude);
			place.setLongitude(longitude);
			place.setName(name);
		}
		catch (MorphException e)
		{
		}
		checkin.setId(postId);
		checkin.setComments(comments);
		checkin.setDate(date);
		checkin.setPlace(place);
		checkin.setUser(userId);
		checkin.setMessage(message);
		checkin.setType("Checkin");
		return checkin;
	}
	
	private	WallStatusMessageCreateDto	GetStatusContent( final DynaBean object )
	{
		_LOGGER.warn("Creating status message object.");
		WallStatusMessageCreateDto statusMessage = new WallStatusMessageCreateDto();
		String	message	=	GetMessage( object );
		Set<WallCommentCreateDto> comments = getComments(object);
		Date date = getDate(object);
		WallUserCreateDto user = getUser(object);
		FacebookPostIdDto id = getId(object);
		statusMessage.setMessage(message);
		statusMessage.setComments(comments);
		statusMessage.setDate(date);
		statusMessage.setId(id);
		statusMessage.setUser(user);
		statusMessage.setType("StatusMessage");
		return	statusMessage;
	}
	
	private WallNoteCreateDto GetNoteContent( final DynaBean object )
	{
		_LOGGER.warn("Creating note object.");
		WallNoteCreateDto note = new WallNoteCreateDto();
		String	message	=	GetMessage( object );
		String	subject	=	object.get( "subject" ).toString();
		note.setMessage(message);
		note.setSubject(subject);
		note.setComments(getComments(object));
		note.setDate(getDate(object));
		note.setId(getId(object));
		note.setUser(getUser(object));
		note.setType("Note");
		
		return note;

	}
	
	private	WallLinkCreateDto	GetLinkContent( final DynaBean object )
	{
		_LOGGER.warn("Creating link object.");
		String	name	=	"";
		String	url	=	"";
		String	linkURL	=	"";
		String	message	=	"";
		String	icon	=	"";
		String	description =	"";
		try
		{
			name = object.get("name").toString();
		}
		catch(MorphException e)
		{
		}
		try
		{
			message = object.get("message").toString();
		}
		catch(MorphException ex)
		{
		}		
		try
		{
			description = object.get("description").toString();
		}
		catch(MorphException ex)
		{
		}
		try
		{
			url=object.get("picture").toString();
		}
		catch (MorphException ex) 
		{
		}
		try
		{
			linkURL=object.get("link").toString();
		}
		catch (MorphException ex) 
		{
		}
		try
		{
			icon=object.get("icon").toString();
		}
		catch (MorphException ex) 
		{
		}
		
		WallLinkCreateDto	link	=	new	WallLinkCreateDto();
		link.setDescription( description );
		link.setLink( linkURL );
		link.setPicture( url );
		link.setName( name );
		link.setMessage( message );
		link.setType("Link");
		link.setComments(getComments(object));
		link.setDate(getDate(object));
		link.setId(getId(object));
		link.setUser(getUser(object));
		link.setIcon(icon);
		return	link;
	}
	
	private	WallPhotoCreateDto	GetPictureContent(  final DynaBean object  )
	{
		_LOGGER.warn("Creating photo object.");
		String	name	=	"";
		String	icon	=	"";
		String	picture	=	"";
		String	link	=	"";
		
		try
		{
			name	=	object.get( "name" ).toString();
		}
		catch (MorphException e) 
		{
		}
		try
		{
			picture	=	object.get( "picture" ).toString();
		}
		catch (MorphException e) 
		{
		}
		try
		{
			link	=	object.get( "link" ).toString();
		}
		catch (MorphException e) 
		{
		}
		try
		{
			icon	=	object.get( "icon" ).toString();
		}
		catch (MorphException e) 
		{
		}
		WallPhotoCreateDto	photo	=	new	WallPhotoCreateDto();
		photo.setPicture( picture );
		photo.setType("Photo");
		photo.setComments(getComments(object));
		photo.setDate(getDate(object));
		photo.setId(getId(object));
		photo.setUser(getUser(object));
		photo.setLink(link);
		photo.setIcon(icon);
		photo.setName(name);
		return	photo;
	}
	
	private WallVideoCreateDto GetVideoContent( final DynaBean object )
	{
		_LOGGER.warn("Creating video object.");
		String	name	=	"";
		String 	description = "";
		String	picture	=	"";
		try
		{
			name	=	object.get("name").toString();
		}
		catch (MorphException e) 
		{

		}
		try
		{
			description	=	object.get("description").toString();
		}
		catch (MorphException e) 
		{

		}
		try
		{
			picture	=	object.get("picture").toString();
		}
		catch (MorphException e) 
		{

		}
		WallVideoCreateDto video =	new	WallVideoCreateDto();
		video.setType("Video");
		video.setComments(getComments(object));
		video.setDate(getDate(object));
		video.setId(getId(object));
		video.setUser(getUser(object));
		video.setName(name);
		video.setDescription(description);
		video.setPicture(picture);
		return video;
	}

	private	String	GetMessage( final DynaBean object )
	{
		return	object.get( "message" ).toString();
	}
	
	private Set<WallCommentCreateDto> getComments( final DynaBean object )
	{
		Set<WallCommentCreateDto>	comments = new HashSet<WallCommentCreateDto>();
		WallFacebookPostCreateDto	post	=	getFacebookPost(object);
		try
		{
			ArrayList commentArray	=	(ArrayList) (((DynaBean) ((DynaBean)object).get("comments")).get("data"));
			for(int i=0;i<commentArray.size();i++)
			{
				String commentID	=	( ( DynaBean )commentArray.get(i)).get( "id" ).toString();
				String message = ( ( DynaBean )commentArray.get(i)).get( "message" ).toString();
				WallUserCreateDto user = getUser(( DynaBean )commentArray.get(i));
				WallCommentCreateDto comment = new WallCommentCreateDto();
				CommentIdDto id = new CommentIdDto();
				id.setId(commentID);
				comment.setId(id);
				comment.setMessage(message);
				comment.setUser(user);
				comment.setPost(post);
				comments.add(comment);
			}
		}
		catch (MorphException e) 
		{
			// TODO: handle exception
		}
		return	comments;
	}
	
	private	WallFacebookPostCreateDto getFacebookPost( final DynaBean object )
	{
		FacebookPostIdDto	id	=	getId(object);
		WallFacebookPostCreateDto	post	=	new	WallFacebookPostCreateDto();
		post.setId(id);
		return	post;
	}
	
	private Date getDate( final DynaBean object )
	{
		return null;
	}
	
	private FacebookPostIdDto getId( final DynaBean object )
	{
		FacebookPostIdDto id = new FacebookPostIdDto();
		id.setId(object.get( "id" ).toString());
		return	id;
	}
	
	private	WallUserCreateDto getUser( final DynaBean object )
	{
		WallUserCreateDto user = new WallUserCreateDto();
		String id = ( ( DynaBean )( object.get( "from" ) ) ).get( "id" ).toString();
		String username = "";
		try
		{
			username = ( ( DynaBean )( object.get( "from" ) ) ).get( "username" ).toString();
		}
		catch (MorphException e)
		{
			// TODO: handle exception
		}
		UserIdDto userId = new UserIdDto();
		userId.setId(id);
		user.setId(userId);
		user.setUsername(username);
		return user;
	}

}
