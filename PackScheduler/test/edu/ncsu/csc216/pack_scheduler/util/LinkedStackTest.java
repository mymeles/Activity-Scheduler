package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

/**
 * 
 * @author Meles Meles
 *
 */
public class LinkedStackTest {

	/**
	 * Testing method for ArrayStack()
	 */
	@Test
	public void testLinkedStack() {
		Stack<String> list = new LinkedStack<String>(5);

		// adding elements to the stack
		assertEquals(0, list.size());
		list.push("1");
		assertEquals(1, list.size());
		list.push("2");
		assertEquals(2, list.size());
		list.push("3");
		assertEquals(3, list.size());
		list.push("4");
		assertEquals(4, list.size());
		list.push("5");
		assertEquals(5, list.size());

	}

	/**
	 * Test method for push()
	 */ 
	@Test
	public void testPush() {
		Stack<String> list = new LinkedStack<String>(5);

		// adding elements to the stack
		assertEquals(0, list.size());
		list.push("1");
		assertEquals(1, list.size());
		list.push("2");
		assertEquals(2, list.size());
		list.push("3");
		assertEquals(3, list.size());
		list.push("4");
		assertEquals(4, list.size());
		list.push("5");
		assertEquals(5, list.size());

		// trying to add when the capacity is full

		try {
			list.push("item");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("capacity has been reached", e.getMessage());
			assertEquals(5, list.size());
		}
	}

	@Test
	public void testPop() {
		Stack<String> list = new LinkedStack<String>(5);
		assertTrue(list.isEmpty());

		// adding elements to the stack
		assertEquals(0, list.size());
		list.push("1");
		assertEquals(1, list.size());
		list.push("2");
		assertEquals(2, list.size());
		list.push("3");
		assertEquals(3, list.size());
		list.push("4");
		assertEquals(4, list.size());
		list.push("5");
		assertEquals(5, list.size());
		
		// Checking if the list is not empty 
		assertFalse(list.isEmpty());

		// removing the 5th element
		assertEquals("5", list.pop());
		assertEquals(4, list.size());
		
		// removing the 4th element
		list.pop();
		// removing the 3rd element
		list.pop();
		assertEquals(2, list.size());
		
		// removing the 2nd element
		assertEquals("2", list.pop());
		
		// Removing the 1 element 
		assertEquals("1", list.pop());
		
		try {
			list.pop();
			fail();
		} catch (EmptyStackException e) {
			assertTrue(list.isEmpty());
		}
		
		
		
		
	}

	@Test
	public void testSetcapacity() {
		Stack<String> list = new LinkedStack<String>(5);

		// adding elements to the stack
		assertEquals(0, list.size());
		list.push("1");
		assertEquals(1, list.size());
		list.push("2");
		assertEquals(2, list.size());
		list.push("3");
		assertEquals(3, list.size());
		list.push("4");
		assertEquals(4, list.size());
		list.push("5");
		assertEquals(5, list.size());
		
		// try to add with out increasing the capacity 
		try {
			list.push("item");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("capacity has been reached", e.getMessage());
			assertEquals(5, list.size());
		}
		
		try {
			list.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, list.size());
		}
		
		try {
			list.setCapacity(4);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(5, list.size());
		}
		
		list.setCapacity(6);
		list.push("item");
		assertEquals(6, list.size());
		assertEquals("item", list.pop());
	}

}
