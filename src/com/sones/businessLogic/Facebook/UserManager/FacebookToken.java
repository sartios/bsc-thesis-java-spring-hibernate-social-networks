package com.sones.buisinessLogic.Facebook.UserManager;


public	class	FacebookToken extends AbstractUserToken	implements	IFacebookToken, IPersistableFacebookToken
{

	private	String	_token;
	
	public	FacebookToken()
	{
	}
	
	public	FacebookToken( final String	token)
	{
		setToken(token);
	}

	public	void	setToken( String token ) 
	{
		this._token	=	token;
	}
	
	public	String	getToken()
	{
		return	_token;
	}

	@Override
	public	String	GetValue() 
	{
		return	getToken();
	}

	@Override
	public String get_token() 
	{
		return	GetValue();
	}

	@Override
	public String get_userID() 
	{
		return	super.GetUserID();
	}

	@Override
	public void set_token(String token)
	{
		setToken( token );
	}

	@Override
	public void set_userID(String userID) 
	{
		super.SetUserID( userID );
	}
	
}
