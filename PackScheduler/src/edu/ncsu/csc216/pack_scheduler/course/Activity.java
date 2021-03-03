package edu.ncsu.csc216.pack_scheduler.course;

/**
 * A class representaion of a school Activtity that handles Courses and Events
 * 
 * @author meles
 */
public abstract class Activity implements Conflict {

	/** 
	 * This method sees overlap between activities and throws ConflictException if they have overlaping time and date 
	 * 
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {

		for (int i = 0; i < possibleConflictingActivity.getMeetingDays().length(); i++) {

			// this.meetingDays
			if (this.meetingDays.contains("" + possibleConflictingActivity.getMeetingDays().charAt(i))) {
				if(this.startTime == 0 || possibleConflictingActivity.startTime == 0) {
					return;
				}
				if (this.startTime <= possibleConflictingActivity.startTime
						&& this.endTime >= possibleConflictingActivity.startTime) {
					throw new ConflictException();
				}
				if (this.startTime <= possibleConflictingActivity.endTime
						&& this.endTime >= possibleConflictingActivity.endTime) {
					throw new ConflictException();
				}
				if (possibleConflictingActivity.startTime <= this.endTime
						&& possibleConflictingActivity.endTime >= this.endTime) {
					throw new ConflictException();
				}
				if (possibleConflictingActivity.startTime <= this.startTime
						&& possibleConflictingActivity.endTime >= this.startTime) {
					throw new ConflictException();

				}
			}
		} 
	}
 
	/** Activities title. */
	private String title;
	/** Activities meeting days */
	private String meetingDays;
	/** Activites starting time */
	private int startTime;
	/** Activities endTime */
	private int endTime;
	/** UPPER_HOUR limit for hour */
	static final int UPPER_HOUR = 24;
	/** upper limit for minute */
	static final int UPPER_MIN = 60;

	/**
	 * Constructs a class that represents an activity with title title, meeting days
	 * given with meetingDays, which starts at startTime and ends at endTime
	 * meetingDays is given as a concatenation of the first letters of the days the
	 * activity holds except for Thursday which is represented by "H" and Saturday
	 * which is represented by "U"
	 * 
	 * @param title       a string that holds title of the activity
	 * @param meetingDays a string that holds the meeting day of the activity
	 * @param startTime   an integer that holds the startTime of activity
	 * @param endTime     a string that holds the endTime of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);

	}

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
	 * It returns true is obj is equal to this. obj and this are equal if they refer
	 * to the smae object or obj is an instance of the same class and this and they
	 * have the same end time and meeting days, start and end times, and title
	 * 
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
	 * gets the title from the given parameter
	 * 
	 * @return a string value
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * sets the setTitle to the given parameter and checks whether the title is an
	 * empty string or null and throws an exce23ption of " invalid title"
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if (title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		if (title == null || title.length() == 0) {
			throw new IllegalArgumentException("Invalid title.");
		}

		this.title = title;
	}

	/**
	 * fetchs an array of 4 elemts where the 3rd and 4th elemnets are title and
	 * meeting information
	 * 
	 * @return a four element string array
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * fetchs an array of seven elements rwhere the 3rd and 6thth elemnets are title
	 * and meeting information contaning condensed information about the activity
	 * 
	 * @return a seven element string array detailed information about the activity
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * a method that checks wether event or course is duplicate
	 * 
	 * @param activity holds event and course
	 * @return true if duplicate otherwise false
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * 
	 * need to comment h sets the meeting-days and checks if the days are correct
	 * 
	 * the start time and checks if the time is in correct and checks if week days
	 * are valid if not it @throws IAE startTime endTime sets the endTime and checks
	 * if the time is correct if not @throws invalid time the below variables are
	 * counter that are incremented in their respective places a method to set the
	 * meeting days and class time *sets meeting days to the given parameter
	 * 
	 * @param meetingDays sets endTime to the given paramter
	 * @param endTime     sets start time to the given parameter
	 * @param startTime
	 * 
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {

		if ("A".equals(meetingDays)) {
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
			return;
		}
		// counter for Monday
		int countMonday = 0;
		// counter for Tuesday
		int countTuseday = 0;
		// counter for Wednesday
		int countWednesday = 0;
		// counter for Thursday
		int countThursday = 0;
		// counter for Friday
		int countFriday = 0;

		if (meetingDays == null || "".equals(meetingDays)) {
			throw new IllegalArgumentException("Invalid meeting days");

		}

		else {

			int startHour = startTime / 100;
			int startMin = startTime % 100;
			int endHour = endTime / 100;
			int endMin = endTime % 100;

			if (startHour < 0 || startHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid start time.");
			}

			else if (startMin < 0 || startMin >= UPPER_MIN) {
				throw new IllegalArgumentException("Invalid start time.");
			}

			else if (endHour < 0 || endHour >= UPPER_HOUR) {
				throw new IllegalArgumentException("Invalid end time.");
			}

			else if (endMin < 0 || endMin >= UPPER_MIN) {
				throw new IllegalArgumentException("Invalid end time.");
			} else if (endTime < startTime) {
				throw new IllegalArgumentException("End time cannot be before start time.");
			}
			for (int i = 0; i < meetingDays.length(); i++) {

				if (meetingDays.charAt(i) == 'M') {
					countMonday++;
				} else if (meetingDays.charAt(i) == 'T') {
					countTuseday++;
				} else if (meetingDays.charAt(i) == 'W') {
					countWednesday++;
				} else if (meetingDays.charAt(i) == 'H') {
					countThursday++;
				} else if (meetingDays.charAt(i) == 'F') {
					countFriday++;
				} else if (meetingDays.charAt(i) == 'U' || meetingDays.charAt(i) == 'S') {
					continue;
				}

				else {
					throw new IllegalArgumentException("Invalid meeting days.");
				}
			}

			if (countMonday > 1 || countTuseday > 1 || countWednesday > 1 || countThursday > 1 || countFriday > 1) {

				throw new IllegalArgumentException("Invalid meeting days.");
			}

		}
		this.meetingDays = meetingDays;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	private String getTimeString(int time) {
		String timeD = "0" + String.valueOf(time % 100);
		// endTime and startTime are given in military time so to convert
		// for hour divide and for minute get the remainder
		int timeH = time / 100;
		int timeM = time % 100;

		if (timeH >= 13 && timeM >= 10) {
			return (timeH - 12) + ":" + timeM + "PM";
		} else if (timeH > 12 && timeM < 10) {
			return (timeH - 12) + ":" + timeD + "PM";
		} else if (timeH < 12 && timeM >= 10) {
			return timeH + ":" + timeM + "AM";
		} else if (timeH == 12 && timeM >= 10) {
			return timeH + ":" + timeM + "PM";
		} else if (timeH == 12 && timeM < 10) {
			return timeH + ":" + timeD + "PM";
		} else {
			return timeH + ":" + timeD + "AM";
		}

	}

	/**
	 * returns a human readable time range and meeting days of the class schedule
	 * example "MW 1:30PM-2:45PM"
	 * 
	 * @return string of time date of the above format
	 */
	public String getMeetingString() {

		if (endTime == 0) {
			return "Arranged";
		}
		return meetingDays + " " + getTimeString(startTime) + "-" + getTimeString(endTime);
	}

	/**
	 * returns the value of StartTime
	 * 
	 * @return the tstartTime return timeH + ":" + timeM + "AM";
	 * 
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * returns the end time of the course
	 * 
	 * @return an integer
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * returns the meeting day of the course
	 * 
	 * @return a string
	 */
	public String getMeetingDays() {
		
		return meetingDays;
	}

}