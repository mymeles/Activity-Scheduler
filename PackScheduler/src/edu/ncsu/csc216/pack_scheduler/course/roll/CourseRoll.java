/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;

/**
 * This class lists students enrolled in a single course
 * 
 * @author Alex Bernard
 *
 */
public class CourseRoll {
	/** The maximum number of students that can be enrolled in a course */
	private int enrollmentCap;

	/** The minimum enrollmentCap that can be set for a course */
	private static final int MIN_ENROLLMENT = 10;

	/** The maximum enrollmentCap that can be set for a course */
	private static final int MAX_ENROLLMENT = 250;

	/** A list containing with */
	private LinkedAbstractList<Student> roll;

	/**
	 * The constructor for a CourseRoll object. The enrollmentCap is set to the
	 * given parameter and a new LinkedAbstractList of Student objects is created.
	 * 
	 * @param enrollmentCap The maximum number of students that can be enrolled in a
	 *                      single course
	 */
	public CourseRoll(int enrollmentCap) {
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
	}

	/**
	 * Returns the enrollmentCap of the given course
	 * 
	 * @return The maximum number of students that can be enrolled in a single
	 *         course
	 */
	public int getEnrollmentCap() {
		return enrollmentCap;
	}

	/**
	 * Sets the maximum number of students that can be enrolled in a single course
	 * 
	 * @param enrollmentCap The value being set as the enrollmentCap
	 * @throws IllegalArgumentException If the enrollmentCap is less than
	 *                                  MIN_ENROLLMENT, greater than MAX_ENROLLMENT,
	 *                                  or less than the current number of enrolled
	 *                                  students
	 */
	public void setEnrollmentCap(int enrollmentCap) {
		if (enrollmentCap < MIN_ENROLLMENT || enrollmentCap > MAX_ENROLLMENT)
			throw new IllegalArgumentException("Invalid enrollmentCap.");
		else if (roll != null && enrollmentCap < roll.size())
			throw new IllegalArgumentException("enrollmentCap cannot be less than enrolled students.");
		else if(roll != null)
			roll.setCapacity(enrollmentCap);
		this.enrollmentCap = enrollmentCap;
	}

	/**
	 * Adds a new student object to the roll LinkedAbstractList
	 * 
	 * @param student The student being added to the course
	 * @throws IllegalArgumentException If the student cannot be added to the course
	 */
	public void enroll(Student student) {
		if (student == null)
			throw new IllegalArgumentException("Student cannot be null.");
		else if (getOpenSeats() == 0)
			throw new IllegalArgumentException("Course cannot exceed enrollmentCap.");
		else {
			try {
				roll.add(student);
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Student is already enrolled.");
			}
		}
	}

	/**
	 * Removes a student object from the roll LinkedAbstractList
	 * 
	 * @param student The student being removed from the course
	 * @throws IllegalArgumentException If the student cannot be removed from the
	 *                                  course
	 */
	public void drop(Student student) {
		if (student == null)
			throw new IllegalArgumentException("Cannot remove null student");
		else {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(student)) {
					roll.remove(i);
					break;
				}
			}
		}
	}

	/**
	 * Shows the number of open seats in a given course
	 * 
	 * @return The enrollment cap minus the number of enrolled students
	 */
	public int getOpenSeats() {
		return enrollmentCap - roll.size();
	}

	/**
	 * Returns true if a student is able to enroll in a course
	 * 
	 * @param student The student being added to the course
	 * @return False if the student can't be enrolled, or is already enrolled in the
	 *         course
	 */
	public boolean canEnroll(Student student) {
		if (getOpenSeats() == 0)
			return false;
		else {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(student))
					return false;
			}
			return true;
		}
	}
}
