/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

/**
 * @author meles
 *
 */
public interface Queue<E> {
	void enqueue(E element);
	
	E dequeue();
	
	boolean isEmpty();
	
	int size();
	
	void setCapacity(int capacity);
}
