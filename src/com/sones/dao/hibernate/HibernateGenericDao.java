package com.sones.dao.hibernate;

import java.io.Serializable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sones.dao.IGenericDao;

public class HibernateGenericDao<TModel extends Serializable,TId extends Serializable> implements IGenericDao<TModel, TId>
{
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	private Class<TModel> entityClass;
	
	public HibernateGenericDao(Class<TModel> entityClass)
	{
		this.entityClass = entityClass;
		ApplicationContext context =
		    new ClassPathXmlApplicationContext("spring-hibernate.xml");
		hibernateTemplate = (HibernateTemplate) context.getBean("hibernateTemplate");
	}
	
	@Override
	public void Delete(TModel model) 
	{
		hibernateTemplate.delete(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TModel> Get(final int from, final int maxResults) 
	{		
		return	hibernateTemplate.getSessionFactory().openSession().createCriteria(entityClass).setFirstResult(from).setMaxResults(maxResults).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterable<TModel> GetAll() 
	{
		return	hibernateTemplate.getSessionFactory().openSession().createCriteria(entityClass).list();
	}

	@Override
	public TModel GetById(TId id) 
	{
		return	hibernateTemplate.get(entityClass,id);
	}

	@Override
	public TModel LoadById(TId id) 
	{
		return hibernateTemplate.load(entityClass, id);
	}

	@Override
	public void Merge(TModel model) 
	{
		hibernateTemplate.merge(model);
	}

	@Override
	public void Refresh(TModel model) 
	{
		hibernateTemplate.refresh(model);
	}

	@Override
	public void Save(TModel model) 
	{
		hibernateTemplate.save(model);
	}

	@Override
	public void Update(TModel model) 
	{
		hibernateTemplate.update(model);
	}

	@Override
	public void SaveOrUpdate(TModel model)
	{
		hibernateTemplate.saveOrUpdate(model);
	}
}
