package com.sones.dao.Keywords;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.KeywordManager.IKeyword;
import com.sones.businessLogic.KeywordManager.IKeywords;
import com.sones.businessLogic.KeywordManager.IUserKeywordRetriever;
import com.sones.businessLogic.KeywordManager.Keyword;
import com.sones.businessLogic.KeywordManager.Keywords;

import com.sones.dao.AbstractDao;

public class KeywordDao	extends	AbstractDao	implements	IUserKeywordRetriever, IKeywordDao
{
	public	KeywordDao()
	{
		super();
	}
	
	public	IKeyword	GetKeyword( final String keywordID)
	{
		return	(IKeyword) super.find( Keyword.class, keywordID );
	}

	@Override
	public IKeywords GetKeywords(List<String> keywordIDs) 
	{
		IKeywords	keywords	=	new	Keywords();
		for( Iterator<String> iterator = keywordIDs.iterator(); iterator.hasNext(); )
		{
			keywords.Add(GetKeyword( iterator.next().toString() ) );
		}
		return	keywords;
	}
	
	public	IKeyword	GetKeywordByValue( final String keywordValue )
	{
		IKeyword	keyword	=	null;
		if( null != keywordValue )
		{
			startOperation();
			try
			{
				Query	query	=	session.createSQLQuery("" +
						"SELECT PK_KEYWORD_ID, KEYWORD_VALUE " +
						"FROM	sones.keywords " +
						"WHERE	KEYWORD_VALUE = :keyword");
				query.setParameter("keyword", keywordValue);
				List	list	=	query.list();
				tx.commit();
				session.flush();
				keyword	=	CreateKeyword(list);
			}
			catch (Exception e) 
			{
				tx.rollback();
			}
			finally
			{
				HibernateUtil.close(session);
			}
		}
		return	keyword;
	}
	
	public	boolean	SaveKeyword( final String value )
	{
		boolean	isSaved	=	false;
		if( null != value )
		{
			startOperation();
			try
			{
				Query	query	=	session.createSQLQuery("" +
						"INSERT INTO sones.keywords " +
						"(keyword_value) VALUES " +
						"(:keyword)");
				query.setParameter("keyword", value);
				query.executeUpdate();
				tx.commit();
				session.flush();
			}
			catch (Exception e) 
			{
				tx.rollback();
			}
			finally
			{
				HibernateUtil.close(session);
			}
		}
		return	isSaved;
	}
	
	private	IKeyword	CreateKeyword(List<Object[]> list)
	{
		IKeyword	keyword	=	null;
		if( (null != list) && (false == list.isEmpty()) )
		{
			Object[]	object	=	list.get(0);
			keyword	=	new	Keyword();
			((Keyword)keyword).set_id(object[0].toString());
			((Keyword)keyword).SetValue(object[1].toString());
		}
		return	keyword;
	}
}
