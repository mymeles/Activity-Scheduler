package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Meles Meles
 *
 * @param <E> The generic object type stored in the LinkedList
 */
public class LinkedList<E> extends AbstractSequentialList<E> {

	/** The number of ListNodes in the LinkedList */
	private int size;

	/** The ListNode contained at index 0 */
	private ListNode front;

	/** The ListNode containing the last element of the list. */
	private ListNode back;

	/**
	 * Default constructor for a LinkedAbstractList. Assigns the given parameter as
	 * the capacity and initializes size with 0 and front with null
	 */
	public LinkedList() {
		front = new ListNode(null);
		back = new ListNode(null);
		front.next = back;
		back.prev = front;
		size = 0;
	}

	@Override
	public ListIterator<E> listIterator(int idx) {
		return new LinkedListIterator(idx);

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int idx, E element) {
		if (contains(element) && size > 0)
			throw new IndexOutOfBoundsException();
		ListIterator<E> iter = listIterator(idx);
		iter.add(element);
	}

	@Override
	public E set(int idx, E element) {
		if (contains(element))
			throw new IllegalArgumentException();
		if (idx >= size)
			throw new IndexOutOfBoundsException();

		ListIterator<E> iter = listIterator(idx);
		E rtn = (E) iter.next();

		iter.set(element);

		return rtn;

	}

	private class LinkedListIterator implements ListIterator<E> {

		/** pervious node in the linked list */
		public ListNode previous;
		/** the next node in the linked list */
		public ListNode next;
		/** the Idex of the pervious node */
		public int prevIdx;
		/** the Idex of the next node */
		public int nextIdx;
		/** the last retrived node of the list */
		private ListNode lastRetrevied;

		/**
		 * A constructor for LinkedListIterator
		 * 
		 * @param idx of the LinkedList at which the iterator is created
		 * @throws IndexOutOfBoundsException if the idex is above the size or less than
		 *                                   0
		 */
		public LinkedListIterator(int idx) {
			if (idx < 0 || idx > size)
				throw new IndexOutOfBoundsException();
			ListNode current = front;
			int position = -1;

			while (position < idx - 1) {
				current = current.next;
				position++;
			}

			previous = current;
			next = current.next;
			prevIdx = position;
			nextIdx = position + 1;
			lastRetrevied = null;

		}

		/**
		 * Adds an object before the element that would be returned and increments the
		 * size
		 * 
		 * @param ele is generic type data
		 * @throws NullPointerException id the added element is null
		 */
		@Override
		public void add(E ele) {
			if (ele == null) {
				throw new NullPointerException();
			}
			ListNode add = new ListNode(ele, next, previous);
			previous.next = add;
			add.prev = previous;
			previous = add;
			add.next = next;
			next.prev = add;

			lastRetrevied = null;
			size++;
		}

		/**
		 * Checks if the next reference of the current is not null, returns true if so
		 * otherwise flase
		 * 
		 * @return a boolean returns true if so otherwise false
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Checks if the previous reference of the current is not null
		 * 
		 * @return a boolean returns true if so otherwise false
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * assuming there is an element after the iterators position the retrived data
		 * node is updated to the next().data element we want to return and then the
		 * current position idex is decremented
		 * @return  a generic type found at the idex of next idex 
		 * @throws NoSuchElementException is hasNext is false
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastRetrevied = next;
			E rtn = next.data;

			previous = previous.next;
			next = next.next;
			nextIdx--;
			prevIdx--;

			return rtn;
		}

		@Override
		public int nextIndex() {
			return nextIdx;
		}

		@Override
		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			lastRetrevied = previous;
			E rtn = previous.data;

			previous = previous.next;
			next = next.prev;
			nextIdx--;
			prevIdx--;

			return rtn;
		}

		@Override
		public int previousIndex() {
			return prevIdx;
		}

		@Override
		public void remove() {
			if (lastRetrevied == null)
				throw new IllegalStateException();
			if (previous.prev != null)
				previous.prev.next = next;

			next.prev = previous.prev;
			previous = previous.prev;
			lastRetrevied = null;
			size--;

		}

		@Override
		public void set(E element) {
			if (lastRetrevied == null)
				throw new IllegalStateException();
			if (element == null)
				throw new NullPointerException();

			lastRetrevied.data = element;
		}

	}

	/**
	 * The object used to hold data in the LinkedList
	 * 
	 * @author Meles Meles
	 *
	 */
	private class ListNode {
		/** The data being stored in the list */
		private E data;

		/** The subsequent object in the list */
		private ListNode next;

		/** The previous object in the list */
		private ListNode prev;

		/**
		 * Constructs a ListNode with the given data and no references
		 * 
		 * @param data The object being stored at the current ListNode
		 */
		public ListNode(E data) {
			this(data, null, null);
		}

		/**
		 * Constructs a ListNode with the given data and reference
		 * 
		 * @param data The object stored at the given list node
		 * @param next The subsequent ListNode in the linkedList
		 * @param prev The previous ListNode in the linkedList
		 */
		public ListNode(E data, ListNode next, ListNode prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

}
