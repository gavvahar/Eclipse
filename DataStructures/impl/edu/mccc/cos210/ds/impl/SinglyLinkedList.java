package edu.mccc.cos210.ds.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.mccc.cos210.ds.ISinglyLinkedList;

public class SinglyLinkedList<T> implements ISinglyLinkedList<T> {

	private int size = 0;

	private Node<T> first = null;
	private Node<T> last = null;

	@Override
	public void addFirst(T data) {
        Node<T> f = first;
        Node<T> newNode = new Node<>(data, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        }
        size++;
	}

	@Override
	public T getFirst() {
        Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return f.item;
	}

	@Override
	public T removeFirst() {
        Node<T> f = first;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
	}

    private T unlinkFirst(Node<T> f) {
        T element = f.item;
        Node<T> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null) {
            last = null;
        }
        size--;
        return element;
    }

	@Override
	public void addLast(T data) {
        Node<T> l = last;
        Node<T> newNode = new Node<>(data, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
        	l.next = newNode;
        }
        size++;
	}

	@Override
	public T getLast() {
        final Node<T> l = last;
        if (l == null) {
            throw new NoSuchElementException();
        }
        return l.item;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Itr();
	}

    private static class Node<T> {
        T item;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
    
    private class Itr implements Iterator<T> {

        private Node<T> lastReturned;
        private Node<T> next;
        private int nextIndex;

        Itr() {
        	lastReturned = null;
            next = first;
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public void remove() {
        	throw new UnsupportedOperationException();
        }
    }

}
