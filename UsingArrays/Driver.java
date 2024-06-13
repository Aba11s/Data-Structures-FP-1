package UsingArrays;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Driver {
    private static Queue<Marker> xInsertionOrder = new LinkedList<>();
    private static Queue<Marker> oInsertionOrder = new LinkedList<>();
    private static final char X = 'x';
    private static final char O = 'o';
    private static boolean isPlayerTurn = true;
    private static int playerRow;
    private static int playerCol;
    private static int maxDepth = 4;

    public static void main(String[] args) {
        Board gameBoard = new Board();

        //addNewMarker(gameBoard, X, 0,0);
        //addNewMarker(gameBoard, O, 0, 1);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            int turnCount = 1;

            if (isPlayerTurn) {
                gameBoard.printBoard();
                System.out.println("Player's turn, pick a move!");

                playerRow = scanner.nextInt();
                playerCol = scanner.nextInt();
                addNewMarker(gameBoard, 'o', playerRow, playerCol);

                if (gameBoard.checkForWin()) {
                    System.out.println("Player won");
                    break;
                }

                gameBoard.printBoard();

                isPlayerTurn = false;
            } else {
                gameBoard.checkPossibleMovesArrays(xInsertionOrder, oInsertionOrder, turnCount, 8);
                System.out.println(xInsertionOrder.peek().getRowPos());
                System.out.println(oInsertionOrder.peek().getRowPos());

                if(gameBoard.checkForWin()) {
                    System.out.println("AI won");
                    break;
                }
                System.out.println("Minimax Count: " + Minimax.count);

                Minimax.count = 0;
                isPlayerTurn = true;
            }
        }
    }

    /**
     * adds a new marker into the board
     * @param board
     * @param marker
     * @param rowPos
     * @param colPos
     */
    private static void addNewMarker(Board board, char marker, int rowPos, int colPos) {
        board.insertCharMarker(marker, rowPos, colPos);

        switch(marker) {
            case 'x':
                xInsertionOrder.add(new Marker(marker, rowPos, colPos));
                break;
            case 'o':
                oInsertionOrder.add(new Marker(marker, rowPos, colPos));
                break;
            default:
                System.out.println("Error: incorrect marker inserted");
        }
    }
}
