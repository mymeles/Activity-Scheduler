/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A method that constructs a LinkedStack 
 * @author meles
 *
 * @param <E> a generic object for Linikedstack 
 */
public class LinkedStack<E> implements Stack<E>  {

	/**
	 * A linked AbstractList used to build the stack
	 */
	private LinkedAbstractList<E> list;
	
	/**
	 * A constrcutor for bulding an ArrayStack
	 * @param capacity is an integer
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
		return list.size() == 0;
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
