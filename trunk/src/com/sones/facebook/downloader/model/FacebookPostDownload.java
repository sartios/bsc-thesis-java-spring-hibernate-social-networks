package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPost;

/**
 * <b>Table:</b> FCBK.FABOOK_POST_DOWNLOADS <br/><br/>
 * Represents the facebook post with the download instance in which was downloaded.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table( name = FacebookPostDownloadConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class FacebookPostDownload	implements	Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -784382494000971383L;
	
	/**
	 * <b>Column:</b> FCDW_ID <br/>
	 * <b>Column:</b> FCDW_ID <br/><br/>
	 * The composite id.
	 */
	@EmbeddedId
	private	FacebookPostDownloadId	id;
	
	/**
	 * Initializes the object.
	 */
	public	FacebookPostDownload()
	{
	}
	
	/**
	 * Initializes the object.
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
