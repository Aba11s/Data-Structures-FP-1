package UsingArrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Minimax {
    static int count;
    /**
     *
     * @return
     */
    static int minimax(Move move, boolean isMax, int currentDepth, int maxDepth) {
        count++;

        if(currentDepth == maxDepth) {
            return move.getScore();
        }

        if(!move.isBoardInWinState()) {
            move.findNextPossibleMoves();
        }

        if(move.nextMoves.isEmpty()) {
            return move.getScore();
        }

        ArrayList<Integer> scores = new ArrayList<>();

        if(isMax) {
            for(Move currentMove : move.nextMoves) {
                scores.add(minimax(currentMove, false, currentDepth+1, maxDepth));
            }
            move.setScore(findLargest(scores));
        } else {
            for(Move currentMove : move.nextMoves) {
                scores.add(minimax(currentMove, true, currentDepth+1, maxDepth));
            }
            move.setScore(findSmallest(scores));
        }
        return move.getScore();
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
