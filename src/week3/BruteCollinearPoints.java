package week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
	private List<LineSegment> list;
	public BruteCollinearPoints(Point[] points) {
		// finds all line segments containing 4 points
		if (points == null) {
			throw new NullPointerException();
		}
		checkDuplicatePoints(points);
		
		Arrays.sort(points);
		list = new ArrayList<>();
		for (int i = 0; i < points.length-3; i++) {
			for (int j = i+1; j < points.length-2; j++) {
				for (int k = j+1; k < points.length-1; k++) {
					for (int l = k+1; l < points.length; l++) {
						double slope1 = points[i].slopeTo(points[j]);
						double slope2 = points[i].slopeTo(points[k]);
						double slope3 = points[i].slopeTo(points[l]);
						if (slope1 == slope2 && slope1 == slope3) {
							list.add(new LineSegment(points[i], points[l]));
						}
					}
				}
			}
		}
	}

	public int numberOfSegments() {
		// the number of line segments
		return list.size();
	}

	public LineSegment[] segments() {
		// the line segments
		return list.toArray(new LineSegment[list.size()]);
	}

	private void checkDuplicatePoints(Point[] points) {
		for (int i = 0; i < points.length-1; i++) {
			for (int j = i+1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) {
					throw new IllegalArgumentException();
				}
			}
		}
	}
	
}
