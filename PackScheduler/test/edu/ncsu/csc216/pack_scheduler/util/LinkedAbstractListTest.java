/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the functionality for 
 * @author Alex Bernard
 *
 */
public class LinkedAbstractListTest {

	/**
	 * Test size() method for LinkedAbstractList
	 */
	@Test
	public void testSize() {
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(4);
		// Assert size != capacity
		assertEquals(0, testList.size());
		// Size after adding object
		testList.add(0, "Celery");
		assertEquals(1, testList.size());
		// Size after IAE
		testList.add(1, "Cabbage");
		testList.add(2, "Carrot");
		testList.add(3, "Vegtable");
		assertEquals(4, testList.size());
		try {
			testList.add(4, "Fruit");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, testList.size());
		}
	}

	/**
	 * Tests functionality of the default constructor
	 */
	@Test
	public void testLinkedAbstractList() {
		// Test valid LinkedAbstractList
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(10);
		assertEquals(0, testList.size());
		
	}

	/**
	 * Tests setCapacity method for functionality
	 */
	@Test
	public void testSetCapacity() {
		LinkedAbstractList<String> testList = null;
		// Tests capacity <= 0
		try {
			testList = new LinkedAbstractList<String>(0);
			fail("Constructed Invalid LinkedList");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid capacity", e.getMessage());
			assertNull(testList);
		}
		try {
			testList = new LinkedAbstractList<String>(-1);
			fail("Constructed Invalid LinkedList");
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid capacity", e.getMessage());
			assertNull(testList);
		}
	}

	/**
	 * Test get() method for LinkedAbstractlist
	 */
	@Test
	public void testGet() {
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(5);
		String getElement = null;
		// Get from empty list
		try {
			getElement = testList.get(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(getElement);
		}
		
		// Add Elements
		testList.add(0, "Celery");
		testList.add(1, "Cabbage");
		testList.add(2, "Carrot");
		testList.add(3, "Vegtable");
		
		// Get Index Out of Bounds
		try {
			getElement = testList.get(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(getElement);
		}
		
		try {
			getElement = testList.get(4);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(getElement);
		}
		
		getElement = testList.get(0);
		assertEquals("Celery", getElement);
	}

	/**
	 * Test set() method for LinkedAbstractList 
	 */
	@Test
	public void testSet() {
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(4);
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
		
		// Set in middle
		removedElement = testList.set(2, "Vegtable 2");
		assertEquals("Carrot", removedElement);
		assertEquals("Vegtable 2", testList.get(2));
	}

	/**
	 * Tests the functionality of the add method
	 */
	@Test
	public void testAdd() {
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(6);
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
		
		// Test IndexOutOfBoundsException
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
		
		// Test IllegalArgumentException with duplicate
		try {
			testList.add(0, "Vegtable");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, testList.size());
		}
		
		// Test NullPointerException
		try {
			testList.add(0, null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, testList.size());
		}
		
		// Test IllegalArgumentException size == capacity
		testList.add(4, "Cauliflower");
		testList.add(5, "Lettuce");
		assertEquals(6, testList.size());
		try {
			testList.add(6, "Poo");
			fail();
		} catch(IllegalArgumentException e) {
			assertEquals("Size == Capacity", e.getMessage());
			assertEquals(6, testList.size());
		}
	}

	/**
	 * Test functionality of LinkedAbstractList remove() method
	 */
	@Test
	public void testRemove() {
		LinkedAbstractList<String> testList = new LinkedAbstractList<String>(5);
		String removedElement = null;
		// Remove from empty list
		try {
			testList.remove(0);
			fail();
		} catch(IndexOutOfBoundsException e) {
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
		} catch(IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}
		
		try {
			removedElement = testList.remove(5);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}
		
		try {
			removedElement = testList.remove(-1);
			fail();
		} catch(IndexOutOfBoundsException e) {
			assertNull(removedElement);
			assertEquals(4, testList.size());
		}
		
		// Remove from the end of the list
		removedElement = testList.remove(3);
		assertEquals("Vegtable", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Celery", testList.get(0));
		testList.add(3, "Vegtable");
		
		// Remove from the middle of the list
		removedElement = testList.remove(1);
		assertEquals("Cabbage", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Celery", testList.get(0));
		assertEquals("Carrot", testList.get(1));
		assertEquals("Vegtable", testList.get(2));
		testList.add(1, removedElement);
		removedElement = null;
		
		// Remove from the beginning of the list
		removedElement = testList.remove(0);
		assertEquals("Celery", removedElement);
		assertEquals(3, testList.size());
		assertEquals("Cabbage", testList.get(0));
		assertEquals("Carrot", testList.get(1));
		assertEquals("Vegtable", testList.get(2));
	}

}
