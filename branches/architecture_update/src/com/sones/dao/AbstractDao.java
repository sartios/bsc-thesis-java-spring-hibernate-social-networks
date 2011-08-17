package com.sones.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.exceptions.DataAccessLayerException;
import com.persistance.HibernateUtil;

/**
 * A layer supertype that handles the common operations for all
 * Data Access Objects.
 * @author Sartios
 *
 */
public abstract class AbstractDao {

	protected Session session;
	protected Transaction tx;
	
	public AbstractDao(){
		HibernateUtil.buildIfNeeded();
	}
	
	protected void saveOrUpdate(Object obj){
		try{
			startOperation();
			session.saveOrUpdate(obj);
			tx.commit();
		}
		catch(HibernateException e){
			handleException(e);
		}
		finally{
			HibernateUtil.close(session);
		}
	}
	
	protected boolean delete(Object obj){
		boolean deletionIsOk=false;
		try{
			startOperation();
			session.delete(obj);
			tx.commit();
			deletionIsOk=true;
		}
		catch(HibernateException e){
			handleException(e);
			deletionIsOk=false;
		}
		finally{
			HibernateUtil.close(session);
		}
		return deletionIsOk;
	}
	
	protected Object find(Class clazz, String id){
		Object obj = null;
		try{
			startOperation();
			obj = session.get(clazz, id);
			tx.commit();
		}
		catch (HibernateException e) {
			handleException(e);
		}
		finally{
			HibernateUtil.close(session);
		}
		return obj;
	}
	
	protected List findAll(Class clazz){
		List objects = null;
		try{
			startOperation();
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			tx.commit();
		}
		catch(HibernateException e){
			handleException(e);
		}
		finally{
			HibernateUtil.close(session);
		}
		return objects;
	}
	
	protected Object find(Class clazz, long id){
		Object obj = null;
		try{
			startOperation();
			obj = session.get(clazz, id);
			tx.commit();
		}
		catch (HibernateException e) {
			handleException(e);
		}
		finally{
			HibernateUtil.close(session);
		}
		return obj;
	}
	
	protected void handleException(HibernateException e) throws DataAccessLayerException{
		HibernateUtil.rollback(tx);
		throw new DataAccessLayerException(e);
	}
	
	protected void startOperation() throws HibernateException{
		HibernateUtil.buildIfNeeded();
		session = HibernateUtil.openSession();
		tx = session.beginTransaction();
	}
}
