package hu.nye.winning.conditions;

import hu.nye.model.Board;
import hu.nye.model.Disk;

import java.util.List;

public class CheckWin {

    private static final int WIN_COUNT = 4;

    public boolean checkWin(Board board, Disk disk) {
        return checkHorizontal(board, disk) ||
                checkVertical(board, disk) ||
                checkDiagonalTopLeftToBottomRight(board, disk) ||
                checkDiagonalBottomLeftToTopRight(board, disk);
    }

    private boolean checkHorizontal(Board board, Disk disk) {
        for (List<Disk> column : board.getColumns()) {
            for (int row = 0; row < board.getRows(); row++) {
                int count = 0;
                for (int col = 0; col < board.getColumns().size(); col++) {
                    if (board.getColumns().get(col).get(row) == disk) {
                        count++;
                        if (count == WIN_COUNT) {
                            return true;
                        }
                    } else {
                        count = 0;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkVertical(Board board, Disk disk) {
        for (int col = 0; col < board.getColumns().size(); col++) {
            int count = 0;
            for (int row = 0; row < board.getRows(); row++) {
                if (board.getColumns().get(col).get(row) == disk) {
                    count++;
                    if (count == WIN_COUNT) {
                        return true;
                    }
                } else {
                    count = 0;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalTopLeftToBottomRight(Board board, Disk disk) {
        int columns = board.getColumns().size();
        int rows = board.getRows();

        for (int startCol = 0; startCol <= columns - WIN_COUNT; startCol++) {
            for (int startRow = 0; startRow <= rows - WIN_COUNT; startRow++) {
                int count = 0;
                for (int offset = 0; offset < WIN_COUNT; offset++) {
                    if (board.getColumns().get(startCol + offset).get(startRow + offset) == disk) {
                        count++;
                        if (count == WIN_COUNT) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }

    private boolean checkDiagonalBottomLeftToTopRight(Board board, Disk disk) {
        int columns = board.getColumns().size();
        int rows = board.getRows();

        for (int startCol = 0; startCol <= columns - WIN_COUNT; startCol++) {
            for (int startRow = WIN_COUNT - 1; startRow < rows; startRow++) {
                int count = 0;
                for (int offset = 0; offset < WIN_COUNT; offset++) {
                    if (board.getColumns().get(startCol + offset).get(startRow - offset) == disk) {
                        count++;
                        if (count == WIN_COUNT) {
                            return true;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
