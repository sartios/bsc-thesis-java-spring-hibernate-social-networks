package com.sones.sharedDto.facebook.result;

import java.util.HashSet;
import java.util.Set;

import com.sones.sharedDto.facebook.feed.FacebookPostIdDto;

public class FacebookResultViewDto 
{
	private String descripton;
	private Set<String> keywordValues;
	private String type;
	private FacebookPostIdDto id;
	
	/**
	 * 
	 */
	public FacebookResultViewDto()
	{
		keywordValues = new HashSet<String>();
	}
	
	/**
	 * @param descripton the descripton to set
	 */
	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}
	/**
	 * @return the descripton
	 */
	public String getDescripton() {
		return descripton;
	}
	/**
	 * @param keywordValues the keywordValues to set
	 */
	public void setKeywordValues(Set<String> keywordValues) {
		this.keywordValues = keywordValues;
	}
	/**
	 * @return the keywordValues
	 */
	public Set<String> getKeywordValues() {
		return keywordValues;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(FacebookPostIdDto id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public FacebookPostIdDto getId() {
		return id;
	}
	
	/**
	 * 
	 * @param value
	 */
	public void addKeywordValue(String value)
	{
		keywordValues.add(value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if(obj == null)
		{
			return false;
		}
		if( (obj instanceof FacebookResultViewDto) == false )
		{
			return false;
		}
		FacebookResultViewDto other = (FacebookResultViewDto) obj;
		if( other.getId() == null )
		{
			if(this.id == null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		if( other.getId().getId() == this.id.getId() )
		{
			return true;
		}
		return false;
	}
	
	
}
