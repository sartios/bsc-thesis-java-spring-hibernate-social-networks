package com.sones.facebook.dao.hibernate.source;

import org.junit.Assert;
import org.junit.Test;

import com.sones.facebook.dao.hibernate.HibernateDaoTesterUtil;
import com.sones.facebook.dao.source.ISourceTypeDao;
import com.sones.facebook.model.source.SourceType;

public class HibernateSourceTypeDaoTester  extends HibernateDaoTesterUtil
{
	private	ISourceTypeDao sourceTypeDao;
	
	public HibernateSourceTypeDaoTester()
	{
		sourceTypeDao = (HibernateSourceTypeDao) getDAOContext().getBean("sourceTypeDao");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetByTypeIllegalArgumentException()
	{
		sourceTypeDao.GetByType(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetByTypeIllegalArgumentExceptionNullType()
	{
		sourceTypeDao.GetByType(new SourceType());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void TestGetByTypeIllegalArgumentExceptionEmptyType()
	{
		SourceType type = new SourceType();
		type.setType("");
		sourceTypeDao.GetByType(type);
	}
	
	@Test(expected=NullPointerException.class)
	public void TestGetByTypeNullPointerException()
	{
		SourceType type = new SourceType();
		type.setType("Something");
		sourceTypeDao.GetByType(type);
	}
	
	@Test
	public void TestGetByType()
	{
		SourceType type = new SourceType();
		type.setId("1");
		type.setType("User");
		sourceTypeDao.Save(type);
		SourceType tmpType = sourceTypeDao.GetByType(type);
		Assert.assertEquals(type.getId(), tmpType.getId());
		Assert.assertEquals(type.getType(), tmpType.getType());
		sourceTypeDao.Delete(type);
	}
}
