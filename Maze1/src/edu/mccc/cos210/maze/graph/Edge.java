package edu.mccc.cos210.maze.graph;

public class Edge<T> {
	private T from;
	private T to;
	private int weight;
	public Edge(T from, T to, int weight) {
		this.from = from;
		this.to = to;
		this.setWeight(weight);
	}
	public T getFrom() {
		return this.from;
	}
	public void setFrom(T from) {
		this.from = from;
	}
	public T getTo() {
		return this.to;
	}
	public void setTo(T to) {
		this.to = to;
	}
	public int getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
