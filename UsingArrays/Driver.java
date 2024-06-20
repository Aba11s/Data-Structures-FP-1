package UsingArrays;

import java.util.*;

public class Driver {
    private static final char X = 'x';
    private static final char O = 'o';
    private static boolean isPlayerTurn = true;
    private static int playerRow;
    private static int playerCol;

    public static void main(String[] args) {
        runGame();
        //runTimeEfficiencyTestOverall();
        //runTimeEfficiencyTestOneByOne();
        //runSpaceEfficiencyTestOneByOne();
    }

    private static void runGame() {
        Board gameBoard = new Board();
        ArrayList<ArrayList<Integer>> listOfEmptySlots = gameBoard.getEmptySlotRowColumn();
        Scanner scanner = new Scanner(System.in);

        gameBoard.printBoard();

        while (true) {
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

                if (gameBoard.checkForWin(X)) {
                    System.out.println("AI won");
                    break;
                }

                Minimax.count = 0;
                isPlayerTurn = true;
            }
        }
    }

    private static void runTimeEfficiencyTestOverall() {
        Board gameBoard = new Board();
        ArrayList<ArrayList<Integer>> listOfEmptySlots = gameBoard.getEmptySlotRowColumn();
        int iteration = 10000;

        gameBoard.insertCharMarker(O, 0, 0);
        gameBoard.insertCharMarker(X, 1, 1);
        gameBoard.insertCharMarker(O, 2, 2);
        gameBoard.insertCharMarker(X, 0, 1);
        gameBoard.insertCharMarker(O, 2, 1);
        gameBoard.insertCharMarker(X, 2, 0);
        gameBoard.insertCharMarker(O, 0, 2);

        long startTime = System.nanoTime();
        for (int count = 0; count < iteration; count++) {
            HashMap<ArrayList<Integer>, Integer> boardScore = new HashMap<>();

            for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
                Board boardCopy = new Board(gameBoard);
                int tryRow = emptySlotPosition.get(0);
                int tryCol = emptySlotPosition.get(1);

                boardCopy.insertCharMarker(X, tryRow, tryCol);
                int minimaxResult = Minimax.minimax(boardCopy, O, true);
                boardScore.put(new ArrayList<>(List.of(tryRow, tryCol)), minimaxResult);
            }

            ArrayList<Integer> optimalBoardPos = new ArrayList<>();
            int bestScore = 2;

            for (Map.Entry<ArrayList<Integer>, Integer> boardScorePair : boardScore.entrySet()) {
                if (boardScorePair.getValue() < bestScore) {
                    bestScore = boardScorePair.getValue();
                    optimalBoardPos = boardScorePair.getKey();
                }
            }
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("iterations: " + iteration);
        System.out.println("completed in: " + duration + " nanoseconds");
        System.out.println("completed in: " + duration / 1000000000f + " seconds");
    }

    private static void runTimeEfficiencyTestOneByOne() {
        Board gameBoard = new Board();
        ArrayList<ArrayList<Integer>> listOfEmptySlots = gameBoard.getEmptySlotRowColumn();

//        gameBoard.insertCharMarker(O, 0, 0);
//        gameBoard.insertCharMarker(X, 1, 1);
//        gameBoard.insertCharMarker(O, 2, 2);
//        gameBoard.insertCharMarker(X, 0, 1);
//        gameBoard.insertCharMarker(O, 2, 1);
//        gameBoard.insertCharMarker(X, 2, 0);
//        gameBoard.insertCharMarker(O, 0, 2);

        long startTime = System.nanoTime();
        HashMap<ArrayList<Integer>, Integer> boardScore = new HashMap<>();

        for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
            Board boardCopy = new Board(gameBoard);
            int tryRow = emptySlotPosition.get(0);
            int tryCol = emptySlotPosition.get(1);

            boardCopy.insertCharMarker(X, tryRow, tryCol);
            int minimaxResult = Minimax.minimax(boardCopy, O, true);
            boardScore.put(new ArrayList<>(List.of(tryRow, tryCol)), minimaxResult);
        }

        ArrayList<Integer> optimalBoardPos = new ArrayList<>();
        int bestScore = 2;

        for (Map.Entry<ArrayList<Integer>, Integer> boardScorePair : boardScore.entrySet()) {
            if (boardScorePair.getValue() < bestScore) {
                bestScore = boardScorePair.getValue();
                optimalBoardPos = boardScorePair.getKey();
            }
        }
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        //System.out.println("completed in: " + duration + " nanoseconds");
        System.out.println("completed in: " + duration / 1000000000f + " seconds");
    }

    private static void runSpaceEfficiencyTestOneByOne() {
        Runtime gfg = Runtime.getRuntime();
        long memory1, memory2;

        Board gameBoard = new Board();
        ArrayList<ArrayList<Integer>> listOfEmptySlots = gameBoard.getEmptySlotRowColumn();

//        gameBoard.insertCharMarker(O, 0, 0);
//        gameBoard.insertCharMarker(X, 1, 1);
//        gameBoard.insertCharMarker(O, 2, 2);
//        gameBoard.insertCharMarker(X, 0, 1);
//        gameBoard.insertCharMarker(O, 2, 1);
//        gameBoard.insertCharMarker(X, 2, 0);
//        gameBoard.insertCharMarker(O, 0, 2);

        HashMap<ArrayList<Integer>, Integer> boardScore = new HashMap<>();

        System.out.println("Total memory: " + gfg.totalMemory());
        memory1 = gfg.freeMemory();
        System.out.println("Initial Free Memory: " + memory1);

        for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
            Board boardCopy = new Board(gameBoard);
            int tryRow = emptySlotPosition.get(0);
            int tryCol = emptySlotPosition.get(1);

            boardCopy.insertCharMarker(X, tryRow, tryCol);
            int minimaxResult = Minimax.minimax(boardCopy, O, true);
            boardScore.put(new ArrayList<>(List.of(tryRow, tryCol)), minimaxResult);
        }

        ArrayList<Integer> optimalBoardPos = new ArrayList<>();
        int bestScore = 2;

        for (Map.Entry<ArrayList<Integer>, Integer> boardScorePair : boardScore.entrySet()) {
            if (boardScorePair.getValue() < bestScore) {
                bestScore = boardScorePair.getValue();
                optimalBoardPos = boardScorePair.getKey();
            }
        }

        memory2 = gfg.freeMemory();
        System.out.println("Free Memory after operation: " + memory2);

        System.out.println("Memory used by operation: " + (memory1 - memory2));
    }
}

