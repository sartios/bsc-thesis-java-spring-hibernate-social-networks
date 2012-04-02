package com.sones.facebook.publicsource.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.PUBLIC_SOURCE_CRITERIAS <br/><br/>
 * Represents a criterion for searching public sources.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table( name = CriteriaConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class Criteria	implements	Serializable
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -6363355117512832510L;

	/**
	 * <b>Column:</b> PSCR_ID <br/><br/>
	 * The id of criteria is an assigned string.
	 */
	@Id
	@Column( name = CriteriaConstants.PROPERTY_ID, length = CriteriaConstants.LENGTH_ID )
	private	String	id;
	
	/**
	 * <b>Column:</b> PSCR_VALUE <br/><br/>
	 * The value of criteria.
	 */
	@Column( name = CriteriaConstants.PROPERTY_VALUE, length = CriteriaConstants.LENGTH_VALUE )
	private	String	value;
	
	/**
	 * Initializes the object.
	 */
	public	Criteria()
	{}
	
	/**
	 * Initializes the object.
	 * @param value the value of criteria.
	 */
	public	Criteria( String value )
	{
		this.setValue(value);
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

	/**
	 * 
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return	false;
		}
		if( ( obj instanceof Criteria ) == false )
		{
			return	false;
		}
		
		Criteria	criteria	=	( Criteria )	obj;
		if( ( criteria.getId().equals( id ) ) && ( criteria.getValue().equals( value ) ) )
		{
			return	true;
		}
		return false;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() 
	{
		int	idHashCode	=	2;
		int	valueHashCode	=	super.hashCode();
		
		if( id != null )
		{
			idHashCode	=	id.hashCode();
		}
		
		if( value != null )
		{
			valueHashCode	=	value.hashCode();
		}
		
		return valueHashCode	^	idHashCode;
	}
	
	
	
}
