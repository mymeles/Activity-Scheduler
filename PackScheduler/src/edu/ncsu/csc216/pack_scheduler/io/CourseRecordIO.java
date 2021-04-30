package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
//import java.util.List;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.manager.RegistrationManager;
import edu.ncsu.csc216.pack_scheduler.user.Faculty;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * Reads Course records from text files and creates an associated ArrayList.
 * Writes a set of CourseRecords from an ArrayList to a file.
 * 
 * @author Alex Bernard
 */
public class CourseRecordIO {

	/**
	 * Reads course records from a file and generates a list of valid Courses. Any
	 * invalid Courses are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName The file to read Course records from.
	 * @return A list of valid Courses.
	 * @throws FileNotFoundException if the file cannot be found or read
	 */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try { // Attempt to do the following
					// Read the line, process it in readCourse, and get the object
					// If trying to construct a Course in readCourse() results in an exception, flow
					// of control will transfer to the catch block, below
				Course course = readCourse(fileReader.nextLine());

				// Create a flag to see if the newly created Course is a duplicate of something
				// already in the list
				boolean duplicate = false;
				// Look at all the courses in our list
				for (int i = 0; i < courses.size(); i++) {
					// Get the course at index i
					Course current = courses.get(i);
					// Check if the name and section are the same
					if (course.getName().equals(current.getName())
							&& course.getSection().equals(current.getSection())) {
						// It's a duplicate!
						duplicate = true;
						break; // We can break out of the loop, no need to continue searching
					}
				}
				// If the course is NOT a duplicate
				if (!duplicate) {
					courses.add(course); // Add to the SortedList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close();
		// Return the SortedList with all the courses we read!
		return courses;
	}

	/**
	 * Reads through a given line and returns the line as a course object. If the
	 * line does not fit the criteria for a valid object, it throws an illegal
	 * argument exception.
	 * 
	 * @param nextLine The line input from the text file being used to create the
	 *                 course object
	 * @return A course object based on the given information from the given line
	 * @throws Illegal Argument Exception If the given line does not have the right
	 *                 number of lines.
	 */
	private static Course readCourse(String nextLine) {
		Scanner lineReader = new Scanner(nextLine);
		lineReader.useDelimiter(",");
		Course returnCourse = null;
		String[] courseField = new String[9];
		int i = 0;

		while (lineReader.hasNext() && i < 9) {
			courseField[i] = lineReader.next();
			i++;
		}
		String id = courseField[4];
		if (i < 7 || lineReader.hasNext()) {
			lineReader.close();
			throw new IllegalArgumentException("Invalid course");
		} else if (courseField[6] != null && courseField[6].equals("A")) {
			if (i > 7) {
				lineReader.close();
				throw new IllegalArgumentException("Invalid Course");
			}
			returnCourse = new Course(courseField[0], courseField[1], courseField[2], Integer.parseInt(courseField[3]),
					null, Integer.parseInt(courseField[5]), courseField[6]);

			if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id) != null)
				RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id).getSchedule()
						.addCourseToSchedule(returnCourse);

		} else {
			returnCourse = new Course(courseField[0], courseField[1], courseField[2], Integer.parseInt(courseField[3]),
					null, Integer.parseInt(courseField[5]), courseField[6], Integer.parseInt(courseField[7]),
					Integer.parseInt(courseField[8]));
			if (RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id) != null)
				RegistrationManager.getInstance().getFacultyDirectory().getFacultyById(id).getSchedule()
						.addCourseToSchedule(returnCourse);

		}
		lineReader.close();
		return returnCourse;

	}

	/**
	 * Writes the given list of Courses to a new file
	 * 
	 * @param fileName The file to write the schedule of Courses to.
	 * @param courses  The list of Courses to write to the given file.
	 * @throws IOException If the program cannot write to the given file.
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();
	}

}
