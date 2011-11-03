package com.sones.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import com.persistance.HibernateUtil;
import com.sones.dao.exceptions.DataAccessLayerException;

/**
 * A layer supertype that handles the common operations for all
 * Data Access Objects.
 * @author Sartios
 *
 */
public abstract class AbstractDao {

	protected Session session;
	protected Transaction tx;
	private	Logger	_logger;
	
	public AbstractDao(){
		HibernateUtil.buildIfNeeded();
		_logger	=	Logger.getLogger( AbstractDao.class );
	}
	
	protected boolean saveOrUpdate(Object obj){
		boolean operationCompleted = false;
		if(null!=obj){
			try{
				startOperation();
				session.saveOrUpdate(obj);
				session.flush();
				tx.commit();
				operationCompleted=true;
			}
			catch ( ConstraintViolationException constraintViolation ) 
			{			
				_logger.error(constraintViolation.getSQL());
				_logger.error(constraintViolation.getCause());
				handleException(constraintViolation);
				operationCompleted=false;
			}
			finally{
				HibernateUtil.close(session);
			}
		}
		return operationCompleted;
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
			_logger.error( e.getMessage() );
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
