package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.KEYWORDS <br/><br/>
 * Represents a keyword.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=KeywordConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class Keyword  implements Serializable
{
	/**
	 * <b>Column:</b> KEYW_ID <br/><br/>
	 * The id of keyword is an assigned number.
	 */
	@Id
	@Column(name=KeywordConstants.PROPERTY_ID,length=KeywordConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> KEYW_VALUE <br/><br/>
	 * The value of the keyword is an assigned string.
	 */
	@Column(name=KeywordConstants.PROPERTY_VALUE,length=KeywordConstants.LENGTH_VALUE)
	private	String	value;
	
	/**
	 * Initializes the object.
	 */
	public Keyword()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param value
	 */
	public Keyword(String value)
	{
		setValue(value);
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

	@Override
	public boolean equals(Object obj)
	{
		if( obj == null )
		{
			return	false;
		}
		if( (obj instanceof Keyword) == false )
		{
			return false;
		}
		Keyword keyword = (Keyword)obj;
		if(keyword.getId().equals(id))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return this.id.hashCode() ^ 5;
	}
	
	
}
