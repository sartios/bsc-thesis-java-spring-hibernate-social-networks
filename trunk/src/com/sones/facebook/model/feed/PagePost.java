package com.sones.facebook.model.feed;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.comment.Comment;
import com.sones.facebook.model.source.Page;
import com.sones.facebook.model.source.PageConstants;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.EVENT_POSTS <br/><br/>
 * Represents a post that was published on an {@link Page}.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = PagePostConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class PagePost extends FacebookPost
{
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name=PageConstants.PROPERTY_ID)
	private Page page;
	
	/**
	 * Initializes the object.
	 */
	public PagePost()
	{
		super();
	}
	
	/**
	 * Initalizes the object.
	 * @param createdDate
	 * @param user
	 * @param comments
	 */
	public PagePost(Date createdDate, User user, Set<Comment> comments)
	{
		super(createdDate,user,comments);
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page page) {
		this.page = page;
	}

	/**
	 * @return the page
	 */
	public Page getPage() {
		return page;
	} 
}
