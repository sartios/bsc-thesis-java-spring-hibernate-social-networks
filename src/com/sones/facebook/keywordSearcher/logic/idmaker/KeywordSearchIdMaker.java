package com.sones.facebook.keywordSearcher.logic.idmaker;

import com.sones.facebook.keywordSearcher.dao.IKeywordSearchDao;

public class KeywordSearchIdMaker implements IKeywordSearchIdMaker 
{

	private	IKeywordSearchDao	keywordSearchDao;
	
	public	KeywordSearchIdMaker()
	{
		
	}
	
	@Override
	public String generateId() 
	{
		Number rowCount	=	keywordSearchDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() ) );
		return	id;
	}

	/**
	 * @param keywordSearchDao the keywordSearchDao to set
	 */
	public void setKeywordSearchDao(IKeywordSearchDao keywordSearchDao) {
		this.keywordSearchDao = keywordSearchDao;
	}

	/**
	 * @return the keywordSearchDao
	 */
	public IKeywordSearchDao getKeywordSearchDao() {
		return keywordSearchDao;
	}

}
