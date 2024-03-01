package com.cg.hims.Exceptions;

public class UserNotFoundException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String str)
    {
    	super(str);
    }
}
