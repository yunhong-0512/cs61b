package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF grid;   // N-by-N grid
    int[][] markGrid;            // whether each site open or not, 0 for blocked, 1 for open
    int openSites;               //number of open sites

    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(N * N + 2);
        openSites = 0;
        markGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; i < N; j++) {
                markGrid[i][j] = 0;
            }
        }
        for (int i = 1; i <= N; i++) {
            grid.union(0, i);
            grid.union(N * N, N * N-i);
        }
    }

    /** Return the index of each site in the grid. */
    private int indexOf(int row, int col) {
        return row * markGrid.length + col + 1;
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if ((row < 0) || (col < 0) || (row >= markGrid.length) || (col >= markGrid.length)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        markGrid[row][col] = 1;
        openSites += 1;
        int num = indexOf(row, col);
        if (markGrid[row][col - 1] == 1 && grid.connected(num, num - 1)) {
            grid.union(num, num - 1);
        }
        if (markGrid[row][col + 1] == 1 && grid.connected(num, num + 1)) {
            grid.union(num, num + 1);
        }
        if ((row > 0) && markGrid[row - 1][col] == 1 && grid.connected(num, num - markGrid.length)) {
            grid.union(num, num - markGrid.length);
        }
        if ((row < markGrid.length) && markGrid[row + 1][col] == 1 && grid.connected(num, num + markGrid.length)) {
            grid.union(num, num + markGrid.length);
        }
    }

    /** Return true if the site is open. */
    public boolean isOpen(int row, int col) {
        if ((row < 0) || (col < 0) || (row >= markGrid.length) || (col >= markGrid.length)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        if (markGrid[row][col] == 1) {
            return true;
        }
        return false;
    }

    /** Return false if the site is full. */
    public boolean isFull(int row, int col) {
        if ((row < 0) || (col < 0) || (row >= markGrid.length) || (col >= markGrid.length)) {
            throw new java.lang.IndexOutOfBoundsException();
        }
        return (grid.find(0) == grid.find(indexOf(row, col)));
    }

    /** Return number of open sites. */
    public int numberOfOpenSites() {
        return openSites;
    }

    /** Return true if the system percolate. */
    public boolean percolates() {
        return grid.find(0) == grid.find(markGrid.length * markGrid.length);
    }

    public static void main(String[] args) {

    }

}
