package edu.mccc.cos210.ds.impl;

import java.util.ArrayList;
import java.util.Iterator;

import edu.mccc.cos210.ds.IArray;

public class Array<T> implements IArray<T> {

	private ArrayList<T> theArray = null;

	public Array(int size) {
		theArray = new ArrayList<>(size);
		for (int i = 0; i < size; i++) {
			theArray.add(null);
		}
	}

	@Override
	public T get(int index) {
		return theArray.get(index);
	}

	@Override
	public void set(int index, T data) {
		theArray.set(index, data);
	}

	@Override
	public int getSize() {
		return theArray.size();
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}
	
	class ArrayIterator implements Iterator<T> {
		
		private Iterator<T> iterator = theArray.iterator();

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next();
		}
		
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
		
	}
}
