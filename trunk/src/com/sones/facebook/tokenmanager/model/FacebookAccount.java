package com.sones.facebook.tokenmanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.userManager.model.ApplicationUser;

/**
 * <b>Table:</b> FCBK.FACEBOOK_ACCOUNTS <br/><br/>
 * Represents a facebook account.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table( name = FacebookAccountConstants.TABLE_NAME , schema = DatabaseConstants.FACEBOOK_SCHEMA )
public class FacebookAccount	implements	Serializable
{
	/**
	 * The serial UID
	 */
	private static final long serialVersionUID = -50507612669189761L;

	/**
	 * <b>Column:</b> FBAC_ID <br/><br/>
	 * The facebook account id is an assigned number.
	 */
	@Id
	@Column( name = FacebookAccountConstants.PROPERTY_ID , length = FacebookAccountConstants.LENGTH_ID )
	private	String	id;
	
	/**
	 * <b>Column:</b> APUS_ID <br/><br/>
	 * The application user to whom the account belongs to.
	 */
	@OneToOne( targetEntity = ApplicationUser.class )
	@JoinColumn( name = FacebookAccountConstants.PROPERTY_APPLICATION_USER )
	private	ApplicationUser	appUser;
	
	/**
	 * <b>Column:</b> FBAC_EMAIL<br/><br/>
	 * The email of the facebook account.
	 */
	@Column( name = FacebookAccountConstants.PROPERTY_EMAIL , length = FacebookAccountConstants.LENGTH_EMAIL )
	private	String	email;
	
	/**
	 * Initializes the object.
	 */
	public	FacebookAccount()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param appUser
	 */
	public	FacebookAccount( ApplicationUser appUser )
	{
		setAppUser( appUser );
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
	 * @param appUser the appUser to set
	 */
	public void setAppUser(ApplicationUser appUser) {
		this.appUser = appUser;
	}

	/**
	 * @return the appUser
	 */
	public ApplicationUser getAppUser() {
		return appUser;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
}
