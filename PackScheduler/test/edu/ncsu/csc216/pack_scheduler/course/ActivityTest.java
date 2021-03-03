package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A Test class for Activity
 * @author meles
 * 
 */
public class ActivityTest {

	/**
	 * Test method for checksconflcit without conflict
	 */
	@Test 
	public void testCheckConflict() {
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		try {
			a1.checkConflict(a2);
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString()); 
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
		} catch (ConflictException e) {
	        fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
	    }
	}
	 
	
	/**
	 * Test Method for chekcsConflict with Conflict in activity
	 */
	@Test
	public void testCheckConflictWithConflict() { 
	    Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
	    Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
	    try {
	        a1.checkConflict(a2);
	        fail("A ConflictException was NOT thrown when two Activities had a day/time conflict.");
	    } catch (ConflictException e) {
	        assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
	        assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM", a2.getMeetingString());
	    }
	}

}
