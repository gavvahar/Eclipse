package edu.mccc.cos210.ds.impl;

import java.util.Iterator;

import edu.mccc.cos210.ds.IArray;
import edu.mccc.cos210.ds.IDoublyLinkedList;
import edu.mccc.cos210.ds.ISet;

public class Set<T> implements ISet<T> {
	
	private IArray<IDoublyLinkedList<T>> theBucketArray;
	
	private int size = 0;
	
	public Set() {
		this(8);
	}
	
	public Set(int capacity) {
		theBucketArray = new Array<IDoublyLinkedList<T>>(capacity);
		for (int index = 0; index < theBucketArray.getSize(); index++) {
			theBucketArray.set(index, new DoublyLinkedList<T>());
		}
	}

	private void increaseCapacity() {
		IArray<IDoublyLinkedList<T>> newBucketArray = new Array<>(theBucketArray.getSize() * 2);
		for (int i = 0; i < newBucketArray.getSize(); i++) {
			newBucketArray.set(i, new DoublyLinkedList<T>());
		}
		for (int i = 0; i < theBucketArray.getSize(); i++) {
			IDoublyLinkedList<T> list = theBucketArray.get(i);
			for (T data : list) {
				int index = compress(hash(data), newBucketArray.getSize());
				newBucketArray.get(index).addFirst(data);
			}
		}
		theBucketArray = newBucketArray;
	}

	private int hash(T data) {
		return data.hashCode();
	}
	private int compress(int hash) {
		return compress(hash, theBucketArray.getSize());
	}
	private int compress(int hash, int mod) {
		return Math.abs(hash % mod);
	}

	@Override
	public void add(T data) {
		if (data == null) {
			throw(new IllegalArgumentException());
		}
		if ((double) getSize() / theBucketArray.getSize() > 0.7) {
			increaseCapacity();
		}
		int index = compress(hash(data));
		if (!contains(data)) {
			theBucketArray.get(index).addFirst(data);
			size++;
		}
	}

	@Override
	public void addAll(ISet<T> set) {
		IDoublyLinkedList<T> theList = set.asList();
		for (T data : theList) {
			add(data);
		}
	}

	@Override
	public void remove(T data) {
		if (data == null) {
			throw(new IllegalArgumentException());
		}
		int index = compress(hash(data));
		Iterator<T> it = theBucketArray.get(index).iterator();
		while (it.hasNext()) {
			if (it.next().equals(data)) {
				it.remove();;
				size--;
				break;
			}
		}

	}

	@Override
	public boolean contains(T data) {
		boolean b = false;
		int index = compress(hash(data));
		Iterator<T> it = theBucketArray.get(index).iterator();
		while (it.hasNext()) {
			if (it.next().equals(data)) {
				b = true;
				break;
			}
		}
		return b;
	}

	@Override
	public boolean containsAll(ISet<T> set) {
		boolean b = true;
		IDoublyLinkedList<T> theList = set.asList();
		for (T data : theList) {
			if (!contains(data)) {
				b = false;
				break;
			}
		}
		return b;
	}

	@Override
	public ISet<T> union(ISet<T> set) {
		ISet<T> theUnion = new Set<>();
		return theUnion;
	}

	@Override
	public ISet<T> intersection(ISet<T> set) {
		ISet<T> theIntersection = new Set<>();
		return theIntersection;
	}

	@Override
	public IDoublyLinkedList<T> asList() {
		IDoublyLinkedList<T> theList = new DoublyLinkedList<>();
		for (int i = 0; i < theBucketArray.getSize(); i++) {
			IDoublyLinkedList<T> dll = theBucketArray.get(i);
			Iterator<T> it = dll.iterator();
			while (it.hasNext()) {
				theList.addFirst(it.next());
			}
		}
		return theList;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return getSize() == 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < theBucketArray.getSize(); i++) {
			IDoublyLinkedList<T> dll = theBucketArray.get(i);
			System.out.print(i + " ");
			for (T d : dll) {
				System.out.print(d + " ");
			}
			System.out.println();
		}
		return sb.toString();
	}
}
