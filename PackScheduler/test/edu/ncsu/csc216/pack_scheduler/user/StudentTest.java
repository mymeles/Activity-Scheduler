package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the Student class
 * 
 * All getter methods will be Tested through other method tests, thus they have
 * been ommitted.
 * 
 * @author meles
 *
 */
public class StudentTest {

	/** students first name */
	private static final String FIRST_NAME = "Meles";
	/** students las name */
	private static final String LAST_NAME = "Meles";
	/** students id */
	private static final String ID = "mmeles";
	/** filed for email */
	private static final String EMAIL = "meles@ncsu.edu";
	/** filed for hashPW */
	private static final String PASSWORD = "MM@testing";
	/** field for max credit */
	private static final int CREDITS = 16;
	/** Constant for maximum credit */
	public final static int MAX_CREDITS = 18;

	/**
	 * Tests the student constructor with all the field parameters.
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		// Test a valid construction
		Student s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
			assertEquals(null, s.getFirstName());

			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD, CREDITS);
			assertEquals(null, s.getFirstName());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, null, EMAIL, PASSWORD, CREDITS);
			assertEquals(null, s.getId());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s); 
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "meles.meles", PASSWORD, CREDITS);
			assertEquals("meles.meles", s.getEmail());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "", CREDITS);
			assertEquals("", s.getPassword());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, -2);
			assertEquals(-2, s.getMaxCredits());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	/**
	 * Tests constructor with 5 parameter is constructed correctly
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s = null;
		try {
			s = new Student(null, LAST_NAME, ID, EMAIL, PASSWORD);
			assertEquals(null, s.getFirstName());

			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, null, ID, EMAIL, PASSWORD);
			assertEquals(null, s.getFirstName());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, "", EMAIL, PASSWORD);
			assertEquals("", s.getId());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, "meles.meles", PASSWORD);
			assertEquals("meles.meles", s.getEmail());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		try {
			s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "");
			assertEquals("", s.getPassword());
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

	}

	/**
	 * Tests email is set correctly
	 */
	@Test
	public void testSetEmail() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		try {
			s.setEmail(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(EMAIL, s.getEmail());
		}

		try {
			s.setEmail("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(EMAIL, s.getEmail());
		}

		try {
			s.setEmail("melesncsu.edu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(EMAIL, s.getEmail());
		}

		try {
			s.setEmail("meles@ncsuedu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(EMAIL, s.getEmail());
		}

		try {
			s.setEmail("meles.meles@ncsu");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(EMAIL, s.getEmail());
		}
	}

	/**
	 * Test password is set Correctly
	 */
	@Test
	public void testSetPassword() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		try {
			s.setPassword(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(PASSWORD, s.getPassword());
		}

		try {
			s.setPassword("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(PASSWORD, s.getPassword());
		}
	}

	/**
	 * Tests max credits is set correctly
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);

		try {
			s.setMaxCredits(-2);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(CREDITS, s.getMaxCredits());
		}
	}

	/**
	 * Tests first name is set correctly
	 */
	@Test
	public void testSetFirstName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
			s.setFirstName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
		}

		try {
			s.setFirstName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(FIRST_NAME, s.getFirstName());
		}

	}

	/**
	 * Test last name is set correctly
	 */
	@Test
	public void testSetLastName() {
		Student s = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		try {
			s.setLastName(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(LAST_NAME, s.getFirstName());
		}

		try {
			s.setLastName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(LAST_NAME, s.getFirstName());
		}
	}

	/**
	 * Tests that the equals method works for all Course fields.
	 */
	@Test
	public void testEqualsObject() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		Student s3 = new Student(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "000", EMAIL, PASSWORD, CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID, "Different@ncsu.edu", PASSWORD, CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Null", CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 5);

		// Test for equality in both direction
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));

		// Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
	}

	/**
	 * Test that hashCode works correctly
	 */
	@Test
	public void testHashCode() {
		Student s1 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		Student s3 = new Student(FIRST_NAME, "Different", ID, EMAIL, PASSWORD, CREDITS);
		Student s4 = new Student(FIRST_NAME, LAST_NAME, "000", EMAIL, PASSWORD, CREDITS);
		Student s5 = new Student(FIRST_NAME, LAST_NAME, ID, "Different@ncsu.edu", PASSWORD, CREDITS);
		Student s6 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, "Null", CREDITS);
		Student s7 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, 5);

		// Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());

		// Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
	}

	/**
	 * Tests that toString returns the correct comma-sepreates value
	 */
	@Test
	public void testToString() {
		Student s0 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, CREDITS);
		String s1 = "Meles,Meles,mmeles,meles@ncsu.edu,MM@testing,16";
		assertEquals(s1, s0.toString());

		Student s2 = new Student(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD);
		String s3 = "Meles,Meles,mmeles,meles@ncsu.edu,MM@testing,18";
		assertEquals(s3, s2.toString());

	}

}
