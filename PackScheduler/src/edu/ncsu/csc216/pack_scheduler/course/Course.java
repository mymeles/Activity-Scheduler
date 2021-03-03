package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.user.Student;

/**
 * A class that represent one course
 * 
 * @author meles
 */
public class Course extends Activity implements Comparable<Course> {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;

	/**
	 * Constructs a Course object with values for all fields
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 * @param startTime    start time for Course
	 * @param endTime      end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);

	}

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
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name         name of Course
	 * @param title        title of Course
	 * @param section      section of Course
	 * @param credits      credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays  meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {

		this(name, title, section, credits, instructorId, meetingDays, 0, 0);

	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */

	@Override
	public String toString() {
		if ("A".equals(getMeetingDays())) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + ","
					+ getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays()
				+ "," + getStartTime() + "," + getEndTime();
	}

	/**
	 * return the Name of the course
	 * 
	 * @return name as a string
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Constraints for setting the name*
	 *
	 * Constant to Determines the length of the name
	 */
	final static int MIN_NAME_LENGTH = 5;
	/** constant to determines the maximum length of name */
	final static int MAX_NAME_LENGHT = 8;
	/** constant determines the minimum letter count */
	final static int MIN_LETTER_COUNT = 1;
	/** Constant to determine the maximum letter count */
	final static int MAX_LETTER_COUNT = 4;
	/** Constant to determine the digit count */
	final static int DIGIT_COUNT = 3;

	/**
	 * Sets the Course's name. If the name is null, has a length less than 5 or more
	 * than 8, does not contain a space between letter characters and number
	 * characters, has less than 1 or more than 4 letter characters, and not exactly
	 * three trailing digit characters, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {

		if (name == null) {
			throw new IllegalArgumentException("Name cannot be null.");
		}

		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGHT) {
			throw new IllegalArgumentException("Name's length should be between 5 and 8, inclusive.");
		}

		int countLetter = 0;
		int countDigit = 0;
		boolean flag = false;
		for (int i = 0; i < name.length(); i++) {

			if (!flag) {
				if (Character.isLetter(name.charAt(i))) {
					countLetter++;
				} else if (name.charAt(i) == ' ') {
					flag = true;
				} else {

					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
				}
			}

			else {
				if (Character.isDigit(name.charAt(i))) {
					countDigit++;
				}

				else {

					throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
				}

			}
		}
		// check that the number of letters is correct
		if (countLetter < MIN_LETTER_COUNT || countLetter > MAX_LETTER_COUNT) {

			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}

		// check that number of digit is correct
		if (countDigit != DIGIT_COUNT || countDigit < DIGIT_COUNT) {

			throw new IllegalArgumentException("Names should have 1-4 letters, a space, and 3 digits.");
		}

		this.name = name;

	}

	/**
	 * gets the section from the parameter
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/** Constant to determines the section length */
	final static int SECTION_LENGTH = 3;

	/**
	 * sets the section to the given parameter checks whether the section is null or
	 * or if the digits are
	 * CCharacter.isDigit(name.charAt(i))haracter.isDigit(name.charAt(i)) not 3 and
	 * throw
	 * 
	 * @param section the section to set
	 * 
	 */
	public void setSection(String section) {

		if (section == null || section.length() != SECTION_LENGTH) {

			throw new IllegalArgumentException("Invalid section.");
		}

		// it is saying if the char is not equal to digit throw IAE
		for (int i = 0; i < section.length(); i++) {
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Section should be Three Digit");
			}

		}

		this.section = section;
	}

	/**
	 * returns the value of credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/** Minimum credit hour limit */
	static final int MIN_CREDITS = 1;
	/** maximum credit hour limit */
	static final int MAX_CREDITS = 5;

	/**
	 * checks to see if the credits are between 1 to 5 and @throws an exception of
	 * "Credits should be between 1 and 5, inclusive" sets the credits to the given
	 * parameter
	 * 
	 * @param credits the credits to set sets limit to the lower credit hour
	 * 
	 */
	public void setCredits(int credits) {

		if (credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Credits should be between 1 and 5, inclusive.");
		}

		this.credits = credits;
	}

	/**
	 * returns the value of instuctorId
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * set the instructor id
	 * 
	 * @param instructorId sets the id
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || "".equals(instructorId)) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		this.instructorId = instructorId;
	}

	/**
	 * overrides method in activity parent class so course won't meet if meetingday
	 * is weekend.
	 * 
	 * @param meetingDays day course meets
	 * @param startTime   time course starts
	 * @param endTime     time course ends
	 * @throws IAE if meetingday is invalid( the weekend)
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		if (meetingDays.contains("U") || meetingDays.contains("S")) {
			throw new IllegalArgumentException("Invalid meeting days.");
		}

		super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] shortDisplay = new String[4];
		shortDisplay[0] = name;
		shortDisplay[1] = section;
		shortDisplay[2] = getTitle();
		shortDisplay[3] = getMeetingString();

		return shortDisplay;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longDisplay = new String[7];
		longDisplay[0] = name;
		longDisplay[1] = section;
		longDisplay[2] = getTitle();
		longDisplay[3] = Integer.toString(credits);
		longDisplay[4] = instructorId;
		longDisplay[5] = getMeetingString();
		longDisplay[6] = "";
		return longDisplay;

	}

	/**
	 * this method is overridden in activity so that it can check course have the
	 * same name
	 * 
	 * @returns true if the course has the same otherwise false.
	 */
	@Override
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course c = (Course) activity;
			return this.getTitle().equals(c.getTitle());
		}

		return false;
	}

	@Override
	public int compareTo(Course c) {
		if (c == null) {
			throw new NullPointerException();
		}
		if (this.name.compareToIgnoreCase(c.getName()) == 0 && this.section.compareToIgnoreCase(c.getSection()) == 0) {
			return 0;
		}

		if (this.name.compareToIgnoreCase(c.getName()) < 0) {
			return -1;
		} else if (this.name.compareToIgnoreCase(c.getName()) > 0) {
			return 1;
		} else if (this.name.compareToIgnoreCase(c.getName()) == 0) {
			if (this.section.compareToIgnoreCase(c.getSection()) < 0) {
				return -1;
			} else if (this.section.compareToIgnoreCase(c.getSection()) > 0) {
				return 1;

			}
		}
		return 0;

	}

}
