package usingBitboard;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver {

    static boolean isPlayerTurn = true;
    static Board board = new Board(0,0);
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        testMinimax();
    }

    private static void run() {

        System.out.println(board.toString());

        while (true) {
            if(isPlayerTurn) {
                System.out.println("Player's turn, pick an empty square from 1 - 9");

                boolean scanning = true;
                while (scanning) {
                    int idx = scanner.nextInt() - 1;
                    int newBB = board.markSpace(board.oBB, idx);
                    if(newBB == 0) {
                        System.out.println("Space occupied at square[" + idx + ']');
                        System.out.println("pick an EMPTY square from 1 - 9");

                    } else {
                        board.oBB = newBB;
                        System.out.println("marked square [" + idx + "] with O");
                        System.out.println(board.toString());
                        scanning = false;
                    }
                }

                // Game state check
                if(board.checkForWin(board.oBB)) {
                    System.out.println("PLAYER WINS");
                    break;
                }
                if(board.getEmptySlots().isEmpty()) {
                    System.out.println("DRAW");
                    break;
                }

                isPlayerTurn = false;
            }
            else {
                // HashMap<index, score>
                HashMap<Integer, Integer> idxScores = new HashMap<>();

                // Tries each empty slot and stores their respective score
                for(int idx : board.getEmptySlots()) {
                    Board phantomBoard = new Board(board);
                    phantomBoard.xBB = phantomBoard.markSpace(board.xBB, idx);

                    int minimaxResult = Minimax.minimax(phantomBoard, true);
                    idxScores.put(idx, minimaxResult);
                    System.out.println("UsingBitBoard.Minimax result:" + minimaxResult + ",idx:" + idx + ", count:" + Minimax.count);
                    Minimax.count = 0;
                }

                int optimalIdx = board.getEmptySlots().getFirst();
                int bestScore = 2;

                for(Map.Entry<Integer, Integer> idxScorePair : idxScores.entrySet()) {
                    if(idxScorePair.getValue() < bestScore) {
                        bestScore = idxScorePair.getValue();
                        optimalIdx = idxScorePair.getKey();
                    }
                }

                // Finally marks square with O
                board.xBB = board.markSpace(board.xBB, optimalIdx);
                System.out.println("computer marked square [" + optimalIdx + "] with O");
                System.out.println(board.toString());

                // Game state check
                if(board.checkForWin(board.xBB)) {
                    System.out.println("COMPUTER WINS");
                    break;
                }
                if(board.getEmptySlots().isEmpty()) {
                    System.out.println("DRAW");
                    break;
                }

                isPlayerTurn = true;
            }
        }
    }

    // test for minimax only
    private static void testMinimax() {

        int iteration = 10000;
        Board testBoard = new Board(0,0);

        long startTime = System.nanoTime();
        for(int i = 0; i < iteration; i ++) {

            // minimax
            Minimax.minimax(testBoard, true);
        }
        long endTime = System.nanoTime();

        long duration = (endTime - startTime);
        System.out.println("Completed in: " + duration + " nanoseconds");
        System.out.println("Completed in: " + (duration/1000000000f) + " seconds");
        System.out.println("Minimax count: " + Minimax.count);
    }
}
