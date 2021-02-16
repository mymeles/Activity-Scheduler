package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * Reader and writer class for Students called StudentRecordIO
 * 
 * @author meles
 *
 */
public class StudentRecordIO {

	/**
	 * string parameter for file name
	 * 
	 * @param fileName
	 * 
	 * @return a value of arraylist that conatins the fields value for student
	 * @throws FileNotFoundException
	 * 
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner read the file
		ArrayList<Student> students = new ArrayList<Student>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following)
					students.add(processStudent(fileReader.nextLine()));
	} catch (IllegalArgumentException e) {
		throw e;
	} 
		} 
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the ArrayList with all the courses we read!
		return students; 
	}

	/**
	 * parameter that passes Through file from ReadStudent Records fileName
	 * 
	 * @param line Returns objects of students
	 * @return uses this method to seprate the fields from the passes line and
	 *         return value of students
	 */
	private static Student processStudent(String line) {

		// Changing the default delimiter of space to , and sperera the values
		Scanner scan = new Scanner(line);
		scan.useDelimiter(",");
		Student student = null;
		String firstName;
		String lastName;
		String id;
		String email;
		String hashPW; 
		int maxCredits = 0;
		try {

			if(scan.hasNext()){
				firstName = scan.next(); 
			} else {
				scan.close();
				throw new IllegalArgumentException();
			}
			if(scan.hasNext()){
				lastName = scan.next(); 
			} else {
				scan.close();
				throw new IllegalArgumentException();
			}
			if(scan.hasNext()){
				id = scan.next(); 
			} else {
				scan.close();
				throw new IllegalArgumentException();
			}
			if(scan.hasNext()){
				email = scan.next(); 
			} else {
				scan.close();
				throw new IllegalArgumentException();
			}
			
			if(scan.hasNext()){
				hashPW = scan.next(); 
			} else {
				scan.close();
				throw new IllegalArgumentException();
			}
			if(scan.hasNext()){
				maxCredits = scan.nextInt();
				scan.close();
			} 
			scan.close();
			student = new Student(firstName, lastName, id, email, hashPW, maxCredits);
			//scan.close();
			return student; 
			

		} catch (IllegalArgumentException e) {
			throw e;
		}

	}

	/**
	 * passes a file called fileName
	 * 
	 * @param fileName         student records stored in Student directory that hold
	 *                         student fields
	 * @param studentDirectory throws an exception
	 * @throws IOException this method writes students information taking it from
	 *                     student directory and on to the fileName
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

	}
}