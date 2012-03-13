package com.sones.sharedDto.facebook.GraphApi.Wall;

public class WallSourceFacebookPostIdDto 
{
	private WallFacebookPostCreateDto post;
	private WallSourceCreateDto source;
	
	public WallSourceFacebookPostIdDto() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(WallFacebookPostCreateDto post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public WallFacebookPostCreateDto getPost() {
		return post;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(WallSourceCreateDto source) {
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public WallSourceCreateDto getSource() {
		return source;
	}
}
