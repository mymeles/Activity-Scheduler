/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * Sends an InvalidTransitionException with the default or given message
 * @author Alex Bernard
 *
 */
public class InvalidTransitionException extends Exception {
	
	
	
	/**
	 * ID Used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Sends an InvalidTransitionException using the given message
	 * @param message The message being sent through the InvalidTransitionException
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Sends an InvalidTransitionException using the default message
	 */
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
}
