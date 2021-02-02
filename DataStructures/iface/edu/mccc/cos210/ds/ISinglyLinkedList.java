package edu.mccc.cos210.ds;

public interface ISinglyLinkedList<T> extends java.lang.Iterable<T> {
	public void addFirst(T data);
	public T getFirst();
	public T removeFirst();
	public void addLast(T data);
	public T getLast();
	public int getSize();
	public boolean isEmpty();
    @Override
    default java.util.Spliterator<T> spliterator() {
        return java.util.Spliterators.spliterator(iterator(), getSize(), java.util.Spliterator.ORDERED);
    }
    default java.util.stream.Stream<T> stream() {
        return java.util.stream.StreamSupport.stream(spliterator(), false);
    }
    default java.util.stream.Stream<T> parallelStream() {
        return java.util.stream.StreamSupport.stream(spliterator(), true);
    }
}
