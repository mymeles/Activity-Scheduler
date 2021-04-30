package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.directory.FacultyDirectory;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * A test class for RegistrationManager
 * 
 * @author meles meles, Darren Gonsalves
 *
 */
public class RegistrationManagerTest {

	/** Instance for RegistrationManager */
	private RegistrationManager manager;
	/** Registrar user name */
	private String registrarUsername;
	/** Registrar password */
	private String registrarPassword;
	/** Properties file */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Sets up the CourseManager and clears the data.
	 * 
	 * @throws Exception if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.logout();
		manager.clearData();

		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			registrarUsername = prop.getProperty("id");
			registrarPassword = prop.getProperty("pw");
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot process properties file.");
		}
	}

	/**
	 * A test Method for getCourseCatalog
	 */
	@Test
	public void testGetCourseCatalog() {

		CourseCatalog c = manager.getCourseCatalog();
		assertEquals(0, c.getCourseCatalog().length);

		c.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", 10, "MW", 910, 1100);
		assertEquals(1, c.getCourseCatalog().length);

		c.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "A", 910, 1100);
		assertEquals(2, c.getCourseCatalog().length);

		c.loadCoursesFromFile("test-files/course.txt");
		assertEquals(13, c.getCourseCatalog().length);

	}

	/**
	 * A test Method for getStudentDirectory
	 */
	@Test
	public void testGetStudentDirectory() {
		StudentDirectory s = manager.getStudentDirectory();
		assertEquals(0, s.getStudentDirectory().length);

		s.loadStudentsFromFile("test-files/student_records.txt");
		assertEquals(10, s.getStudentDirectory().length);

	}

	/**
	 * A test Method for getStudentDirectory
	 */
	@Test
	public void testGetFacultyDirectory() {
		FacultyDirectory f = manager.getFacultyDirectory();

		f.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(8, f.getFacultyDirectory().length);

	}

	@Test
	public void testAddAndRemoveFromFaccultyToCourse() {
		StudentDirectory s = manager.getStudentDirectory();
		s.loadStudentsFromFile("test-files/student_records.txt");
		Student s1 = s.getStudentById("dnolan");
		
		CourseCatalog c = manager.getCourseCatalog();
		c.loadCoursesFromFile("test-files/course.txt");
		assertEquals(13, c.getCourseCatalog().length);

		FacultyDirectory f = manager.getFacultyDirectory();
		f.loadFacultyFromFile("test-files/faculty_records.txt");
		assertEquals(8, f.getFacultyDirectory().length);

		Faculty fy = f.getFacultyById("awitt");
		Course cr = c.getCourseFromCatalog("CSC116", "001");
		
		try {
			manager.login(s1.getId(), "pw");
			assertTrue(manager.addFacultyToCourse(cr, fy));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Illegal Action", e.getMessage());
		}

		manager.login(registrarUsername, registrarPassword);

		assertTrue(manager.addFacultyToCourse(cr, fy));
	}

	/**
	 * A test Method for Login
	 */
	@Test
	public void testLoginStudent() {

		// assert there is no user logged in
		assertEquals(null, manager.getCurrentUser());

		// initialize student directory
		StudentDirectory s = manager.getStudentDirectory();

		s.loadStudentsFromFile("test-files/student_records.txt");
		System.out.println(s.getStudentDirectory().length);
		Student s1 = s.getStudentById("dnolan");

		// login in a registarar
		assertTrue(manager.login(registrarUsername, registrarPassword));
		assertEquals(registrarUsername, manager.getCurrentUser().getId());
		manager.logout();
		assertEquals(null, manager.getCurrentUser());

		// login in a student with valid credintials
		assertTrue(manager.login(s1.getId(), "pw"));
		assertFalse(manager.login("efrost", "pw"));
		assertEquals(s1, manager.getCurrentUser());
		manager.logout();
		assertEquals(null, manager.getCurrentUser());

		// login invalid registrar
		assertFalse(manager.login(registrarUsername, "invalidpassword"));
		assertEquals(null, manager.getCurrentUser());

		// login in an invlaid student
		assertFalse(manager.login(s1.getId(), "invalid"));
		assertEquals(null, manager.getCurrentUser());

		try {
			manager.login("invalidid", "pw");
		} catch (IllegalArgumentException e) {
			assertEquals(null, manager.getCurrentUser());
			assertEquals("User doesn't exist.", e.getMessage());
		}
	}

	/**
	 * A test Method for Login
	 */
	@Test
	public void testLoginFaculty() {

		// assert there is no user logged in
		assertEquals(null, manager.getCurrentUser());

		// initialize student directory
		FacultyDirectory f = manager.getFacultyDirectory();

		f.loadFacultyFromFile("test-files/faculty_records.txt");
		System.out.println(f.getFacultyDirectory().length);
		Faculty f1 = f.getFacultyById("awitt");

		// login in a registarar
		assertTrue(manager.login(registrarUsername, registrarPassword));
		assertEquals(registrarUsername, manager.getCurrentUser().getId());
		manager.logout();
		assertEquals(null, manager.getCurrentUser());

		// login in a student with valid credintials
		assertTrue(manager.login(f1.getId(), "pw"));
		assertFalse(manager.login("efrost", "pw"));
		assertEquals(f1, manager.getCurrentUser());
		manager.logout();
		assertEquals(null, manager.getCurrentUser());

		// login invalid registrar
		assertFalse(manager.login(registrarUsername, "invalidpassword"));
		assertEquals(null, manager.getCurrentUser());

		// login in an invlaid student
		assertFalse(manager.login(f1.getId(), "invalid"));
		assertEquals(null, manager.getCurrentUser());

		try {
			manager.login("invalidid", "pw");
		} catch (IllegalArgumentException e) {
			assertEquals(null, manager.getCurrentUser());
			assertEquals("User doesn't exist.", e.getMessage());
		}
	}

	/**
	 * A test Method for Logout
	 */
	@Test
	public void testLogout() {

		assertTrue(manager.login(registrarUsername, registrarPassword));
		assertEquals(registrarUsername, manager.getCurrentUser().getId());
		manager.logout();
		assertEquals(null, manager.getCurrentUser());
	}

	/**
	 * Tests RegistrationManager.enrollStudentInCourse()
	 */
	@Test
	public void testEnrollStudentInCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// test if not logged in
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.enrollStudentInCourse() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.enrollStudentInCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals(
					"RegistrationManager.enrollStudentInCourse() - currentUser is registrar, so cannot enroll in course.",
					registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertTrue(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));

		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 3, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC216-001\n", 1, scheduleFrostArray.length);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "CSC216", scheduleFrostArray[0][0]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "001", scheduleFrostArray[0][1]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "Software Development Fundamentals",
				scheduleFrostArray[0][2]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "TH 1:30PM-2:45PM", scheduleFrostArray[0][3]);
		assertEquals("User: efrost\nCourse: CSC216-001\n", "9", scheduleFrostArray[0][4]);

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		// Check Student Schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9,
				scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals",
				scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM",
				scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "8", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n",
				"Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM",
				scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java",
				scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM",
				scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);

		manager.logout();
	}

	/**
	 * Tests RegistrationManager.dropStudentFromCourse()
	 */
	@Test
	public void testDropStudentFromCourse() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// test if not logged in
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.dropStudentFromCourse() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001"));
			fail("RegistrationManager.dropStudentFromCourse() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals(
					"RegistrationManager.dropStudentFromCourse() - currentUser is registrar, so cannot enroll in course.",
					registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertTrue(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));

		assertFalse(
				"Action: drop\nUser: efrost\nCourse: CSC217-211\nResults: False - student not enrolled in the course",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC217", "211")));
		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));

		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then removed\n", 0, scheduleFrostArray.length);

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 9,
				scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", 3, scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC216", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Software Development Fundamentals",
				scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 1:30PM-2:45PM",
				scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC226", scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "001", scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n",
				"Discrete Mathematics for Computer Scientists", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "MWF 9:35AM-10:25AM",
				scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[1][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "CSC116", scheduleHicksArray[2][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "003", scheduleHicksArray[2][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "Intro to Programming - Java",
				scheduleHicksArray[2][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "TH 11:20AM-1:10PM",
				scheduleHicksArray[2][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\n", "9", scheduleHicksArray[2][4]);

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 6,
				scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", 2,
				scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC216",
				scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "001",
				scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n",
				"Software Development Fundamentals", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n",
				"TH 1:30PM-2:45PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9",
				scheduleHicksArray[0][4]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "CSC116",
				scheduleHicksArray[1][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "003",
				scheduleHicksArray[1][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n",
				"Intro to Programming - Java", scheduleHicksArray[1][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n",
				"TH 11:20AM-1:10PM", scheduleHicksArray[1][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001\n", "9",
				scheduleHicksArray[1][4]);

		assertFalse("Action: drop\nUser: efrost\nCourse: CSC226-001\nResults: False - already dropped",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC226", "001")));

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC216-001\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC216", "001")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 3,
				scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", 1,
				scheduleHicksArray.length);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n",
				"CSC116", scheduleHicksArray[0][0]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n",
				"003", scheduleHicksArray[0][1]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n",
				"Intro to Programming - Java", scheduleHicksArray[0][2]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n",
				"TH 11:20AM-1:10PM", scheduleHicksArray[0][3]);
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001\n", "9",
				scheduleHicksArray[0][4]);

		assertTrue("Action: drop\nUser: efrost\nCourse: CSC116-003\nResults: True",
				manager.dropStudentFromCourse(catalog.getCourseFromCatalog("CSC116", "003")));

		// Check schedule
		ahicks = directory.getStudentById("ahicks");
		scheduleHicks = ahicks.getSchedule();
		assertEquals(
				"User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n",
				0, scheduleHicks.getScheduleCredits());
		scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals(
				"User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, removed CSC226-001, CSC216-001, CSC116-003\n",
				0, scheduleHicksArray.length);

		manager.logout();
	}

	/**
	 * Tests RegistrationManager.resetSchedule()
	 */
	@Test
	public void testResetSchedule() {
		StudentDirectory directory = manager.getStudentDirectory();
		directory.loadStudentsFromFile("test-files/student_records.txt");

		CourseCatalog catalog = manager.getCourseCatalog();
		catalog.loadCoursesFromFile("test-files/course_records.txt");

		manager.logout(); // In case not handled elsewhere

		// Test if not logged in
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is null, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertNull("RegistrationManager.resetSchedule() - currentUser is null, so cannot enroll in course.",
					manager.getCurrentUser());
		}

		// test if registrar is logged in
		manager.login(registrarUsername, registrarPassword);
		try {
			manager.resetSchedule();
			fail("RegistrationManager.resetSchedule() - If the current user is registrar, an IllegalArgumentException should be thrown, but was not.");
		} catch (IllegalArgumentException e) {
			assertEquals("RegistrationManager.resetSchedule() - currentUser is registrar, so cannot enroll in course.",
					registrarUsername, manager.getCurrentUser().getId());
		}
		manager.logout();

		manager.login("efrost", "pw");
		assertTrue(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001\nResults: True - Student max credits are 3 and course has 3.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertFalse(
				"Action: enroll\nUser: efrost\nCourse: CSC216-001 then CSC 217-211\nResults: False - Student max credits are 3 and additional credit of CSC217-211 cannot be added, will exceed max credits.",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC217", "211")));

		manager.resetSchedule();
		// Check Student Schedule
		Student efrost = directory.getStudentById("efrost");
		Schedule scheduleFrost = efrost.getSchedule();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrost.getScheduleCredits());
		String[][] scheduleFrostArray = scheduleFrost.getScheduledCourses();
		assertEquals("User: efrost\nCourse: CSC226-001, then schedule reset\n", 0, scheduleFrostArray.length);
		assertEquals("Course should have all seats available after reset.", 10,
				catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());

		manager.logout();

		manager.login("ahicks", "pw");
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC226-001\nResults: False - duplicate",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC226", "001")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-001\nResults: False - time conflict",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "001")));
		assertTrue("Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003\nResults: True",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC116", "003")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC116-002\nResults: False - already in section of 116",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC216", "601")));
		assertFalse(
				"Action: enroll\nUser: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, CSC230-001\nResults: False - exceeded max credits",
				manager.enrollStudentInCourse(catalog.getCourseFromCatalog("CSC230", "001")));

		manager.resetSchedule();
		// Check Student schedule
		Student ahicks = directory.getStudentById("ahicks");
		Schedule scheduleHicks = ahicks.getSchedule();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0,
				scheduleHicks.getScheduleCredits());
		String[][] scheduleHicksArray = scheduleHicks.getScheduledCourses();
		assertEquals("User: ahicks\nCourse: CSC216-001, CSC226-001, CSC116-003, then schedule reset\n", 0,
				scheduleHicksArray.length);
		assertEquals("Course should have all seats available after reset.", 10,
				catalog.getCourseFromCatalog("CSC226", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10,
				catalog.getCourseFromCatalog("CSC216", "001").getCourseRoll().getOpenSeats());
		assertEquals("Course should have all seats available after reset.", 10,
				catalog.getCourseFromCatalog("CSC116", "003").getCourseRoll().getOpenSeats());

		manager.logout();
	}
}