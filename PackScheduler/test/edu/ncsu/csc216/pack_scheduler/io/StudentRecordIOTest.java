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

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Tests the readStudentRecords() and writeStudentRecords() methods in
 * StudentRecordIO
 * 
 * @author Alex Bernard
 *
 */
public class StudentRecordIOTest {

	/** String value of the first student in student_records.txt */
	private String validStudent0 = "Zahir,King,zking,orci.Donec@ametmassaQuisque.com,pw,15";
	
	/** String value of the second student in student_records.txt */
	private String validStudent1 = "Cassandra,Schwartz,cschwartz,semper@imperdietornare.co.uk,pw,4";
	
	/** String value of the third student in student_records.txt */
	private String validStudent2 = "Shannon,Hansen,shansen,convallis.est.vitae@arcu.ca,pw,14";
	
	/** String value of the fourth student in student_records.txt */
	private String validStudent3 = "Demetrius,Austin,daustin,Curabitur.egestas.nunc@placeratorcilacus.co.uk,pw,18";
	
	/** String value of the fifth student in student_records.txt */
	private String validStudent4 = "Raymond,Brennan,rbrennan,litora.torquent@pellentesquemassalobortis.ca,pw,12";
	
	/** String value of the sixth student in student_records.txt */
	private String validStudent5 = "Emerald,Frost,efrost,adipiscing@acipsumPhasellus.edu,pw,3";
	
	/** String value of the seventh student in student_records.txt */
	private String validStudent6 = "Lane,Berg,lberg,sociis@non.org,pw,14";
	
	/** String value of the eighth student in student_records.txt */
	private String validStudent7 = "Griffith,Stone,gstone,porta@magnamalesuadavel.net,pw,17";

	/** String value of the ninth student in student_records.txt */
	private String validStudent8 = "Althea,Hicks,ahicks,Phasellus.dapibus@luctusfelis.com,pw,11";

	/** String value of the tenth student in student_records.txt */
	private String validStudent9 = "Dylan,Nolan,dnolan,placerat.Cras.dictum@dictum.net,pw,5";

	/** String value of the students in student_records.txt arranged in the order given by SortedList */
	private String[] validStudents = { validStudent3, validStudent6, validStudent4, validStudent5, validStudent2, 
			validStudent8, validStudent0, validStudent9, validStudent1, validStudent7 };
	//private String[] validStudents = { validStudent0, validStudent1, validStudent2, validStudent3, validStudent4,
			//validStudent5, validStudent6, validStudent7, validStudent8, validStudent9 };
	
	
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

			for (int i = 0; i < validStudents.length; i++) {
				validStudents[i] = validStudents[i].replace(",pw,", "," + hashPW + ",");
			}
		} catch (NoSuchAlgorithmException e) {
			fail("Unable to create hash during setup");
		}
	}

	/**
	 * Tests the readStudentRecords method
	 */
	@Test
	public void testReadStudentRecords() {
		SortedList<Student> actualArray = new SortedList<Student>();
		
		// Test invalid file
		try {
			actualArray = StudentRecordIO.readStudentRecords("test-files/invalid_student_records.txt");
		} catch (FileNotFoundException e) {
			fail("File not found.");
		}
		assertEquals(actualArray.size(), 0);
		
		// Tests file not found
		try {
			actualArray = StudentRecordIO.readStudentRecords("test-files/student_record.txt");
			fail("Invalid file found");
		} catch (FileNotFoundException e) {
			//assertEquals("", e.getMessage());
			assertEquals(actualArray.size(), 0);
		}
		
		// Test valid test cases
		actualArray = new SortedList<Student>();
		try {
			actualArray = StudentRecordIO.readStudentRecords("test-files/student_records.txt");
		} catch (FileNotFoundException e) {
			fail("File Not Found");
		}
		assertEquals(actualArray.size(), validStudents.length);
		for (int i = 0; i < actualArray.size(); i++) {
			assertTrue(actualArray.get(i).toString().equals(validStudents[i].toString()));
		}
	}

	/**
	 * Tests the writeStudentRecords method
	 */
	@Test
	public void testWriteStudentRecords() {
		
		SortedList<Student> actualArray = new SortedList<Student>();
		actualArray.add(new Student("Zahir", "King", "zking", "orci.Donec@ametmassaQuisque.com", hashPW, 15));
		try {
			StudentRecordIO.writeStudentRecords("test-files/actual_student_records.txt", actualArray);
		} catch(IOException e) {
			fail("Unable to write file.");
		}
		checkFiles("test-files/expected_student_records.txt", "test-files/actual_student_records.txt");
		
		try {
			StudentRecordIO.writeStudentRecords("/home/sesmith5/actual_student_records.txt", actualArray);
		} catch(IOException e) {
			assertEquals("/home/sesmith5/actual_student_records.txt (Permission denied)", e.getMessage()); 
		}
	}

	/**
	 * Checks two test files and determines if both are equal
	 * @param expFile The file that resembles the expected results of the test
	 * @param actFile The file received after using writeStudentRecords
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
