package com.sones.dao.Facebook.Feed.Contents;

import org.apache.log4j.Logger;

public class FacebookContentDaoFactory implements	IFacebookContentDaoFactory
{
	private	IFacebookContentDao	_dao;
	private	Logger	_logger;
	
	public	FacebookContentDaoFactory()
	{
		_dao	=	null;
		_logger	=	Logger.getLogger( FacebookContentDaoFactory.class );
	}

	@Override
	public IFacebookContentDao GetContentDao( final String type )
	{
		if( null != type )
		{
			if( type.equals( "StatusMessage" ) )
			{
				_dao	=	new	StatusMessageContentDao();
			}
			else if( type.equals( "Link" ) )
			{
				_dao	=	new	LinkContentDao();
			}			
			else if( type.equals( "Note" ) )
			{
				_dao	=	new	NoteContentDao();
			}
			else if( type.equals( "Picture" ) )
			{
				_dao	=	new	PictureContentDao();
			}
			else if( type.equals( "Video" ) )
			{
				_dao	=	new	VideoContentDao();
			}
		}
		return	GetDao();
	}
	
	private	IFacebookContentDao	GetDao()
	{
		if( null == _dao )
		{
			_logger.error( " Not appropriate content type" );
		}
		return	_dao;
	}
	
}
