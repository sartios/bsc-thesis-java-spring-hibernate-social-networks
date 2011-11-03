package com.sones.buisinessLogic.Facebook.UserManager;

public interface IPersistableFacebookToken 
{
	public	String	get_userID();
	public	void	set_userID( String userID );
	
	public	String	get_token();
	public	void	set_token( String token );
}
