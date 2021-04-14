/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * A class that constructs an array Queue 
 * @author Meles Meles
 * @param <E> is a generic object for the class
 */
public class ArrayQueue<E> implements Queue<E> {

	/** A list of Arraylist to construct the ArrayQueue */
	private ArrayList<E> list;

	/** an inetger to set the capacity*/
	private int capacity;
	
	/**
	 * A constructor for ArrayQueue that takes in capacity
	 * @param capacity is an integer
	 */
	public ArrayQueue(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}
	
	
	@Override
	public void enqueue(E element) {
		if(capacity == list.size()) {
			throw new IllegalArgumentException("capacity has been reached");
		}
		list.add(element); 
 
		
	}

	@Override
	public E dequeue() {
		if (list.isEmpty()) {
			throw new NoSuchElementException();
		}
		E top = list.get(0);
		list.remove(0);
		return top;
	}

	@Override
	public boolean isEmpty() {
		if (list.size() == 0) 
			return true;
		return false;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void setCapacity(int capacity) {
		if(capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		} else {
			this.capacity = capacity;
		}

	}


	@Override
	public boolean contains(E obj) {

		return list.contains(obj);
	}

}
