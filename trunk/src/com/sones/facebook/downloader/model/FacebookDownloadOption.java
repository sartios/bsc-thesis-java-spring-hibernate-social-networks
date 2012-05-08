package com.sones.facebook.downloader.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Entity;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.tokenmanager.model.FacebookAccount;

@Entity
@Table(name = FacebookDownloadOptionConstants.TABLE_NAME, schema = DatabaseConstants.FACEBOOK_SCHEMA)
public class FacebookDownloadOption implements Serializable
{
	private static final long serialVersionUID = -6175619255540058286L;
	
	@Id
	@Column(name = FacebookDownloadOptionConstants.PROPERTY_ID, length = FacebookDownloadOptionConstants.LENGTH_ID)
	private String id;
	
	@ManyToOne(targetEntity = FacebookAccount.class)
	@JoinColumn(name = FacebookDownloadOptionConstants.PROPERTY_FACEBOOK_ACCOUNT)
	private FacebookAccount account;
	
	@Column(name = FacebookDownloadOptionConstants.PROPERTY_DONWLOAD_INTERVAL, length = FacebookDownloadOptionConstants.LENGTH_DONWLOAD_INTERVAL)
	private String downloadInterval;
	
	public FacebookDownloadOption()
	{}
	
	/**
	 * @param account
	 * @param downloadInterval
	 */
	public FacebookDownloadOption(FacebookAccount account,
			String downloadInterval) {
		super();
		this.account = account;
		this.downloadInterval = downloadInterval;
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
	 * @return the account
	 */
	public FacebookAccount getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(FacebookAccount account) {
		this.account = account;
	}

	/**
	 * @return the downloadInterval
	 */
	public String getDownloadInterval() {
		return downloadInterval;
	}

	/**
	 * @param downloadInterval the downloadInterval to set
	 */
	public void setDownloadInterval(String downloadInterval) {
		this.downloadInterval = downloadInterval;
	}
	
	
}
