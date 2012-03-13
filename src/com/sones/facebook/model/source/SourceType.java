package com.sones.facebook.model.source;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;


/**
 * <b>Table:</b> FCBK.SOURCE_TYPES <br/><br/>
 * A facebook source.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name = SourceTypeConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class SourceType implements Serializable
{
	/**
	 * <b>Column:</b> SRCT_ID <br/><br/>
	 * The source ID.
	 */
	@Id
	@Column(name=SourceTypeConstants.PROPERTY_ID,length = SourceTypeConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> SRCT_TYPE
	 * The source type.
	 */
	@Column(name=SourceTypeConstants.PROPERTY_TYPE,length = SourceTypeConstants.LENGTH_TYPE)
	private	String	type;
	
	/**
	 * Initializes the object.
	 */
	public SourceType()
	{
	}
	
	/**
	 * Initializes the object.
	 * @param type
	 */
	public SourceType(String type)
	{
		setType(type);
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

	@Override
	public boolean equals(Object obj)
	{
		if((obj instanceof SourceType) == false)
		{
			return false;
		}
		SourceType t = (SourceType)obj;
		if((t.getId().equals(id))==true)
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return id.hashCode();
	}
	
	
}