package edu.ncsu.csc216.pack_scheduler.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import edu.ncsu.csc216.pack_scheduler.user.Student;

public class StudentRecordIO {
	
	
	
	
	// file name student  the format string 

	// ArrayList<Studet> sd;
	
	//  
	
	// ArrayList<String> splitLine = line.split(",") we get an array 
	    /**
	     * firstName  0
	     * lastName   1
	     *     ..... 5
	     * 	     * 
	     * 
	     * Student st1 = new Student(splitLIne[0], ..LIne[5] -----this is in the loop
	     * sd.add(st1); 
	     *  
	     *  * @param fileName...splitK
	     * @return
	     * @throws FileNotFoundException
	     */
	public static ArrayList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		
		ArrayList<Student> sd;	
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = reader.readLine();
			while( line != null) {
				line = reader.readLine();
				ArrayList<String> splitLine = new ArrayList<>(Arrays.asList(line.split(",")));
				int credit = Integer.parseInt(splitLine.get(5));
				Student st1 = new Student(splitLine.get(0), splitLine.get(1), splitLine.get(2), splitLine.get(3), splitLine.get(4), credit);
				sd.add(st1);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sd;
	}

	
//	
//	public class TextFileWritingExample2 {
//		 
//	    public static void main(String[] args) {
//	        try {
//	            FileWriter writer = new FileWriter(fileName, true); // loads the file name 
//	            BufferedWriter bufferedWriter = new BufferedWriter(writer); object to write the string 
//	 null
//	           // reverse the process of readFile 
	/**
	 *  for(int i = 0; i < studentdirectory.sixe(); i++){  
	 *    student st = studentdirectory[i];
	 *    
	 *    string line = st.getFirstName() + "," +....... unitll max credit
	 *    bufferedWriter.write(line)
	 *    if(i != studentDirectory.size()-1){
	 *    		bufferwriter.newLine();}
	 *    
	 *  }
	 */
//	 
//	            bufferedWriter.close();
//	        } catch (IOException e) {https://ncsu.zoom.us/j/93922393721?pwd=enJaeEJ5WmxlUzh5TzZMK1Fwem4ydz09
//	            e.printStackTrace();
//	        }
//	 
//	  
//	 
//g
	public static void writeStudentRecords(String fileName, ArrayList<Student> studentDirectory) throws IOException {
		
		try {
			FileWriter writer = new FileWriter(fileName, true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer); 
			
			for(int i =0; i < studentDirectory.size(); i++) {
				Student st = studentDirectory.get(i);
				
				String line = st.getFirstName() + "," + st.getLastName() + "," + st.getId() + "," + st.getEmail() + "," + st.getPassword() + "," + st.getMaxCredits();
				bufferedWriter.write(line);
				
				if(i != studentDirectory.size()-1) {
					bufferedWriter.newLine();
					
				}
			}
		}
		
	}

} 
