package com.sones.sharedDto.facebook.keywordSearcher;

import com.sones.facebook.keywordSearcher.model.Keyword;

/**
 * Search dto for {@link Keyword} model.
 * @author sartios.sones@gmail.com.
 *
 */
public class KeywordSearchDto 
{
	/**
	 * The keyword id.
	 */
	private	String	id;
	
	/**
	 * The keyword value.
	 */
	private	String	value;
	
	/**
	 * Initializes the object.
	 */
	public KeywordSearchDto()
	{
		
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
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
	
	
}
