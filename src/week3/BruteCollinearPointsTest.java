package week3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BruteCollinearPointsTest {
	
	@Test
	public void EightPointsTest() {
		Point point1 = new Point(19000, 10000);
		Point point2 = new Point(18000, 10000);
		Point point3 = new Point(32000, 10000);
		Point point4 = new Point(21000, 10000);
		Point point5 = new Point(1234, 5678);
		Point point6 = new Point(14000, 10000);
		Point[] points = new Point[]{point1, point2, point3, point4, point5, point6};
		BruteCollinearPoints bp = new BruteCollinearPoints(points);
		LineSegment[] lsArray = bp.segments();
		for (LineSegment ls : lsArray) {
			System.out.println(ls.toString());
		}
		assertEquals(bp.numberOfSegments(), 1);
	}
}
