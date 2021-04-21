package edu.ncsu.csc216.pack_scheduler.user;

/**
 * The following class stores the first name, last name, id, email, password,
 * and courses numbers of a given faculty.
 * 
 * @author Meles Meles
 */
public class Faculty extends User {

	/** Maximum number of courses. */
	public static final int MAX_COURSES = 3;

	/** Maximum number of courses. */
	public static final int MIN_COURSES = 1;

	/** The Faculty's max courses numbers. */
	private int maxCourses;


	/**
	 * Constructs a Faculty object with the given fields.
	 * 
	 * @param firstName  The Faculty's first name.
	 * @param lastName   The Faculty's last name.
	 * @param id         The Faculty's id.
	 * @param email      The Faculty's email.
	 * @param hashPW     The Faculty's hash password.
	 * @param maxCourses The Faculty's maximum number of courses.
	 */
	public Faculty(String firstName, String lastName, String id, String email, String hashPW, int maxCourses) {
		super(firstName, lastName, id, email, hashPW);
		this.setMaxCourses(maxCourses);
	}

	/**
	 * Returns the Faculty's maximum courses.
	 * 
	 * @return the maxCourses.
	 */
	public int getMaxCourses() {
		return maxCourses;
	}

	/**
	 * Sets the given max courses.
	 * 
	 * @param maxCourses the maxCourses to set.
	 * @throws IllegalArgumentException If max courses are greater than 18 or lower
	 *                                  than 3
	 */
	public void setMaxCourses(int maxCourses) {
		if (maxCourses < MIN_COURSES || maxCourses > MAX_COURSES)
			throw new IllegalArgumentException("Invalid max Courses");
		this.maxCourses = maxCourses;
	}

	/**
	 * Creates a comma separated string using the object's fields.
	 * 
	 * @return String containing a list of fields in the Faculty object separated by
	 *         commas.
	 */ 
	@Override
	public String toString() {
		String finalString = this.getFirstName() + "," + this.getLastName() + "," + this.getId() + "," + this.getEmail()
				+ "," + this.getPassword() + "," + maxCourses;
		return finalString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCourses;
		return result;
	}

	/**
	 * Determines if the faculty's and User fields of the two given objects are equal.
	 * 
	 * @return True if the fields of each object are equal
	 */ 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (maxCourses != other.maxCourses)
			return false;
		return true;
	}

}
