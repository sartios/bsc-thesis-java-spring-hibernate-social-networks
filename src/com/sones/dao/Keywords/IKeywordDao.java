package com.sones.dao.Keywords;

import com.sones.businessLogic.KeywordManager.IKeyword;

public interface IKeywordDao 
{
	public	IKeyword	GetKeywordByValue( final String keywordValue );
	public	boolean	SaveKeyword( final String value );
}
