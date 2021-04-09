package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.roll.CourseRoll;
import edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator;
import edu.ncsu.csc216.pack_scheduler.course.validator.InvalidTransitionException;

/**
 * Creates a course object that stores a name, title, section, credit hours,
 * instructorId, meetingDays, starting and ending time
 * 
 * @author Alex Bernard
 */
public class Course extends Activity implements Comparable<Course> {

	// Constants used throughout the code.
	/** Minimum length of a course name */
	private static final int MIN_NAME_LENGTH = 4;

	/** Maximum length of a course name */
	private static final int MAX_NAME_LENGTH = 8;

	/** The length of any given section */
	private static final int SECTION_LENGTH = 3;

	/** Maximum number of credit hours in a course */
	private static final int MAX_CREDITS = 5;

	/** Minimum number of credit hours in a course */
	private static final int MIN_CREDITS = 1;

	// Below are the fields used for the program's constructors
	/** Course's name. */
	private String name;

	/** Course's section. */
	private String section;

	/** Course's credit hours */
	private int credits;

	/** Course's instructor */
	private String instructorId;

	/** Course's Enrollment Capacity */
	private CourseRoll roll;

	/** Validator for course naming convention */
	private CourseNameValidator validator = new CourseNameValidator();

	/**
	 * Sets the given values when constructing a course object.
	 * 
	 * @param name          The name of the course.
	 * @param title         The title of the course.
	 * @param section       The course section.
	 * @param credits       The course's number of credit hours.
	 * @param instructorId  The course instructor's ID
	 * @param meetingDays   The course's meeting days.
	 * @param startTime     The starting time of the course.
	 * @param endTime       The ending time of the course.
	 * @param enrollmetnCap The enrollment capacity of a course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmetnCap,
			String meetingDays, int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		this.setName(name);
		this.setSection(section);
		this.setCredits(credits);
		this.setInstructorId(instructorId);
		roll = new CourseRoll(enrollmetnCap);
	}

	/**
	 * Sets the given values when constructing a course object without the start or
	 * end time fields
	 * 
	 * @param name          The name of the course.
	 * @param title         The title of the course.
	 * @param section       The section of the course.
	 * @param credits       The course's credit hours.
	 * @param instructorId  The course instructor ID.
	 * @param meetingDays   The meeting days of the course.
	 * @param enrollmetnCap The enrollment capacity of a course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, int enrollmetnCap,
			String meetingDays) {
		this(name, title, section, credits, instructorId, enrollmetnCap, meetingDays, 0, 0);
	}

	/**
	 * Returns the name of the course
	 * 
	 * @return the name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the course
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is either
	 */
	private void setName(String name) {
		if (name == null)
			throw new IllegalArgumentException("Invalid name");
		else if (name.length() > MAX_NAME_LENGTH || name.length() < MIN_NAME_LENGTH) {
			throw new IllegalArgumentException("Invalid name");
		}
		boolean validatorResults = false;
		try {
			validatorResults = validator.isValid(name);
		} catch (InvalidTransitionException e) {
			throw new IllegalArgumentException("Invalid name");
		}
		if (validatorResults) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Invalid name");
		}
	}

	/**
	 * Returns the section of the course.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Changes the section of the course to the given title.
	 * 
	 * @param section The section to set.
	 * @throws IllegalArgumentException If the section is null or not equal to three
	 *                                  characters in length.
	 * @throws IllegalArgumentException If the section does not contain three digits
	 */
	public void setSection(String section) {
		if (section == null || section.length() != SECTION_LENGTH)
			throw new IllegalArgumentException("Invalid section number");
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i)))
				throw new IllegalArgumentException("Invalid section number");
		}
		this.section = section;
	}

	/**
	 * Returns the number of credit hours in the course.
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the number of credit hours in the course.
	 * 
	 * @param credits The credits to set as a field in the course
	 * @throws IllegalArgumentException If credits are not between 1 and 5,
	 *                                  inclusively
	 */
	public void setCredits(int credits) {
		if (credits > MAX_CREDITS || credits < MIN_CREDITS) {
			throw new IllegalArgumentException("Invalid credit hours");
		}
		this.credits = credits;
	}

	/**
	 * Returns the ID of the instructor for the course
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	
	/**
	 * A method that returns the Course roll of a course 
	 * @return a courseRoll of Course
	 */
	public CourseRoll getCourseRoll() {
		return roll;
	}

	/**
	 * Sets the ID of the instructor for the course
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException If instructorId is null or empty
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() == 0)
			throw new IllegalArgumentException("Invalid instructor unity id");
		this.instructorId = instructorId;
	}

	/**
	 * Returns a string containing the fields within the course object
	 * 
	 * @return String with the object fields
	 */
	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ roll.getEnrollmentCap() + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
				+ roll.getEnrollmentCap() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * Generates a hashCode using the Course's credit field, instructorId, name, and
	 * section.
	 * 
	 * @return A hashCode based on the course's current fields.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/**
	 * Determines if the current object and the given input are equivalent.
	 * 
	 * @return True if the opposing object's fields are equivalent the current
	 *         course.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Creates an array containing the name, section, title, and meetingString of
	 * the current object.
	 * 
	 * @return A shortened string array containing the field values of the current
	 *         object.
	 */
	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[5];
		shortDisplay[0] = getName();
		shortDisplay[1] = getSection();
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();
		shortDisplay[4] = "" + roll.getOpenSeats();
		return shortDisplay;
	}

	/**
	 * Creates a string array of length six containing the course fields.
	 * 
	 * @return A string array containing the fields present in the course object.
	 */
	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = getName();
		longDisplay[1] = getSection();
		longDisplay[2] = getTitle();
		longDisplay[3] = "" + getCredits();
		longDisplay[4] = getInstructorId();
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;
	}

	/**
	 * Sets the meeting days, start, and end times of the given course. Accounts for
	 * 'A' and omits 'U.'
	 * 
	 * @param meetingDays the meetingDays to set.
	 * @param startTime   The starting time of the given course.
	 * @param endTime     The end time of the given course.
	 * @throws IllegalArgumentException If the days or times fall out of bounds.
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days.");
		} else {
			for (int i = 0; i < meetingDays.length(); i++) {
				switch (meetingDays.charAt(i)) {
				case 'M':
					break;
				case 'T':
					break;
				case 'W':
					break;
				case 'H':
					break;
				case 'F':
					break;
				case 'A':
					break;
				default:
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
	}

	/**
	 * Determines if the given activity has the same class and title as the the
	 * current course.
	 * 
	 * @param activity The activity being compared to the current course
	 * @return True if the activity has the same title as the given course.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (getClass() != activity.getClass())
			return false;
		Course obj = (Course) activity;
		return this.getTitle().equals(obj.getTitle());
	}

	/**
	 * Analyzes the name and section strings of two course objects and determines if
	 * the current course object is placed before or after the other.
	 * 
	 * @param other The course object being compared to the current course.
	 * @return 1 if the current course is to be placed ahead of the other, -1 if the
	 *         current is placed before the other, and 0 if both are equal
	 */
	@Override
	public int compareTo(Course other) {
		if (!name.equals(other.getName())) {
			return compareStrings(name, other.getName());
		} else if (!this.getTitle().equals(other.getTitle())) {
			return compareStrings(this.getTitle(), other.getTitle());
		} else if (!section.equals(other.getSection())) {
			return compareStrings(section, other.getSection());
		} else
			return 0;
	}

	/**
	 * Compares each character in a set of Strings. When two unequal characters are
	 * found, the method determines which one has a higher value, if the current has
	 * the greater value, the method returns 1, and -1 otherwise.
	 * 
	 * @param current The string value of the current course field.
	 * @param other   The string value of the other course field
	 * @return 1 if current has the higher value, and -1 otherwise
	 */
	private int compareStrings(String current, String other) {
		for (int i = 0; i < current.length(); i++) {
			if (i >= other.length())
				return 1;
			char currentChar = current.charAt(i);
			char otherChar = other.charAt(i);
			if (Character.isLowerCase(currentChar))
				currentChar = Character.toUpperCase(currentChar);
			if (Character.isLowerCase(otherChar))
				otherChar = Character.toUpperCase(otherChar);
			if (currentChar > otherChar)
				return 1;
			else if (currentChar < otherChar)
				return -1;
		}
		if (current.length() < other.length())
			return -1;
		return 0;
	}
}
