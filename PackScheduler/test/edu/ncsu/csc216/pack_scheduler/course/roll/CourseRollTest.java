/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Tests the functionality of methods in CourseRoll
 * @author Alex Bernard
 *
 */
public class CourseRollTest {

	/**
	 * Tests the constructor for CourseRoll
	 */
	@Test
	public void testCourseRoll() {
		CourseRoll testRoll;
		// Construct valid CourseRoll objects
		testRoll = new CourseRoll(10);
		assertNotNull(testRoll);
		assertEquals(10, testRoll.getEnrollmentCap());
		testRoll = new CourseRoll(250);
		assertEquals(250, testRoll.getEnrollmentCap());
		
		// Construct invalid CourseRoll objects
		testRoll = null;
		// enrollmentCap > 250
		try {
			testRoll = new CourseRoll(251);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(testRoll);
		}
		// enrollmentCap < 10
		try {
			testRoll = new CourseRoll(9);
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
		CourseRoll testRoll;
		testRoll = new CourseRoll(20);
		
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
		CourseRoll testRoll;
		testRoll = new CourseRoll(20);
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
		} catch(IllegalArgumentException e) {
			assertEquals(15, testRoll.getEnrollmentCap());
		}
		
	}

	/**
	 * Tests the enroll() method for CourseRoll
	 */
	@Test
	public void testEnroll() {
		CourseRoll testRoll;
		testRoll = new CourseRoll(15);
		Student[] testStudents = new Student[15];
		// enroll 15 students when enrollmentCap == 15
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
			testRoll.enroll(testStudents[i]);
		}
		
		assertEquals(15, testRoll.getEnrollmentCap());
		// Invalid enroll(): class full
		try {
			testRoll.enroll(new Student("firstName", "lastName", "id", "i@email.com", "password"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Course cannot exceed enrollmentCap.", e.getMessage());
		}
		
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
		CourseRoll testRoll;
		testRoll = new CourseRoll(15);
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
		} catch(IllegalArgumentException e) {
			assertEquals("Cannot remove null student", e.getMessage());
			assertEquals(1, testRoll.getOpenSeats());
		}
	
		// Remove student not present in class
		testRoll.drop(testStudents[0]);
		assertEquals(1, testRoll.getOpenSeats());
	}

	/**
	 * Tests the getOpenSeats() method for CourseRoll
	 */
	@Test
	public void testGetOpenSeats() {
		CourseRoll testRoll;
		testRoll = new CourseRoll(15);
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
		CourseRoll testRoll;
		testRoll = new CourseRoll(10);
		Student[] testStudents = new Student[15];
		for (int i = 0; i < 15; i++) {
			testStudents[i] = new Student("firstName" + i, "lastName" + i, "id" + i, i + "@email.com", "password" + i);
		}
		// Valid/Invalid tests. canEnroll is true when a student has not been added, and false afterward
		for (int i = 0; i < 10; i++) {
			assertTrue(testRoll.canEnroll(testStudents[i]));
			testRoll.enroll(testStudents[i]);
			assertFalse(testRoll.canEnroll(testStudents[i]));
		}
		// Invalid test: Student cannot be added to a fully enrolled course
		assertFalse(testRoll.canEnroll(testStudents[10]));
		
	}

}
