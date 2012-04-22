package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.model.feed.FacebookPost;

/**
 * Represents the composite id of {@link FacebookPostDownload} model.
 * @author sartios.sones@gmail.com.
 *
 */
@Embeddable
public class FacebookPostDownloadId	implements	Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -246626440648326768L;

	/**
	 * <b>Column:</b> FCDW_ID<br/><br/>
	 * The donwload id.
	 */
	@ManyToOne( targetEntity = FacebookDownload.class )
	@JoinColumn( name = FacebookPostDownloadIdConstants.PROPERTY_FACEBOOK_DONWLOAD )
	private	FacebookDownload	download;
	
	/**
	 * <b>Column:</b> FCBK_POST_ID <br/><br/>
	 * The facebook post id.
	 */
	@ManyToOne( targetEntity = FacebookPost.class )
	@JoinColumn( name = FacebookPostDownloadIdConstants.PROPERTY_FACEBOOK_POST )
	private	FacebookPost	post;

	/**
	 * Initializes the object.
	 */
	public	FacebookPostDownloadId()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param download
	 * @param post
	 */
	public	FacebookPostDownloadId( FacebookDownload download, FacebookPost post )
	{
		setDownload( download );
		setPost( post );
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(FacebookDownload download) {
		this.download = download;
	}

	/**
	 * @return the download
	 */
	public FacebookDownload getDownload() {
		return download;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(FacebookPost post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public FacebookPost getPost() {
		return post;
	}
	
	@Override
	public boolean equals( Object obj ) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof FacebookPostDownloadId ) == false )
		{
			return	false;
		}
		FacebookPostDownloadId	id	=	(FacebookPostDownloadId)obj;
		if( ( post.getId().equals( id.getPost().getId() ) ) && ( download.getId().equals( id.getDownload().getId() ) ) )
		{
			return	true;
		}
		return	false;
	}

	@Override
	public int hashCode() 
	{
		return	( post.getId().hashCode() ^ download.getId().hashCode() );
	}
}
