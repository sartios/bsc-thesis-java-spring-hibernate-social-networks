package com.sones.facebook.keywordSearcher.logic.idmaker;

public class IdMaker implements IIdMaker 
{
	private	IKeywordSearchIdMaker	searchIdMaker;
	
	private	IKeywordResultIdMaker	resultIdMaker;
	
	public	IdMaker()
	{
		
	}
	
	@Override
	public String getKeywordSearchId() 
	{
		return	searchIdMaker.generateId();
	}

	@Override
	public String getKeywordSearchResultId() 
	{
		return	resultIdMaker.generateId();
	}

	/**
	 * @param searchIdMaker the searchIdMaker to set
	 */
	public void setSearchIdMaker(IKeywordSearchIdMaker searchIdMaker) {
		this.searchIdMaker = searchIdMaker;
	}

	/**
	 * @return the searchIdMaker
	 */
	public IKeywordSearchIdMaker getSearchIdMaker() {
		return searchIdMaker;
	}

	/**
	 * @param resultIdMaker the resultIdMaker to set
	 */
	public void setResultIdMaker(IKeywordResultIdMaker resultIdMaker) {
		this.resultIdMaker = resultIdMaker;
	}

	/**
	 * @return the resultIdMaker
	 */
	public IKeywordResultIdMaker getResultIdMaker() {
		return resultIdMaker;
	}

}
