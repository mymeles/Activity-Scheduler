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
 * @author meles
 *
 */
public class StudentRecordIO {

	
	/**
	 * string parameter for file name
	 * @param fileName
	 * 
	 * @return a value of arraylist that conatins the fields value for student 
	 * @throws FileNotFoundException
	 * 
	 */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Student> students = new ArrayList<Student>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Student student = processStudent(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < students.size(); i++) {
	                //Get the course at index i
	                Student current = students.get(i);
	                //Check if the name and section are the same
	                if (student.getFirstName().equals(current.getFirstName()) &&
	                        student.getEmail().equals(current.getEmail())) {
	                    //It's a duplicate!
	                    duplicate = true;
						break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	            students.add(student); //Add to the ArrayList!
	            } //Otherwise ignore 
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return students;
	}

		
	/**
	 * parameter that passes Through file from ReadStudent Records fileName
	 * @param line
	 * Returns objects of students  
	 * @return
	 * uses this method to seprate the fields from the passes line and return value of students 
	 */
	private static Student processStudent(String line) {

		//  Changing the default delimiter of space to , and sperera the values  
		Scanner scan = new Scanner(line).useDelimiter(",");
		
		
		Student student;
		try { 
		// let say you have array list
		ArrayList<String> fields = new ArrayList<String>();
		while(scan.hasNext()) {
		 fields.add(scan.next());
		}
		
	
		String firstName = fields.get(0);
		String lastName = fields.get(1);
		String id = fields.get(2); 
		int maxCreidt = Integer.parseInt(fields.get(5));
		String email = fields.get(3);
		String hashPW = fields.get(4);
		
		
		if(maxCreidt == 18) {
			student = new Student(firstName, lastName, id, email, hashPW);
			return student;
		}
		
		else {
			
			student = new Student(firstName, lastName, id, email, hashPW, maxCreidt);
			return student;			
			}
 } catch (IllegalArgumentException e) {
		 throw e; 
	 }
		
	}



	/**
	 * passes a file called  fileName
	 * @param fileName
	 * student records stored in Student directory that hold student fields 
	 * @param studentDirectory
	 * throws an exception 
	 * @throws IOException
	 * this method writes students information taking it from student directory and on to the fileName
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString()); 
		}

		fileWriter.close();
	
	}
}