package com.sones.facebook.keywordSearcher.dao.hibernate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.sones.dao.IGenericDao;
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
		saveIfNotExist(keyword, keyword.getId(), keywordDao);
		Keyword	dbKeyword	=	keywordDao.GetById(keyword.getId());
		assertEquals(keyword.getId(), dbKeyword.getId());
		assertEquals(keyword.getValue(), keyword.getValue());
		deleteIfExists(keyword, keyword.getId(), keywordDao);
	}
	
	@Test
	public void getByValueShouldReturnNullWhenDoesNotExist()
	{
		Keyword filter = new Keyword("aasssddd");
		Keyword dbKeyword = keywordDao.getByValue(filter);
		assertNull( dbKeyword );
	}
	
	@Test
	public void getByValueShouldReturnTheKeyword()
	{
		Keyword filter = new Keyword("aasssddd");
		Number number = keywordDao.GetRowCount();
		filter.setId( number.toString() );
		saveIfNotExist(filter,filter.getId(),keywordDao);
		Keyword dbKeyword = keywordDao.getByValue(filter);
		assertNotNull( dbKeyword );
		deleteIfExists(filter,filter.getId(),keywordDao);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getByValueShouldThrowOnNullKeyword()
	{
		keywordDao.getByValue(null);
	}
	
	private void saveIfNotExist(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) == null)
		{
			dao.Save(model);
		}
	}
	
	private void deleteIfExists(Object model, Object id, IGenericDao dao)
	{
		if(dao.GetById(id) != null)
		{
			dao.Delete(model);
		}
	}
	
}
