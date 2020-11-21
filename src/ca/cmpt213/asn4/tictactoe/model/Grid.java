package ca.cmpt213.asn4.tictactoe.model;

public class Grid {
    private final int numRows;
    private final int numCol;
    private int[][] timesClicked;

    public Grid() {
        numRows = 4;
        numCol = 4;
        timesClicked = new int[4][4];
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCol() {
        return numCol;
    }

    /**
     * @param row index in grid
     * @param col index in grid
     * @return number of clicks at cell [row,col]
     * @throws IndexOutOfBoundsException is row or col is out of bounds
     */
    public int getTimesClicked(int row, int col) {
        if (row < 0 || row > numRows) throw new IndexOutOfBoundsException("Number of Rows Out of Bounds");
        if (col < 0 || col > numCol) throw new IndexOutOfBoundsException("Number of Cols Out of Bounds");
        return timesClicked[row][col];
    }

    /**
     * Sets the value of cell to 1 indicating presence of X
     * @param row index in grid
     * @param col index in grid
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     */
    public void setXClicked(int row, int col) {
        if (row < 0 || row > numRows) throw new IndexOutOfBoundsException("Number of Rows Out of Bounds");
        if (col < 0 || col > numCol) throw new IndexOutOfBoundsException("Number of Cols Out of Bounds");
        timesClicked[row][col] = 1;

        System.out.println("Set x called");
        printTimesClicked();
    }

    /**
     * Sets the value of cell to 2 indicating presence of O
     * @param row index in grid
     * @param col index in grid
     * @throws IndexOutOfBoundsException if row or col is out of bounds
     */
    public void setOClicked(int row, int col) {
        if (row < 0 || row > numRows) throw new IndexOutOfBoundsException("Number of Rows Out of Bounds");
        if (col < 0 || col > numCol) throw new IndexOutOfBoundsException("Number of Cols Out of Bounds");
        timesClicked[row][col] = 2;

        System.out.println("Set o called");;
        printTimesClicked();
    }

    /*
    Debugging Code
     */
    private void printTimesClicked() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCol; j++) {
                System.out.printf(timesClicked[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
