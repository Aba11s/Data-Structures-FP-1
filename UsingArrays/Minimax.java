package UsingArrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Minimax {

    /**
     *
     * @return
     */
    static int minimax(Move move, boolean isMax) {
        if(move.nextMoves.isEmpty()) {
            return move.getScore();
        }

        ArrayList<Integer> scores = new ArrayList<>();

        if(isMax) {
            for(Move currentMove : move.nextMoves) {
                scores.add(minimax(currentMove, false));
            }
            move.setScore(findLargest(scores));
        } else {
            for(Move currentMove : move.nextMoves) {
                scores.add(minimax(currentMove, true));
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
