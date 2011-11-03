package com.sones.businessLogic.Facebook.DateManager;

/**
 * Facebook uses a specific format for representing the dates. This interface
 * provides a unified approach to access facebook dates which agree on facebook
 * business rules.
 * 
 * @author Sartios
 *
 */
public interface IFacebookDate {

	/**
	 * @return a valid date with facebook format
	 */
	public	String	GetDateValue();
}
