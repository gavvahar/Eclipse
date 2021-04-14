package edu.mccc.cos210.maze.graph;

public class Vertex<T> {
	private java.util.Set<Edge<Vertex<T>>> edgesIn = new java.util.HashSet<>();
	private java.util.Set<Edge<Vertex<T>>> edgesOut = new java.util.HashSet<>();
	private T element;
	private int distance = Integer.MAX_VALUE;
	private Vertex<T> previous = null;
	public Vertex(T element) {
		this.element = element;
	}
	public void addEdgeIn(Edge<Vertex<T>> edge) {
		this.edgesIn.add(edge);
	}
	public void addEdgeOut(Edge<Vertex<T>> edge) {
		this.edgesOut.add(edge);
	}
	public java.util.Set<Edge<Vertex<T>>> getEdgesIn() {
		return this.edgesIn;
	}
	public java.util.Set<Edge<Vertex<T>>> getEdgesOut() {
		return this.edgesOut;
	}
	public T getElement() {
		return this.element;
	}
	public void setElement(T element) {
		this.element = element;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Vertex<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Vertex<T> previous) {
		this.previous = previous;
	}
}
