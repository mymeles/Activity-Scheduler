package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;


import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * A class to catalog courses 
 * @author meles
 *
 */
public class CourseCatalog {

	/**
	 *  a sorted list catalog to hold courses 
	 */

	private SortedList<Course> catalog;

	/**
	 * A contrsuctor for course cat
	 */
	public CourseCatalog() {
		newCourseCatalog();
	}
 
	/**
	 * Constructs an empty course catalog
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Loads course records into catalog
	 * 
	 * @param fileName represents a ditination name for course file
	 */
	public void loadCourseFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Cannot find file");
		}
	}

	/**
	 * creates an array list of Activity catalog information
	 * 
	 * @return a 2D array of the catalog
	 */
	public String[][] getCourseCatalog() {
		// construct an array with a row of size
		String[][] directory = new String[catalog.size()][3];

		// if condition to get the course catalog of there is any courses in the catalog
		if (catalog.size() != 0) {
			for (int i = 0; i < catalog.size(); i++) {

				Course c = catalog.get(i);
				directory[i] = c.getShortDisplayArray();

			}
			// if the If- condition statisfied retun this
			return directory;
		}

		// if nor return an empty 2D array
		else {
			return directory;
		}
	}

	/**
	 * Adds a Course to the catalog. Returns true if the course is added.
	 * 
	 * @param name         course name
	 * @param title        course title
	 * @param section      courses section
	 * @param credits      courses credits
	 * @param instructorId courses instructor id
	 * @param meetingDays  courses meeting days
	 * @param startTime    courses start time
	 * @param endTime      courses end time
	 * @return true if course is added to catalog
	 * 
	 * @throws IAE if course name is null or empty
	 * @throws IAE if course section is null or empty
	 * 
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			String meetingDays, int startTime, int endTime) {

		if (name == null || ("").equals(name)) {
			throw new IllegalArgumentException("Invalid name");

		}
		if (section == null || ("").equals(section)) {
			throw new IllegalArgumentException("Invalid section");

		}

		Course course = null;
		if (("A").equals(meetingDays)) {
			course = new Course(name, title, section, credits, instructorId, meetingDays);
		} else {
			course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);

		}
		return catalog.add(course);
	}

	/**
	 * a name to pass to look for a course
	 * 
	 * @param name    a section to pass to look for a course
	 * @param section then it returns the course that has the name and
	 * sectionDetermine the probability that you ski between 17 and
	 * 22 laps (inclusive) on your favorite lift. Provide your answer
	 * as a number between 0 and 1 accurate to four digits beyond the
	 * decimal point.
	 * 
	 * 
	 * @return if not returns null
	 */

	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; 0 < catalog.size(); i++) {
			if (catalog.get(i).equals(name) && catalog.get(i).getSection().equals(section)) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * a method to remove course from catalog with the given course and section. If
	 * reference name and section if empty or null it returns false indication
	 * course is not removed from catalog. otherwise returns true and removes course
	 * from catalog
	 * 
	 * @param name courses name 
	 * @param section courses section        
	 * @return a boolean as specified above
	 */
	public boolean removeCourseFromCatalog(String name, String section) {

		if (name == null || ("").equals(name) || section == null || ("").equals(section)) {
			return false;

		} else {

			for (int i = 0; 0 < catalog.size(); i++) {
				if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section)) {
					catalog.remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Receives a string parameter that is the file name where the students schedule
	 * is saved
	 * 
	 * @param fileName using CourseRecordIO.writeCourseRecords export and if
	 *                 CourseRecord throws an IOExceptionCourse the method catches
	 *                 it and prints the file cannot be saved
	 */
	public void saveCourseCatalog(String fileName) {

		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);

		} catch (IOException e) {
			System.out.println("The file cannot be saved");
			e.printStackTrace();
		}

	}
}
