/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This exception occurs when the times of two activities sharing the same day overlap.
 * @author Alex Bernard
 *
 */
public class ConflictException extends Exception {
	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * When called, the ConflictException returns the given message.
	 * @param message The custom message given when the exception is called.
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Returns the default message when the ConflictException is called. 
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
