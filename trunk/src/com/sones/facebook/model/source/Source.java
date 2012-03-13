package com.sones.facebook.model.source;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Lazy;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.SOURCES <br/><br/>
 * A facebook source.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=SourceConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
@Lazy(value=false)
public class Source implements Serializable
{
	/**
	 * <b>Column:</b> SOUR_ID <br/><br/>
	 * The source ID.
	 */
	@Id
	@Column(name=SourceConstants.PROPERTY_ID,length = SourceConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> SOUR_TYPE_ID
	 * The source type id.
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name=SourceTypeConstants.PROPERTY_ID)
	private	SourceType	type;
	
	public Source()
	{
	}
	
	public Source(SourceType type)
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
	public void setType(SourceType type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public SourceType getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if((obj instanceof Source) == false)
		{
			return false;
		}
		Source s = (Source)obj;
		if(s.getId().equals(id))
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
