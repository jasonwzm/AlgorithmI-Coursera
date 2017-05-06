package week5;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {
	private TreeSet<Point2D> set;
	public PointSET() {
		// construct an empty set of points 
		set = new TreeSet<>();
	}

	public boolean isEmpty() {
		// is the set empty? 
		return set.isEmpty();
	}

	public int size() {
		// number of points in the set 
		return set.size();
	}

	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		if (p == null) {
			throw new NullPointerException();
		}
		if (!this.contains(p)) {
			set.add(p);
		}
	}

	public boolean contains(Point2D p) {
		// does the set contain point p? 
		if (p == null) {
			throw new NullPointerException();
		}
		return set.contains(p);
	}

	public void draw() {
		// draw all points to standard draw 
		for (Point2D p : this.set) {
			p.draw();
		}
	}

	public Iterable<Point2D> range(RectHV rect) {
		// all points that are inside the rectangle 
		if (rect == null) {
			throw new NullPointerException();
		}
		List<Point2D> list = new ArrayList<>();
		for (Point2D point : this.set) {
			if (rect.contains(point)) {
				list.add(point);
			}
		}
		return list;
	}

	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty 
		if (p == null) {
			throw new NullPointerException();
		}
		Point2D point = null;
		if (!this.isEmpty()) {
			double min = Double.MAX_VALUE;
			for (Point2D curr : this.set) {
				if (p.distanceTo(curr) <= min) {
					min = p.distanceSquaredTo(curr);
					point = curr;
				}
			}
		}
		return point;
	}

	public static void main(String[] args) {
		// unit testing of the methods (optional) 
	}
}
