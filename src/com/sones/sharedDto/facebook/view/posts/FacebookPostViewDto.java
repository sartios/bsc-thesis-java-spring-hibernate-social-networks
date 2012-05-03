package com.sones.sharedDto.facebook.view.posts;

import java.util.Set;

public class FacebookPostViewDto 
{
	private Set<CommentViewDto> comments;
	private UserViewDto user;
	
	public FacebookPostViewDto()
	{}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(Set<CommentViewDto> comments) {
		this.comments = comments;
	}

	/**
	 * @return the comments
	 */
	public Set<CommentViewDto> getComments() {
		return comments;
	}
	
	public void addComment(CommentViewDto comment)
	{
		comments.add(comment);
	}
	
	/**
	 * @param user the user to set
	 */
	public void setUser(UserViewDto user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public UserViewDto getUser() {
		return user;
	}
}
