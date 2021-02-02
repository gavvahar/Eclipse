package edu.mccc.cos210.ds;

public interface IArray<T> extends java.lang.Iterable<T> {
	public T get(int index);
	public void set(int index, T data);
	public int getSize();
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
