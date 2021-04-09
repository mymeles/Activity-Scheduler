/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality of methods within the Activity class.
 * 
 * @author Alex Bernard
 *
 */
public class ActivityTest {

	/**
	 * Tests the checkConflict method of Activity.java
	 */
	@Test
	public void testCheckConflict() {
		Activity a1 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330,
				1445);
		Activity a2 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "TH", 1330,
				1445);
		Activity a3 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "M", 1330,
				1445);
		Activity a4 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MT", 1200,
				1330);
		Activity a5 = new Course("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MT", 1445,
				1600);
		Activity a6 = new Course("CS216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "A");
		Activity a7 = new Course("CSC217", "Software Development Fundamentals Lab", "002", 3, "sesmith5", 10, "A");

		// testCheckConflict same day same times
		try {
			// Call to checkConflict
			a1.checkConflict(a2);
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "TH 1:30PM-2:45PM",
					a2.getMeetingString());
		} catch (ConflictException e) {
			fail("A ConflictException was thrown when two Activities at the same time on completely distinct days were compared.");
		}

		//
		try {
			a1.checkConflict(a3);
			fail();
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "MW 1:30PM-2:45PM", a1.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "M 1:30PM-2:45PM",
					a3.getMeetingString());
		}

		// testCheckConflict startTime of this equals endTime of
		// possiblyConflictingActivity
		try {
			a2.checkConflict(a4);
			fail();
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 12:00PM-1:30PM",
					a4.getMeetingString());
		}

		// testCheckConflict: startTime of possibleConflictingActivity equals endTime of
		// this
		try {
			a4.checkConflict(a2);
			fail();
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 12:00PM-1:30PM",
					a4.getMeetingString());
		}

		// testCheckConflict startTime of this equals endTime of
		// possiblyConflictingActivity
		try {
			a2.checkConflict(a5);
			fail();
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 2:45PM-4:00PM",
					a5.getMeetingString());
		}

		// testCheckConflict endTime of this equals endTime of
		// possiblyConflictingActivity
		try {
			a5.checkConflict(a2);
			fail();
		} catch (ConflictException e) {
			assertEquals("Incorrect meeting string for this Activity.", "TH 1:30PM-2:45PM", a2.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "MT 2:45PM-4:00PM",
					a5.getMeetingString());
		}

		// testCheckConflict two arranged classes
		try {
			a6.checkConflict(a7);
			assertEquals("Incorrect meeting string for this Activity.", "Arranged", a6.getMeetingString());
			assertEquals("Incorrect meeting string for possibleConflictingActivity.", "Arranged",
					a7.getMeetingString());
		} catch (ConflictException e) {
			fail();
		}
	}

}
