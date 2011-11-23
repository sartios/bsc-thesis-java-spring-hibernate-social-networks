package com.sones.businessLogic.Facebook.JSON;

import net.sf.ezmorph.MorphException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.IFacebookFeedContent;
import com.sones.businessLogic.Facebook.FeedManager.LinkContent;
import com.sones.businessLogic.Facebook.FeedManager.NoteContent;
import com.sones.businessLogic.Facebook.FeedManager.PictureContent;
import com.sones.businessLogic.Facebook.FeedManager.StatusMessageContent;
import com.sones.businessLogic.Facebook.FeedManager.VideoContent;

public class FacebookFeedContentFactoryFromDynaBean	implements	IFacebookFeedContentFactory
{
	private	String	_type;
	private	PropertiesConfiguration _config;
	private	Logger	_logger;
	
	public	FacebookFeedContentFactoryFromDynaBean()
	{
		_logger	=	Logger.getLogger( FacebookFeedCommentsFactoryFromDynaBean.class );
		try 
		{
			_config	=	new	PropertiesConfiguration( "config/Facebook/Content/Facebook.Content.properties" );
		} 
		catch (ConfigurationException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public IFacebookFeedContent GetFeedContent(Object feedObject) {
		IFacebookFeedContent	content	=	null;
		
		if( null != feedObject)
		{
			DynaBean	feedContent	=	( DynaBean )feedObject;
			_type	=	feedContent.get( "type" ).toString();
			
			if( _type.equals( "status" ) )
			{
				content	=	GetStatusContent( feedContent );
			}
			if( _type.equals( "note" ) )
			{
				content	=	GetNoteContent( feedContent );
			}
			if( _type.equals( "link" ) )
			{
				content	=	GetLinkContent( feedContent );
			}
			if( _type.equals( "photo" ) )
			{
				content	=	GetPictureContent( feedContent );
			}
			if( _type.equals( "video" ) )
			{
				content	=	GetVideoContent( feedContent );
			}
		}
		return	content;
	}
	
	private	IFacebookFeedContent	GetStatusContent( final DynaBean object )
	{
		String	message	=	GetMessage( object );
		IFacebookFeedContent	statusContent	=	new	StatusMessageContent();
		( ( StatusMessageContent ) statusContent).SetMessage(message);
		String	type	=	_config.getString( "StatusMessage.Type" );
		( ( StatusMessageContent ) statusContent).SetType( type );

		return	statusContent;
	}
	
	private IFacebookFeedContent GetNoteContent( final DynaBean object )
	{
		String	message	=	GetMessage( object );
		String	subject	=	object.get( "subject" ).toString();
		return	new	NoteContent( subject, message );
	}
	
	private	IFacebookFeedContent	GetLinkContent( final DynaBean object )
	{
		String	name	=	"";
		String	url	=	"";
		String	linkURL	=	"";
		String	message	=	"";
		String	caption	=	"";
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
			caption = object.get("caption").toString();
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
		
		IFacebookFeedContent	feedContent	=	new	LinkContent();
		( ( LinkContent ) feedContent ).SetCaption( caption );
		( ( LinkContent ) feedContent ).SetDescription( description );
		( ( LinkContent ) feedContent ).SetLinkURL( linkURL );
		( ( LinkContent ) feedContent ).SetPhotoURL( url );
		( ( LinkContent ) feedContent ).SetName( name );
		( ( LinkContent ) feedContent ).SetMessage( message );
		return	feedContent;
	}
	
	private	IFacebookFeedContent	GetPictureContent(  final DynaBean object  )
	{
		String	caption	=	"";
		String	url	=	"";
		String	message	=	"";
		
		try
		{
			caption	=	object.get( "caption" ).toString();
		}
		catch (MorphException e) 
		{
		}
		try
		{
			url	=	object.get( "picture" ).toString();
		}
		catch (MorphException e) 
		{
		}
		try
		{
			message	=	object.get( "message" ).toString();
		}
		catch (MorphException e) 
		{
		}
		IFacebookFeedContent	feedContent	=	new	PictureContent();
		( ( PictureContent ) feedContent ).SetCaption( caption );
		( ( PictureContent ) feedContent ).SetMessage( message );	
		( ( PictureContent ) feedContent ).SetUrl( url );
		
		return	feedContent;
	}
	
	private IFacebookFeedContent GetVideoContent( final DynaBean object )
	{
		String	caption	=	"";
		try
		{
			caption	=	object.get("caption").toString();
		}
		catch (MorphException e) 
		{

		}
		return	new	VideoContent( caption );		
	}

	private	String	GetMessage( final DynaBean object )
	{
		return	object.get( "message" ).toString();
	}
}
