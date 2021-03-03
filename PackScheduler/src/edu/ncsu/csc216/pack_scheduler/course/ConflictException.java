/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A class that Handles Conflict exception
 * @
 * @author meles
 *
 */
public class ConflictException extends Exception {

	/**ID used for Serialization  */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Conflcit exception contructed with a custom messgae 
	 * @param message represents a string representation of custom message.
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Conflcit exception contructed with a default message "Schedule conflict"
	 */
	public ConflictException() {
		this("Schedule conflict."); //Schedule conflict.
	}
	
	
	
	


}
