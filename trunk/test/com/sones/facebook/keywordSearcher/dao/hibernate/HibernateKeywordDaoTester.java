package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.facebook.keywordSearcher.dao.IKeywordDao;
import com.sones.facebook.keywordSearcher.model.Keyword;

public class HibernateKeywordDaoTester 
{
	private	Keyword	keyword;
	private	IKeywordDao	keywordDao;
	private	ApplicationContextProvider	contextProvider;
	
	@Before
	public	void	setUp()
	{
		contextProvider	=	new ApplicationContextProvider();
		keyword	=	(Keyword)contextProvider.getModelContext().getBean("keyword");
		keywordDao	=	(HibernateKeywordDao)contextProvider.getDaoContext().getBean("keywordDao");
	}
	
	@After
	public	void	tearDown()
	{
		keywordDao	=	null;
		keyword	=	null;
	}
	
	@Test
	public	void	testSaveKeyword()
	{
		keywordDao.Save(keyword);
		Keyword	dbKeyword	=	keywordDao.GetById(keyword.getId());
		assertEquals(keyword.getId(), dbKeyword.getId());
		assertEquals(keyword.getValue(), keyword.getValue());
		keywordDao.Delete(keyword);
	}
}
