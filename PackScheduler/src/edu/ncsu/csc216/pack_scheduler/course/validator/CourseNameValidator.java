/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

/**
 * The following class is used to determine if a given courseName is valid
 * 
 * @author Alex Bernard
 *
 */
public class CourseNameValidator {
	/** Boolean determining if the given string is a valid course name */
	private boolean validEndState;

	/** The number of letters in the given string minus the suffix */
	private int letterCount;

	/** The number of digits in the given string */
	private int digitCount;

	/** State determining which methods the FSM will access at a given time */
	private State currentState;

	/** Variable used to change the currentState to the initialState */
	private State initialState = new InitialState();

	/** Variable used to change the currentState to the LetterState */
	private State letterState = new LetterState();

	/** Variable used to change the currentState to the NumberState */
	private State numberState = new NumberState();

	/** State used to change the currentState to the SuffixState */
	private State suffixState = new SuffixState();

	/**
	 * Determines if the given name fits the criteria for a course name
	 * 
	 * @param courseName The name of the given course
	 * @return True if the name has at most 4 letters and 3 digits
	 * @throws InvalidTransitionException If a transition is not allowed by the FSM
	 */
	public boolean isValid(String courseName) throws InvalidTransitionException {
		validEndState = false;
		letterCount = 0;
		digitCount = 0;
		currentState = initialState;
		if (courseName == null)
			throw new InvalidTransitionException();
		for (int i = 0; i < courseName.length(); i++) {
			if (Character.isLetter(courseName.charAt(i))) {
				currentState.onLetter();
			} else if (Character.isDigit(courseName.charAt(i))) {
				currentState.onDigit();
			} else {
				currentState.onOther();
			}
		}
		return validEndState;
	}

	/**
	 * The class lists the methods shared by the 4 states used in the FSM machine
	 * 
	 * @author Alex Bernard
	 *
	 */
	public abstract class State {
		/**
		 * The class's behavior when the given character is a letter
		 * 
		 * @throws InvalidTransitionException If the given state cannot transition from
		 *                                    a letter value
		 */
		public abstract void onLetter() throws InvalidTransitionException;

		/**
		 * The class's behavior when analyzing a digit
		 * 
		 * @throws InvalidTransitionException If the given state cannot transition from
		 *                                    a digit value
		 */
		public abstract void onDigit() throws InvalidTransitionException;

		/**
		 * The class's behavior when on any other type of character
		 * 
		 * @throws InvalidTransitionException If the given state is located on a
		 *                                    character other than a digit or letter
		 */
		public void onOther() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only contain letters and digits.");
		}
	}

	/**
	 * The list of methods used when the FSM starts
	 * 
	 * @author Alex Bernard
	 *
	 */
	public class InitialState extends State {

		/**
		 * Increments the letterCount and transitions the currentState to the
		 * LetterState
		 */
		@Override
		public void onLetter() {
			letterCount++;
			currentState = letterState;
		}

		/**
		 * Stops the FSM if a digit is the first character in the given string
		 * 
		 * @throws InvalidTransitionException If the first character is a digit rather
		 *                                    than a letter
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name must start with a letter.");

		}
	}

	/**
	 * The list of methods used after the FSM has looked over a letter
	 * 
	 * @author Alex Bernard
	 *
	 */
	public class LetterState extends State {

		/**
		 * Increments the letterCount and throws an exception if letterCount is greater
		 * than 4
		 * 
		 * @throws InvalidTransitionException if the letterCount variable increments
		 *                                    more than 4 times.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			letterCount++;
			if (letterCount > 4)
				throw new InvalidTransitionException("Course name cannot start with more than 4 letters.");
		}

		/**
		 * Transition method from LetterState to NumberState.
		 */
		@Override
		public void onDigit() {
			digitCount++;
			currentState = numberState;
		}
	}

	/**
	 * The list of methods used after the FSM has looked over a digit
	 * 
	 * @author Alex Bernard
	 *
	 */
	public class NumberState extends State {

		/**
		 * Transitions the FSM to the suffixState if 3 digits have been incremented.
		 * 
		 * @throws InvalidTransitionException If the digitCount has not been incremented
		 *                                    3 times.
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			if (digitCount < 3)
				throw new InvalidTransitionException("Course name must have 3 digits.");
			else
				currentState = suffixState;

		}

		/**
		 * Increments the digitCount variable and sets the validEndState to true if
		 * digitCount reaches 3. Throws an exception if the digitCount exceeds the
		 * maximum
		 * 
		 * @throws InvalidTransitionException If the digitCount increments over 3
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			digitCount++;
			if (digitCount == 3)
				validEndState = true;
			else if (digitCount > 3)
				throw new InvalidTransitionException("Course name can only have 3 digits.");

		}
	}

	/**
	 * The list of methods used after the FSM has looked over a letter after the
	 * three listed digits
	 * 
	 * @author Alex Bernard
	 *
	 */
	public class SuffixState extends State {

		/**
		 * Stops the FSM if an additional letter is placed after the suffix
		 * 
		 * @throws InvalidTransitionException If the string contains another letter
		 *                                    after the suffix
		 */
		@Override
		public void onLetter() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name can only have a 1 letter suffix.");

		}

		/**
		 * Stops the FSM if there is a digit placed after the suffix
		 * 
		 * @throws InvalidTransitionException If the string contains a digit after the
		 *                                    suffix.
		 */
		@Override
		public void onDigit() throws InvalidTransitionException {
			throw new InvalidTransitionException("Course name cannot contain digits after the suffix.");

		}
	}
}
