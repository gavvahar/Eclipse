package edu.mccc.cos210.ds.impl;

import edu.mccc.cos210.ds.ISinglyLinkedList;
import edu.mccc.cos210.ds.IStack;

public class Stack<T> implements IStack<T> {
	ISinglyLinkedList<T> theList = new SinglyLinkedList<>();

	@Override
	public void push(T data) {
		theList.addFirst(data);

	}
	@Override
	public T pop() {
		return theList.removeFirst();
	}

	@Override
	public T peek() {
		return theList.getFirst();
	}

	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}

}
