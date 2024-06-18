package UsingArrays;

import java.util.*;

public class Driver {
    private static final char X = 'x';
    private static final char O = 'o';
    private static boolean isPlayerTurn = true;
    private static int playerRow;
    private static int playerCol;

    public static void main(String[] args) {
        Board gameBoard = new Board();
        ArrayList<ArrayList<Integer>> listOfEmptySlots = gameBoard.getEmptySlotRowColumn();
        Scanner scanner = new Scanner(System.in);

        gameBoard.printBoard();

        while(true) {
            if (isPlayerTurn) {
                if (listOfEmptySlots.isEmpty()) {
                    System.out.println("It's a Draw!");
                    break;
                }

                System.out.println("Player's turn, pick a move!");

                playerRow = scanner.nextInt();
                playerCol = scanner.nextInt();
                gameBoard.insertCharMarker(O, playerRow, playerCol);

                if (gameBoard.checkForWin(O)) {
                    System.out.println("Player won");
                    break;
                }

                gameBoard.printBoard();

                isPlayerTurn = false;
            } else {
                if (listOfEmptySlots.isEmpty()) {
                    System.out.println("It's a Draw!");
                    break;
                }

                // gameBoard.checkPossibleMovesArrays(xInsertionOrder, oInsertionOrder, turnCount);

                HashMap<ArrayList<Integer>, Integer> boardScore = new HashMap<>();

                for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
                    Board boardCopy = new Board(gameBoard);
                    int tryRow = emptySlotPosition.get(0);
                    int tryCol = emptySlotPosition.get(1);

                    boardCopy.insertCharMarker(X, tryRow, tryCol);
                    int minimaxResult = Minimax.minimax(boardCopy, O, true);
                    boardScore.put(new ArrayList<>(List.of(tryRow, tryCol)), minimaxResult);
                }
                System.out.println("Minimax Count: " + Minimax.count);

                ArrayList<Integer> optimalBoardPos = new ArrayList<>();
                int bestScore = 2;

                for (Map.Entry<ArrayList<Integer>, Integer> boardScorePair : boardScore.entrySet()) {
                    if (boardScorePair.getValue() < bestScore) {
                        bestScore = boardScorePair.getValue();
                        optimalBoardPos = boardScorePair.getKey();
                    }
                }

                gameBoard.insertCharMarker('x', optimalBoardPos.get(0), optimalBoardPos.get(1));

                gameBoard.printBoard();

                if(gameBoard.checkForWin(X)) {
                    System.out.println("AI won");
                    break;
                }

                Minimax.count = 0;
                isPlayerTurn = true;
            }
        }
    }
}
