/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * @author Meles Meles
 *
 */
public class ArrayStack<E> implements Stack<E> {

	private ArrayList<E> list;
	private int capacity;

	public ArrayStack(int capacity) {
		list = new ArrayList<E>();
		setCapacity(capacity);
	}

	
	@Override
	public void push(E element) {
		if(capacity == list.size()) {
			throw new IllegalArgumentException("capacity has been reached");
		}
		list.add(element); // this is wrong 
	

	}

	@Override
	public E pop() {
		if (list.isEmpty()) {
			throw new EmptyStackException();
		}
		E top = list.get(list.size() - 1);
		list.remove(list.size() - 1);
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
