package com.sones.sharedDto.facebook.view.posts;

public class CommentViewDto 
{
	private UserViewDto user;
	private String value;
	
	public CommentViewDto()
	{
	}
	
	public CommentViewDto(UserViewDto user, String value)
	{
		this.setUser(user);
		this.setValue(value);
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
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
