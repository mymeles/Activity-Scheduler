/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.roll;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.util.LinkedAbstractList;
import edu.ncsu.csc216.pack_scheduler.util.LinkedQueue;
import edu.ncsu.csc216.pack_scheduler.util.Queue;

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

	/** An inetger representing Wait list size */
	private static final int WAITLIST_SIZE = 10;

	/** a course that is being enrolled */
	private Course course;

	/** a queue for wait list students */
	private Queue<Student> waitlist;

	/**
	 * The constructor for a CourseRoll object. The enrollmentCap is set to the
	 * given parameter and a new LinkedAbstractList of Student objects is created.
	 * 
	 * @param enrollmentCap The maximum number of students that can be enrolled in a
	 *                      single course
	 * @param course        representes a course that is being enrolled
	 */
	public CourseRoll(Course course, int enrollmentCap) {

		setCourse(course);
		setEnrollmentCap(enrollmentCap);
		roll = new LinkedAbstractList<Student>(this.enrollmentCap);
		waitlist = new LinkedQueue<Student>(WAITLIST_SIZE);
	}

	/**
	 * A method to set the course
	 * 
	 * @param course
	 */
	private void setCourse(Course course) {
		if (course == null)
			throw new IllegalArgumentException();
		this.course = course;
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
		else if (roll != null)
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
		else if (getOpenSeats() == 0 || enrollmentCap == roll.size())
			waitlist.enqueue(student);
		else if (waitlist.size() == WAITLIST_SIZE)
			throw new IllegalArgumentException();
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
		Student s;
		if (student == null)
			throw new IllegalArgumentException("Cannot remove null student");
		if (roll.contains(student))
			roll.remove(student);

		// checks if the Student is on wait list
		if (!waitlist.isEmpty()) {
			s = waitlist.dequeue(); // Removes and returns the element at the front of the Queue
			roll.add(roll.size(), s);
			s.getSchedule().addCourseToSchedule(course);
		} else {
			for (int i = 0; i < waitlist.size(); i++) {
				s = waitlist.dequeue(); // Removes and returns the element at the front of the Queue
				if (!student.equals(s)) {
					waitlist.enqueue(s);
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
		if (getOpenSeats() == 0 && waitlist.size() < WAITLIST_SIZE)
			return true;
		else if (waitlist.contains(student)) {
			return false;
		} else {
			for (int i = 0; i < roll.size(); i++) {
				if (roll.get(i).equals(student))
					return false;
			}
			return true;
		}
	}

	/**
	 * A method that returns the size of the students on wait list
	 * 
	 * @return an integer
	 */
	public int getNumberOnWaitlist() {
		return waitlist.size();
	}
}
