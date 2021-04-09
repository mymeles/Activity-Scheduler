package edu.ncsu.csc216.pack_scheduler.user;

import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.user.schedule.Schedule;

/**
 * The following class stores the first name, last name, id, email, password,
 * and credit hours of a given student.
 * 
 * @author Alex Bernard
 */
public class Student extends User implements Comparable<Student> {

	/** Maximum number of credits. */
	public static final int MAX_CREDITS = 18;

	/** The student's max credit hours. */
	private int maxCredits;
	
	/** Single instance of a schedule for each student object */
	private Schedule schedule = new Schedule();

	/**
	 * Constructs a student object with the given fields.
	 * 
	 * @param firstName  The student's first name.
	 * @param lastName   The student's last name.
	 * @param id         The student's id.
	 * @param email      The student's email.
	 * @param hashPW     The student's hash password.
	 * @param maxCredits The student's maximum number of credits.
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		this.setMaxCredits(maxCredits);
	}

	/**
	 * Constructs a student object without being given the max credits
	 * 
	 * @param firstName The student's first name.
	 * @param lastName  The student's last name.
	 * @param id        The student's id.
	 * @param email     The student's email.
	 * @param hashPW    The student's hash password.
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
	}

	/**
	 * Returns the student's maximum credits.
	 * 
	 * @return the maxCredits.
	 */
	public int getMaxCredits() {
		return maxCredits;
	}

	/**
	 * Sets the given max credits.
	 * 
	 * @param maxCredits the maxCredits to set.
	 * @throws IllegalArgumentException If max Credits are greater than 18 or lower
	 *                                  than 3
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits > MAX_CREDITS || maxCredits < 3)
			throw new IllegalArgumentException("Invalid max credits");
		this.maxCredits = maxCredits;
	}

	/**
	 * Creates a comma separated string using the object's fields.
	 * 
	 * @return String containing a list of fields in the student object separated by
	 *         commas.
	 */
	@Override
	public String toString() {
		String finalString = this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail()
				+ "," + this.getPassword() + "," + maxCredits;
		return finalString;
	}

	/**
	 * This method determines a student object's position in relation to another
	 * given student object. The method first compares last names, then first names,
	 * and IDs last.
	 * 
	 * @param o The other student object
	 */
	@Override
	public int compareTo(Student o) {
		if (!this.getLastName().equals(o.getLastName())) {
			return compareStrings(this.getLastName(), o.getLastName());

		} else if (!this.getFirstName().equals(o.getFirstName())) {
			return compareStrings(this.getFirstName(), o.getFirstName());

		} else if (!this.getId().equals(o.getId())) {
			return compareStrings(this.getId(), o.getId());

		}
		return 0;
	}

	/**
	 * Helper method for compareTo. Iterates through the input string values and
	 * determines which is to be placed first in a sorted list
	 * 
	 * @param here  The value of one of this object's fields
	 * @param other The field of the opposing object the current is being compared
	 *              to.
	 * @return 1 if this object is placed ahead of the opposing object, and -1 in
	 *         the opposite case.
	 */
	private int compareStrings(String here, String other) {
		for (int i = 0; i < here.length(); i++) {
			if (i >= other.length())
				return 1;
			char thisChar = here.charAt(i);
			char otherChar = other.charAt(i);
			if (thisChar != otherChar) {
				if (Character.isLowerCase(thisChar))
					Character.toUpperCase(thisChar);
				if (Character.isLowerCase(otherChar))
					Character.toUpperCase(otherChar);
				if (thisChar > otherChar)
					return 1;
				else if (thisChar < otherChar)
					return -1;
			}
		}
		return -1;
	}

	/**
	 * Returns a unique hashCode based on the current Student's max credits and
	 * fields that are listed as part of this object's superclass
	 * 
	 * @return A hashCode generated from the current Student's maxCredits field and
	 *         User superclass fields
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
		return result;
	}

	/**
	 * Determines if the Student and User fields of the two given objects are equal.
	 * 
	 * @return True if the fields of each object are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return maxCredits == other.maxCredits;

	}
	
	/**
	 * Getter method for pulling in the student's specific schedule for adjustments
	 * 
	 * @return the student's schedule
	 */
	public Schedule getSchedule() {
		return schedule;
	}
	
	/**
	 * Method that determines if the selected course "can" be added to the student's schedule
	 * @param course selected by the student
	 * @return true if the course can be added to the schedule
	 */
	public boolean canAdd(Course course) {
		if (!schedule.canAdd(course)) {
			return false;
		} 
		return !(schedule.getScheduleCredits() + course.getCredits() > this.maxCredits);
	}
}
