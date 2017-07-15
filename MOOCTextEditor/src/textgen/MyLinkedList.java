package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		
		head = new LLNode(null);
		tail = new LLNode(null);
		
		head.next = tail;
		tail.prev = head;
		size = 0;
		// TODO: Implement this method
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 * @return 
	 */
	public boolean add(E element ) 
	{
		
		add(size, element);
		
		return true;
		
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> pointer = head;
		
		pointer = pointer.next;		
		
		while(index > 0){
						
			pointer = pointer.next;
			
			index--;


		}
				
		// TODO: Implement this method.
		return pointer.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		/*
		*/
		
		if(index < 0|| index > size){
			throw new IndexOutOfBoundsException();
		}
		
		
		
		if(element == null){
			throw new NullPointerException();
		}
		
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		
		LLNode<E> pointer = head;
		
		while(index > 0){
			
			pointer = pointer.next;
			
			index--;
		}
		
		LLNode<E> newLink = new LLNode(element);
		
		pointer.next.prev = newLink;
		newLink.next = pointer.next;
		
		newLink.prev = pointer;
		pointer.next = newLink;
		
		size++;
		
		
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		
		LLNode<E> pointer = head;
		
		while(index > 0){
			
			pointer = pointer.next;
			
			index--;
		}
		
		E old = pointer.next.data;
		pointer.next = pointer.next.next;
		pointer.next.prev = pointer;
		
		size--;
		
		return old;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{

		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		if(element == null){
			throw new NullPointerException();
		}
		
		LLNode<E> pointer = head;
		
		while(index>=0){
			pointer = pointer.next;
			index--;
		}
		
		E old = pointer.data;
		pointer.data = element;
		
		// TODO: Implement this method
		return old;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
