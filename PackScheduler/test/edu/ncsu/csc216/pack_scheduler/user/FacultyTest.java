package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A test class to test Faculty
 * 
 * @author Meles Meles
 *
 */
public class FacultyTest {
	/** Valid first name used for testing. */
	private final String first = "jhon";

	/** Valid last name used for testing. */
	private final String last = "doe";

	/** Valid id used for testing. */
	private final String id = "doej";

	/** Alternate valid id used for testing */
	private final String validId = "hmm1";

	/** Valid email used for testing. */
	private final String email = "doej2@ncsu.edu";

	/** Valid Email */
	private final String validEmail = "email@ncsu.edu";

	/** Valid password used for testing. */
	private final String password = "Something";

	/** Alternate valid password used for testing */
	private final String validPassword = "Password";

	/** Valid creditHours used for testing */
	private final int courses = 2;

	/**
	 * Tests the default faculty constructor
	 */

	@Test
	public void testFaculty() {
		Faculty f = null;
		// Constructing invalid facultys, firstName = null.
		try {
			f = new Faculty(null, last, id, email, password, courses);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}
		// Constructing invalid faculty, lastName = null.
		try {
			f = new Faculty(first, null, id, email, password, courses);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}
		// Constructing invalid faculty, id = null.
		try {
			f = new Faculty(first, last, null, email, password, courses);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}

		// Constructing invalid faculty, email = null.
		try {
			f = new Faculty(first, last, id, null, password, courses);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}
		// Constructing invalid faculty, password = null.
		try {
			f = new Faculty(first, last, id, email, null, courses);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}
		// Constructing invalid faculty, maxcourses = 0.
		try {
			f = new Faculty(first, last, id, email, password, 0);
			fail("Invalid faculty constructed");
		} catch (IllegalArgumentException e) {
			assertNull(f);
		}
		f = new Faculty(first, last, id, email, password, courses);
		assertNotNull(f);
		assertEquals(first, f.getFirstName());
		assertEquals(last, f.getLastName());
		assertEquals(id, f.getId());
		assertEquals(email, f.getEmail());
		assertEquals(password, f.getPassword());
		assertEquals(courses, f.getMaxCourses());
	}

	/**
	 * Tests the toString method in faculty.java
	 */
	@Test
	public void testToString() {
		User f1 = new Faculty(first, last, id, email, password, courses);
		String faculty1 = "jhon,doe,doej,doej2@ncsu.edu,Something,2";
		assertEquals(faculty1, f1.toString());

	}

	/**
	 * Tests the hashCode method in Faculty.java
	 */
	@Test
	public void testHashCode() {
		User s1 = new Faculty(first, last, id, email, password, courses);
		User s2 = new Faculty(first, last, id, email, password, courses);
		User s3 = new Faculty(first, last, id, email, validPassword, courses);
		User s4 = new Faculty(first, last, id, validEmail, password, courses);
		User s5 = new Faculty(first, last, validId, email, password, courses);
		User s6 = new Faculty(first, first, id, email, password, courses);
		User s7 = new Faculty(last, last, id, email, password, courses);
		assertEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
	}

	/**
	 * Tests the equals method in Student.java
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Faculty(first, last, id, email, password, courses);
		User s2 = new Faculty(first, last, id, email, password, courses);
		User s3 = new Faculty(first, last, id, email, validPassword, courses);
		User s4 = new Faculty(first, last, id, validEmail, password, courses);
		User s5 = new Faculty(first, last, validId, email, password, courses);
		User s6 = new Faculty(first, first, id, email, password, courses);
		User s7 = new Faculty(last, last, id, email, password, courses);
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
	}

}
