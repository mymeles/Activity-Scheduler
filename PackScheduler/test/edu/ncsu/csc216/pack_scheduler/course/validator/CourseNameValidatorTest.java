/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the CourseNameValidator FSM
 * @author Alex Bernard
 *
 */
public class CourseNameValidatorTest {

	/**
	 * Test method for {@link edu.ncsu.csc216.pack_scheduler.course.validator.CourseNameValidator#isValid(java.lang.String)}.
	 */
	@Test
	public void testIsValid() {
		CourseNameValidator testValidator = new CourseNameValidator();
		Boolean isValid;
		// Test Characters other than digit || letter
		try {
			testValidator.isValid("CSC_216E");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only contain letters and digits.", e.getMessage());
		}
		// Test State I Fail/Other
		try {
			testValidator.isValid("5CSC216");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must start with a letter.", e.getMessage());
		}
		
		// Test State L Fail
		isValid = true;
		try {
			isValid = testValidator.isValid("C");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State LL Fail
		isValid = true;
		try {
			isValid = testValidator.isValid("CS");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State LLL Fail
		isValid = true;
		try {
			isValid = testValidator.isValid("CSC");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State LLLL Fail
		try {
			testValidator.isValid("CSCSC216");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot start with more than 4 letters.", e.getMessage());
		}
		
		isValid = true;
		try {
			isValid = testValidator.isValid("CSCS");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State D Fail
		try {
			testValidator.isValid("CSC2I6");
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		isValid = true;
		try {
			isValid = testValidator.isValid("CSC1");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State DD Fail
		
		try {
			testValidator.isValid("CSCS21G6");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name must have 3 digits.", e.getMessage());
		}
		
		isValid = true;
		try {
			isValid = testValidator.isValid("CSC11");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertFalse(isValid);
		
		// Test State DDD Fail
		try {
			testValidator.isValid("CSC2166");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have 3 digits.", e.getMessage());
		}
		
		// Test State Suffix Fail
		try {
			testValidator.isValid("CSC216bb");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name can only have a 1 letter suffix.", e.getMessage());
		}
		
		try {
			testValidator.isValid("CSC216b1");
			fail();
		} catch (InvalidTransitionException e) {
			assertEquals("Course name cannot contain digits after the suffix.", e.getMessage());
		}
		// Test valid String (L-DDD)
		isValid = false;
		try {
			isValid = testValidator.isValid("E116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LL-DDD)
		isValid = false;
		try {
			isValid = testValidator.isValid("CS116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LLL-DDD)
		isValid = false;
		try {
			isValid = testValidator.isValid("CSC116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LLLL-DDD)
		isValid = false;
		try {
			isValid = testValidator.isValid("CSCS116");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (L-DDDs)
		isValid = false;
		try {
			isValid = testValidator.isValid("E116s");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LL-DDDs)
		isValid = false;
		try {
			isValid = testValidator.isValid("CS116s");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LLL-DDDs)
		isValid = false;
		try {
			isValid = testValidator.isValid("CSC116s");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
		// Test valid String (LLLL-DDDs)
		isValid = false;
		try {
			isValid = testValidator.isValid("CSCS116s");
		} catch (InvalidTransitionException e) {
			fail();
		}
		assertTrue(isValid);
		
	}

}
