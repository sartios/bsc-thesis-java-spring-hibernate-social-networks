package com.sones.dao.Facebook.DownloadInfo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.persistance.HibernateUtil;
import com.sones.businessLogic.Facebook.IFacebookDownloadInfo;
import com.sones.dao.AbstractDao;

public class FacebookDownloadInfoDao	extends	AbstractDao	implements	IFacebookDownloadInfoDao
{
	public	FacebookDownloadInfoDao()
	{
		super();
	}
	
	@Override
	public void SaveDownloadInfo(IFacebookDownloadInfo downloadInfo)
	{
		CreateDownloadRecord(downloadInfo.GetDate(), downloadInfo.GetUserID());
		String	downloadingID	=	GetDownloadID(downloadInfo.GetUserID());
		SaveFeeds(downloadInfo.GetFeedIDs(), downloadingID);
		SaveComments(downloadInfo.GetCommentIDs(), downloadingID);
	}
	
	private	void	CreateDownloadRecord( String date, String userID )
	{
		startOperation();
		Query	query	=	session.createSQLQuery("insert into FACEBOOK_DOWNLOADINGS (DATE, APPLICATION_USERS_PK_USER_ID) values (:downloadDate, :appUserID)");
		query.setParameter("appUserID", userID);
		query.setParameter("downloadDate", date);
		try
		{
			query.executeUpdate();
			tx.commit();
			session.flush();
		}
		catch (HibernateException exception)
		{
			
		}
		finally
		{
			HibernateUtil.close(session);
		}
	}
	
	private	void	SaveComments( List< String > comments, String id)
	{
		startOperation();
		Query	query	=	session.createSQLQuery(
				"insert	into	FACEBOOK_COMMENTS_DOWNLOADED_DATE	"
				+"(FACEBOOK_DOWNLOADINGS_ID , Facebook_COMMENTS_PK_COMMENT_ID)	"
				+"values	(:downloadingID, :commentID)");
		query.setParameter("downloadingID", id);
		try
		{
			for( int commentIndex = 0; commentIndex < comments.size(); commentIndex++)
			{
				query.setParameter("commentID", comments.get(commentIndex));
				query.executeUpdate();
			}
			tx.commit();
			session.flush();
		}
		catch (HibernateException exception)
		{
		}
		finally
		{
			HibernateUtil.close(session);
		}
	}
	
	private	void	SaveFeeds( List< String > feeds, String id)
	{
		startOperation();
		Query	query	=	session.createSQLQuery(
				"insert	into	FACEBOOK_FEEDS_DOWNLAODED_DATE	"
				+"(FACEBOOK_DOWNLOADINGS_ID , FACEBOOK_FEEDS_PK_FEED_ID)	"
				+"values	(:downloadingID, :feedID)");
		query.setParameter("downloadingID", id);
		try
		{
			for( int feedIndex = 0; feedIndex < feeds.size(); feedIndex++)
			{
				query.setParameter("feedID", feeds.get(feedIndex));
				query.executeUpdate();
			}
			tx.commit();
			session.flush();
		}
		catch (HibernateException exception)
		{
		}
		finally
		{
			HibernateUtil.close(session);
		}
	}
	
	private	String	GetDownloadID( String userID )
	{
		startOperation();
		Query	query	=	session.createSQLQuery(
												"select	ID	"
												+"from FACEBOOK_DOWNLOADINGS	"
												+"where	APPLICATION_USERS_PK_USER_ID	=	:appUserID	"
												+"order by	DATE	desc"
												);
		query.setParameter("appUserID", userID);
		String	downloadingID	=	new	String();
		try
		{
			downloadingID	=	String.valueOf( query.list().get(0) );
			tx.commit();
			session.flush();
		}
		catch (HibernateException exception)
		{
		}
		finally
		{
			HibernateUtil.close(session);
		}
		return	downloadingID;
	}
}
