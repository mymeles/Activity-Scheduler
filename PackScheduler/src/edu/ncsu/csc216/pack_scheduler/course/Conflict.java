/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Creates a method to check for a conflict exception between activities
 * @author Alex Bernard
 */
public interface Conflict {
	/**
	 * Determines if the time of the given activity conflicts with another activity in the schedule that shares the same meeting day
	 * @param possibleConflictingActivity The activity being tested for conflicts in the schedule
	 * @throws ConflictException If the time and days of the given activity overlaps with another.
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
