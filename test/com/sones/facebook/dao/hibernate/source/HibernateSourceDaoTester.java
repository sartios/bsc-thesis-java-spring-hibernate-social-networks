package com.sones.facebook.dao.hibernate.source;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.source.ISourceDao;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.Source;

public class HibernateSourceDaoTester extends HibernateDaoTesterUtil
{
	private Source source;
	private ISourceDao sourceDao;
	private ISourceTypeDao sourceTypeDao;
	
	public HibernateSourceDaoTester()
	{
		super();
	}
	
	@Before
	public void SetUp()
	{
		source = (Source) getModelContext().getBean("source");
		sourceDao = (HibernateSourceDao) getDAOContext().getBean("sourceDao");
		sourceTypeDao = (HibernateSourceTypeDao) getDAOContext().getBean("sourceTypeDao");
		
		sourceTypeDao.Save(source.getType());
		sourceDao.Save(source);
	}
	
	@After
	public void TearDown()
	{
		sourceDao.Delete(source);
		sourceTypeDao.Delete(source.getType());
	}
	
	@Test
	public void TestSaveSourceType()
	{
		Source dpPost = sourceDao.GetById(source.getId());
		assertEquals(source.getType(), dpPost.getType());
	}
	
}