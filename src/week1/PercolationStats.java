package week1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private int trials;
	private double[] xmean;
	private double mean;
	private double stddev;

	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		this.trials = trials;
		xmean = new double[trials];
		for (int k = 0; k < trials; k++) {
			Percolation percolation = new Percolation(n);
			int open = 0;
			while (!percolation.percolates()) {
				int i = StdRandom.uniform(1, n + 1);
				int j = StdRandom.uniform(1, n + 1);
				while (percolation.isOpen(i, j)) {
					i = StdRandom.uniform(1, n + 1);
					j = StdRandom.uniform(1, n + 1);
				}
				percolation.open(i, j);
				open++;
			}
			xmean[k] = (double) open/(n*n);
		}
	}

	public double mean() {
		// sample mean of percolation threshold
		mean = StdStats.mean(xmean);
		return mean;
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		stddev = StdStats.stddev(xmean);
		return stddev;
	}

	public double confidenceLo() {
		// low endpoint of 95% confidence interval
		return mean-((1.96*stddev)/Math.sqrt(trials));
	}

	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean+((1.96*stddev)/Math.sqrt(trials));
	}

	public static void main(String[] args) {
		// test client (described below)
		int n = StdIn.readInt();
		int trials = StdIn.readInt();
		PercolationStats ps = new PercolationStats(n, trials);
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
	}
}
