package com.sones.facebook.keywordSearcher.logic.idmaker;

import com.sones.facebook.keywordSearcher.dao.IFacebookPostKeywordResultDao;

public class KeywordResultIdMaker	implements	IKeywordResultIdMaker
{
	private IFacebookPostKeywordResultDao keywordSearchResultDao;
	
	public	KeywordResultIdMaker()
	{
		
	}

	@Override
	public	String	generateId()
	{
		Number rowCount	=	keywordSearchResultDao.GetRowCount();
		String	id	=	new	String( String.valueOf( rowCount.longValue() ) );
		return	id;
	}
	

	public IFacebookPostKeywordResultDao getKeywordSearchResultDao() {
		return keywordSearchResultDao;
	}

	public void setKeywordSearchResultDao(
			IFacebookPostKeywordResultDao keywordSearchResultDao) {
		this.keywordSearchResultDao = keywordSearchResultDao;
	}
}
