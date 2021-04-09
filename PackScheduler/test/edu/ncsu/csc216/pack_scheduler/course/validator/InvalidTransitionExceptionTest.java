/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of the InvalidTransitionException class
 * @author Alex Bernard
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Tests the functionality of both InvalidTransitionException constructors
	 */
	@Test
	public void test() {
		try {
			throw new InvalidTransitionException();
		} catch(InvalidTransitionException e) {
			assertEquals("Invalid FSM Transition.", e.getMessage());
		}
		
		try {
			throw new InvalidTransitionException("Custom message");
		} catch(InvalidTransitionException e) {
			assertEquals("Custom message", e.getMessage());
		}
		
	}

}
