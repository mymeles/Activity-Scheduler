package edu.ncsu.csc216.pack_scheduler.io;

import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc217.collections.list.SortedList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * A class to read and Write course records 
 *  
 * @author meles 
 * 
 */
public class CourseRecordIO { 

	/**  
	 * this method reads course records from fileName
	 * 
	 * @param fileName inputs is a string path to a .txt file 
	 * @return this method reads CourseRecords file named fileName and returns a
	 *         value of course lists in the form os arrayList 
	 * @throwsFileNotFoundException
	 */
	public static SortedList<edu.ncsu.csc216.pack_scheduler.course.Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName)); // Create a file scanner to read the file
		SortedList<Course> courses = new SortedList<Course>(); // Create an empty array of Course objects
		while (fileReader.hasNextLine()) { // While we have more lines in the file
			try {
				// Attempt to do the following
				// Read the line, process it in readCourse, and get the object 

				// If trying to construct a Cocoursesurse in readCourse() results in an exception, flow
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
					courses.add(course); // Add to the ArrayList!
				} // Otherwise ignore
			} catch (IllegalArgumentException e) {
				// The line is invalid b/c we couldn't create a course, skip it!
			}
		}
		// Close the Scanner b/c we're responsible with our file handles
		fileReader.close(); 
		// Return the ArrayList with all the courses we read
		return courses; 
	}


	/**
	 * this method accepts lines from the readCourseRecod and use a dilemeter to split them apart and turn them into 
	 * an array list. 
	 * @return
	 * to pass the string file so it can be loaded by scanner object 
	 * @param fileName
	 * then we added the items into a course object and return it 
	 */
	
	private static Course readCourse(String fileName)  {
		
			Scanner scan = new Scanner(fileName);
			scan.useDelimiter(",");
			// example CSC 116,Intro to Programming - Java,001,3,jdyoung2,MW,0910,1100
			Course course;
			try { 
			// let say you have array list
			ArrayList<String> fields = new ArrayList<String>();
			while(scan.hasNext()) {
			 fields.add(scan.next()); 
			 
			} scan.close();
			
			String name = fields.get(0);
			String title = fields.get(1); // meaning full 
			String section = fields.get(2); 
			int credits = Integer.parseInt(fields.get(3));
			String id = fields.get(4);
			String meetD = fields.get(5);
			
			if("A".equals(meetD) && fields.size() == 6) { 
				course = new Course(name, title, section, credits, id, meetD);
				return course;
			} else if("A".equals(meetD) && fields.size() != 6) {
				throw new IllegalArgumentException("Invalid meeting day and time");
			}
		
			else {
				try {
				int stime = Integer.parseInt(fields.get(6)); 
				int etime = Integer.parseInt(fields.get(7));
				course = new Course(name, title, section, credits, id, meetD, stime, etime);
				return course; 
				} catch (Exception e) {
					throw new IllegalArgumentException();
			}
		}
			
						
				
	} catch (IllegalArgumentException e) {
			 throw e;
		 }
	}
	
	/**
	 * fileName is the name of the file that list of course info is stored
	 * This course writes the information provided from the arrayList course and writes it in the fileName path 
	 * @param fileName an input of arrayList of course information
	 * @param courses  this method writes course information on the file called fileName
	 * 
	 * @throwsIOException
	 * 
	 */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		// The line is invalid b/c we couldn't create a course, skip it!
	
		for (int i = 0; i < courses.size(); i++) {
			fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();

	}


}
