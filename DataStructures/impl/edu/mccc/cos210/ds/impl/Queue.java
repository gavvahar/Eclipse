package edu.mccc.cos210.ds.impl;

import edu.mccc.cos210.ds.IQueue;
import edu.mccc.cos210.ds.ISinglyLinkedList;

public class Queue<T> implements IQueue<T>
{
	ISinglyLinkedList<T> theList = new SinglyLinkedList<>();
	@Override
	public void enqueue(T data)
	{
		theList.addLast(data);

	}

	@Override
	public T dequeue() {
		return theList.removeFirst();
	}

	@Override
	public T peek() {
		return theList.getFirst();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return theList.isEmpty();
	}

}
