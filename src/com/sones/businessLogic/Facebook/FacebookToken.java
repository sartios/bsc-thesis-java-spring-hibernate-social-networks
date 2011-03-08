package com.sones.businessLogic.Facebook;

import java.io.Serializable;

public class FacebookToken implements Serializable{

	private String token;
	
	public FacebookToken(){
		
	}

	public String getToken() {
		return "access_token="+token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
