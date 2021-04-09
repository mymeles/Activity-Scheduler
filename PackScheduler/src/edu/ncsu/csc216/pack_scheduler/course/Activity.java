package edu.ncsu.csc216.pack_scheduler.course;

/**
 * This superclass saves the title, meeting days, starting and ending times of
 * any given course. It also contains code to determine if any two
 * activities are equivalent.
 * 
 * @author Alex Bernard
 *
 */
public abstract class Activity implements Conflict {

	/**
	 * If the meeting day and time of one activity occurs simultaneously with another activity in the 
	 * schedule, a ConflictException is thrown.
	 * @param possibleConflictingActivity The activity being compared to those in the schedule for time conflicts.
	 * @throws ConflictException If two activities sharing the same meeting day have overlapping times.
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameDay = false;
		for (int i = 0; i < meetingDays.length(); i++) {
			char thisDay = meetingDays.charAt(i);
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				char possibleActivityDay = possibleConflictingActivity.getMeetingDays().charAt(j);
				if (thisDay == possibleActivityDay && thisDay != 'A') {
					sameDay = true;
					break;
				}
			}
		}
		if (sameDay) {
			int possibleStartTime = possibleConflictingActivity.getStartTime();
			int possibleEndTime = possibleConflictingActivity.getEndTime();
			if (startTime >= possibleStartTime && startTime <= possibleEndTime) {
				throw new ConflictException(title + " occurs during " + possibleConflictingActivity.getTitle());
			} else if (possibleStartTime >= startTime && possibleStartTime <= endTime) {
				throw new ConflictException(possibleConflictingActivity.getTitle() + " occurs during " + title);
			}
		}		
	}

	/** Maximum number of hours in a day */
	private static final int UPPER_HOUR = 24;
	/** Maximum number of minutes in an hour */
	private static final int UPPER_MINUTE = 60;
	/** Activity's title. */
	private String title;
	/** Activity's meeting days */
	private String meetingDays;
	/** Activity's starting time */
	private int startTime;
	/** Activity's ending time */
	private int endTime;

	/**
	 * Constructs a new subclass under the Activity superclass
	 * 
	 * @param title       The title of the given activity.
	 * @param meetingDays Meeting days of the given activity.
	 * @param startTime   The starting time of the given activity.
	 * @param endTime     The endTime of the given activity.
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}

	/**
	 * Returns the Activity's title.
	 * 
	 * @return the title of the course
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Changes the title of the Activity.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException If title is null or empty
	 */
	public void setTitle(String title) {
		if (title == null || title.length() == 0)
			throw new IllegalArgumentException("Invalid course title");
		this.title = title;
	}

	/**
	 * Returns the meeting days of the class.
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the meeting days, start, and end times of the given activity.
	 * 
	 * @param meetingDays The meetingDays to set.
	 * @param startTime   The starting time of the given Activity.
	 * @param endTime     The end time of the given Activity.
	 * @throws IllegalArgumentException If the days or times fall out of bounds.
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		if (meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days.");
		} else if (meetingDays.charAt(0) == 'A' && meetingDays.length() == 1) {
			this.meetingDays = "A";
			this.startTime = 0;
			this.endTime = 0;
		} else {
			int countM = 0;
			int countT = 0;
			int countW = 0;
			int countH = 0;
			int countF = 0;
			int countS = 0;
			int countU = 0;
			for (int i = 0; i < meetingDays.length(); i++) {
				switch (meetingDays.charAt(i)) {
				case 'M':
					countM += 1;
					break;
				case 'T':
					countT += 1;
					break;
				case 'W':
					countW += 1;
					break;
				case 'H':
					countH += 1;
					break;
				case 'F':
					countF += 1;
					break;
				case 'S':
					countS += 1;
					break;
				case 'U':
					countU += 1;
					break;
				default:
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}
			if (countM > 1 || countT > 1 || countW > 1 || countH > 1 || countF > 1) {
				throw new IllegalArgumentException("Invalid meeting days.");
			}
			if (!isValidTime(startTime))
				throw new IllegalArgumentException("Invalid start time.");
			if (!isValidTime(endTime))
				throw new IllegalArgumentException("Invalid end time.");
			if (endTime < startTime)
				throw new IllegalArgumentException("End time cannot be before start time.");
			this.meetingDays = "";
			if (countU == 1)
				this.meetingDays += "U";
			if (countM == 1)
				this.meetingDays += "M";
			if (countT == 1)
				this.meetingDays += "T";
			if (countW == 1)
				this.meetingDays += "W";
			if (countH == 1)
				this.meetingDays += "H";
			if (countF == 1)
				this.meetingDays += "F";
			if (countS == 1)
				this.meetingDays += "S";
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	/**
	 * Determines if the given time is valid for the given activity.
	 * 
	 * @param timeInput The Activity's time given in Military Time format
	 * @return true If the minutes are between 60 and 0
	 */
	private boolean isValidTime(int timeInput) {
		int inputHours = timeInput / 100;
		int inputMinutes = timeInput % 100;
		return !(inputHours >= UPPER_HOUR || inputHours < 0 || inputMinutes >= UPPER_MINUTE || inputMinutes < 0);
	}

	/**
	 * Creates a string containing the meeting days, start and end times of a given
	 * activity.
	 * 
	 * @return String containing the starting and end time of the activity along with
	 *         its meeting days
	 */
	public String getMeetingString() {
		String finalMessage = "";
		String startString = "";
		String endString = "";
		if (meetingDays.charAt(0) == 'A')
			finalMessage = "Arranged";
		else {
			startString = getTimeString(startTime);
			endString = getTimeString(endTime);
			finalMessage += meetingDays + " " + startString + "-" + endString;
		}
		return finalMessage;
	}

	/**
	 * Creates a string that converts the time from military to standard format.
	 * 
	 * @param time The start or end time given in military format
	 * @return String containing the time in standard format
	 */
	private String getTimeString(int time) {
		String finalString = "";
		boolean atPM = false;
		int hours = 0;
		int minutes = 0;
		hours = time / 100;
		if (hours > 11) {
			atPM = true;
			if (hours != 12)
				hours -= 12;
		}
		minutes = time % 100;
		finalString += hours + ":";
		if (minutes < 10)
			finalString += 0;
		finalString += minutes;
		if (atPM)
			finalString += "PM";
		else
			finalString += "AM";
		return finalString;
	}

	/**
	 * Returns the start time of the course.
	 * 
	 * @return The startTime of the course.
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the end time of the course.
	 * 
	 * @return The endTime of the course
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Creates a shortened string array of fields for the GUI to display.
	 * 
	 * @return A shortened array of strings for the GUI to display.
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Creates a longer string array of fields for the GUI to display.
	 * 
	 * @return An array of strings to be displayed by the GUI.
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * This method generates a unique hashCode for the given object based on its
	 * current fields.
	 * 
	 * @return A hash code based on the endTime, startTime, meetingDays, and title
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * This method determines if two Activity objects are equivalent based on given
	 * fields.
	 * 
	 * @param obj The object being compared to the current activity.
	 * @return True if the endTime, meetingDays, startTime, and title are equal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Determines if two activity objects are equivalent.
	 * 
	 * @param activity The activity being compared to the current object.
	 * @return True if two activities have the same name and class
	 */
	public abstract boolean isDuplicate(Activity activity);
}