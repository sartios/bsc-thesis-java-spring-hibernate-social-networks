package com.sones.keyword.creator.logic;

import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.Keyword;

public class KeywordCreatorService implements IKeywordCreatorService
{
	private IKeywordDao keywordDao;
	
	public KeywordCreatorService(IKeywordDao keywordDao)
	{
		this.keywordDao = keywordDao;
	}
	
	@Override
	public void createKeyword(String keywordValue) 
	{
		checkNullability(keywordValue,"Keyword can't be null");
		Keyword keyword = new Keyword( keywordValue );
		if(keywordDao.getByValue( keyword ) == null )
		{
			Number number = keywordDao.GetRowCount();
			keyword.setId( number.toString() );
			keywordDao.Save(keyword);
		}
	}
	
	private void checkNullability(Object object, String message) throws IllegalArgumentException
	{
		if(object == null)
		{
			throw new IllegalArgumentException(message);
		}
	}
	
}
