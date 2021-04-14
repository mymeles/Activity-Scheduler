package edu.ncsu.csc216.pack_scheduler.util;

/**
 * an interface for the classes ArrayStack and LikedStack
 * @author Meles MELES
 *
 * @param <E> defines generic objects 
 */
public interface Stack<E> {

	/**
	 * A method that adds at the top of the stack 
	 * @param element the object that is added to the stack 
	 * @throws IllegalArgumentException if the capacity is full
	 */
	void push(E element);
	
	/**
	 * a method used to remove from the top of the stack 
	 * @return the object E
	 * @throws IllegalArgumentException if the stack is empty
	 */
	E pop();
	
	/**
	 * A method used to check is the stack is empty 
	 * @return a boolean
	 */
	boolean isEmpty();
	
	/**
	 * A method used to return the size of the stack
	 * @return an inetger
	 */
	int size();
	
	/**
	 * A method to set the capacity for the stack
	 * @param capacity is an integer
	 * @throws IllegalArgumentException if the capacity is less than zero or less than the intial size
	 */
	void setCapacity(int capacity);
}
