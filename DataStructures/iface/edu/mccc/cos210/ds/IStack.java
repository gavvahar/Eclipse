package edu.mccc.cos210.ds;

public interface IStack<T>
{
	public void push(T data);
	public T pop();
	public T peek();
	public boolean isEmpty();
}
