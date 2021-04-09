/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * Creates a list of any given object that
 * 
 * @author Alex Bernard
 * @param <E> The type of object being stored in the given list
 */
public class ArrayList<E> extends AbstractList<E> {
	/** Default size for the array list */
	private static final int INIT_SIZE = 10;

	/** Array used to store values within the Array List */
	private E[] list;

	/** The current size of the array */
	private int size;

	/**
	 * Constructs a new empty generic list with a size of zero when called
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}

	/**
	 * Adds a new element to the list at the given index
	 */
	@Override
	public void add(int idx, E element) {
		if (element == null)
			throw new NullPointerException();
		if (idx < 0 || idx > size())
			throw new IndexOutOfBoundsException();
		for (int i = 0; i < size; i++) {
			if (element.equals(list[i]))
				throw new IllegalArgumentException();
		}
		// Element is added to the end of the list
		if (idx == size)
			list[idx] = element;

		// Element is added at the beginning or middle
		else {
			for (int i = size; i > idx; i--) {
				list[i] = list[i - 1];
			}
			list[idx] = element;
		}

		size++;
		if (size == list.length)
			growArray();

	}

	/**
	 * Private method used to double the size of the current list
	 */
	@SuppressWarnings("unchecked")
	private void growArray() {
		int tempSize = size * 2;
		E[] tempList = (E[]) new Object[tempSize];
		for (int i = 0; i < size; i++) {
			tempList[i] = list[i];
		}
		list = tempList;
	}

	/**
	 * Removes a given object from the ArrayList located at the given index. The
	 * removed object is then returned and the size is incremented by -1.
	 * 
	 * @param idx The index of the object being removed
	 * @throws IndexOutOfBoundsException If the given index is less than zero or
	 *                                   greater than or equal the current size
	 */
	@Override
	public E remove(int idx) {
		if (idx < 0 || idx >= size())
			throw new IndexOutOfBoundsException();
		E element = null;
		element = list[idx];
		if (idx == size - 1) {
			list[idx] = null;
		} else {
			for (int i = idx; i < size - 1; i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
		}
		size--;
		return element;
	}

	/**
	 * Replaces an object from the list at the given index with the given element. The replaced object is then returned to the user.
	 * @param idx The index of the object being replaced
	 * @param element The element used to replace the object at the given index
	 * @return The object that was replaced from the original list
	 */
	@Override
	public E set(int idx, E element) {
		if (element == null) throw new NullPointerException();
		if (idx < 0 || idx >= size()) throw new IndexOutOfBoundsException();
		for (int i = 0; i < size; i++) {
			if (element.equals(list[i]))
				throw new IllegalArgumentException();
		}
		E output = null;
		output = list[idx];
		list[idx] = element;
		return output;
	}
	
	/**
	 * Returns an object found in the list object at the given index value
	 * 
	 * @param idx The index of the desired object in the ArrayList
	 * @throws IndexOutOfBoundsException If the index is less than 0 or greater than
	 *                                   or equal to the size of the current list
	 * @return The object found in the ArrayList at the given index
	 */
	@Override
	public E get(int idx) {
		E element = null;
		if (idx < 0 || idx >= size)
			throw new IndexOutOfBoundsException();
		element = list[idx];
		return element;
	}

	/**
	 * Returns the current size of the arrayList
	 * 
	 * @return The size of the current ArrayList as given by the size variable
	 */
	public int size() {
		return size;
	}
}
