/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.user.schedule;

import edu.ncsu.csc216.pack_scheduler.course.ConflictException;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.util.ArrayList;

/**
 * Creates a list of courses that a user can add to and remove courses from. The
 * course can have it's title changed and be reset to it's default values. When
 * constructed, the schedule has a blank array with the title "My Schedule".
 * Interacts with GUI to give a viewable array of the arrayList.
 * 
 * @author Alex Bernard
 *
 */
public class Schedule {
	/** Field used to hold the Schedule title */
	private String title;

	/** Array List used to store a list of */
	private ArrayList<Course> schedule;

	/**
	 * Default constructor for schedule objects
	 */
	public Schedule() {
		title = "My Schedule";
		schedule = new ArrayList<Course>();
	}

	/**
	 * Adds a given course to the schedule ArrayList
	 * 
	 * @param course The course object being added to the arrayList
	 * @return True if the course has been added, and false otherwise
	 */
	public boolean addCourseToSchedule(Course course) {
		if (schedule.size() > 0) {
			for (int i = 0; i < schedule.size(); i++) {
				if (course.isDuplicate(schedule.get(i)))
					throw new IllegalArgumentException(
							"You are already enrolled in " + schedule.get(i).getName());
				try {
					course.checkConflict(schedule.get(i));
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
		}
		schedule.add(schedule.size(), course);
		return true;
	}

	/**
	 * Removes a given course from the schedule ArrayList
	 * 
	 * @param course The course object being removed from the arrayList
	 * @return True if the course has been removed, and false otherwise
	 */
	public boolean removeCourseFromSchedule(Course course) {
		if (course == null) {
			return false;
		}
		if (schedule.size() > 0) {
			for (int i = 0; i < schedule.size(); i++) {
				if (course.equals(schedule.get(i))) {
					schedule.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Clears the current schedule ArrayList() and resets the title to the default
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Course>();
		setTitle("My Schedule");
	}

	/**
	 * Creates and returns a 2-D String array containing a list of each course with
	 * their name, section, title, and meeting string
	 * 
	 * @return a 2-D Array with the name, section, title, and meeting string of each
	 *         course object
	 */
	public String[][] getScheduledCourses() {
		String[][] outputArray = new String[schedule.size()][5];
		for (int i = 0; i < schedule.size(); i++) {
			outputArray[i][0] = schedule.get(i).getName();
			outputArray[i][1] = schedule.get(i).getSection();
			outputArray[i][2] = schedule.get(i).getTitle();
			outputArray[i][3] = schedule.get(i).getMeetingString();
			outputArray[i][4] = schedule.get(i).getShortDisplayArray()[4];
		}
		return outputArray;
	}

	/**
	 * Changes the current schedule's title field with the given string
	 * 
	 * @param title The string used to replace the current title
	 */
	public void setTitle(String title) {
		if (title == null)
			throw new IllegalArgumentException("Title cannot be null.");
		this.title = title;
	}

	/**
	 * Returns the current value of the title field of the current schedule
	 * 
	 * @return The string contained in the title field
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Creates a cumulative sum of the total credits a student has scheduled
	 * @return the integer value of the total credits scheduled
	 */
	public int getScheduleCredits() {
		int totalCreds = 0;
		for (int i = 0; i < schedule.size(); i++) {
			totalCreds += schedule.get(i).getCredits(); 
		}
		return totalCreds;
	}
	
	/**
	 * Boolean method that determines if a course can be added to the student's schedule
	 * @param course that student is attempting to add
	 * @return false if the course is null, a duplicate, or has a time conflict with an already-
	 *         scheduled course.
	 */
	public boolean canAdd(Course course) {
		if (course == null) {
			return false;
		} 
		for (int i = 0; i < schedule.size(); i++) {
			if (course.isDuplicate(schedule.get(i)))
				return false;
			try {
				course.checkConflict(schedule.get(i));
			} catch (ConflictException e) {
				return false;
			}
		}
		return true;
	}
}
