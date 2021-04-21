package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc216.pack_scheduler.util.LinkedList;

/**
 * This class reads text files to create SortedLists of Faculty objects. Saved
 * sortedLists can then be printed into a text file.
 * 
 * @author Meles Meles
 */
public class FacultyRecordIO {

	/**
	 * This method creates a sorted list of Faculty objects using the file provided
	 * by the user.
	 * 
	 * @param fileName The name of the text file the Faculty objects will be
	 *                 constructed from
	 * @return SortedList containing a sorted list of Faculty objects
	 * @throws FileNotFoundException If the file from the provided fileName is
	 *                               invalid 
	 */
	public static LinkedList<Faculty> readFacultyRecords(String fileName) throws FileNotFoundException {
		LinkedList<Faculty> finalArray = new LinkedList<Faculty>();
		Scanner scan = new Scanner(new FileInputStream(fileName));
		String currentFaculty = "";
		Faculty newFaculty;
		while (scan.hasNextLine()) {
			currentFaculty = scan.nextLine();
			try {
				newFaculty = processFaculty(currentFaculty);
				finalArray.add(newFaculty);
			} catch (IllegalArgumentException e) {
				//
			}
		}
		scan.close();
		return finalArray;
	}

	/**
	 * Constructs a Faculty object using the elements from the input string.
	 * 
	 * @param Faculty The string input being read to construct a Faculty object.
	 * @return A Faculty object using the tokens from the string as fields.
	 * @throws IllegalArgumentException If the input string does not meet the
	 *                                  necessary criteria to construct a Faculty
	 *                                  object.
	 */
	private static Faculty processFaculty(String Faculty) {
		Scanner input = new Scanner(Faculty);
		Faculty outputFaculty;
		input.useDelimiter(",");
		int i = 0;
		String[] facultyField = new String[6];
		while (input.hasNext()) {
			facultyField[i] = input.next();
			i++;
		}
		input.close();
		if (i < 6)
			throw new IllegalArgumentException("Invalid Faculty");
		else {
			outputFaculty = new Faculty(facultyField[0], facultyField[1], facultyField[2], facultyField[3],
					facultyField[4], Integer.parseInt(facultyField[5]));
		}
		return outputFaculty;
	}

	/**
	 * Writes the elements of the Faculty list into one text file.
	 * 
	 * @param fileName         The name of the file where the Faculty directory is
	 *                         being written to.
	 * @param facultyDirectory The list of Faculty objects being changed to string
	 *                         objects.
	 * @throws IOException If the string cannot be written to the given file
	 */
	public static void writeFacultyRecords(String fileName, LinkedList<Faculty> facultyDirectory) throws IOException {
		PrintStream facultyFile = new PrintStream(new File(fileName));
		for (int i = 0; i < facultyDirectory.size(); i++) {
			facultyFile.println(facultyDirectory.get(i).toString());
		}
		facultyFile.close();
	}

}
