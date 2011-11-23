package com.sones.businessLogic.Facebook.FeedManager;

/**
 * Provides methods for accessing Facebook picture content
 * @author Sones
 *
 */
public interface IFacebookPicture 
{

	public String GetCaption();
	public void SetCaption(final String caption);
	
	public String GetUrl();
	public void SetUrl(final String url);
	
	public void SetMessage(final String message);
	public String GetMessage();
}
