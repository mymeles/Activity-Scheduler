package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests metods within the CSC217Collections Library
 * 
 * @author Alex Bernard
 *
 */
public class SortedListTest {

	/**
	 * Tests that new created SortedLists contain no elements, and that the size of
	 * the list's capacity adjusts when new elements are added.
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));

		for (int i = 0; i < 11; i++) {
			list.add("object" + i);
		}
		assertEquals(11, list.size());

	}

	/**
	 * Tests the functionality of the add method by adding elements to specific
	 * positions in the list and attempting to throw IllegalArgumentException
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// Test adding to the front of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));

		// Tests adding to the middle of the list
		list.add("artichoke");
		assertEquals(3, list.size());
		assertEquals("artichoke", list.get(1));

		// Adds to the back of the list
		list.add("watermelon");
		assertEquals(4, list.size());
		assertEquals("watermelon", list.get(3));
		
		// Tests adding a null element
		try {
			list.add(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		
		// Tests adding a duplicate element
		try {
			list.add("banana");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(4, list.size());
		}
		
	}

	/**
	 * Tests SortedList.get() method for IndexOutOfBoundsExceptions
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// Tests getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		list.add("Watermelon");
		list.add("Apple");
		list.add("Orange");
		
		// Tests getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		
		// Tests getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		

	}

	/**
	 * Tests the functionality of the remove method through test cases evaluated invalid inputs and thrown exceptions
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		// Tests removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size()); // Possibly remove
		}
		
		// Add some elements to the list - at least 4
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");	
		
		// Tests removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size()); // 
		}
		
		// Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size()); // 
		}
		
		// Tests removing a middle element
		assertEquals("B", list.get(1));
		assertEquals(4, list.size());
		list.remove(1);
		assertEquals("C", list.get(1));
		assertEquals(3, list.size());
		
		// Tests removing the last element
		assertEquals("D", list.get(2));
		list.remove(2);
		assertEquals("C", list.get(1));
		assertEquals(2, list.size());
		
		// Tests removing the first element
		assertEquals("A", list.get(0));
		list.remove(0);
		assertEquals("C", list.get(0));
		assertEquals(1, list.size());
				
		// Tests removing the last element
		list.remove(0);
		assertEquals(0, list.size());		
	}

	/**
	 * Tests the indexOf method by showing the results of invalid inputs and thrown test exceptions
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("A"));
		
		// Add some elements
		list.add("D");
		list.add("B");
		list.add("A");
		list.add("C");
		
		// Tests various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("A"));
		assertEquals(2, list.indexOf("C"));
		assertEquals(-1, list.indexOf("F"));
		assertEquals(-1, list.indexOf("a"));
		
		// Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size()); // Remove later
		}
	}

	/**
	 * Tests the functionality of the SortedList.clear() method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		// Add some elements
		list.add("D");
		list.add("B");
		list.add("A");
		assertEquals(3, list.size());
		
		// Clear the list
		list.clear();
		
		// Test that the list is empty
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
	}

	/**
	 * Tests the functionality of the isEmpty method.
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();

		// Tests that the list starts empty
		assertTrue(list.isEmpty());
		list.add("D");
		// Checks that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests the functionality of the contains methods by using the command with elements that are and are not contained within the sorted list.
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("a"));

		list.add("D");
		list.add("B");
		list.add("A");
		list.add("C");
		
		// Test some true and false cases
		assertTrue(list.contains("A"));
		assertTrue(list.contains("D"));
		assertFalse(list.contains("E"));
		assertFalse(list.contains("a"));
	}

	/**
	 * Tests the equals method of SortedList by comparing equal and unequal lists
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		list1.add("A");
		list1.add("B");
		list2 = list1;
		list3.add("a");
		list3.add("B");		

		// Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertFalse(list2.equals(list3));
		assertFalse(list1.equals(list3));
	}

	/**
	 *  Tests the hashcode method of SortedLists by comparing the result from equal and unequal lists
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		// Make two lists the same and one list different
		list1.add("A");
		list1.add("B");
		list2 = list1;
		list3.add("a");
		list3.add("B");

		// TODO Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
	}

}