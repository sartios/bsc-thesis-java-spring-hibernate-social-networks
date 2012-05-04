package com.sones.facebook.placemanager.associator.exception;

/**
 * Exception that is thrown when there is not source type.
 * 
 * @author sartios.sones@gmail.com.
 *
 */
public class NoSuchSourceType extends RuntimeException 
{
	/**
	 * The serial version.
	 */
	private static final long serialVersionUID = -4645679117865483688L;

	/**
	 * Initiates the object.
	 */
	public NoSuchSourceType()
	{
		super();
	}
	
	/**
	 * Initiates the object
	 * @param message the message that will be thrown.
	 */
	public NoSuchSourceType(String message)
	{
		super(message);
	}
}
