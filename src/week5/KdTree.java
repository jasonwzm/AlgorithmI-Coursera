package week5;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private class Node {
		private Point2D p;
		private RectHV rect;
		private Node lb;
		private Node rt;
		private boolean vertical;
		private Node(Point2D p, Node parent) {
			this.p = p;
			if (parent == null) {
				this.rect = new RectHV(0, 0, 1, 1);
				this.vertical = true;
			}
			else {
				RectHV parentRect = parent.rect;
				Point2D parentP = parent.p;
				this.vertical = !parent.vertical;
				if (parent.vertical) {
					if (p.x() <= parentP.x()) {
						this.rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentP.x(), parentRect.ymax());
					}
					else {
						this.rect = new RectHV(parentP.x(), parentRect.ymin(), parentRect.xmax(), parentRect.ymax());
					}
				}
				else {
					if (p.y() <= parentP.y()) {
						this.rect = new RectHV(parentRect.xmin(), parentRect.ymin(), parentRect.xmax(), parentP.y());
					}
					else {
						this.rect = new RectHV(parentRect.xmin(), parentP.y(), parentRect.xmax(), parentRect.ymax());
					}
				}
			}
		}
	}
	
	private Node root;
	private List<Point2D> list;
	public KdTree() {
		// construct an empty set of points 
		root = null;
		list = new ArrayList<>();
	}

	public boolean isEmpty() {
		// is the set empty? 
		return list.isEmpty();
	}

	public int size() {
		// number of points in the set
		return list.size();
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			root = new Node(p, null);
		}
		else {
			Node parent = searchParent(p);
			if (parent.p.equals(p)) {
				return;
			}
			if (parent.vertical) {
				if (p.x() <= parent.p.x()) {
					parent.lb = new Node(p, parent);
				}
				else {
					parent.rt = new Node(p, parent);
				}
			}
			else {
				if (p.y() <= parent.p.y()) {
					parent.lb = new Node(p, parent);
				}
				else {
					parent.rt = new Node(p, parent);
				}
			}
		}
		list.add(p);
	}
	
	private Node searchParent(Point2D p) {
		Node curr = root;
		Node prev = null;
		while (curr != null) {
			if (curr.p.equals(p)) {
				return curr;
			}
			prev = curr;
			if (curr.vertical) {
				if (p.x() <= curr.p.x()) {
					curr = curr.lb;
				}
				else {
					curr = curr.rt;
				}
			}
			else {
				if (p.y() <= curr.p.y()) {
					curr = curr.lb;
				}
				else {
					curr = curr.rt;
				}
			}
		}
		return prev;
	}

	public boolean contains(Point2D p) {
		// does the set contain point p? 
		if (p == null) {
			throw new NullPointerException();
		}
		return list.contains(p);
	}

	public void draw() {
		// draw all points to standard draw 
		draw(root);
	}
	
	private void draw(Node node) {
		if (node == null) {
			return;
		}
		StdDraw.setPenRadius(0.05);
		StdDraw.setPenColor();
		StdDraw.point(node.p.x(), node.p.y());
		if (node.vertical) {
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
		}
		else {
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
		}
		draw(node.lb);
		draw(node.rt);
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle 
		if (rect == null) {
			throw new NullPointerException();
		}
		List<Point2D> list = new ArrayList<>();
		rangeHelper(root, rect, list);
		return list;
	}
	
	private void rangeHelper(Node node, RectHV rect, List<Point2D> list) {
		if (node == null || !node.rect.intersects(rect)) {
			return;
		}
		if (rect.contains(node.p)) {
			list.add(node.p);
		}
		rangeHelper(node.lb, rect, list);
		rangeHelper(node.rt, rect, list);
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty 
		if (p == null) {
			throw new NullPointerException();
		}
		return findNearest(root, p, root.p, Double.MAX_VALUE);
	}
	
	private Point2D findNearest(Node x, Point2D p, Point2D nearest, double nearestDistance) {
		if (x == null) {
			return nearest;
		}
		Point2D closest = nearest;
		double closestDistance = nearestDistance;
		double distance = x.p.distanceSquaredTo(p);
		if (distance < nearestDistance) {
			closest = x.p;
			closestDistance = distance;
		}
		Node first, second;
		if (x.vertical) {
			if (p.x() <= x.p.x()) {
				first = x.lb;
				second = x.rt;
			}
			else {
				first = x.rt;
				second = x.lb;
			}
		}
		else {
			if (p.y() <= x.p.y()) {
				first = x.lb;
				second = x.rt;
			}
			else {
				first = x.rt;
				second = x.lb;
			}
		}
		if (first != null && first.rect.distanceSquaredTo(p) < closestDistance) {
			closest = findNearest(first, p, closest, closestDistance);
			closestDistance = closest.distanceSquaredTo(p);
		}
		if (second != null && second.rect.distanceSquaredTo(p) < closestDistance) {
			closest = findNearest(second, p, closest, closestDistance);
			closestDistance = closest.distanceSquaredTo(p);
		}
		return closest;
	}
	
	public static void main(String[] args) {
		// unit testing of the methods (optional) 
		KdTree kd = new KdTree();
		kd.insert(new Point2D(0.02, 0.02));
		kd.insert(new Point2D(0.03, 0.03));
		kd.insert(new Point2D(0.04, 0.04));
		System.out.println(kd.nearest(new Point2D(0.05, 0.05)).toString());
		
	}

}
