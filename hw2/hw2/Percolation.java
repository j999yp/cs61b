package hw2;

import edu.princeton.cs.algs4.UF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF UF;
    private boolean is_open[][];
    private int N;
    private int numOpenSite = 0;

    public Percolation(int N) // create N-by-N grid, with all sites initially blocked
    {
        if (N <= 0)
            throw new java.lang.IllegalArgumentException("N must be greater than 0.");
        this.N = N;
        UF = new WeightedQuickUnionUF(N * N + 1);
        is_open = new boolean[N][N];
    }

    public void open(int row, int col) // open the site (row, col) if it is not open already
    {
        check_outbound(row, col);
        if (is_open[row][col] == false) {
            is_open[row][col] = true;
            numOpenSite++;
            if (row == 0)
                UF.union(col, N * N);
            // iff i < j, i-->j
            // i==j, j-->i
            if (row - 1 >= 0 && is_open[row - 1][col])
                UF.union((row - 1) * N + col, row * N + col);

            if (row + 1 < N && is_open[row + 1][col])
                UF.union((row + 1) * N + col, row * N + col);

            if (col - 1 >= 0 && is_open[row][col - 1])
                UF.union(row * N + (col - 1), row * N + col);

            if (col + 1 < N && is_open[row][col + 1])
                UF.union(row * N + (col + 1), row * N + col);
        }
    }

    public boolean isOpen(int row, int col) // is the site (row, col) open?
    {
        check_outbound(row, col);
        return is_open[row][col];
    }

    public boolean isFull(int row, int col) // is the site (row, col) full?
    {
        check_outbound(row, col);
        return UF.connected(row * N + col, N * N);
    }

    public int numberOfOpenSites() // number of open sites
    {
        return numOpenSite;
    }

    public boolean percolates() // does the system percolate?
    {
        for (int i = 0; i < N; i++)
            if (is_open[N - 1][i])
                if (isFull(N - 1, i))
                    return true;
        return false;
    }

    public static void main(String[] args) // use for unit testing (not required)
    {
        ;
    }

    private void check_outbound(int row, int col) {
        if (!(0 <= row && row < N && 0 <= col && col < N))
            throw new java.lang.IndexOutOfBoundsException();
    }
}
