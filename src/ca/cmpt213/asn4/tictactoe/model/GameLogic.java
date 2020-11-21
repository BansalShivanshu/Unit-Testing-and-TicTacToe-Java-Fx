package ca.cmpt213.asn4.tictactoe.model;

/**
 * This is the main logic of the game
 * This is responsible for checking which
 * user won or if the match is a draw
 */
public class GameLogic {
    private Grid grid;

    public GameLogic() {
        this.grid = Grid.getInstance();
    }

    public int checkWon() {
        int result = -1;

        // then for win x (1)
        // then for win o (2)

        // check in all rows
        for (int i = 1; i < 3; i++) {
            for (int row = 0; row < grid.getNumRows(); row++) {
                result = checkInRow(row, i);
                if (result != -1) return result;
            }
        }

        // check in all cols
        for (int i = 1; i < 3; i++) {
            for (int col = 0; col < grid.getNumCol(); col++) {
                result = checkInCol(col, i);
                if (result != -1) return result;
            }
        }

        // check int the normal diagonals
        for (int i = 1; i < 3; i++) {
            result = checkNormalDiagonal(i);
            if (result != -1) return result;
        }

        // check int the non diagonals
        for (int i = 1; i < 3; i++) {
            result = checkNonNormalDiagonal(i);
            if (result != -1) return result;
        }

        return result;
    }

    public boolean checkDraw() {
        for (int i = 0; i < grid.getNumRows(); i++) {
            for (int j = 0; j < grid.getNumCol(); j++) {
                if (grid.getTimesClicked(i, j) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private int checkInRow(int row, int checkFor) {
        for (int i = 0; i < grid.getNumCol(); i++) {
            if (grid.getTimesClicked(row, i) != checkFor) {
                return -1;
            }
        }
        return checkFor;
    }

    private int checkInCol(int col, int checkFor) {
        for (int i = 0; i < grid.getNumRows(); i++) {
            if (grid.getTimesClicked(i, col) != checkFor) {
                return -1;
            }
        }
        return checkFor;
    }

    private int checkNormalDiagonal(int checkFor) {
        // check for (00)(11)(22)(33)
        if (grid.getTimesClicked(0, 0) != checkFor) return -1;
        if (grid.getTimesClicked(1, 1) != checkFor) return -1;
        if (grid.getTimesClicked(2, 2) != checkFor) return -1;
        if (grid.getTimesClicked(3, 3) != checkFor) return -1;
        return checkFor;
    }

    private int checkNonNormalDiagonal(int checkFor) {
        // check for (03)(12)(21)(30)
        if (grid.getTimesClicked(0, 3) != checkFor) return -1;
        if (grid.getTimesClicked(1, 2) != checkFor) return -1;
        if (grid.getTimesClicked(2, 1) != checkFor) return -1;
        if (grid.getTimesClicked(3, 0) != checkFor) return -1;
        return checkFor;
    }
}
