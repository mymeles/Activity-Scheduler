/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * A Method that represents a Queue of objects 
 * @param <E> The generic object type stored in the list 
 * @author Meles Meles
 */
public class LinkedQueue<E> implements Queue<E> {

	/**
	 * A filed the represents the Queue of a list 
	 */
	private LinkedAbstractList<E> list;
	
	/**
	 * A constructor that constructs Queue's with a given capacity 
	 * @param capacity an integer value 
	 */
	public LinkedQueue(int capacity){
		list = new LinkedAbstractList<E>(capacity); 
		
	}
	
	@Override
	public void enqueue(E element) {
		if(list.getCapacity() == list.size()) {
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
		if(list.size() == 0)
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
