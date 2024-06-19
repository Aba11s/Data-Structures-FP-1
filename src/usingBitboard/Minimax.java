package usingBitboard;

import java.util.ArrayList;
import java.util.Collections;

public class Minimax {

    static int count; // keeps track how many branches does the function recurse through

    public static int minimax(Board board, boolean isMax) {
        count++;

        /* Base case */
        if(board.checkForWin(board.xBB)) { // if a winning move found in X bitboard
            return -1;
        } else if (board.checkForWin(board.oBB)) { // if a winning move found in O bitboard
            return 1;
        }

        /* Recurse */
        ArrayList<Integer> boardScores = new ArrayList<>();
        ArrayList<Integer> emptySlots = board.getEmptySlots();

        if (emptySlots.isEmpty()) {
            return 0;
        }

        if(isMax) {
            for(int slot : emptySlots) {
                Board phantomBoard = new Board(board);
                phantomBoard.oBB = phantomBoard.markSpace(phantomBoard.oBB, slot);
                boardScores.add(minimax(phantomBoard, !isMax));
            }
        } else {
            for(int slot : emptySlots) {
                Board phantomBoard = new Board(board);
                phantomBoard.xBB = phantomBoard.markSpace(phantomBoard.xBB, slot);
                boardScores.add(minimax(phantomBoard, !isMax));
            }
        }

        if(isMax) {
            return Collections.max(boardScores);
        } else {
            return Collections.min(boardScores);
        }
    }
}
