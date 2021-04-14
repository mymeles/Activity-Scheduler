/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This last creates a list of objects stored through ListNode objects. Objects
 * can be added to the list up to the given total capacity
 * 
 * @param <E> The generic object type stored in the list
 * @author Alex Bernard
 *
 */
public class LinkedAbstractList<E> extends AbstractList<E> {

	/** The number of ListNodes in the LinkedAbstractList */
	private int size;

	/** The total number of ListNodes that the List can contain */
	private int capacity;

	/** The ListNode contained at index 0 */
	private ListNode front;

	/** The ListNode containing the last element of the list. */
	private ListNode back;

	/**
	 * Default constructor for a LinkedAbstractList. Assigns the given parameter as
	 * the capacity and initializes size with 0 and front with null
	 * 
	 * @param capacity The total number of ListNodes a LinkedAbstractList will
	 *                 contain
	 */
	public LinkedAbstractList(int capacity) {
		this.size = 0;
		this.front = null;
		this.back = null;
		setCapacity(capacity);
	}

	/**
	 * Sets the total capacity of the list to the given value
	 * 
	 * @param capacity The value being set as the AbstractList capacity
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0 || capacity < size)
			throw new IllegalArgumentException("Invalid capacity");
		this.capacity = capacity;
	}

	/**
	 * Returns the capacity of a list
	 * 
	 * @return an integer
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Returns the object found at the given index
	 * 
	 * @param idx The index of the object being taken from the list
	 * @return The object found at the given index
	 * @throws IndexOutOfBoundsException If the given index is greater than size - 1
	 *                                   or less than 0
	 */
	@Override
	public E get(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException("Invalid index");
		else {
			ListNode current = front;
			for (int i = 0; i < idx; i++) {
				current = current.next;
			}
			return current.data;
		}
	}
	
	/**
	 * A method that returns an elemnt that exists in the list 
	 * @param e the object we are looking for in the element 
	 * @return a boolean
	 */
	public boolean contains1(E e) {
		ListNode current = front;
		while (current != null) {
			if (current.data.equals(e))
				return true;
			current = current.next;
		}
		return false;
	}

	/**
	 * Replaces the object at the given index with the given object
	 * 
	 * @param idx     The index of the object being replaced
	 * @param element The object being placed in the AbstractList in place of the
	 *                other object
	 * @return The element being replaced from the given index
	 */
	public E set(int idx, E element) {
		if (idx < 0 || idx >= this.size())
			throw new IndexOutOfBoundsException("Invalid index");
		else if (element == null)
			throw new NullPointerException("element cannot equal null");
		else {
			ListNode current = front;
			while (current != null) {
				if (current.data.equals(element))
					throw new IllegalArgumentException("Duplicate element");
				current = current.next;
			}
			current = front;
//			if (idx == 0) {
//				E removedElement = front.data;
//				if (size == 1)
//					front = new ListNode(element);
//				else
//					front = new ListNode(element, front.next);
//				return removedElement;
	//		} else {
//				for (int i = 0; i < idx - 1; i++) {
//					current = current.next;
//				}
//				E removedElement = current.next.data;
//				if (idx == size - 1)
//					current.next = new ListNode(element);
//				else
//					current.next = new ListNode(element, current.next.next);
//				return removedElement;
//			}

			for (int i = 0; i < idx; i++) {
				current = current.next;
			}
			E remove = current.data;
			current.data = element;
			return remove;
		}
	}

	/**
	 * Adds the given object to the list at the given index
	 * 
	 * @param idx     The index at which the current element is being added
	 * @param element The object being added to the LinkedList
	 * @throws NullPointerException      If the element field is null
	 * @throws IllegalArgumentException  If a duplicate of the element is present or
	 *                                   the size == capacity
	 * @throws IndexOutOfBoundsException If the index is less than 0 or greater than
	 *                                   size - 1
	 */
	@Override
	public void add(int idx, E element) {
		if (idx < 0 || idx > this.size())
			throw new IndexOutOfBoundsException("Invalid index");
		else if (size == capacity)
			throw new IllegalArgumentException("Size == Capacity");
		else if (element == null)
			throw new NullPointerException("element cannot equal null");
		else {
			ListNode current = front;
			while (current != null) {
				if (current.data.equals(element))
					throw new IllegalArgumentException("Duplicate element");
				current = current.next;
			}
			if (front == null) {
				front = new ListNode(element);
				back = front;
			} else if (idx == 0) {
				front = new ListNode(element, front);
			} else if (idx == size) {
				back.next = new ListNode(element, null);
				back = back.next;
			} else {
				current = front;
				for (int i = 0; i < idx - 1; i++) {
					current = current.next;
				}
				current.next = new ListNode(element, current.next);
			}
			size++;
		}
	}

	/**
	 * Removes the object found at the given index
	 * 
	 * @param idx The index of the object being removed
	 * @return The element removed from the list
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   size - 1
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= this.size())
			throw new IndexOutOfBoundsException("Invalid index");
		else {
			E removedObject = null;
			if (idx == 0) {
				if (size == 1) {
					removedObject = front.data;
					front = null;
					back = null;
				} else {
					removedObject = front.data;
					front = front.next;
				}
			}  
			
			else {
				ListNode current = front;
				for (int i = 0; i < idx - 1; i++) {
					current = current.next;
				}
				removedObject = current.next.data;
				current.next = current.next.next;
				if(idx == size - 1) {
					back = current;
				}
			}
			size--;
			return removedObject;
		}

	}

	/**
	 * Returns the current size field of the list
	 * 
	 * @return The LinkedAbstractList size field
	 */
	public int size() {
		return size;
	}

	/**
	 * The object used to hold data in the LinkedList
	 * 
	 * @author Alex Bernard
	 *
	 */
	private class ListNode {
		/** The data being stored in the list */
		private E data;

		/** The subsequent object in the list */
		private ListNode next;

		/**
		 * Constructs a ListNode with the given data and no references
		 * 
		 * @param data The object being stored at the current ListNode
		 */
		public ListNode(E data) {
			this(data, null);
		}

		/**
		 * Constructs a ListNode with the given data and reference
		 * 
		 * @param data The object stored at the given list node
		 * @param next The subsequent ListNode in the linkedList
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}
}
