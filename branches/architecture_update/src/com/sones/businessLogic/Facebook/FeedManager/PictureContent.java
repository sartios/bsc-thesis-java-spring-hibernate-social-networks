package com.sones.businessLogic.Facebook.FeedManager;

import java.io.EOFException;

import com.sones.businessLogic.Searcher.ISearchableContent;

public class PictureContent	extends	AbstractFacebookFeedContent	implements	IFacebookFeedContent, ISearchableContent
{
	private	String _caption;
	private String _url;
	private String _message;
	
	public PictureContent()
	{
		_caption	=	new	String();
		_url	=	new	String();
		_message	=	new	String();
	}
	
	public PictureContent( final String caption )
	{
		_caption = caption;
	}

	public String GetCaption() 
	{
		return _caption;
	}

	public void SetCaption(final String caption) 
	{
		if(null!=caption){
			_caption = caption;
		}
		else if(null==caption){
			_caption="";
		}
	}
	
	public String GetUrl()
	{
		return _url;
	}
	
	public void SetUrl(final String url)
	{
		if(null!=url){
			_url = url;
		}
		else if(null==url){
			_url="";
		}
	}
	
	public void SetMessage(final String message){
		if(null!=message){
			_message = message;
		}
		else if(null==message){
			_message="";
		}
	}
	
	public String GetMessage(){
		return _message;
	}

	@Override
	public String GetContentType() {
		return	"Picture";
	}

	@Override
	public boolean isContaining(String word) 
	{
		if( IsNotNull( word ) && ( false == IsEmpty( word ) ) )
		{
			if( MessageContains( word ) )
			{
				return	true;
			}
			else if( CaptionContains( word ) )
			{
				return	true;
			}
		}
		return false;
	}

	private boolean CaptionContains( final String word ) 
	{
		return	word.toLowerCase().contains( word );
	}

	private boolean MessageContains( final String word ) 
	{
		return	word.toLowerCase().contains( word );
	}

	private boolean IsEmpty( final String word )
	{
		return word.isEmpty();
	}

	private boolean IsNotNull( final Object object ) 
	{
		if( null == object )
		{
			return	false;
		}
		return	true;
	}
}
