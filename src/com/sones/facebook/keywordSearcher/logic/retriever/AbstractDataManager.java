package com.sones.facebook.keywordSearcher.logic.retriever;

import org.dozer.DozerBeanMapper;

public abstract class AbstractDataManager
{
	/**
	 * Responsible for mapping model objects to dto.
	 */
	private	DozerBeanMapper mapper;
	
	/**
	 * Responsible for retrieving the facebook posts per download.
	 */
	private	IFacebookPostDataManager	manager;
	
	public	AbstractDataManager()
	{
	}

	/**
	 * @param mapper the mapper to set
	 */
	public void setMapper(DozerBeanMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @return the mapper
	 */
	public DozerBeanMapper getMapper() {
		return mapper;
	}

	/**
	 * @param manager the manager to set
	 */
	public void setManager(IFacebookPostDataManager manager) {
		this.manager = manager;
	}

	/**
	 * @return the manager
	 */
	public IFacebookPostDataManager getManager() {
		return manager;
	}
	
	
}
