package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * This class tests methods within the Student.java program
 * 
 * @author Alex Bernard
 *
 */
public class StudentTest {

	/** Valid first name used for testing. */
	private final String first = "Alex";

	/** Valid last name used for testing. */
	private final String last = "Bernard";

	/** Valid id used for testing. */
	private final String id = "ajberna2";

	/** Alternate valid id used for testing */
	private final String validId = "ajbernar";

	/** Valid email used for testing. */
	private final String email = "ajberna2@ncsu.edu";

	/** Invalid Email, no @ symbol */
	private final String invalidEmail1 = "emailncsu.edu";

	/** Invalid Email, no . symbol */
	private final String invalidEmail2 = "email@ncsuedu";

	/** Invalid Email, . before @ */
	private final String invalidEmail3 = "email.ncsu@edu";

	/** Valid Email */
	private final String validEmail = "email@ncsu.edu";

	/** Valid password used for testing. */
	private final String password = "Something";

	/** Alternate valid password used for testing */
	private final String validPassword = "Password";

	/** Valid creditHours used for testing */
	private final int credits = 15;

	/** Invalid creditHours, less than the minimum limit. */
	private final int lessCredits = 2;

	/** Invalid creditHours, greater than the max limit. */
	private final int moreCredits = 19;

	/**
	 * Tests the default Student constructor
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		Student s = null;
		// Constructing invalid students, firstName = null.
		try {
			s = new Student(null, last, id, email, password, credits);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Constructing invalid student, lastName = null.
		try {
			s = new Student(first, null, id, email, password, credits);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Constructing invalid student, id = null.
		try {
			s = new Student(first, last, null, email, password, credits);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Constructing invalid student, email = null.
		try {
			s = new Student(first, last, id, null, password, credits);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Constructing invalid student, password = null.
		try {
			s = new Student(first, last, id, email, null, credits);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		// Constructing invalid student, maxCredits = 0.
		try {
			s = new Student(first, last, id, email, password, 0);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
		s = new Student(first, last, id, email, password, credits);
		assertNotNull(s);
		assertEquals(first, s.getFirstName());
		assertEquals(last, s.getLastName());
		assertEquals(id, s.getId());
		assertEquals(email, s.getEmail());
		assertEquals(password, s.getPassword());
		assertEquals(credits, s.getMaxCredits());
	}

	/**
	 * Tests the Student constructor without an int parameter.
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		User s = null;

		// Constructing invalid students, firstName = null.
		try {
			s = new Student(null, last, id, email, password);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Constructing invalid student, lastName = null.
		try {
			s = new Student(first, null, id, email, password);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Constructing invalid student, id = null.
		try {
			s = new Student(first, last, null, email, password);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Constructing invalid student, email = null.
		try {
			s = new Student(first, last, id, null, password);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}

		// Constructing invalid student, password = null.
		try {
			s = new Student(first, last, id, email, null);
			fail("Invalid student constructed");
		} catch (IllegalArgumentException e) {
			assertNull(s);
		}
	}

	/**
	 * Tests the setFirstName method of Student.java.
	 */
	@Test
	public void testSetFirstName() {
		User s = new Student(first, last, id, email, password, credits);
		// Tests setFirstName(null);
		try {
			s.setFirstName(null);
			fail("Set invalid firstName.");
		} catch (IllegalArgumentException e) {
			assertEquals(first, s.getFirstName());
		}

		// Tests setFirstName("");
		try {
			s.setFirstName("");
			fail("Set invalid firstName");
		} catch (IllegalArgumentException e) {
			assertEquals(first, s.getFirstName());
		}

		// Tests valid firstName
		try {
			s.setFirstName(last);
		} catch (IllegalArgumentException e) {
			assertEquals(last, s.getFirstName());
		}
	}

	/**
	 * Tests the setLastName method of Student.java
	 */
	@Test
	public void testSetLastName() {
		User s = new Student(first, last, id, email, password, credits);
		// Tests setLastName(null);
		try {
			s.setLastName(null);
			fail("Set invalid lastName.");
		} catch (IllegalArgumentException e) {
			assertEquals(last, s.getLastName());
		}

		// Tests setLastName("");
		try {
			s.setLastName("");
			fail("Set invalid lastName");
		} catch (IllegalArgumentException e) {
			assertEquals(last, s.getLastName());
		}

		// Tests valid LastName
		try {
			s.setLastName(first);
		} catch (IllegalArgumentException e) {
			assertEquals(first, s.getLastName());
		}
	}

	/**
	 * Tests the setEmail method in Student.java
	 */
	@Test
	public void testSetEmail() {
		User s = new Student(first, last, id, email, password, credits);
		// Tests invalid Email without @ symbol.
		try {
			s.setEmail(invalidEmail1);
			fail("Set invalid Email.");
		} catch (IllegalArgumentException e) {
			assertEquals(email, s.getEmail());
		}

		// Tests invalid Email without . symbol.
		try {
			s.setEmail(invalidEmail2);
			fail("Set invalid Email.");
		} catch (IllegalArgumentException e) {
			assertEquals(email, s.getEmail());
		}

		// Tests invalid Email with . before @ symbol.
		try {
			s.setEmail(invalidEmail3);
			fail("Set invalid Email.");
		} catch (IllegalArgumentException e) {
			assertEquals(email, s.getEmail());
		}

		// Tests invalid Email with null.
		try {
			s.setEmail(null);
			fail("Set invalid Email.");
		} catch (IllegalArgumentException e) {
			assertEquals(email, s.getEmail());
		}

		// Tests invalid blank Email.
		try {
			s.setEmail("");
			fail("Set invalid Email.");
		} catch (IllegalArgumentException e) {
			assertEquals(email, s.getEmail());
		}

		// Tests valid Email
		s.setEmail(validEmail);
		assertEquals(validEmail, s.getEmail());
	}

	/**
	 * Tests the setPassword method in Student.java.
	 */
	@Test
	public void testSetPassword() {
		User s = new Student(first, last, id, email, password, credits);
		// Tests invalid password with setPassword(null);
		try {
			s.setPassword(null);
			fail("Set invalid password.");
		} catch (IllegalArgumentException e) {
			assertEquals(password, s.getPassword());
		}

		// Tests invalid password with setPassword("");
		try {
			s.setPassword("");
			fail("Set invalid password");
		} catch (IllegalArgumentException e) {
			assertEquals(password, s.getPassword());
		}

		// Tests valid password with setPassword(validPassword);
		try {
			s.setPassword(validPassword);
		} catch (IllegalArgumentException e) {
			assertEquals(validPassword, s.getPassword());
		}
	}

	/**
	 * Tests the setMaxCredits method in Student.java
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student(first, last, id, email, password, credits);
		// Tests an invalid amount of maxCredits, less than the minimum.
		try {
			s.setMaxCredits(lessCredits);
			fail("Set invalid credits.");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid max credits", e.getMessage());
		}

		// Tests an invalid amount of maxCredits, greater than the maximum.
		try {
			s.setMaxCredits(moreCredits);
			fail("Set invalid credits.");
		} catch (IllegalArgumentException e) {
			assertEquals(credits, s.getMaxCredits());
		}

		// Tests valid amounts of maxCredits, at inclusive boundary values.
		s.setMaxCredits(3);
		assertEquals(3, s.getMaxCredits());
		s.setMaxCredits(18);
		assertEquals(18, s.getMaxCredits());
	}

	/**
	 * Tests the equals method in Student.java
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student(first, last, id, email, password, credits);
		User s2 = new Student(first, last, id, email, password, credits);
		User s3 = new Student(first, last, id, email, validPassword, credits);
		User s4 = new Student(first, last, id, validEmail, password, credits);
		User s5 = new Student(first, last, validId, email, password, credits);
		User s6 = new Student(first, first, id, email, password, credits);
		User s7 = new Student(last, last, id, email, password, credits);
		User s8 = new Student(first, last, id, email, password);
		assertTrue(s1.equals(s2));
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
	}

	/**
	 * Tests the hashCode method in Student.java
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student(first, last, id, email, password, credits);
		User s2 = new Student(first, last, id, email, password, credits);
		User s3 = new Student(first, last, id, email, validPassword, credits);
		User s4 = new Student(first, last, id, validEmail, password, credits);
		User s5 = new Student(first, last, validId, email, password, credits);
		User s6 = new Student(first, first, id, email, password, credits);
		User s7 = new Student(last, last, id, email, password, credits);
		User s8 = new Student(first, last, id, email, password);
		assertEquals(s1.hashCode(), s2.hashCode());
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
	}

	/**
	 * Tests the toString method in student.java
	 */
	@Test
	public void testToString() {
		User s1 = new Student(first, last, id, email, password, credits);
		User s2 = new Student(last, first, validId, validEmail, validPassword);
		String student1 = "Alex,Bernard,ajberna2,ajberna2@ncsu.edu,Something,15";
		String student2 = "Bernard,Alex,ajbernar,email@ncsu.edu,Password,18";
		assertEquals(student1, s1.toString());
		assertEquals(student2, s2.toString());
	}

	/**
	 * Tests that the student compare to methods positions student objects by
	 * comparing last names first, first names second, and ids last
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Zahir", "King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		Student s2 = new Student("Cassandra", "Schwartz", "cschwartz", "semper@imperdietornare.co.uk", "pw", 4);
		Student s3 = new Student(first, last, id, email, "pw", credits);
		// ID with Numbers
		Student s4 = new Student(first, last, validId, email, "pw", credits);
		Student s5 = new Student(first, last, id, email, "pw", credits);
		Student s6 = new Student(last, last, id, email, password, credits);
		// Last Name with Numbers
		Student s7 = new Student("Zahir", "2King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		// Last Name with Numbers2
		Student s8 = new Student("Zahir", "0King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		// Shorter Last Name
		Student s13 = new Student("Zahir", "Kin", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		
		// First Name with Numbers
		Student s9 = new Student("0Zahir", "King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		// First Name with Numbers2
		Student s10 = new Student("1Zahir", "King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		
		
		// ID With Numbers
		Student s11 = new Student(first, last, "ajberna3", email, "pw", credits);
		// ID With Letters
		Student s12 = new Student("Zahir", "King", "zkins", "orci.Done@ametmassaQuisque.com", "pw", 15);
		
		
		// Compare Equal Students
		assertEquals(0, s3.compareTo(s5));
		
		// LastName Tests
		// Compare Letters Only
		assertEquals(-1, s1.compareTo(s2));
		assertEquals(1, s2.compareTo(s1));
		// Compare Letters v Numbers
		assertEquals(-1, s7.compareTo(s1));
		assertEquals(1, s1.compareTo(s7));
		// Compare Numbers Only
		assertEquals(-1, s8.compareTo(s7));
		assertEquals(1, s7.compareTo(s8));
		// Shorter v Longer Names
		assertEquals(1, s1.compareTo(s13));
		assertEquals(-1, s13.compareTo(s1));
		
		// FirstName Tests
		// Compare Letters Only
		assertEquals(-1, s3.compareTo(s6));
		assertEquals(1, s6.compareTo(s3));
		// Compare Numbers v Letters
		assertEquals(-1, s9.compareTo(s1));
		assertEquals(1, s1.compareTo(s9));
		// Compare Numbers Only
		assertEquals(-1, s9.compareTo(s10));
		assertEquals(1, s10.compareTo(s9));
		
		// ID Tests
		// Compare Letters v Letters
		assertEquals(-1, s1.compareTo(s12));
		assertEquals(1, s12.compareTo(s1));
		// Compare Number v Letters
		assertEquals(1, s4.compareTo(s3));
		assertEquals(-1, s3.compareTo(s4));
		// Compare Number v Number
		assertEquals(1, s11.compareTo(s3));
		assertEquals(-1, s3.compareTo(s11));
	}
	
	/**
	 * Tests the canAdd() method
	 */
	@Test
	public void testCanAdd() {
		Student s1 = new Student("Zahir", "King", "zking", "orci.Done@ametmassaQuisque.com", "pw", 15);
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		Course c = testCatalog.getCourseFromCatalog("CSC116", "003");
		Course d = testCatalog.getCourseFromCatalog("CSC230", "001");
		Course e = testCatalog.getCourseFromCatalog("CSC226", "001");
		Course f = testCatalog.getCourseFromCatalog("CSC216", "601");
		Course g = new Course("CSC237", "Test", "601", 4, "jctetter", 10, "A");
		Course h = new Course("CSC337", "Tests", "601", 3, "dkgonsal", 10, "A");

		
		//Add courses to student's schedule
		s1.getSchedule().addCourseToSchedule(c);
		s1.getSchedule().addCourseToSchedule(d);
		s1.getSchedule().addCourseToSchedule(e);
		s1.getSchedule().addCourseToSchedule(f);
		
		assertEquals(12, s1.getSchedule().getScheduleCredits());
		
		//canAdd a duplicate courses 
		assertFalse(s1.canAdd(c));
		assertFalse(s1.canAdd(e));
		
		//canAdd a null course
		assertFalse(s1.canAdd(null));
		
		//canAdd a course that makes scheduled credits > s1.maxCredits
		assertFalse(s1.canAdd(g));
		
		//canAdd a course that makes scheduled credits = s1.maxCredits
		assertTrue(s1.canAdd(h));
	}
}
