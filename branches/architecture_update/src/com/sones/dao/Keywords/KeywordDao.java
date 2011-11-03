package com.sones.dao.Keywords;

import java.util.Iterator;
import java.util.List;

import com.sones.businessLogic.KeywordManager.IKeyword;
import com.sones.businessLogic.KeywordManager.IKeywords;
import com.sones.businessLogic.KeywordManager.IUserKeywordRetriever;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keywords;

import com.sones.dao.AbstractDao;

public class KeywordDao	extends	AbstractDao	implements	IUserKeywordRetriever
{
	public	KeywordDao()
	{
		super();
	}
	
	public	IKeyword	GetKeyword( final String keywordID)
	{
		return	(IKeyword) super.find( Keyword.class, keywordID );
	}

	@Override
	public IKeywords GetKeywords(List<String> keywordIDs) 
	{
		IKeywords	keywords	=	new	Keywords();
		for( Iterator<String> iterator = keywordIDs.iterator(); iterator.hasNext(); )
		{
			keywords.Add(GetKeyword( iterator.next().toString() ) );
		}
		return	keywords;
	}
	
	



}
