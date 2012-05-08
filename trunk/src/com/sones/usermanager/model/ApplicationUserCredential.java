package com.sones.usermanager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;

@Entity
@Table(name=ApplicationUserCredentialConstants.TABLE_NAME, schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class ApplicationUserCredential implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5245964570026773943L;

	@Id
	@Column(name=ApplicationUserCredentialConstants.PROPERTY_ID,length=ApplicationUserCredentialConstants.LENGTH_ID)
	private String id;
	
	@Column(name=ApplicationUserCredentialConstants.PROPERTY_NAME, length=ApplicationUserCredentialConstants.LENGTH_NAME)
	private String name;
	
	@Column(name=ApplicationUserCredentialConstants.PROPERTY_USERNAME, length=ApplicationUserCredentialConstants.LENGTH_USERNAME)
	private String username;
	
	@Column(name=ApplicationUserCredentialConstants.PROPERTY_PASSWORD, length=ApplicationUserCredentialConstants.LENGTH_PASSWORD)
	private String password;

	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=ApplicationUserCredentialConstants.PROPERTY_APPLICATION_USER)
	private ApplicationUser appUser;
	
	public ApplicationUserCredential()
	{}

	/**
	 * @param name
	 * @param username
	 * @param password
	 * @param appUser
	 */
	public ApplicationUserCredential(String name, String username,
			String password, ApplicationUser appUser) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.appUser = appUser;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the appUser
	 */
	public ApplicationUser getAppUser() {
		return appUser;
	}

	/**
	 * @param appUser the appUser to set
	 */
	public void setAppUser(ApplicationUser appUser) {
		this.appUser = appUser;
	}
	
	
}
