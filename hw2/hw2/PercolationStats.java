package hw2;

import edu.princeton.cs.introcs.*;

public class PercolationStats {

    private Percolation p;
    private double[] lst;
    private int T;

    public PercolationStats(int N, int T, PercolationFactory pf) // perform T independent experiments on an N-by-N grid
    {
        if (T <= 0 || N <= 0)
            throw new java.lang.IllegalArgumentException();
        this.T = T;
        lst = new double[T];
        for (int i = 0; i < T; i++) {
            p = pf.make(N);
            while (!p.percolates()) {
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                p.open(x, y);
            }
            lst[i] = (double)p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() // sample mean of percolation threshold
    {
        return StdStats.mean(lst);
    }

    public double stddev() // sample standard deviation of percolation threshold
    {
        return StdStats.stddev(lst);
    }

    public double confidenceLow() // low endpoint of 95% confidence interval
    {
        return mean() - 1.96 * stddev() / Math.pow(T, 0.5);
    }

    public double confidenceHigh() // high endpoint of 95% confidence interval
    {
        return mean() + 1.96 * stddev() / Math.pow(T, 0.5);
    }
}
