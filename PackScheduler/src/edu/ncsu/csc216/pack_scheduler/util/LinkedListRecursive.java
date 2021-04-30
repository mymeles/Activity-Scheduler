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

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
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

	public E get(int idx) {
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		else
			return front.get(idx);

	}

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
