import java.util.Arrays;
import java.util.Random;

public class Main {

    public static int[][] createGameBoard(int columns, int rows) {
        int[][] gameBoard = new int[columns][rows];
        for (int[] ints : gameBoard) {
            Arrays.fill(ints, 1);
        }
        return gameBoard;
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }


    public static int countColumnIndexOfRandomAdjacentSquare(int[][] gameBoard, int column) {
        int lastColumnIndex = gameBoard.length - 1;
        if (column == 0) {
            return getRandomNumber(column, column + 1);
        } else if (column == lastColumnIndex) {
            return getRandomNumber(column - 1, column);
        } else {
            return getRandomNumber(column-1, column + 1);
        }
    }

    public static int countRowIndexOfRandomAdjacentSquare(int[][] gameBoard, int row) {
        int lastRowIndex = gameBoard[0].length - 1; //???
        if (row == 0) {
            return getRandomNumber(row, row + 1);
        } else if (row == lastRowIndex) {
            return getRandomNumber(row - 1, row);
        } else {
            return getRandomNumber(row - 1, row + 1);
        }
    }

    public static int[][] transformGameBoard(int rounds) {
        int[][] board = createGameBoard(15, 15);
        while (rounds > 0) {
            for (int i = 0; i < board.length; i++) {
                for (int k = 0; k < board[i].length; k++) {
                    //while (board[i][k] > 0) {
                        int randomRow = i;
                        int randomColumn = k;
                        while (randomRow == i && randomColumn == k) {
                            randomRow = countRowIndexOfRandomAdjacentSquare(board, i);
                            randomColumn = countColumnIndexOfRandomAdjacentSquare(board, k);
                        }
                        board[i][k] -= 1;
                        board[randomRow][randomColumn] += 1;
                    //}
                }
            }
            rounds -= 1;

        }
        return board;
    }

    public static void main(String[] args) {
        int[][] board = createGameBoard(15, 15);
        int[][] boardAfterGame = transformGameBoard(1);
        for (int i = 0; i < boardAfterGame.length; i++) {
            System.out.println(i);
            for (int j = 0; j < boardAfterGame[i].length; j++) {
                System.out.print(boardAfterGame[i][j]);
            }
            System.out.println();
        }

    }
}
