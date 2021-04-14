/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the functionality of methods in CourseRoll
 * 
 * @author Alex Bernard
 *
 */
public class CourseRollTest {

	/** Course name */
	private static final String NAME = "CSC216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** enrollment capacity for a given course */
	private static final int ENCAP = 10;
	/** Course meeting days */
	private static final String MEETING_DAYS = "MW";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;

	/** Valid first name used for testing. */
	private final String first = "Alex";

	/** Valid last name used for testing. */
	private final String last = "Bernard";

	/** Valid id used for testing. */
	private final String id = "ajberna2";

	/** Valid email used for testing. */
	private final String email = "ajberna2@ncsu.edu";

	/** Valid password used for testing. */
	private final String password = "Something";

	/** Valid creditHours used for testing */
	private final int credits = 15;

	/**
	 * Tests the constructor for CourseRoll
	 */
	@Test
	public void testCourseRoll() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);

		CourseRoll testRoll;
		// Construct valid CourseRoll objects
		testRoll = new CourseRoll(c, 10);
		assertNotNull(testRoll);
		assertEquals(10, testRoll.getEnrollmentCap());
		testRoll = new CourseRoll(c, 250);
		assertEquals(250, testRoll.getEnrollmentCap());

		// Construct invalid CourseRoll objects
		testRoll = null;
		// enrollmentCap > 250
		try {
			testRoll = new CourseRoll(c, 251);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(testRoll);
		}
		// enrollmentCap < 10
		try {
			testRoll = new CourseRoll(c, 9);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(testRoll);
		}
	}

	/**
	 * Tests the getEnrollmentCap() method for CourseRoll
	 */
	@Test
	public void testGetEnrollmentCap() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 20);

		// setEnrollment cap 20 -> 250
		testRoll.setEnrollmentCap(250);
		assertEquals(250, testRoll.getEnrollmentCap());

		// setEnrollment cap 250 -> 10
		testRoll.setEnrollmentCap(10);
		assertEquals(10, testRoll.getEnrollmentCap());
	}

	/**
	 * Tests the setEnrollmentCap() method for CourseRoll
	 */
	@Test
	public void testSetEnrollmentCap() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 20);
		Student[] testStudents = new Student[15];
		// enroll 15 students
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
			testRoll.enroll(testStudents[i]);
		}
		assertEquals(20, testRoll.getEnrollmentCap());

		// setEnrollment cap 20 -> 25
		testRoll.setEnrollmentCap(25);
		assertEquals(25, testRoll.getEnrollmentCap());

		// setEnrollment cap 25 -> 16
		testRoll.setEnrollmentCap(15);
		assertEquals(15, testRoll.getEnrollmentCap());

		// Invalid Case: enrollmentCap < roll.size()
		try {
			testRoll.setEnrollmentCap(10);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(15, testRoll.getEnrollmentCap());
		}

	}

	/**
	 * Tests the enroll() method for CourseRoll
	 */
	@Test
	public void testEnroll() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 15);
		Student[] testStudents = new Student[15];
		// enroll 15 students when enrollmentCap == 15
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
			testRoll.enroll(testStudents[i]);
		}

		assertEquals(15, testRoll.getEnrollmentCap());

		// Invalid enroll(): null student
		testRoll.setEnrollmentCap(20);
		try {
			testRoll.enroll(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Student cannot be null.", e.getMessage());
		}

		// Invalid enroll(): student is already enrolled
		try {
			testRoll.enroll(testStudents[0]);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Student is already enrolled.", e.getMessage());
		}
	}

	/**
	 * Tests the drop() method for CourseRoll
	 */
	@Test
	public void testDrop() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 15);
		Student[] testStudents = new Student[15];
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
			testRoll.enroll(testStudents[i]);
		}
		assertEquals(0, testRoll.getOpenSeats());
		testRoll.drop(testStudents[0]);
		assertEquals(1, testRoll.getOpenSeats());

		// Invalid Case: Remove null student
		try {
			testRoll.drop(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Cannot remove null student", e.getMessage());
			assertEquals(1, testRoll.getOpenSeats());
		}

		// Remove student not present in class
		testRoll.drop(testStudents[0]);
		assertEquals(1, testRoll.getOpenSeats());
		testRoll.enroll(testStudents[0]);
		assertEquals(0, testRoll.getOpenSeats());

		Student s = new Student(first, last, id, email, password, credits);

		assertTrue(testRoll.canEnroll(s));
		testRoll.enroll(s);

		testRoll.drop(testStudents[0]);
		assertEquals(0, testRoll.getOpenSeats());

	}

	/**
	 * Tests the getOpenSeats() method for CourseRoll
	 */
	@Test
	public void testGetOpenSeats() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 15);
		// Tests that enrollmentCap == openSeats when no students are enrolled
		assertEquals(testRoll.getEnrollmentCap(), testRoll.getOpenSeats());

		Student[] testStudents = new Student[15];
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
			testRoll.enroll(testStudents[i]);
		}
		assertEquals(0, testRoll.getOpenSeats());
		testRoll.drop(testStudents[0]);
		assertEquals(1, testRoll.getOpenSeats());
		testRoll.setEnrollmentCap(14);
		assertEquals(0, testRoll.getOpenSeats());
		testRoll.setEnrollmentCap(20);
		assertEquals(6, testRoll.getOpenSeats());
	}

	/**
	 * Tests the canEnroll() method for CourseRoll
	 */
	@Test
	public void testCanEnroll() {
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		CourseRoll testRoll;
		testRoll = new CourseRoll(c, 10);
		Student s = new Student(first, last, id, email, password, credits);

		assertTrue(testRoll.canEnroll(s));
		testRoll.enroll(s);
		assertFalse(testRoll.canEnroll(s));

	}

}
