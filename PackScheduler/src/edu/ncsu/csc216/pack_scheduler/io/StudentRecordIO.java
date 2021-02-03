package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	 * firstName 0 lastName 1 ..... 5 *
	 * 
	 * Student st1 = new Student(splitLIne[0], ..LIne[5] -----this is in the loop
	 * sd.add(st1);
	 * 
	 * * @param fileName...splitK
	 * 
	 * @return 
	 * @throws FileNotFoundException
	 */
	/**
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
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
	 * askhfdblasbdf
	 * @param nextLine
	 * adkjfbisabdf
	 * @return
	 * alsjdkbfaskjbdf
	 */
	
	private static Student processStudent(String line) {

		Scanner scan= new Scanner(line).useDelimiter(",");
		// example CSC 116,Intro to Programming - Java,001,3,jdyoung2,MW,0910,1100
		
		Student student;
		try { 
		// let say you have array list
		ArrayList<String> fields= new ArrayList<String>();
		while(scan.hasNext()) {
		 fields.add(scan.next());
		}
		
	
		String firstName =fields.get(0);
		String lastName =fields.get(1); // meaning full 
		String id =fields.get(2); 
		int maxCreidt = Integer.parseInt(fields.get(5));
		String email = fields.get(4);
		String hashPW = fields.get(5);
		
		
		if(maxCreidt == 18) { 
			student = new Student(firstName, lastName, id, email, hashPW);
			return student;
		}
		
		else {
			student = new Student(firstName, lastName, id, email, hashPW, maxCreidt);
			return student;			
			}
 }catch (InputMismatchException e) {
		 throw e;
	 }
		
	}



	/**
	 * 
	 * @param fileName
	 * @param studentDirectory
	 * @throws IOException
	 */
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {

		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
		    fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();
	
	}
}