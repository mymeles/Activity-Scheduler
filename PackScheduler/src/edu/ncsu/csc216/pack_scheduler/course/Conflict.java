/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import edu.ncsu.csc216.pack_scheduler.course.Activity;
import edu.ncsu.csc216.pack_scheduler.course.ConflictException;

/**
 * A class that Chekcs weather two instances of Activity have a conflict 
 *@author meles
 * @throws  ConflictException
 * 
 */
public interface Conflict {
	
	/**
	 * A method that Checks wather Course and Events have conflicts when they are added to The schedule 
	 * @param possibleConflictingActivity represents Courses and events 
	 * @throws ConflictException when event and course over lap in time and 
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;


}
