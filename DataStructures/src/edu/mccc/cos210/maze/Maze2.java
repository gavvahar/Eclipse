package edu.mccc.cos210.maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Comparator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import edu.mccc.cos210.maze.graph.Edge;
import edu.mccc.cos210.maze.graph.Graph;
import edu.mccc.cos210.maze.graph.Vertex;

public class Maze2 {
	private static final int SIZE = 5;
	private Spot[][] spots;
	private java.util.Set<Spot> hotSpots = new java.util.HashSet<>();
	private Spot startSpot = null;
	private Spot endSpot = null;
	private JPanel jp = new JPanel();
	public Maze2() {
		JFrame jf = new JFrame("Maze");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.setLayout(new GridLayout(SIZE, SIZE));
		jp.setPreferredSize(new Dimension(800 / SIZE, 800 / SIZE));
		PropertyChangeListener pcl = this::propertyChange;
		MouseListener ml = new MazeMouseListener();
		this.spots = new Spot[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Spot spot = new Spot(row, col);
				jp.add(spot);
				this.spots[row][col] = spot;
				spot.addPropertyChangeListener(pcl);
				spot.addMouseListener(ml);
			}
		}
		jf.add(jp, BorderLayout.CENTER);
		jf.setSize(800, 800);
		jf.setLocationRelativeTo(null);
		jf.setResizable(false);
		jf.setVisible(true);
	}
	public static void main(String... args) {
		EventQueue.invokeLater(Maze2::new);
	}
	public class Spot extends JPanel {
		private static final long serialVersionUID = 1L;
		private int row;
		private int col;
		private Color color;
		public Spot(int row, int col) {
			setLayout(null);
			setEnabled(true);
			this.row = row;
			this.col = col;
			this.color = Color.white;//.random() < 0.75 ? Color.WHITE : Color.BLACK;
			setBackground(this.color);
			setBorder(new MatteBorder(0, 0, 1, 1, new Color(200, 200, 224)));
		}
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g.create();
			if (getStartSpot() == this) {
				Ellipse2D e2d = new Ellipse2D.Double(0.0, 0.0, getWidth() - 2.0, getHeight() - 2.0);
				g2d.setPaint(Color.BLUE);
				g2d.fill(e2d);
			} else {
				if (getEndSpot() == this) {
					Ellipse2D e2d = new Ellipse2D.Double(0.0, 0.0, getWidth() - 2.0, getHeight() - 2.0);
					g2d.setPaint(Color.RED);
					g2d.fill(e2d);
				} else {
					if (hotSpots.contains(this)) {
						Ellipse2D e2d = new Ellipse2D.Double(8.0, 8.0, getWidth() - 18.0, getHeight() - 18.0);
						g2d.setPaint(new Color(128, 255, 220));
						g2d.fill(e2d);
					}
				}
			}
			g2d.dispose();
		}
		@Override
		public Color getBackground() {
			return this.color;
		}
		public int getRow() {
			return row;
		}
		public void setRow(int row) {
			this.row = row;
		}
		public int getCol() {
			return col;
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public void flipColor() {
			this.color = color == Color.BLACK ? Color.WHITE : Color.BLACK;
			if (color == Color.BLACK) {
				if (this == getStartSpot()) {
					setStartSpot(null);
				} else {
					if (this == getEndSpot()) {
						setEndSpot(null);
					}
				}
			}
			repaint();
			firePropertyChange("SpotColor", null, this);
		}
		@Override
		public String toString() {
			return new String("[" + row + ", " + col + "]");
		}
	}
	private class MazeMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			Spot spot = (Spot) e.getSource();
			if (e.isShiftDown()) {
				if (spot.getColor() != Color.BLACK) {
					if (getStartSpot() == spot) {
						setStartSpot(null);
					} else {
						if (spot != getEndSpot()) {
							setStartSpot(spot);
						}
					}
				}
			} else {
				if (e.isControlDown()) {
					if (spot.getColor() != Color.BLACK) {
						if (getEndSpot() == spot) {
							setEndSpot(null);
						} else {
							if (spot != getStartSpot()) {
								setEndSpot(spot);
							}
						}
					}
				} else {
					spot.flipColor();
				}
			}
			spot.getParent().repaint();
		}
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if ("SpotColor".equals(evt.getPropertyName())) {
			if (getStartSpot() != null && getEndSpot() != null) {
				clearSpots();
				doDijkstra();
			} else {
				clearSpots();
			}
		}
	}
	public Spot getStartSpot() {
		return startSpot;
	}
	public void setStartSpot(Spot startSpot) {
		this.startSpot = startSpot;
		if (getStartSpot() != null && getEndSpot() != null) {
			clearSpots();
			doDijkstra();
		} else {
			clearSpots();
		}
	}
	public Spot getEndSpot() {
		return endSpot;
	}
	public void setEndSpot(Spot endSpot) {
		this.endSpot = endSpot;
		if (getStartSpot() != null && getEndSpot() != null) {
			clearSpots();
			doDijkstra();
		} else {
			clearSpots();
		}
	}
	private void clearSpots() {
		hotSpots.clear();
		jp.repaint();
	}
	private void doDijkstra() {
		Graph<Spot> graph = new Graph<>(Comparator.comparingInt(Vertex::getDistance));
		java.util.Map<Spot, Vertex<Spot>> vertices = new java.util.HashMap<>();
		for (int row = 0; row < spots.length; row++) {
			for (int col = 0; col < spots[0].length; col++) {
				Spot s = spots[row][col];
				Vertex<Spot> v = new Vertex<Spot>(s);
				graph.addVertex(v);
				vertices.put(s, v);
			}
		}
		for (int row = 0; row < spots.length; row++) {
			for (int col = 0; col < spots[0].length; col++) {
				Spot spot = spots[row][col];
				Vertex<Spot> vertex = vertices.get(spot);
				Color color = spot.getColor();
				if (row - 1 >= 0) {
					Spot otherSpot = spots[row - 1][col];
					Vertex<Spot> otherVertex = vertices.get(otherSpot);
					Color otherColor = otherSpot.getColor();
					if (color == Color.WHITE && otherColor == Color.WHITE) {
						Edge<Vertex<Spot>> edgeOut = new Edge<>(vertex, otherVertex, 1);
						Edge<Vertex<Spot>> edgeIn = new Edge<>(otherVertex, vertex, 1);
						vertex.addEdgeOut(edgeOut);
						vertex.addEdgeIn(edgeIn);
						otherVertex.addEdgeOut(edgeIn);
						otherVertex.addEdgeIn(edgeOut);
					}
				}
				if (row + 1 < spots.length) {
					Spot otherSpot = spots[row + 1][col];
					Vertex<Spot> otherVertex = vertices.get(otherSpot);
					Color otherColor = otherSpot.getColor();
					if (color == Color.WHITE && otherColor == Color.WHITE) {
						Edge<Vertex<Spot>> edgeOut = new Edge<>(vertex, otherVertex, 1);
						Edge<Vertex<Spot>> edgeIn = new Edge<>(otherVertex, vertex, 1);
						vertex.addEdgeOut(edgeOut);
						vertex.addEdgeIn(edgeIn);
						otherVertex.addEdgeOut(edgeIn);
						otherVertex.addEdgeIn(edgeOut);
					}
				}
				if (col - 1 >= 0) {
					Spot otherSpot = spots[row][col - 1];
					Vertex<Spot> otherVertex = vertices.get(otherSpot);
					Color otherColor = otherSpot.getColor();
					if (color == Color.WHITE && otherColor == Color.WHITE) {
						Edge<Vertex<Spot>> edgeOut = new Edge<>(vertex, otherVertex, 1);
						Edge<Vertex<Spot>> edgeIn = new Edge<>(otherVertex, vertex, 1);
						vertex.addEdgeOut(edgeOut);
						vertex.addEdgeIn(edgeIn);
						otherVertex.addEdgeOut(edgeIn);
						otherVertex.addEdgeIn(edgeOut);
					}
				}
				if (col + 1 < spots[0].length) {
					Spot otherSpot = spots[row][col + 1];
					Vertex<Spot> otherVertex = vertices.get(otherSpot);
					Color otherColor = otherSpot.getColor();
					if (color == Color.WHITE && otherColor == Color.WHITE) {
						Edge<Vertex<Spot>> edgeOut = new Edge<>(vertex, otherVertex, 1);
						Edge<Vertex<Spot>> edgeIn = new Edge<>(otherVertex, vertex, 1);
						vertex.addEdgeOut(edgeOut);
						vertex.addEdgeIn(edgeIn);
						otherVertex.addEdgeOut(edgeIn);
						otherVertex.addEdgeIn(edgeOut);
					}
				}
			}
		}
		Vertex<Spot> from = vertices.get(getStartSpot());
		Vertex<Spot> to = vertices.get(getEndSpot());
		graph.doDijkstra(from);
		Vertex<Spot> vert = to;
		while (vert.getPrevious() != null) {
			vert = vert.getPrevious();
			hotSpots.add(vert.getElement());
		}
	}
}
