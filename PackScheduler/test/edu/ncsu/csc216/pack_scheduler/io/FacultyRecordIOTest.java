package edu.ncsu.csc216.pack_scheduler.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * Tests the readFacultyRecords() and writeFacultyRecords() methods in
 * FacultyRecordIO
 * 
 * @author Meles Meles
 *
 */
public class FacultyRecordIOTest {

	/** String value of the first Faculty in Faculty_records.txt */
	private String validFaculty0 = "Ashely,Witt,awitt,mollis@Fuscealiquetmagna.net,pw,2";

	/** String value of the second Faculty in Faculty_records.txt */
	private String validFaculty1 = "Fiona,Meadows,fmeadow,pharetra.sed@et.org,pw,3";

	/** String value of the third Faculty in Faculty_records.txt */
	private String validFaculty2 = "Brent,Brewer,bbrewer,sem.semper@orcisem.co.uk,pw,1";

	/** String value of the fourth Faculty in Faculty_records.txt */
	private String validFaculty3 = "Halla,Aguirre,haguirr,Fusce.dolor.quam@amalesuadaid.net,pw,3";

	/** String value of the fifth Faculty in Faculty_records.txt */
	private String validFaculty4 = "Kevyn,Patel,kpatel,risus@pellentesque.ca,pw,1";

	/** String value of the sixth Faculty in Faculty_records.txt */
	private String validFaculty5 = "Elton,Briggs,ebriggs,arcu.ac@ipsumsodalespurus.edu,pw,3";

	/** String value of the seventh Faculty in Faculty_records.txt */
	private String validFaculty6 = "Norman,Brady,nbrady,pede.nonummy@elitfermentum.co.uk,pw,1";

	/** String value of the eighth Faculty in Faculty_records.txt */
	private String validFaculty7 = "Lacey,Walls,lwalls,nascetur.ridiculus.mus@fermentum.net,pw,2";

	/**
	 * String value of the Facultys in Faculty_records.txt arranged in the order
	 * given by SortedList
	 */
	private String[] validFacultys = { validFaculty3, validFaculty6, validFaculty4, validFaculty5, validFaculty2,
			validFaculty0, validFaculty1, validFaculty7 };
	// private String[] validFacultys = { validFaculty0, validFaculty1,
	// validFaculty2, validFaculty3, validFaculty4,
	// validFaculty5, validFaculty6, validFaculty7, validFaculty8, validFaculty9 };

	/** String used to store hash password */
	private String hashPW;

	/** String used in the setUp method for passwords */
	private static final String HASH_ALGORITHM = "SHA-256";

	/**
	 * Sets up conditions for unit tests to work.
	 */
	@Before
	public void setUp() {
		try {
			String password = "pw";
			MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
			digest.update(password.getBytes());
			hashPW = new String(digest.digest());

			for (int i = 0; i < validFacultys.length; i++) {
				validFacultys[i] = validFacultys[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests the readFacultyRecords method
	 */
	@Test
	public void testReadFacultyRecords() {
		LinkedList<Faculty> actualList = new LinkedList<Faculty>();

		// Test invalid file
		try {
			actualList = FacultyRecordIO.readFacultyRecords("test-files/invalid_faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail("File not found.");
		}
		assertEquals(actualList.size(), 0);

		// Tests file not found
		try {
			actualList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records1.txt");
			fail("Invalid file found");
		} catch (FileNotFoundException e) {
			// assertEquals("", e.getMessage());
			assertEquals(actualList.size(), 0);
		}

		// Test valid test cases
		actualList = new LinkedList<Faculty>();
		try {
			actualList = FacultyRecordIO.readFacultyRecords("test-files/faculty_records.txt");
		} catch (FileNotFoundException e) {
			fail("File Not Found");
		}
		assertEquals(actualList.size(), validFacultys.length);
		
	}

	/**
	 * Tests the writeFacultyRecords method
	 */
	@Test
	public void testWriteFacultyRecords() {

		LinkedList<Faculty> actualList = new LinkedList<Faculty>();
		actualList.add(new Faculty("Ashely", "Witt", "awitt", "mollis@Fuscealiquetmagna.net", hashPW, 2));
		actualList.add(new Faculty("Fiona", "Meadows", "fmeadow", "pharetra.sed@et.org", hashPW, 3));
		actualList.add(new Faculty("Brent", "Brewer", "bbrewer", "sem.semper@orcisem.co.uk", hashPW, 1));

		try {
			FacultyRecordIO.writeFacultyRecords("test-files/actual_Faculty_records.txt", actualList);
		} catch (IOException e) {
			fail("Unable to write file.");
		}
		checkFiles("test-files/expected_faculty_records.txt", "test-files/actual_Faculty_records.txt");

		try {
			FacultyRecordIO.writeFacultyRecords("/home/sesmith5/actual_Faculty_records.txt", actualList);
		} catch (IOException e) {
			assertEquals("/home/sesmith5/actual_Faculty_records.txt (Permission denied)", e.getMessage());
		}
	}

	/**
	 * Checks two test files and determines if both are equal
	 * 
	 * @param expFile The file that resembles the expected results of the test
	 * @param actFile The file received after using writeFacultyRecords
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
}
