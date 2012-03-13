package com.sones.facebook.model.feed;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.source.Source;

/**
 * <b>Table:</b> FCBK.SOURCE_FACEBOOK_POSTS <br/><br/>
 * Represents a source post.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=SourceFacebookPostConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class SourceFacebookPost implements Serializable
{
	/**
	 * <b>Column:</b> SRFP_SOURCE_ID <br/>
	 * <b>Column:</b> SRFP_POST_ID <br/><br/>
	 * The composite id.
	 */
	@EmbeddedId
	private SourceFacebookPostId id;
	
	/**
	 * Initializes the object.
	 */
	public SourceFacebookPost()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param id
	 */
	public SourceFacebookPost(SourceFacebookPostId id)
	{
		setId(id);
	}
	
	/**
	 * Initializes the object.
	 * @param post
	 * @param source
	 */
	public SourceFacebookPost(FacebookPost post, Source source)
	{
		setId(new SourceFacebookPostId(post, source));
	}

	/**
	 * @param id the id to set
	 */
	public void setId(SourceFacebookPostId id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public SourceFacebookPostId getId() {
		return id;
	}
}
