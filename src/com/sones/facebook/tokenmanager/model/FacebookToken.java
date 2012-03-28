package com.sones.facebook.tokenmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

/**
 * <b>Table:</b> FCBK.FACEBOOK_ACCESS_TOKENS <br/><br/>
 * Represents the facebook access token.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table( name = FacebookTokenConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class FacebookToken	implements	Serializable
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 5663714749276293442L;

	/**
	 * <b>Column:</b> FTOK_ID <br/><br/>
	 * The facebook access token id is an assigned string.
	 */
	@Id
	@Column( name = FacebookTokenConstants.PROPERTY_ID , length = FacebookTokenConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> FBAC_ID <br/><br/>
	 * The account for which the access token was generated.
	 */
	@OneToOne( targetEntity = FacebookAccount.class )
	@JoinColumn( name = FacebookTokenConstants.PROPERTY_FACEBOOK_ACCOUNT )
	private	FacebookAccount	account;
	
	/**
	 * <b>Column:</b> FTOK_VALUE <br/><br/>
	 * The value of the access token.
	 */
	@Column( name = FacebookTokenConstants.PROPERTY_VALUE, length = FacebookTokenConstants.LENGTH_VALUE )
	private	String	value;
	
	/**
	 * Initializes the object.
	 */
	public	FacebookToken()
	{
		
	}
	
	/**
	 * Initializs the object.
	 * @param value
	 * @param account
	 */
	public	FacebookToken( String value, FacebookAccount account )
	{
		setValue( value );
		setAccount( account );
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
	 * @param account the account to set
	 */
	public void setAccount(FacebookAccount account) {
		this.account = account;
	}

	/**
	 * @return the account
	 */
	public FacebookAccount getAccount() {
		return account;
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
