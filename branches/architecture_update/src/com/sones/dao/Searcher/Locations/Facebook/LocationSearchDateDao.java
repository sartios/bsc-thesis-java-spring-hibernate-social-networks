package com.sones.dao.Searcher.Locations.Facebook;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Searcher.Locations.ILocationSearchDateProvider;
import com.sones.businessLogic.UserManager.IApplicationUserID;
import com.sones.dao.AbstractDao;

public class LocationSearchDateDao	extends	AbstractDao	implements	ILocationSearchDateProvider
{

	public	LocationSearchDateDao()
	{
		super();
	}
	
	@Override
	public String GetDateOfLastSearch(IApplicationUserID userID) 
	{
		String date	=	new String();
		if( null != userID )
		{
			startOperation();
			Query	query	=	session.createSQLQuery("SELECT  DATE	"+
														"FROM    sones.location_searches	"+
														"WHERE   APPLICATION_USERS_PK_USER_ID   =  :id	"+ 
														"ORDER BY DATE desc;");
			query.setParameter("id", userID.GetValue() );
			try
			{
				date	=	GetDate(query.list());
				tx.commit();
				session.flush();
			}
			catch (HibernateException e) 
			{
				tx.rollback();
			}
			finally
			{
				HibernateUtil.close(session);
			}
		}
		return	date;
	}

	private String GetDate(List list) 
	{
		String	date	=	new	String("0");
		try
		{
			date	=	(String) list.get(0);
		}
		catch (IndexOutOfBoundsException e) 
		{
		}
		return	date;
	}

	@Override
	public void SaveDateOfCurrentSearch(String now, IApplicationUserID	userID ) 
	{
		String queryString	=	"INSERT INTO `sones`.`location_searches` (`DATE`, `APPLICATION_USERS_PK_USER_ID`) VALUES ( :date, :id);";
		try
		{
			startOperation();
			Query	query	=	session.createSQLQuery(queryString);
			query.setParameter("date", now);
			query.setParameter("id", userID.GetValue() );
			query.executeUpdate();
			tx.commit();
			session.flush();
		}
		catch (HibernateException ex)
		{
			tx.rollback();
		}
		finally
		{
			HibernateUtil.close(session);
		}
	}

}
