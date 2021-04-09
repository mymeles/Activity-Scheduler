/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;

/**
 * Tests the functionality of methods in the Schedule class
 * @author Alex Bernard
 *
 */
public class ScheduleTest {

	/**
	 * Tests the default constructor for Schedule
	 */
	@Test
	public void testSchedule() {
		Schedule testSchedule = new Schedule();
		assertEquals(0, testSchedule.getScheduledCourses().length);
		assertEquals("My Schedule", testSchedule.getTitle());
	}

	/**
	 * Tests the addCourseToSchedule method as well as the canAdd() method
	 */
	@Test
	public void testAddCourseToSchedule() {
		Schedule testSchedule = new Schedule();
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		
		//Verify canAdd courses
		assertTrue(testSchedule.canAdd(testCatalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue(testSchedule.canAdd(testCatalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue(testSchedule.canAdd(testCatalog.getCourseFromCatalog("CSC217", "601")));		
		
		// Add multiple courses
		assertTrue(testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue(testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue(testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC217", "601")));
		
		// Add Null Object
		try {
			testSchedule.addCourseToSchedule(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(3, testSchedule.getScheduledCourses().length);
			assertFalse(testSchedule.canAdd(null));
		}
		
		// Add duplicate course
		try {
			testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("You are already enrolled in CSC116", e.getMessage());
			assertFalse(testSchedule.canAdd(testCatalog.getCourseFromCatalog("CSC116", "001")));
		}
		
		// Add course with conflict exception
		try {
			testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC226", "001"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
			assertFalse(testSchedule.canAdd(testCatalog.getCourseFromCatalog("CSC226", "001")));
		}
	}

	/**
	 * Test functionality of removeCourseFromSchedule
	 */
	@Test
	public void testRemoveCourseFromSchedule() {
		Schedule testSchedule = new Schedule();
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		testSchedule.removeCourseFromSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		// Add multiple courses
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC216", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC217", "601"));
		
		// Remove Course not present in list
		assertFalse(testSchedule.removeCourseFromSchedule(testCatalog.getCourseFromCatalog("CSC116", "002")));
		
		// Remove Course
		try {
			testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC226", "001"));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("The course cannot be added due to a conflict.", e.getMessage());
		}
		assertTrue(testSchedule.removeCourseFromSchedule(testCatalog.getCourseFromCatalog("CSC116", "001")));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC226", "001"));
		assertFalse(testSchedule.removeCourseFromSchedule(testCatalog.getCourseFromCatalog("CSC116", "001")));
		assertFalse(testSchedule.removeCourseFromSchedule(null));
		
	}

	/**
	 * Tests functionality of resetSchedule method
	 */
	@Test
	public void testResetSchedule() {
		Schedule testSchedule = new Schedule();
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		// Add multiple courses
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC216", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC217", "601"));
		testSchedule.setTitle("NewSchedule");
		assertEquals("NewSchedule", testSchedule.getTitle());
		assertEquals(3, testSchedule.getScheduledCourses().length);
		
		testSchedule.resetSchedule();
		assertEquals("My Schedule", testSchedule.getTitle());
		assertEquals(0, testSchedule.getScheduledCourses().length);
	}

	/**
	 * Tests schedule getScheduledCourses method
	 */
	@Test
	public void testGetScheduledCourses() {
		Schedule testSchedule = new Schedule();
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		String[][] testCourses = testSchedule.getScheduledCourses();
		assertEquals(0, testCourses.length);
		
		// Add multiple courses
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC216", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC217", "601"));
		testCourses = testSchedule.getScheduledCourses();
		assertEquals("CSC116", testCourses[0][0]);
		assertEquals("001", testCourses[0][1]);
		assertEquals("Intro to Programming - Java", testCourses[0][2]);
		assertEquals("MW 9:10AM-11:00AM", testCourses[0][3]);
		assertEquals("CSC216", testCourses[1][0]);
		assertEquals("CSC217", testCourses[2][0]);
	}

	/**
	 * Tests functionality of setTitle method
	 */
	@Test
	public void testSetTitle() {
		Schedule testSchedule = new Schedule();
		testSchedule.setTitle("Better Schedule");
		assertEquals("Better Schedule", testSchedule.getTitle());
		try {
			testSchedule.setTitle(null);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Title cannot be null.", e.getMessage());
			assertEquals("Better Schedule", testSchedule.getTitle());
		}
	}

	/**
	 * Tests the functionality of the getTitle method
	 */
	@Test
	public void testGetTitle() {
		Schedule testSchedule = new Schedule();
		assertEquals("My Schedule", testSchedule.getTitle());
		testSchedule.setTitle("Other Title");
		assertEquals("Other Title", testSchedule.getTitle());
		testSchedule.resetSchedule();
		assertEquals("My Schedule", testSchedule.getTitle());
	}
	
	/**
	 * Tests the getScheduleCredits method
	 */
	@Test
	public void testGetScheduleCredits() {
		Schedule testSchedule = new Schedule();
		CourseCatalog testCatalog = new CourseCatalog();
		testCatalog.loadCoursesFromFile("test-files/course.txt");
		
		//Add courses to the schedule
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC216", "001"));
		testSchedule.addCourseToSchedule(testCatalog.getCourseFromCatalog("CSC217", "601"));
		
		assertEquals(7, testSchedule.getScheduleCredits());
		
		//Remove a course from the schedule
		testSchedule.removeCourseFromSchedule(testCatalog.getCourseFromCatalog("CSC116", "001"));
		
		assertEquals(4, testSchedule.getScheduleCredits());
	}

}
