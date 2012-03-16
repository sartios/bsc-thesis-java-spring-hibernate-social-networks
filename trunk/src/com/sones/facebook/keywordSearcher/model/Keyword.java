package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

/**
 * <b>Table:</b> FCBK.KEYWORDS <br/><br/>
 * Represents a keyword.
 * @author sartios.sones@gmail.com.
 *
 */
public class Keyword  implements Serializable
{
	/**
	 * <b>Column:</b> KEYW_ID <br/><br/>
	 * The id of keyword is an assigned number.
	 */
	private	String	id;
	
	/**
	 * <b>Column:</b> KEYW_VALUE <br/><br/>
	 * The value of the keyword is an assigned string.
	 */
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
}
