package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sones.dao.DatabaseConstants;
import com.sones.model.ApplicationUser;

/**
 * <b>Table:</b> FCBK.KEYWORD_SEARCHES <br/><br/>
 * Represents a keyword search.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=KeywordSearchConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class KeywordSearch  implements Serializable
{
	/**
	 * <b>Column:</b> KESE_ID <br/><br/>
	 * The keyword search id is an assigned number.
	 */
	@Id
	@Column(name=KeywordSearchConstants.PROPERTY_ID,length=KeywordSearchConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> KESE_APUS_ID <br/><br/>
	 * The application user id for who the search run.
	 */
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=KeywordSearchConstants.PROPERTY_APPLICATION_USER)
	private	ApplicationUser	user;
	
	/**
	 * <b>Column:</b> FKRE_KESE_ID <br/><br/>
	 * The results that found in the current search.
	 */
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name=KeywordSearchConstants.PROPERTY_RESULT)
	private	Set<FacebookPostKeywordResult>	results;

	/**
	 * <b>Column:</b> KESE_DATE <br/><br/>
	 * The date of the search.
	 */
	@Column(name=KeywordSearchConstants.PROPERTY_DATE)
	private	Date	date;
	
	/**
	 * Initializes the object.
	 */
	public KeywordSearch()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param user
	 * @param result
	 * @param date
	 */
	public KeywordSearch(ApplicationUser user, Set<FacebookPostKeywordResult> results, Date date)
	{
		setUser(user);
		setResults(results);
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
	 * @param user the user to set
	 */
	public void setUser(ApplicationUser user) {
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public ApplicationUser getUser() {
		return user;
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

	/**
	 * @param results the results to set
	 */
	public void setResults(Set<FacebookPostKeywordResult> results) {
		this.results = results;
	}

	/**
	 * @return the results
	 */
	public Set<FacebookPostKeywordResult> getResults() {
		return results;
	}
}
