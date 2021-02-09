package edu.mccc.cos210.ds.impl;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import edu.mccc.cos210.ds.IDoublyLinkedList;

public class DoublyLinkedList<T> implements IDoublyLinkedList<T> {
	
	private LinkedList<T> theList = new LinkedList<>();

	@Override
	public void addFirst(T data) {
		theList.addFirst(data);
	}

	@Override
	public T getFirst() {
		return theList.getFirst();
	}

	@Override
	public T removeFirst() {
		return theList.removeFirst();
	}

	@Override
	public void addLast(T data) {
		theList.addLast(data);
	}

	@Override
	public T getLast() {
		return theList.getLast();
	}

	@Override
	public T removeLast() {
		return theList.removeLast();
	}

	@Override
	public int getSize() {
		return theList.size();
	}

	@Override
	public boolean isEmpty() {
		return theList.isEmpty();
	}

	@Override
	public Iterator<T> iterator() {
		return new DoublyLinkedListIterator();
	}
	
	@Override
	public ListIterator<T> listIterator() {
		return new DoublyLinkedListIterator();
	}

	class DoublyLinkedListIterator implements ListIterator<T> {

		private ListIterator<T> iterator = theList.listIterator();

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next();
		}

		@Override
		public boolean hasPrevious() {
			return iterator.hasPrevious();
		}

		@Override
		public T previous() {
			return iterator.previous();
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			iterator.remove();
		}

		@Override
		public void set(T e) {
			iterator.set(e);
		}

		@Override
		public void add(T e) {
			iterator.add(e);
		}

	}
}
