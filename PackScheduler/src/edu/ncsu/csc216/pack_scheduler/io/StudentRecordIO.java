package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc217.collections.list.SortedList;
/**
 * This class reads text files to create SortedLists of student objects. 
 * Saved sortedLists can then be printed into a text file.
 * @author Alex Bernard
 */
public class StudentRecordIO {
	
	/**
	 * This method creates a sorted list of student objects using the file provided by the user.
	 * @param fileName The name of the text file the student objects will be constructed from
	 * @return SortedList containing a sorted list of student objects
	 * @throws FileNotFoundException If the file from the provided fileName is invalid
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		SortedList<Student> finalArray = new SortedList<Student>(); 
		Scanner scan = new Scanner(new FileInputStream(fileName));
		String currentStudent = "";
		Student newStudent;
		while (scan.hasNextLine()) {
			currentStudent = scan.nextLine();
			try {
				newStudent = processStudent(currentStudent);
				finalArray.add(newStudent);
			} catch (IllegalArgumentException e) {
				//
			}
		}
		scan.close();
		return finalArray;
	}

	/**
	 * Constructs a student object using the elements from the input string.
	 * @param student The string input being read to construct a student object.
	 * @return A student object using the tokens from the string as fields.
	 * @throws IllegalArgumentException If the input string does not meet the necessary criteria to construct a student object.
	 */
	private static Student processStudent(String student) {
		Scanner input = new Scanner(student);
		Student outputStudent;
		input.useDelimiter(",");
		int i = 0;
		String[] studentField = new String[6];
		while (input.hasNext() && i < 6) {
			studentField[i] = input.next();
			i++; 
		}
		input.close();
		if (i < 6) throw new IllegalArgumentException("Invalid student");
		else {
			outputStudent = new Student(studentField[0], studentField[1], studentField[2], studentField[3], 
					studentField[4], Integer.parseInt(studentField[5]));
		}
		return outputStudent;
	}
	
	/**
	 * Writes the elements of the student list into one text file.
	 * @param fileName The name of the file where the student directory is being written to.
	 * @param studentDirectory The list of student objects being changed to string objects.
	 * @throws IOException If the string cannot be written to the given file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {	
		PrintStream studentFile = new PrintStream(new File(fileName));
		for (int i = 0; i < studentDirectory.size(); i++) {
			studentFile.println(studentDirectory.get(i).toString());
		}
		studentFile.close();
	}

}
