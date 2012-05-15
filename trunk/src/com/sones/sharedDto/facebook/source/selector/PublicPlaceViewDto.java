package com.sones.sharedDto.facebook.source.selector;

public class PublicPlaceViewDto 
{
	private String id;
	private String name;
	private String criteria;
	
	public PublicPlaceViewDto()
	{}

	/**
	 * @param id
	 * @param name
	 * @param criteria
	 */
	public PublicPlaceViewDto(String id, String name, String criteria) {
		super();
		this.id = id;
		this.name = name;
		this.criteria = criteria;
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
	 * @return the criteria
	 */
	public String getCriteria() {
		return criteria;
	}

	/**
	 * @param criteria the criteria to set
	 */
	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}
	
	
}
