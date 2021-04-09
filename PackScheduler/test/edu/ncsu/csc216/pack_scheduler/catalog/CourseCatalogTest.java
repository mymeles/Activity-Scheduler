package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Tests the CourseCatalog class.
 *
 * @author Sarah Heckman
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";

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
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */ 
	private static final int END_TIME = 1445;

	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws java.lang.Exception occurs
	 */
	@Before
	public void setUp() throws Exception {
		//Reset course_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "starter_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests the load and save course catalog methods in CourseCatalog
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(invalidTestFile);
		assertEquals(0, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_empty_export.txt");
		checkFiles("test-files/expected_empty_export.txt", "test-files/actual_empty_export.txt");
		
		// Tests load course catalog with a valid test file
		CourseCatalog cc1 = new CourseCatalog();
		cc1.loadCoursesFromFile(validTestFile);
		assertEquals(13, cc1.getCourseCatalog().length);
	}
	
	/**
	 * Tests the getCourseFromCatalog() method in CourseCatalog.java
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		
		assertNull(cc.getCourseFromCatalog("CSC492", "001"));
		
		Course c = new Course(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
		assertEquals(c, cc.getCourseFromCatalog("CSC216", "001"));
	}
	
	/**
	 * Tests the functionality of addCourseToCatalog in Course Catalog
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile); 
		// Test adding duplicate course
		assertFalse(cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "002", 3, "spbalik", 10, "MW", 1120, 1310));
		// Test add valid course
		assertTrue(cc.addCourseToCatalog("CSC226", TITLE, "002", CREDITS, "jdyoung2", ENCAP, MEETING_DAYS, START_TIME, END_TIME));
		// Try constructing illegal course
		try {
			cc.addCourseToCatalog(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, ENCAP, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage()); 
		}
	}
	
	/**
	 * Tests the removeCourseFromCatalog() method in CourseCatalog
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		 CourseCatalog cc = new CourseCatalog();
		 // Tests removing a course from a blank catalog
		 assertFalse(cc.removeCourseFromCatalog(NAME, SECTION));
		 cc.loadCoursesFromFile(validTestFile);
		 // Test removing a course not present in catalog
		 assertFalse(cc.removeCourseFromCatalog("MAE201", "003"));
		 
		 // Remove CSC 216
		 assertEquals(13, cc.getCourseCatalog().length);
		 assertNotNull(cc.getCourseFromCatalog(NAME, SECTION));
		 assertTrue(cc.removeCourseFromCatalog(NAME, SECTION));
		 assertNull(cc.getCourseFromCatalog(NAME, SECTION));
		 assertEquals(12, cc.getCourseCatalog().length);
		
		 
	}
	
	/**
	 * Test WolfScheduler.getCourseCatalog().
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.loadCoursesFromFile(validTestFile);
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = cc.getCourseCatalog();
		//Row 0
		assertEquals("CSC116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		assertEquals("10", catalog[0][4]);
		//Row 1
		assertEquals("CSC116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		assertEquals("10", catalog[1][4]);
		//Row 2
		assertEquals("CSC116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		assertEquals("10", catalog[2][4]);
		//Row 3
		assertEquals("CSC216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		assertEquals("10", catalog[3][4]);
		//Row 4
		assertEquals("CSC216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		assertEquals("10", catalog[4][4]);
		//Row 5
		assertEquals("CSC216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		assertEquals("10", catalog[5][4]);
		//Row 6
		assertEquals("CSC217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		assertEquals("10", catalog[6][4]);
		//Row 7
		assertEquals("CSC217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		assertEquals("10", catalog[7][4]);
		//Row 8
		assertEquals("CSC217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		assertEquals("10", catalog[8][4]);
		//Row 9
		assertEquals("CSC217", catalog[9][0]);
		assertEquals("601", catalog[9][1]); 
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		assertEquals("10", catalog[9][4]);
		//Row 10
		assertEquals("CSC226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		assertEquals("10", catalog[10][4]);
		//Row 11
		assertEquals("CSC230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		assertEquals("10", catalog[11][4]);
		//Row 12
		assertEquals("CSC316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
		assertEquals("10", catalog[12][4]);
	}	
	
	/**
	 * Tests the saveCourseCatalog() method in CourseCatalog
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog cc = new CourseCatalog();
		cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", 10, "MW", 1250, 1440);
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "001", 3, "sesmith5", 10, "MW", 1330, 1445);
		cc.addCourseToCatalog("CSC216", "Software Development Fundamentals", "601", 3, "jctetter", 10, "A", 0, 0);
		assertEquals(3, cc.getCourseCatalog().length);
		cc.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");
		CourseCatalog cc1 = new CourseCatalog();
		cc1.loadCoursesFromFile("test-files/actual_course_records.txt");
		// Tests that saved files can be imported correctly
		assertEquals(3, cc1.getCourseCatalog().length);
	}
	
	// Helper method used to compare files
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {

			while (actScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			if (expScanner.hasNextLine()) {
				fail();
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
