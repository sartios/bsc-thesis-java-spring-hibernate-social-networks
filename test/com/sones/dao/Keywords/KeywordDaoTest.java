package com.sones.dao.Keywords;

import org.junit.Assert;
import org.junit.Test;

public class KeywordDaoTest
{
	@Test
	public	void	GetKeywordByValue_KeywordExists_Test()
	{
		KeywordDao	dao	=	new	KeywordDao();
		Assert.assertNotNull(dao.GetKeywordByValue("keyword"));
	}
	
	@Test
	public	void	GetKeywordByValue_KeywordDoesNotExistTest()
	{
		KeywordDao	dao	=	new	KeywordDao();
		Assert.assertNull(dao.GetKeywordByValue("something"));
	}
	
	@Test
	public	void	SaveKeyword_Test()
	{
		KeywordDao	dao	=	new	KeywordDao();
		dao.SaveKeyword("areianara");
		Assert.assertNotNull(dao.GetKeywordByValue("areianara"));
	}
}
