/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A method that constructs a stack 
 * @author meles
 *
 * @param <E> a generic object for stack 
 */
public class ArrayStack<E> implements Stack<E> {

	/**
	 * a list used for bulding the stack
	 */
	private ArrayList<E> list;
	/**
	 * An inetegr to set the capacity 
	 */
	private int capacity;

	/**
	 * A constrcutor for bulding an ArrayStack
	 * @param capacity is an integer
	 */
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
		return list.size() == 0;
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
