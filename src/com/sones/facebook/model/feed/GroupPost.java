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
import com.sones.facebook.model.source.Group;
import com.sones.facebook.model.source.GroupConstants;
import com.sones.facebook.model.source.User;

/**
 * <b>Table:</b> FCBK.GROUP_POSTS <br/><br/>
 * Represents a post that was published on a {@link Group}.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = GroupPostConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
@PrimaryKeyJoinColumn(name=FacebookPostConstants.PROPERTY_ID)
@Lazy(value=false)
public class GroupPost extends FacebookPost
{
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name=GroupConstants.PROPERTY_ID)
	private Group group;
	
	/**
	 * Initializes the object.
	 */
	public GroupPost()
	{
		super();
	}
	
	/**
	 * Initalizes the object.
	 * @param createdDate
	 * @param user
	 * @param comments
	 */
	public GroupPost(Date createdDate, User user, Set<Comment> comments)
	{
		super(createdDate,user,comments);
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(Group group) {
		this.group = group;
	}

	/**
	 * @return the group
	 */
	public Group getGroup() {
		return group;
	} 
}
