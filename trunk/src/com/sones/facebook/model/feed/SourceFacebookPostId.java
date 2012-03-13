package com.sones.facebook.model.feed;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import com.sones.facebook.model.source.Source;

/**
 * Composite id for {@link SourceFacebookPost} entity.
 * @author sartios.sones@gmail.com.
 *
 */
@Embeddable
public class SourceFacebookPostId implements Serializable
{
	/**
	 * <b>Column:</b> SRFP_POST_ID <br/><br/>
	 * The post id.
	 */
	@ManyToOne(targetEntity=FacebookPost.class)
	@JoinColumn(name=SourceFacebookPostConstants.PROPERTY_POST_ID)
	private FacebookPost	post;
	
	/**
	 * <b>Column:</b> SRFP_SOURCE_ID <br/><br/>
	 * The source id.
	 */
	@ManyToOne(targetEntity=Source.class)
	@JoinColumn(name=SourceFacebookPostConstants.PROPERTY_SOURCE_ID)
	private Source source;
	
	/**
	 * Initializes the object.
	 */
	public SourceFacebookPostId()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param post
	 * @param source
	 */
	public SourceFacebookPostId(FacebookPost post, Source source)
	{
		setPost(post);
		setSource(source);
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(FacebookPost postId) {
		this.post = postId;
	}

	/**
	 * @return the post
	 */
	public FacebookPost getPost() {
		return post;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(Source sourceId) {
		this.source = sourceId;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	@Override
	public boolean equals(Object obj)
	{
		if((obj instanceof SourceFacebookPostId) == false)
		{
			return false;
		}
		SourceFacebookPostId id = (SourceFacebookPostId)obj;
		if((post.getId().equals(id.getPost().getId()))&&(source.getId().equals(id.getSource().getId())))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return post.getId().hashCode()^source.getId().hashCode();
	}	
}
