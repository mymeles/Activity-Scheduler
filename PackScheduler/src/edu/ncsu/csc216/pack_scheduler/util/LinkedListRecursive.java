package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * @author Meles Meles
 *
 * @param <E> The generic object type stored in the LinkedList
 */
public class LinkedListRecursive<E> {

	/** The number of ListNodes in the LinkedList */
	private int size;

	/** The ListNode contained at index 0 */
	private ListNode front;

	/**
	 * Default constructor for a LinkedAbstractList. Assigns the given parameter as
	 * the capacity and initializes size with 0 and front with null
	 */
	public LinkedListRecursive() {
		front = null;
		size = 0;
	}

	/**
	 * A method that returns the size of the list
	 * 
	 * @return an integer
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns a true value if the list is empty, otherwise false
	 * 
	 * @return a boolean
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * A method that adds an element into a list by the indicated index
	 * 
	 * @param idx is an integer indicating where the element is added
	 * @param e   is the element that is being added
	 */
	public void add(int idx, E e) {
		if (contains(e))
			throw new IllegalArgumentException();
		if (idx < 0 || idx > size)
			throw new IndexOutOfBoundsException();
		if (e == null)
			throw new NullPointerException();

		if (idx == 0) {
			ListNode oldFront = front;
			front = new ListNode(e, oldFront);
		} else {
			front.add(idx - 1, e);
		}
		size++;
	}

	/**
	 * A method that adds the given element to the list
	 * 
	 * @param e is the element
	 * @return a boolean, true if the element is added, false otherwise
	 */
	public boolean add(E e) {
		if (contains(e))
			throw new IllegalArgumentException();
		if (e == null)
			throw new NullPointerException();
		if (front == null)
			front = new ListNode(e, null);
		else
			front.add(e);
		size++;
		return true;
	}

	/**
	 * Returns an element from the list at a given index
	 * 
	 * @param idx is the index at which we get the element
	 * @return the object E
	 * 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equals to the size of the list.
	 */
	public E get(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		else
			return front.get(idx);

	}

	/**
	 * Sets an element in the list by a given idx with the element passed @param idx
	 * is an integer indicating the set element
	 * 
	 * @param e   is the element that we set
	 * @param idx is an integer indicating the element that is to be replaced
	 * @return the value of the replaced element E
	 * 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equals to the size of the list.
	 * @throws NullPointerException      if the element is null
	 * @throws IllegalArgumentException  if the element is contained in the list
	 */
	public E set(int idx, E e) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		if (e == null)
			throw new NullPointerException();
		if (contains(e) && get(idx) != e) {
			throw new IllegalArgumentException();
		}
		return front.set(idx, e);

	}

	/**
	 * removes an element in the list by a given idx
	 * 
	 * @param idx is an integer indicating the element that is to be replaced
	 * @return the value of the removed element
	 * 
	 * @throws IndexOutOfBoundsException if the index is less than 0 or greater than
	 *                                   or equals to the size of the list.
	 */
	public E remove(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		E rtn = null;
		if (idx == 0) {
			rtn = front.data;
			front = front.next;
		} else {
			rtn = front.remove(idx - 1);
		}

		size--;
		return rtn;

	}

	/**
	 * removes an element in the list by a given idx
	 * 
	 * @param e is the element that we remove
	 * @return the value of the removed element
	 */
	public boolean remove(E e) {

		if (front == null)
			return false;
		else if (e == null)
			return false;

		else if (front.data == e) {
			front = front.next;
			size--;
			return true;
		} else {
			return front.remove(e);
		}
	}

	/**
	 * A method that checks if an element is contained within the list 
	 * @param e is the element that we check in the list 
	 * @return a boolean, true if it is contained, otherwise false 
	 */
	public boolean contains(E e) {
		if (front != null)
			return front.contains(e);
		return false;

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
		public ListNode next;

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

		private boolean contains(E e) {
			if (data == e)
				return true;
			else if (next != null)
				return next.contains(e);

			return false;
		}

		private void add(int idx, E e) {
			if (idx == 0)
				next = new ListNode(e, next);
			else // we are relying on the fact that when the next element is null the idx is 0
				next.add(idx - 1, e);

		}

		private boolean add(E e) {
			if (next == null) {
				next = new ListNode(e, null);
				return true;
			} else
				return next.add(e);

		}

		private E get(int idx) {
			if (idx == 0)
				return data;
			else
				return next.get(idx - 1);

		}

		private boolean remove(E e) {
			if (next == null) {
				return false;
			}
			if (next.data == e) {
				next = next.next;
				size--;
				return true;
			} else {
				return next.remove(e);
			}

		}

		private E remove(int idx) {
			E rtn = null;
			if (idx == 0) {
				rtn = next.data;
				next = next.next;
				return rtn;
			} else {
				return next.remove(idx - 1);
			}

		}

		private E set(int idx, E e) {
			E rtn = null;
			if (idx == 0) {
				rtn = data;
				data = e;
				return rtn;
			} else {
				return next.set(idx - 1, e);
			}

		}
	}
}
