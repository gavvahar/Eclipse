package edu.mccc.cos210.maze.graph;

public class Graph<T> {
	private java.util.Set<Vertex<T>> vertices = new java.util.HashSet<>();
	private java.util.Comparator<Vertex<T>> vertexComparator;
	public Graph(java.util.Comparator<Vertex<T>> comparator) {
		this.vertexComparator = comparator;
	}
	public void addVertex(Vertex<T> vertex) {
		this.vertices.add(vertex);
	}
	public void addEdge(Edge<Vertex<T>> edge) {
		edge.getFrom().addEdgeOut(edge);
		edge.getTo().addEdgeIn(edge);
	}
	public void doDijkstra(Vertex<T> from) {
		for (Vertex<T> vertex : vertices) {
			vertex.setDistance(Integer.MAX_VALUE);
		}
		from.setDistance(0);
		java.util.PriorityQueue<Vertex<T>> Q = new java.util.PriorityQueue<Vertex<T>>(vertices.size(), vertexComparator);
		for (Vertex<T> v : vertices) {
			Q.add(v);
		}
		while (!Q.isEmpty()) {
			Vertex<T> u = Q.remove();
			if (u.getDistance() == Integer.MAX_VALUE) {
				break;
			}
			java.util.Set<Edge<Vertex<T>>> edges = u.getEdgesOut();
			for (Edge<Vertex<T>> edge : edges) {
				Vertex<T> v = edge.getTo();
				int alt = u.getDistance() + edge.getWeight();
				if (alt < v.getDistance()) {
					Q.remove(v);
					v.setDistance(alt);
					v.setPrevious(u);
					Q.add(v);
				}
			}
		}
	}
	public java.util.Set<Vertex<T>> getVertices() {
		return vertices;
	}
}
