/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * @author Meles Meles
 *
 */
public class ArrayQueue<E> implements Queue<E> {

	private ArrayList<E> list;

	private int capacity;
	
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
			throw new EmptyStackException();
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

}
