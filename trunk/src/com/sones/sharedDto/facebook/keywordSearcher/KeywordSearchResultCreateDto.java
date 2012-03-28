package com.sones.sharedDto.facebook.keywordSearcher;

import com.sones.facebook.keywordSearcher.model.FacebookPostKeywordResult;
import com.sones.sharedDto.facebook.keywordSearcher.feeds.FacebookPostSearchDto;

/**
 * Create dto for {@link FacebookPostKeywordResult} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordSearchResultCreateDto
{
	/**
	 * The facebook post.
	 */
	private	FacebookPostSearchDto	post;
	
	/**
	 * The keyword.
	 */
	private	KeywordSearchDto	keyword;
	
	/**
	 * Initializes the object.
	 */
	public KeywordSearchResultCreateDto()
	{
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(FacebookPostSearchDto post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public FacebookPostSearchDto getPost() {
		return post;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyword(KeywordSearchDto keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the keyword
	 */
	public KeywordSearchDto getKeyword() {
		return keyword;
	}
}
