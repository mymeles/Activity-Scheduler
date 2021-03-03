/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for Course catalog 
 * @author meles
 *
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Invalid course records */
	private final String invalidTestFile = "test-files/invalid_course_records.txt";

	/** Course name */
	private static final String NAME = "CSC 216";
	/** Course title */
	private static final String TITLE = "Software Development Fundamentals";
	/** Course section */
	private static final String SECTION = "001";
	/** Course credits */
	private static final int CREDITS = 3;
	/** Course instructor id */
	private static final String INSTRUCTOR_ID = "sesmith5";
	/** Course meeting days */
	private static final String MEETING_DAYS = "TH";
	/** Course start time */
	private static final int START_TIME = 1330;
	/** Course end time */
	private static final int END_TIME = 1445;



	/**
	 * Resets course_records.txt for use in other tests.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset course_records.txt so that it's fine for other needed tests
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
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#CourseCatalog()}.
	 */
	@Test
	public void testCourseCatalog() {
		CourseCatalog sd = new CourseCatalog();
		assertFalse(sd.removeCourseFromCatalog("CSC 216", "001"));
		assertEquals(0, sd.getCourseCatalog().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#newCourseCatalog()}.
	 */
	@Test
	public void testNewCourseCatalog() {
		CourseCatalog sd = new CourseCatalog();

		sd.loadCourseFromFile(validTestFile);
		assertEquals(13, sd.getCourseCatalog().length);

		sd.newCourseCatalog();
		assertEquals(0, sd.getCourseCatalog().length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#loadCourseFromFiles(java.lang.String)}.
	 */
	@Test
	public void testLoadCourseFromFiles() {
		CourseCatalog sd = new CourseCatalog();

		// Test valid file
		sd.loadCourseFromFile(validTestFile);
		assertEquals(13, sd.getCourseCatalog().length);

		// Test invalid files

		try { 
			sd.loadCourseFromFile(invalidTestFile + "fff");
			// fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file " + invalidTestFile + "fff", e.getMessage());
		}
	}

	
	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#addCourseToCatalog(java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, int, int)}.
	 */
	@Test
	public void testAddCourseToCatalog() {
		CourseCatalog sd = new CourseCatalog();

		// Test valid Student
		sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		String[][] course = sd.getCourseCatalog();
		assertEquals(1, course.length);
		assertEquals(NAME, course[0][0]);
		assertEquals(SECTION, course[0][1]);
		assertEquals(TITLE, course[0][2]);

		try {
			sd.addCourseToCatalog(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		}
		
		try {
			sd.addCourseToCatalog(NAME, TITLE, null, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid section", e.getMessage());
		}

		sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, "A", START_TIME, END_TIME);
		course = sd.getCourseCatalog();
		assertEquals(2, course.length);
		assertEquals(NAME, course[0][0]);
		assertEquals(SECTION, course[0][1]);
		assertEquals(TITLE, course[0][2]);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#getCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog ws = new CourseCatalog();
		ws.loadCourseFromFile(validTestFile);
		
		//Get the catalog and make sure contents are correct
		//Name, section, title
		String [][] catalog = ws.getCourseCatalog();
		//Row 0
		assertEquals("CSC 116", catalog[0][0]);
		assertEquals("001", catalog[0][1]);
		assertEquals("Intro to Programming - Java", catalog[0][2]);
		//Row 1
		assertEquals("CSC 116", catalog[1][0]);
		assertEquals("002", catalog[1][1]);
		assertEquals("Intro to Programming - Java", catalog[1][2]);
		//Row 2
		assertEquals("CSC 116", catalog[2][0]);
		assertEquals("003", catalog[2][1]);
		assertEquals("Intro to Programming - Java", catalog[2][2]);
		//Row 3
		assertEquals("CSC 216", catalog[3][0]);
		assertEquals("001", catalog[3][1]);
		assertEquals("Software Development Fundamentals", catalog[3][2]);
		//Row 4
		assertEquals("CSC 216", catalog[4][0]);
		assertEquals("002", catalog[4][1]);
		assertEquals("Software Development Fundamentals", catalog[4][2]);
		//Row 5
		assertEquals("CSC 216", catalog[5][0]);
		assertEquals("601", catalog[5][1]);
		assertEquals("Software Development Fundamentals", catalog[5][2]);
		//Row 6
		assertEquals("CSC 217", catalog[6][0]);
		assertEquals("202", catalog[6][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[6][2]);
		//Row 7
		assertEquals("CSC 217", catalog[7][0]);
		assertEquals("211", catalog[7][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[7][2]);
		//Row 8
		assertEquals("CSC 217", catalog[8][0]);
		assertEquals("223", catalog[8][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[8][2]);
		//Row 9
		assertEquals("CSC 217", catalog[9][0]);
		assertEquals("601", catalog[9][1]);
		assertEquals("Software Development Fundamentals Lab", catalog[9][2]);
		//Row 10
		assertEquals("CSC 226", catalog[10][0]);
		assertEquals("001", catalog[10][1]);
		assertEquals("Discrete Mathematics for Computer Scientists", catalog[10][2]);
		//Row 11
		assertEquals("CSC 230", catalog[11][0]);
		assertEquals("001", catalog[11][1]);
		assertEquals("C and Software Tools", catalog[11][2]);
		//Row 12
		assertEquals("CSC 316", catalog[12][0]);
		assertEquals("001", catalog[12][1]);
		assertEquals("Data Structures and Algorithms", catalog[12][2]);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#removeCourseFromCatalog(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog sd = new CourseCatalog();
		
		//Add students and remove
		sd.loadCourseFromFile(validTestFile);
		assertEquals(13, sd.getCourseCatalog().length);
		assertTrue(sd.removeCourseFromCatalog("CSC 216", "001"));
		String [][] course = sd.getCourseCatalog();
		assertEquals(12, course.length);
		assertNotEquals("Lane", course[5][0]);
		assertNotEquals("Berg", course[5][1]);
		assertNotEquals("lberg", course[5][2]);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog#saveCourseCatalog(java.lang.String)}.
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog sd = new CourseCatalog();
		//Add a course 
		// CSC 217,Software Development Fundamentals Lab,223,1,sesmith5,W,1500,1650
		sd.addCourseToCatalog("CSC 216", "Software Development Fundamentals Lab", "223", 1, "sesmith5", "W", 1500, 1650);
		assertEquals(1, sd.getCourseCatalog().length);
		sd.saveCourseCatalog("test-files/actual_student_records.txt");
		checkFiles("test-files/expected_course_records.txt", "test-files/actual_course_records.txt");	
		}
	
	
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
