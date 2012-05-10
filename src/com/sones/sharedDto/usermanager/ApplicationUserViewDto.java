package com.sones.sharedDto.usermanager;

public class ApplicationUserViewDto 
{
	private String userID;
	private String accountID;
	private String name;
	
	public ApplicationUserViewDto()
	{}
	
	public ApplicationUserViewDto(String userID, String accountID, String name)
	{
		this.setUserID(userID);
		this.setAccountID(accountID);
		this.setName(name);
	}
	
	public ApplicationUserViewDto(ApplicationUserViewDto userDto)
	{
		this.setUserID(userDto.getUserID());
		this.setAccountID(userDto.getAccountID());
		this.setName(userDto.getName());
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}	
}
