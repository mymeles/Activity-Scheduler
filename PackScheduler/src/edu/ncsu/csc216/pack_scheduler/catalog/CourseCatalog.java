/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.catalog;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;
import edu.ncsu.csc217.collections.list.SortedList;

/**
 * This class allows the program to save and edit a list of course objects in
 * the form of a catalog. Courses can be
 * 
 * @author Alex Bernard
 *
 */
public class CourseCatalog {

	/** Array containing the course catalog */
	private SortedList<Course> catalog;

	/**
	 * Default constructor for CourseCatalog(). Creates a blank SortedList for
	 * catalog.
	 */
	public CourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Clears the catalog SortedList.
	 */
	public void newCourseCatalog() {
		catalog = new SortedList<Course>();
	}

	/**
	 * Creates a sortedList of courses from the file given by the user
	 * 
	 * @param fileName The location of the text file being converted into the
	 *                 sortedLIst of courses
	 */
	public void loadCoursesFromFile(String fileName) {
		try {
			catalog = CourseRecordIO.readCourseRecords(fileName);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Error loading files.");
		}
	}

	/**
	 * Constructs a course with the given parameters, and adds it to the course
	 * catalog. If the course is already present in the catalog, the method returns
	 * false.
	 * 
	 * @param name         The name of the given course
	 * @param title        The title of the given course
	 * @param section      The section of the given course
	 * @param credits      Number of credit hours within the given course
	 * @param instructorId The id of the course instructor
	 * @param meetingDays  The initials of the meeting days of the given course
	 * @param startTime    The start time of the given course (Military Format)
	 * @param endTime      The end time of the given course (Military Format)
	 * @param enrollmentCap The enrollment capacity of a course
	 * @return True if the specified course has been added
	 */
	public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId,
			int enrollmentCap, String meetingDays, int startTime, int endTime) {
		Course addCourse = new Course(name, title, section, credits, instructorId, enrollmentCap, meetingDays,
				startTime, endTime);
		if (catalog.contains(addCourse))
			return false;
		else {
			for (int i = 0; i < catalog.size(); i++) {
				if (catalog.get(i).getName().equals(addCourse.getName())
						&& catalog.get(i).getSection().equals(addCourse.getSection()))
					return false;
			}
			return catalog.add(addCourse);
		}

	}

	/**
	 * Removes a course that matches the name and section given. Returns true if the
	 * course is removed, and false if the course cannot be found
	 * 
	 * @param name    Name of the course being removed from the catalog
	 * @param section Section of the course being removed from the
	 * @return True if the specified course has been removed.
	 */
	public boolean removeCourseFromCatalog(String name, String section) {
		if (this.getCourseFromCatalog(name, section) != null) {
			catalog.remove(catalog.indexOf(this.getCourseFromCatalog(name, section)));
			return true;
		} else
			return false;
	}

	/**
	 * Searches for a course in the catalog with the given name and section
	 * 
	 * @param name    Name of the course in the catalog
	 * @param section Section of the course in the catalog
	 * @return The course object with the given name and section
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for (int i = 0; i < catalog.size(); i++) {
			if (catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section))
				return catalog.get(i);
		}
		return null;
	}

	/**
	 * Creates a string array containing the name, section, title, and meeting
	 * string of each course in the catalog
	 * 
	 * @return A string array containing the name, section, title, and meeting
	 *         string of each course in the catalog
	 */
	public String[][] getCourseCatalog() {
		String[][] courseCatalog = new String[catalog.size()][3];
		if (catalog.size() != 0) {
			for (int i = 0; i < catalog.size(); i++) {
				Course c = catalog.get(i);
				courseCatalog[i] = c.getShortDisplayArray();
			}
			return courseCatalog;
		} else {
			return courseCatalog;
		}
	}

	/**
	 * Saves the list of courses in the CourseCatalog as a text document.
	 * 
	 * @param fileName The file the list of courses is saved to
	 */
	public void saveCourseCatalog(String fileName) {
		try {
			CourseRecordIO.writeCourseRecords(fileName, catalog);
		} catch (IOException e) {
			throw new IllegalArgumentException();
		}
	}

}
