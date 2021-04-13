/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * @author meles
 *
 */
public class LinkedStack<E> implements Stack<E>  {

	/**
	 * 
	 */
	private LinkedAbstractList<E> list;
	
	/**
	 * 
	 * @param capacity
	 */
	public LinkedStack(int capacity){
		list = new LinkedAbstractList<E>(capacity);
		
	}
	@Override
	public void push(E element) {
		if(list.getCapacity() == list.size()) {
			throw new IllegalArgumentException("capacity has been reached");
		}
		list.add(element); 
		
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
		list.setCapacity(capacity);
		
	}


}
