package com.sones.facebook.downloader.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.usermanager.model.ApplicationUser;

/**
 * <b>Table:</b> FCBK.FACEBOOK_DOWNLOADS <br/><br/>
 * Represents a facebook download.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=FacebookDownloadConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class FacebookDownload	implements	Serializable
{
	/**
	 * <b>Column:</b> FCDW_ID <br/><br/>
	 * The facebook download id.
	 */
	@Id
	@Column(name=FacebookDownloadConstants.PROPERTY_ID,length=FacebookDownloadConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> FCDW_APUS_ID <br/><br/>
	 * The id of the application user for who the download happened.
	 */
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=FacebookDownloadConstants.PROPERTY_APPLICATION_USER)
	private	ApplicationUser	appUser;
	
	/**
	 * <b>Column:</b> FCDW_DATE <br/><br/>
	 * The date of the download.
	 */
	@Column(name=FacebookDownloadConstants.PROPERTY_DATE)
	private	Date	date;
	
	/**
	 * Initiates the object.
	 */
	public	FacebookDownload()
	{
	}
	
	/**
	 * Initiates the object.
	 * @param appUser
	 * @param date
	 */
	public	FacebookDownload( ApplicationUser appUser, Date date )
	{
		setAppUser(appUser);
		setDate(date);
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
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
}
