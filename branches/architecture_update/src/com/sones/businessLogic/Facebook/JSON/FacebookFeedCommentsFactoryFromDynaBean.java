package com.sones.businessLogic.Facebook.JSON;

import java.util.ArrayList;

import net.sf.ezmorph.MorphException;
import net.sf.json.JSONException;

import org.apache.commons.beanutils.DynaBean;
import org.apache.log4j.Logger;

import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.FacebookComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComment;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.IFacebookComments;
import com.sones.businessLogic.Facebook.FeedManager.CommentManager.AbstractFeedComment;


public class FacebookFeedCommentsFactoryFromDynaBean implements	IFacebookFeedCommentsFactory
{
	private	Logger	_logger;
	
	public	FacebookFeedCommentsFactoryFromDynaBean()
	{
		_logger	=	Logger.getLogger( FacebookFeedCommentsFactoryFromDynaBean.class );
	}

	@Override
	public IFacebookComments GetComments(Object feedObject) {
		IFacebookComments	comments	=	new	FacebookComments();
		try{
			
			ArrayList list = GetCommentsData( feedObject );
			for(int i=0;i<list.size();i++){
				IFacebookComment comment = new FacebookComment();
				( ( FacebookComment )comment ).SetCommentId( GetCommentID( list.get(i) ) );
				( ( FacebookComment )comment ).SetMessage( GetCommentMessage( list.get( i ) ) );
				( ( FacebookComment )comment ).SetCreatorId( GetCommentCreator( list.get( i ) ) );
				( ( AbstractFeedComment )comment ).SetFeedID( GetCommentFeedID( feedObject ));
				( ( FacebookComments )comments ).AddComment( comment );
			}
		}	
		catch (JSONException ex) {			
		}
		catch (MorphException noComments) {
		}
		return	comments;
	}
	
	private	ArrayList	GetCommentsData( Object feedObject )
	{
		ArrayList comments	=	(ArrayList) (((DynaBean) ((DynaBean)feedObject).get("comments")).get("data"));
		if( null == comments ){
			_logger.error( "Comments' table is null from DynaBean object" );
		}
		return comments;
	}
	
	private	String	GetCommentID( Object feedObject )
	{
		String commentID	=	( ( DynaBean )feedObject ).get( "id" ).toString();
		if( null == commentID ){
			_logger.error( "Comment ID is null from DynaBean object" );
		}
		return	commentID;
	}
	
	private	String	GetCommentMessage( Object feedObject )
	{
		String message	=	( ( DynaBean )feedObject ).get( "message" ).toString();
		if( null == message ){
			_logger.error( "Comment nmessage is null from DynaBean object" );
		}
		return	message;
	}
	
	private	String	GetCommentCreator( Object object )
	{
		String	creatorID	=	(((DynaBean)(((DynaBean) object).get( "from" ))).get("id")).toString();
		if( null == creatorID ){
			_logger.error( "Comment nmessage is null from DynaBean object" );
		}
		return	creatorID;
	}
	
	private	String	GetCommentFeedID( final Object object )
	{
		String	feedID	=	( ( DynaBean )object ).get( "id" ).toString();
		if( null == feedID ){
			_logger.error( "Comment message is null from DynaBean object" );
		}
		_logger.debug( "Comments for feed: "+feedID );
		return	feedID;
	}

}
