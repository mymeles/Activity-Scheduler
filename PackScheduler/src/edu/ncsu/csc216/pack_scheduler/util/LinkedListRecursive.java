package edu.ncsu.csc216.pack_scheduler.util;

/**
 * 
 * @author meles
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
	
	public E size() {
		// TODO Auto-generated method stub
		return null;
	}

	public void add(int i, E string) {
		// TODO Auto-generated method stub
		
	}
	
	public void add(E string) {
		// TODO Auto-generated method stub
		
	}

	public E get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public E set(int i, E object) {
		return null;
		// TODO Auto-generated method stub
		
	}

	public E remove(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public boolean remove(E e) {
		// TODO Auto-generated method stub
		return null != null;
	}
	
	public boolean contains(E e) {
		// TODO Auto-generated method stub
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
		private ListNode next;

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
			// TODO Auto-generated method stub
			return false;
		}
		
		private void add(int i, E string) {
			// TODO Auto-generated method stub
			
		}
		
		private void add(E string) {
			// TODO Auto-generated method stub
			
		}
		
		private E get(int i) {
			// TODO Auto-generated method stub
			return null;
		}
		
		private boolean remove(E e) {
			// TODO Auto-generated method stub
			return null != null;
		}
		
		private E remove(int i) {
			// TODO Auto-generated method stub
			return null;
		}
		
		private E set(int i, E object) {
			return null;
			// TODO Auto-generated method stub
			
		}
	}
}
