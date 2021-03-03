
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A Test class to test Conflict Exception class
 * @author meles
 *
 */
public class ConflictExceptionTest {

	/**
	 * Test method for ConflictException with a custome method 
	 */
	@Test
	public void testConflictExceptionString() {
		 ConflictException ce = new ConflictException(); 
		 assertEquals("Schedule conflict.", ce.getMessage());
	}

	/**
	 * Test method for ConflictException with a default messsage 
	 */
	@Test
	public void testConflictException() {
		 ConflictException ce = new ConflictException("Custom exception message");
		 assertEquals("Custom exception message", ce.getMessage()); 
	}

}
