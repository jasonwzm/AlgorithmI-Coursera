package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int[][] block;
	private int open;
	private int n;
	private WeightedQuickUnionUF uf;
	private int top;
	private int bottom;

	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		this.n = n;
		block = new int[n][n];
		open = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				block[i][j] = 1;
			}
		}
		uf = new WeightedQuickUnionUF(n * n + 2);
		top = 0;
		bottom = n * n + 1;
	}

	public void open(int row, int col) {
		// open site (row, col) if it is not open already
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		if (block[row - 1][col - 1] == 0) {
			return;
		}
		block[row - 1][col - 1] = 0;
		open++;
		if (row == 1) {
			uf.union(convertIndex(row, col), top);
		}
		if (row == n) {
			uf.union(convertIndex(row, col), bottom);
		}
		if (col > 1 && isOpen(row, col - 1)) {
			uf.union(convertIndex(row, col), convertIndex(row, col - 1));
		}
		if (col < n && isOpen(row, col + 1)) {
			uf.union(convertIndex(row, col), convertIndex(row, col + 1));
		}
		if (row > 1 && isOpen(row - 1, col)) {
			uf.union(convertIndex(row, col), convertIndex(row - 1, col));
		}
		if (row < n && isOpen(row + 1, col)) {
			uf.union(convertIndex(row, col), convertIndex(row + 1, col));
		}
	}

	public boolean isOpen(int row, int col) {
		// is site (row, col) open?
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		return block[row - 1][col - 1] == 0;
	}

	public boolean isFull(int row, int col) {
		// is site (row, col) full?
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("row index i out of bounds");
		}
		return uf.connected(top, convertIndex(row, col));
	}

	public int numberOfOpenSites() {
		// number of open sites
		return open;
	}

	public boolean percolates() {
		// does the system percolate?
		return uf.connected(top, bottom);
	}

	private int convertIndex(int row, int col) {
		return row * (n - 1) + col;
	}

	public static void main(String[] args) {
		// test client (optional)
	}
}
