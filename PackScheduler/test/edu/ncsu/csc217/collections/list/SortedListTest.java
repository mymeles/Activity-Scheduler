package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * A Test class fopr Sorted list
 * 
 * @author meles
 *
 */
public class SortedListTest {
	
	/**
	 * A method to test Sorted List 
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple")); 

		// TTest that the list grows by adding at least 11 elements
		// Remember the list's initial capacity is 10
		list.add("apples");
		list.add("mango");
		list.add("grapefruit");
		list.add("avocado");
		list.add("pineapple");
		list.add("apricot");
		list.add("papaya"); 
		list.add("pomegranate");
		list.add("peach");
		list.add("jackfruit");
		list.add("lychee");
		assertEquals(11, list.size());

	}

	/**
	 * A method to test add in sorted list
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();

		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));

		// Test adding to the front, middle and back of the list
		list.add("apple");
		assertEquals("apple", list.get(0));

		list.add("mango");
		assertEquals("mango", list.get(2));

		list.add("avocado");
		assertEquals("avocado", list.get(1));

		// Test adding a null element
		boolean thrown = false;
		try {
			list.add(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown);

		// Test adding a duplicate element
		thrown = false;
		try {
			list.add("avocado");
			fail();
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		assertTrue(thrown);

	}

	/**
	* a test method for get method in sorted list
	*/
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		// Since get() is used throughout the tests to check the
		// contents of the list, we don't need to test main flow functionality
		// here. Instead this test method should focus on the error
		// and boundary cases.

		// Test getting an element from an empty list
		boolean thrown = false;
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
		// Add some elements to the list
		list.add("apples");
		list.add("mango");

		// Test getting an element at an index < 0
		thrown = false;
		try {
			list.get(-1);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);

		// Test getting an element at size
		thrown = false;
		try {
			list.get(2);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
	}

	/**
	* A method to test remove from sorted list
	*/
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(1, 1);

		// Test removing from an empty list
		boolean thrown = false;
		try {
			list.get(0);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);

		// Add some elements to the list - at least 4
		list.add("apples");
		list.add("avocado");
		list.add("mango");
		list.add("grapefruit");
		list.add("pineapple");
		list.add("apricot");
		list.add("papaya");

		// Test removing an element at an index < 0
		thrown = false;
		try {
			list.remove(-2);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}

		// Test removing an element at size
		thrown = false;
		try {
			list.remove(7);
		} catch (IndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue(thrown);
		// Test removing a middle element
		list.remove(2);
		assertEquals(6, list.size());
		assertEquals("grapefruit", list.get(2));
		// Test removing the last element
		list.remove(5);
		assertEquals(5, list.size());
		assertEquals("papaya", list.get(4));

		// Test removing the first element
		list.remove(0);
		assertEquals(4, list.size());
		assertEquals("apricot", list.get(0));

		// Test removing the last element
		list.remove(3);
		assertEquals(3, list.size());
		assertEquals("mango", list.get(2));
	}

	/**
	 * A method to test IndexOf from sorted list
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();

		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apples"));

		// Add some elements
		list.add("apples");
		list.add("mango");
		list.add("grapefruit");
		list.add("avocado");
		list.add("pineapple");
		list.add("apricot");
		list.add("papaya");
     	// Test various calls to indexOf for elements in the list
		// and not in the list
		assertEquals(0, list.indexOf("apples"));
		assertEquals(4, list.indexOf("mango"));
		assertEquals(6, list.indexOf("pineapple"));
		assertEquals(-1, list.indexOf("lychee"));
		//Test checking the index of null
		boolean thrown = false;
		try {
			list.indexOf(null);
		} catch (NullPointerException e) {
			thrown = true;
		}
		assertTrue(thrown); 
	}

	/**
	 * A method to test clear in sorted list 
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		// Add some elements
		list.add("apples");
		list.add("mango");
		list.add("grapefruit");
		list.add("avocado");
		list.add("pineapple");
		list.add("apricot");
		list.add("papaya");
		// Clear the list
		list.clear();
		//Test that the list is empty
		assertEquals(0, list.size());
	}

	/**
	 * A method to test sorted list is empty or not 
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		// Test that the list starts empty
		assertTrue(list.isEmpty()); 
		// Add at least one element
		list.add("apples");
		list.add("mango");
		list.add("grapefruit");
		//Check that the list is no longer empty
		assertFalse(list.isEmpty()); 
	}

	/**
	 * A method to test Contains method in sorted list
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();

		// Test the empty list case
		assertFalse(list.contains("apples"));
		// Add some elements
		list.add("apples");
		list.add("mango");
		list.add("grapefruit");
		list.add("avocado");
		list.add("pineapple");
		list.add("apricot");
		list.add("papaya");	
		// Test some true and false cases
		assertTrue(list.contains("apples"));
		assertTrue(list.contains("mango"));
		assertTrue(list.contains("pineapple"));
		assertTrue(list.contains("avocado"));
		assertFalse(list.contains("pear"));
		assertFalse(list.contains("lychee"));
	}

	/**
	 * Tests that the equals method works for sorted list 
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		//Make two lists the same and one list different
		// list 1                 
		list1.add("apples");
		list1.add("mango");
		list1.add("grapefruit");
		list1.add("avocado");
		list1.add("pineapple");
		list1.add("apricot");
		list1.add("papaya");	
		// list 2
		list2.add("apples");
		list2.add("mango");
		list2.add("grapefruit");
		list2.add("avocado");
		list2.add("pineapple");
		list2.add("apricot");
		list2.add("papaya");
		// list 3 
		list3.add("pomegranate");
		list3.add("peach");
		list3.add("jackfruit");
		list3.add("lychee");
		list3.add("cherry");
		list3.add("nectarine");
		list3.add("plum");

		// Test for equality 
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list2));
		//and non-equality
		assertFalse(list1.equals(list3));
		assertFalse(list2.equals(list3));
	}

	/**
	 * Test that hashCode works correctly for sorted list 
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();

		//Make two lists the same and one list different
		// list 1                 
		list1.add("apples");
		list1.add("mango");
		list1.add("grapefruit");
		list1.add("avocado");
		list1.add("pineapple");
		list1.add("apricot");
		list1.add("papaya");	
		// list 2
		list2.add("apples");
		list2.add("mango");
		list2.add("grapefruit");
		list2.add("avocado");
		list2.add("pineapple");
		list2.add("apricot");
		list2.add("papaya");
		// list 3
		list3.add("pomegranate");
		list3.add("peach");
		list3.add("jackfruit");
		list3.add("lychee");
		list3.add("cherry");
		list3.add("nectarine");
		list3.add("plum");

		// Test for the same and different hashCodes
		assertEquals(list1.hashCode(), list2.hashCode());
		// different hashCodes
		assertNotEquals(list1.hashCode(), list3.hashCode()); 
	}

}
