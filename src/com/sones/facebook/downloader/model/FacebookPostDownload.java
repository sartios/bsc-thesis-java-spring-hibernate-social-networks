package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;

import com.sones.facebook.model.feed.FacebookPost;

/**
 * <b>Table:</b> FCBK.FABOOK_POST_DOWNLOADS <br/><br/>
 * Represents the facebook post with the download instance in which was downloaded.
 * @author sartios.sones@gmail.com.
 *
 */
public class FacebookPostDownload	implements	Serializable
{
	/**
	 * <b>Column:</b> FCDW_ID <br/>
	 * <b>Column:</b> FCDW_ID <br/><br/>
	 * The composite id.
	 */
	@EmbeddedId
	private	FacebookPostDownloadId	id;
	
	/**
	 * 
	 */
	public	FacebookPostDownload()
	{
	}
	
	/**
	 * 
	 * @param post
	 * @param download
	 */
	public	FacebookPostDownload( FacebookPost post, FacebookDownload download )
	{
		setId( new FacebookPostDownloadId( download, post ) );
	}

	/**
	 * @param id the id to set
	 */
	public void setId(FacebookPostDownloadId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public FacebookPostDownloadId getId() {
		return id;
	}
}
