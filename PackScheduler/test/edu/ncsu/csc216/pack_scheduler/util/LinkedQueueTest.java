package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Test;

public class LinkedQueueTest {

	@Test
	public void testLinkedQueue() {
		Queue<String> list = new LinkedQueue<String>(5);
		assertEquals(0, list.size());
		list.enqueue("1");
		assertEquals(1, list.size());
		list.enqueue("2");
		assertEquals(2, list.size());
		list.enqueue("3");
		assertEquals(3, list.size());
		list.enqueue("4");
		assertEquals(4, list.size());
		list.enqueue("5");
		assertEquals(5, list.size());
	}

	@Test
	public void testEnqueue() {
		Queue<String> list = new LinkedQueue<String>(5);
		assertTrue(list.isEmpty());

		assertEquals(0, list.size());
		list.enqueue("1");
		assertEquals(1, list.size());
		list.enqueue("2");
		assertEquals(2, list.size());
		list.enqueue("3");
		assertEquals(3, list.size());
		list.enqueue("4");
		assertEquals(4, list.size());
		list.enqueue("5");
		assertEquals(5, list.size());
		// trying to add when the capacity is full

		// Checking if the list is not empty
		assertFalse(list.isEmpty());

		try {
			list.enqueue("item");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("capacity has been reached", e.getMessage());
			assertEquals(5, list.size());
		}
	}

	@Test
	public void testDequeue() {
		Queue<String> list = new LinkedQueue<String>(5);
		assertTrue(list.isEmpty());

		assertEquals(0, list.size());
		list.enqueue("1");
		assertEquals(1, list.size());
		list.enqueue("2");
		assertEquals(2, list.size());
		list.enqueue("3");
		assertEquals(3, list.size());
		list.enqueue("4");
		assertEquals(4, list.size());
		list.enqueue("5");
		assertEquals(5, list.size());

		// Checking if the list is not empty
		assertFalse(list.isEmpty());

		// removing the 5th element
		assertEquals("1", list.dequeue());
		assertEquals(4, list.size());

		// removing the 4th element
		list.dequeue();
		// removing the 3rd element
		list.dequeue();
		assertEquals(2, list.size());

		// removing the 2nd element
		assertEquals("4", list.dequeue());

		// Removing the 1 element
		assertEquals("5", list.dequeue());

		try {
			list.dequeue();
			fail();
		} catch (EmptyStackException e) {
			assertTrue(list.isEmpty());
		}

		try {
			list.setCapacity(-1);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(0, list.size());
		}
	}
}
