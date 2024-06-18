package UsingArrays;

import java.util.ArrayList;
import java.util.Collections;

public class Minimax {
    static int count;

    /**
     *
     * @return value of move after evaluating its listOfMinimax
     */
    public static int minimax(Board board, char markerNextPlayer, boolean isMax) {
        // finds out how many minimaxes are generated
        count++;

        if (board.checkForWin('x')) {
            return -1;
        } else if (board.checkForWin('o')) {
            return 1;
        }

        ArrayList<ArrayList<Integer>> listOfEmptySlots = board.getEmptySlotRowColumn();

        if (listOfEmptySlots.isEmpty()) {
            return 0;
        }

        ArrayList<Integer> listOfBoardScores = new ArrayList<>();

        if (markerNextPlayer == 'x') {
            for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
                Board boardCopy = new Board(board);
                int rowPos = emptySlotPosition.get(0);
                int colPos = emptySlotPosition.get(1);

                boardCopy.insertCharMarker(markerNextPlayer, rowPos, colPos);

                listOfBoardScores.add(minimax(boardCopy, 'o', !isMax));
            }
        }

        if (markerNextPlayer == 'o') {
            for (ArrayList<Integer> emptySlotPosition : listOfEmptySlots) {
                Board boardCopy = new Board(board);
                int rowPos = emptySlotPosition.get(0);
                int colPos = emptySlotPosition.get(1);

                boardCopy.insertCharMarker(markerNextPlayer, rowPos, colPos);

                listOfBoardScores.add(minimax(boardCopy, 'x', !isMax));
            }
        }

        if (isMax) {
            return findLargest(listOfBoardScores);
        } else {
            return findSmallest(listOfBoardScores);
        }
    }

    // Util functions
    static int findLargest(ArrayList<Integer> scores) {
        return Collections.max(scores);
    }

    // Util functions
    static int findSmallest(ArrayList<Integer> scores) {
        return Collections.min(scores);
    }
}
