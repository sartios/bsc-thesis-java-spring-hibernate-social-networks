package com.sones.facebook.tokenmanager.dao.hibernate;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.sones.dao.hibernate.HibernateGenericDao;
import com.sones.facebook.tokenmanager.model.FacebookAccount;
import com.sones.facebook.tokenmanager.dao.IFacebookAccountDao;
import com.sones.usermanager.model.ApplicationUser;

/**
 * ...
 * @see IFacebookAccountDao
 * @author sartios.sones@gmail.com.
 *
 */
public class HibernateFacebookAccountDao	extends	HibernateGenericDao< FacebookAccount, String >	implements	IFacebookAccountDao
{
	/**
	 * The class logger.
	 */
	private	final	Logger	_LOGGER;
	
	/**
	 * Initializes the object.
	 */
	public	HibernateFacebookAccountDao()
	{
		super( FacebookAccount.class );
		_LOGGER	=	Logger.getLogger( HibernateFacebookAccountDao.class );
	}

	@Override
	public FacebookAccount getByApplicationUser(ApplicationUser appUser) 
	{
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(FacebookAccount.class)
			.add( Restrictions.eq("appUser", appUser) );
		FacebookAccount account = (FacebookAccount) criteria.uniqueResult();
		session.close();
		return account;
	}
}
