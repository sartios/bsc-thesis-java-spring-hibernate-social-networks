package com.sones.businessLogic.Facebook.FeedManager;

public interface ILinkContent 
{
	public	String	GetName();
	public	void	SetName( final String name );
	
	public	String	GetCaption();
	public	void	SetCaption( final String caption );
	
	public	String	GetDescription();
	public	void	SetDescription( final String description );
	
	public	String	GetMessage();
	public	void	SetMessage( final String message );
	
	public	void	SetPhotoURL( final String photoURL );
	public	String	GetPhotoURL();
	
	public	void	SetLinkURL(final String linkURL);
	public	String	GetLinkURL();
	
	public String GetContentType();
}
