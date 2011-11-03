package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Searcher.ISearchableContent;

public class VideoContent	extends	AbstractFacebookFeedContent	implements	IFacebookFeedContent, ISearchableContent
{

	private String _caption;
	
	public VideoContent()
	{
		_caption=new String();
	}
	
	public VideoContent(final String caption)
	{
		setCaption( caption );
	}

	public String getCaption() {
		return _caption;
	}

	public void setCaption(final String caption) {
		if(null!=caption){
			_caption = caption;
		}
		else if(null==caption){
			_caption="";
		}
	}

	@Override
	public String GetContentType() {
		return	"Video";
	}

	@Override
	public boolean isContaining( final String word ) 
	{
		if( IsNotNull( word ) && IsNotEmpty( word ) )
		{
			if( CaptionContains( word ) )
			{
				return	true;
			}
		}
		return	false;
	}

	private boolean CaptionContains(String word) 
	{
		return _caption.toLowerCase().contains( word );
	}

	private boolean IsNotNull( final Object object ) 
	{
		if( null == object )
		{
			return	false;
		}
		return true;
	}

	private boolean IsNotEmpty( final String word )
{
		if( true == word.isEmpty() )
		{
			return	false;
		}
		return true;
	}
	
}
