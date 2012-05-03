package com.sones.sharedDto.facebook.view.posts;

public class NoteViewDto extends FacebookPostViewDto
{
	private String message;
	
	public NoteViewDto()
	{}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
