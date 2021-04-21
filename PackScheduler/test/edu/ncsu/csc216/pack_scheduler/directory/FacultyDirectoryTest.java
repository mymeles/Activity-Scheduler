package edu.ncsu.csc216.pack_scheduler.directory;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;

/**
 * Tests FacultyDirectory.
 * 
 * @author Meles Meles
 */
public class FacultyDirectoryTest {

	/** Valid records */
	private final String validTestFile = "test-files/faculty_records.txt";
	/** Test first name */
	private static final String FIRST_NAME = "fa";
	/** Test last name */
	private static final String LAST_NAME = "culty";
	/** Test id */
	private static final String ID = "faculty";
	/** Test email */
	private static final String EMAIL = "@ncsu.edu";
	/** Test password */
	private static final String PASSWORD = "pw";
	/** Test max credits */
	private static final int MAX_CREDITS = 2;

	/**
	 * Resets course_records.txt for use in other tests.
	 * 
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {
		// Reset Faculty_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_full_faculty_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "faculty_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	@Test
	public void testDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fy = new FacultyDirectory();
		assertFalse(fy.removeFaculty("sesmith5"));
		assertEquals(0, fy.getFacultyDirectory().length);
	}
 
	@Test
	public void testNewFacultyDirectory() {
		// Test that if there are faculty in the directory, they
		// are removed after calling newFacultyDirectory().
		FacultyDirectory fy = new FacultyDirectory();

		fy.loadFacultysFromFile(validTestFile);
		assertEquals(8, fy.getFacultyDirectory().length);

		fy.newFacultyDirectory();
		assertEquals(0, fy.getFacultyDirectory().length);
	}

	@Test
	public void testLoadsFromFile() {
		FacultyDirectory fy = new FacultyDirectory();

		// Test valid file
		fy.loadFacultysFromFile(validTestFile);
		assertEquals(8, fy.getFacultyDirectory().length);

		fy = new FacultyDirectory();
		// Test invalid file
		try {
			fy.loadFacultysFromFile("test-files/invalid_file.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to read file test-files/invalid_file.txt", e.getMessage());
		}
	}

	@Test
	public void testAdd() {
		FacultyDirectory fy = new FacultyDirectory();

		// Test valid Faculty
		fy.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		String[][] facultyDirectory = fy.getFacultyDirectory();
		assertEquals(1, facultyDirectory.length);
		assertEquals(FIRST_NAME, facultyDirectory[0][0]);
		assertEquals(LAST_NAME, facultyDirectory[0][1]);
		assertEquals(ID, facultyDirectory[0][2]);

		fy = new FacultyDirectory();
		fy.addFaculty(FIRST_NAME, LAST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS);
		// Test invalid Faculty
		assertFalse(fy.addFaculty(LAST_NAME, FIRST_NAME, ID, EMAIL, PASSWORD, PASSWORD, MAX_CREDITS));
		assertEquals(1, fy.getFacultyDirectory().length);
	}

	@Test
	public void testFacultyRemove() {
		FacultyDirectory fy = new FacultyDirectory();

		// Add Facultys and remove
		fy.loadFacultysFromFile(validTestFile);
		assertEquals(8, fy.getFacultyDirectory().length);
		assertTrue(fy.removeFaculty("awitt"));
		String[][] facultyDirectory = fy.getFacultyDirectory();
		assertEquals(7, facultyDirectory.length);
		assertEquals("Kevyn", facultyDirectory[3][0]);
		assertEquals("Patel", facultyDirectory[3][1]);
		assertEquals("kpatel", facultyDirectory[3][2]);
	}

	@Test
	public void testGetDirectory() {
		// Test that the FacultyDirectory is initialized to an empty list
		FacultyDirectory fy = new FacultyDirectory();
		assertFalse(fy.removeFaculty("namee"));
		assertEquals(0, fy.getFacultyDirectory().length);
	}

	@Test
	public void testSaveDirectory() {
		FacultyDirectory fy = new FacultyDirectory();
		fy.addFaculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", "pw", "pw", 2);
		fy.addFaculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", "pw", "pw", 3);
		fy.addFaculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk","pw", "pw", 1);
		assertEquals(3, fy.getFacultyDirectory().length);
		fy.saveFacultyDirectory("test-files/actual_Faculty_records.txt");
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_Faculty_records.txt");

		// Test IOException
		try {
			fy.saveFacultyDirectory("/home/sesmith5/actual_student_records.txt");
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to write to file /home/sesmith5/actual_student_records.txt", e.getMessage());
		}
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
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
