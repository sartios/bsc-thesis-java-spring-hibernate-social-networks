package com.sones.facebook.keywordSearcher.model;

import java.io.Serializable;

import javax.persistence.*;

import com.sones.dao.DatabaseConstants;
import com.sones.facebook.model.feed.FacebookPost;
import com.sones.userManager.model.ApplicationUser;

/**
 * <b>Table:</b> FCBK.FACEBOOK_POST_KEYWORD_RESULTS<br/><br/>
 * Represents a result from keyword search into facebook feeds.
 * @author sartios.sones@gmail.com.
 *
 */
@Entity
@Table(name=FacebookPostKeywordResultConstants.TABLE_NAME,schema=DatabaseConstants.FACEBOOK_SCHEMA)
public class FacebookPostKeywordResult implements Serializable
{
	/**
	 * <b>Column:</b> FKRE_ID <br/><br/>
	 * The id of a Facebook Keyword Result is an assigned string.
	 */
	@Id
	@Column(name=FacebookPostKeywordResultConstants.PROPERTY_ID,length=FacebookPostKeywordResultConstants.LENGTH_ID)
	private	String	id;
	
	/**
	 * <b>Column:</b> FKRE_KEYW_ID <br/><br/>
	 * The keyword that found in the feed.
	 */
	@ManyToOne(targetEntity=Keyword.class)
	@JoinColumn(name=FacebookPostKeywordResultConstants.PROPERTY_KEYWORD)
	private	Keyword	keyword;

	/**
	 * <b>Column:</b> FKRE_FAPO_ID <br/><br/>
	 * The facebook post into which the keyword found.
	 */
	@ManyToOne(targetEntity=FacebookPost.class)
	@JoinColumn(name=FacebookPostKeywordResultConstants.PROPERTY_FACEBOOK_POST)
	private	FacebookPost	post;
	
	/**
	 * <b>Column:</b> FKRE_APUS_ID <br/><br/>
	 * The application user id from who the search run.
	 */
	@ManyToOne(targetEntity=ApplicationUser.class)
	@JoinColumn(name=FacebookPostKeywordResultConstants.PROPERTY_APPLICATION_USER)
	private	ApplicationUser	user;
	
	/**
	 * <b>Column:</b> FKRE_KESE_ID <br/><br/>
	 * The id of the keyword search that the result found.
	 */
	@OneToOne(targetEntity=KeywordSearch.class)
	@JoinColumn(name=FacebookPostKeywordResultConstants.PROPERTY_KEYWORD_SEARCH)
	private	KeywordSearch	search;
	
	/**
	 * Initializes the object.
	 */
	public FacebookPostKeywordResult()
	{
		
	}
	
	/**
	 * Initializes the object.
	 * @param keyword
	 * @param rate
	 * @param feed
	 */
	public	FacebookPostKeywordResult(Keyword keyword, FacebookPost post, ApplicationUser user, KeywordSearch search)
	{
		setKeyword(keyword);
		setPost(post);
		setUser(user);
		setSearch(search);
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
	 * @param search the search to set
	 */
	public void setSearch(KeywordSearch search) {
		this.search = search;
	}

	/**
	 * @return the search
	 */
	public KeywordSearch getSearch() {
		return search;
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
	 * @param keyword the keyword to set
	 */
	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the keyword
	 */
	public Keyword getKeyword() {
		return keyword;
	}

	/**
	 * @param post the post to set
	 */
	public void setPost(FacebookPost post) {
		this.post = post;
	}

	/**
	 * @return the post
	 */
	public FacebookPost getPost() {
		return post;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if( obj == null )
		{
			return false;
		}
		if(( obj instanceof FacebookPostKeywordResult) == false )
		{
			return false;
		}
		FacebookPostKeywordResult result = (FacebookPostKeywordResult)obj;
		if( result.getId().equals(this.id) )
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() 
	{
		return	this.id.hashCode()^5;
	}
}
