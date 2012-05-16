package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.usermanager.model.ApplicationUser;

@Entity
@Table(name=KeywordSearchOptionConstants.TABLE_NAME, schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class KeywordSearchOption implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6044355876325748646L;

	@Id
	@Column(name=KeywordSearchOptionConstants.PROPERTY_ID, length=KeywordSearchOptionConstants.LENGTH_ID)
	private String id;
	
	@Column(name=KeywordSearchOptionConstants.PROPERTY_INTERVAL, length=KeywordSearchOptionConstants.LENGTH_INTERVAL)
	private String interval;
	
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=KeywordSearchOptionConstants.PROPERTY_APPLICATION_USER)
	private ApplicationUser appUser;

	/**
	 * 
	 */
	public KeywordSearchOption() {
		super();
	}



	/**
	 * @param interval
	 * @param appUser
	 */
	public KeywordSearchOption(String interval, ApplicationUser appUser) {
		super();
		this.interval = interval;
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
	 * @return the interval
	 */
	public String getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(String interval) {
		this.interval = interval;
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
