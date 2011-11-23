package com.sones.businessLogic.Facebook.FeedManager;

import com.sones.businessLogic.Searcher.ISearchableContent;

public	class	NoteContent	extends	AbstractFacebookFeedContent	implements	IFacebookFeedContent, ISearchableContent, IFacebookNote 
{

	private	String	_subject;
	private	String	_message;
	
	
	public NoteContent(){
		super();
		_subject	=	new	String( "" );
		_message	=	new	String( "" );
	}
	
	public NoteContent(final String subject,final String message){
		SetSubject( subject );
		SetMessage( message );
	}

	public String GetSubject() {
		return	_subject;
	}

	public void SetSubject( final String subject )
	{
		if( null != subject )
		{
			_subject	=	subject;
		}
		else if( null == subject )
		{
			_subject	=	"";
		}
	}

	public String GetMessage() {
		return	_message;
	}

	public void SetMessage( final String message ) 
	{
		if( null != message )
		{
			_message	=	message;
		}
		else if( null == message )
		{
			_message	=	"";
		}
	}

	@Override
	public String GetContentType() {
		return	"Note";
	}

	@Override
	public boolean isContaining(String word) 
	{
		if( MessageContains( word ) )
		{
			return	true;
		}
		else	if( SubjectContains( word ) )
		{
			return true;
		}
		return false;
	}

	private boolean SubjectContains( final String word )
	{
		return	_subject.toLowerCase().contains( word );
	}

	private boolean MessageContains( final String word ) 
	{
		return	_message.toLowerCase().contains( word );
	}
}
