package edu.mccc.cos210.ds;

public interface ISet<T> {
	public void add(T data);
	public void addAll(ISet<T> set);
	public void remove(T data);
	public boolean contains(T data);
	public boolean containsAll(ISet<T> set);
	public ISet<T> union(ISet<T> set);
	public ISet<T> intersection(ISet<T> set);
	public IDoublyLinkedList<T> asList();
	public int getSize();
	public boolean isEmpty();
}