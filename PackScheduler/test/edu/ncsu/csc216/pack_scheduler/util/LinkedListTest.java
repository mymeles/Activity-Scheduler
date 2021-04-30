package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;
import java.util.ListIterator;

import org.junit.Test;

/**
 * A test class for LinkedList
 * @author Meles Meles
 *
 */
public class LinkedListTest {

	/**
	 * Default test for LinkedList constructor
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> testList = new LinkedList<String>();
		assertEquals(0, testList.size());
	}

	/**
	 * Tests the functionality of the ArrayList add method
	 */
	@Test
	public void testAdd() {
		LinkedList<String> testList = new LinkedList<String>();

		// Add to an empty list
		testList.add(0, "Celery");
		assertEquals("Celery", testList.get(0));

		// Add to the end of the list
		testList.add(1, "Cabbage");
		assertEquals("Cabbage", testList.get(1));
		assertEquals("Celery", testList.get(0));

		// Add to the beginning of the list
		testList.add(0, "Carrot");
		assertEquals(3, testList.size());
		assertEquals("Carrot", testList.get(0));
		assertEquals("Celery", testList.get(1));
		assertEquals("Cabbage", testList.get(2));

		// Add to the middle of the list
		testList.add(1, "Vegtable");
		assertEquals(4, testList.size());
		assertEquals("Carrot", testList.get(0));
		assertEquals("Vegtable", testList.get(1));
		assertEquals("Celery", testList.get(2));
		assertEquals("Cabbage", testList.get(3));

		// Test IndexOutOfBounds Exceptions
		try {
			testList.add(5, "Fruit");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, testList.size());
		}

		try {
			testList.add(-1, "Fruit");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, testList.size());
		}

		// Tests IllegalArgumentException with duplicate method
		try {
			testList.add(0, "Vegtable");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, testList.size());
		}

		// Tests NullPointerException
		try {
			testList.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, testList.size());
		}

		// Tests GrowArray
		testList.add(4, "Cauliflower");
		testList.add(5, "Lettuce");
		testList.add(6, "Greens");
		testList.add(7, "Other Vegtable");
		testList.add(8, "I'm out of vegtable names");
		testList.add(9, "Help");
		testList.add(10, "It never ends");
		assertEquals(11, testList.size());

		try {
			testList.set(9, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(11, testList.size());
		}
	}

	/**
	 * Tests the functionality of the remove method in ArrayList
	 */
	@Test
	public void testRemove() {
		LinkedList<String> testList = new LinkedList<String>();
		String removedElement = null;
		// Remove from empty list
		try {
			testList.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, testList.size());
		}

		// Remove from list with one element
		testList.add(0, "Celery");
		assertEquals(1, testList.size());
		removedElement = testList.remove(0);
		assertEquals("Celery", removedElement);
		assertEquals(0, testList.size());

		// Add multiple elements
		testList.add(0, "Celery");
		testList.add(1, "Cabbage");
		testList.add(2, "Carrot");
		testList.add(3, "Vegtable");

		// Remove from out of bounds
		removedElement = null;
		try {
			removedElement = testList.remove(4);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}

		try {
			removedElement = testList.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}

		try {
			removedElement = testList.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}

		// Remove from the end of the array
		removedElement = testList.remove(3);
		assertEquals("Vegtable", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Celery", testList.get(0));
		testList.add(3, "Vegtable");

		// Remove from the middle of the array
		removedElement = testList.remove(1);
		assertEquals("Cabbage", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Celery", testList.get(0));
		assertEquals("Carrot", testList.get(1));
		assertEquals("Vegtable", testList.get(2));
		testList.add(1, removedElement);
		removedElement = null;

		// Remove from the beginning of the array
		removedElement = testList.remove(0);
		assertEquals("Celery", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Cabbage", testList.get(0));
		assertEquals("Carrot", testList.get(1));
		assertEquals("Vegtable", testList.get(2));
	}

	/**
	 * Tests the functionality of the set method in LinkedList
	 */
	@Test
	public void testSet() {
		LinkedList<String> testList = new LinkedList<String>();
		String removedElement = null;

		// Set empty list (IndexOutOfBoundsException)
		removedElement = null;
		try {
			removedElement = testList.set(0, "Invalid");
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
		}

		// Set single element
		testList.add("Replace");
		removedElement = testList.set(0, "Celery");
		assertEquals("Replace", removedElement);
		assertEquals("Celery", testList.get(0));
		removedElement = null;

		// Add elements
		testList.add(1, "Cabbage");
		testList.add(2, "Carrot");
		testList.add(3, "Vegtable");

		// Set out of Bounds (IndexOutOfBoundsException)
		try {
			removedElement = testList.set(-1, "Fruit");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
		}

		try {
			removedElement = testList.set(4, "Fruit2");
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertNull(removedElement);
		}

		// Set duplicate element (IllegalArgumentException)
		try {
			removedElement = testList.set(0, "Vegtable");
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(removedElement);
			assertEquals("Celery", testList.get(0));
		}

		// Set Null (NullPointerException)
		try {
			removedElement = testList.set(3, null);
			fail();
		} catch (NullPointerException e) {
			assertNull(removedElement);
			assertEquals("Vegtable", testList.get(3));
		}

		// Set at beginning
		removedElement = testList.set(0, "Broccoli");
		assertEquals("Celery", removedElement);
		assertEquals("Broccoli", testList.get(0));
		removedElement = null;

		// Set at end
		removedElement = testList.set(3, "Tomato");
		assertEquals("Vegtable", removedElement);
		assertEquals("Tomato", testList.get(3));

	}
	
	/**
	 * Testing the LinkedList iterator class 
	 */
	@Test
	public void testIter() {
		LinkedList<String> testList = new LinkedList<String>();
		testList.add(0, "Cauliflower");
		testList.add(1, "Lettuce");
		testList.add(1, "Greens");
		testList.add(2, "Other Vegtable");
		testList.add(0, "I'm out of vegtable names");
		testList.add(5, "Help");
		testList.add(4, "It never ends");
		assertEquals(7, testList.size());
		
		ListIterator<String> iter = testList.listIterator(2);
		assertEquals(2, iter.nextIndex());
		assertEquals(1, iter.previousIndex());
		
		iter.add("tomator");
		assertEquals("tomator", iter.previous());
		iter.add("chiili");
		assertTrue(iter.hasPrevious());
		

		
	}
}
