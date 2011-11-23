package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Searcher.ISearchableContent;

public class LinkContent	extends	AbstractFacebookFeedContent	implements	ILinkContent,IFacebookFeedContent, ISearchableContent
{
	private	String	_name;
	private String	_caption;
	private String 	_description;
	private String 	_message;
	private String 	_photoURL;
	private String	_linkURL;
	
	public LinkContent()
	{
		_name	=	new	String( "" );
		_caption	=	new	String( "" );
		_description	=	new	String( "" );
		_message	=	new	String( "" );
		_photoURL	=	new	String( "" );
		_linkURL	=	new	String( "" );
	}
	
	public LinkContent( final String name, final String caption, final String description, final String message )
	{
		SetName( name );
		SetCaption( caption );
		SetDescription( description );
		SetMessage( message );
	}
	
	public LinkContent(final String name,final String feedId)
	{
		SetName( name );
	}
	
	public	String	GetName()
	{
		return _name;
	}

	public	void	SetName( final String name )
{
		if( null != name )
		{
			_name	=	name;
		}
		else if( null == name )
		{
			_name	=	"";
		}
	}

	public	String	GetCaption()
	{
		return	_caption;
	}

	public	void	SetCaption( final String caption ) 
	{
		if( null != caption )
		{
			_caption	=	caption;
		}
		else if( null == caption )
		{
			_caption	=	"";
		}
	}

	public	String	GetDescription()
	{
		return	_description;
	}

	public	void	SetDescription( final String description ) 
	{
		if( null != description )
		{
			_description	=	description;
		}
		else if( null == description )
		{
			_description	=	"";
		}
	}

	public	String	GetMessage()
	{
		return	_message;
	}

	public	void	SetMessage( final String message ) 
	{
		if( null != message )
		{
			_message	=	message;
		}
		else if(null==message){
			_message="";
		}
	}

	public	void	SetPhotoURL( final String photoURL ) 
	{
		if( null != photoURL )
		{
			_photoURL = photoURL;
		}
		else if(null==photoURL){
			_photoURL="";
		}
	}

	public String GetPhotoURL()
	{
		return _photoURL;
	}

	public void SetLinkURL(final String linkURL)
	{
		if( null != linkURL )
		{
			_linkURL	=	linkURL;
		}
		else if( null == linkURL )
		{
			_linkURL	=	"";
		}	
	}

	public String GetLinkURL() 
	{
		return	_linkURL;
	}

	@Override
	public String GetContentType() {
		return	"Link";
	}

	@Override
	public boolean isContaining( final String word ) 
	{
		if( isNotNull( word ) && ( false == isEmpty( word ) ) )
		{
			if( NameContains( word ) )
			{
				return	true;
			}
			else if( CaptionContains( word ) )
			{
				return	true;
			}
			else if( DescriptionContains( word ) )
			{
				return	true;
			}
			else if( MessageContains( word ) )
			{
				return	true;
			}
		}
		return false;
	}

	private boolean MessageContains( final String word )
	{
		return _message.toLowerCase().contains( word );
	}

	private boolean DescriptionContains( final String word )
	{
		return	_description.toLowerCase().contains( word );
	}

	private boolean CaptionContains( final String word )
	{
		return	_caption.toLowerCase().contains( word );
	}

	private boolean NameContains( final String word ) 
	{
		return	_name.toLowerCase().contains( word );
	}

	private boolean isEmpty( final String word ) 
	{
		return	word.isEmpty();
	}

	private boolean isNotNull( final Object object )
	{
		if( null == object )
		{
			return	false;
		}
		return	true;
	}
}
