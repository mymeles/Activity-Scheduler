/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

 
/**
 * An interface for the ArrayQueue and LinkedQueue 
 * @author Meles Meles 
 *
 * @param <E> is a generic parameter 
 */
public interface Queue<E> {
	/**
	 * A method that adds at the top of the queue
	 * @param element is the object that is added 
	 * @throws IllegalArgumentException if the capacity os full 
	 */
	void enqueue(E element);
	
	/**
	 * A method that removes from the botton of the queue 
	 * @return the element E
	 *  @throws NoSuchElementException if the list is empty 
	 */
	E dequeue();
	
	/**
	 * a method that checks for an empty queue 
	 * @return a boolean
	 */
	boolean isEmpty();
	
	/**
	 * A method that returns the size of the queue 
	 * @return an integer 
	 */
	int size();
	
	/**
	 * sets the capacity of the queue 
	 * @param capacity is an integer
	 */
	void setCapacity(int capacity);

	/**
	 * returns a boolean if the passed object exists in the queuee
	 * @param obj the item we are looking for in the queue 
	 * @return a boolean.
	 */
	boolean contains(E obj);
}
