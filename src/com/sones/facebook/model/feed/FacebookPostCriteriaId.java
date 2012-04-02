package com.sones.facebook.model.feed;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sones.facebook.publicsource.model.Criteria;

@Embeddable
public class FacebookPostCriteriaId	implements	Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8895638598043971874L;

	@ManyToOne( targetEntity = FacebookPost.class )
	@JoinColumn( name = FacebookPostCriteriaConstants.PROPERTY_FACEBOOK_POST )
	private	FacebookPost	post;
	
	@ManyToOne( targetEntity = Criteria.class )
	@JoinColumn( name = FacebookPostCriteriaConstants.PROPERTY_CRITERIA )
	private	Criteria	criteria;
	
	public	FacebookPostCriteriaId()
	{}
	
	public	FacebookPostCriteriaId( FacebookPost post, Criteria criteria )
	{
		setPost( post );
		setCriteria( criteria );
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

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * @return the criteria
	 */
	public Criteria getCriteria() {
		return criteria;
	}
}
