import java.util.ArrayList;
import java.util.Collections;

class Move {

    int xBB;
    int oBB;
    int mBB;
    int score = 0;

    int currentDepth;
    int maxDepth;
    boolean isMax;

    ArrayList<Move> nextMoves = new ArrayList<>();

    Move(int xBB, int oBB, boolean isMax, int currentDepth, int maxDepth) {
        this.xBB = xBB;
        this.oBB = oBB;
        this.mBB = oBB | xBB;

        this.currentDepth = currentDepth;
        this.maxDepth = maxDepth;
        this.isMax = isMax;
    }

    boolean checkForWin() {
        for (int wBB : BitBoard.winBB) {
            if ((wBB & xBB) == wBB) {
                score = 100 - currentDepth;
                return true;
            }

            if ((wBB & oBB) == wBB) {
                score = -100 + currentDepth;
                return true;
            }
        }
        return false;
    }

    void findNextPossibleMoves() {
        for(int i = 0; i < 9; i++) {
            if((mBB & BitBoard.mask(i)) == 0) {
                if(isMax) {
                    nextMoves.add(new Move(xBB, oBB | BitBoard.mask(i), false, currentDepth+1, maxDepth));
                } else {
                    nextMoves.add(new Move(xBB | BitBoard.mask(i), oBB, true, currentDepth+1, maxDepth));
                }
            }
        }
    }
}

class Minimax {

    static int count = 0;

    static int minimax(Move mv, boolean isMax, int currentDepth, int maxDepth) {
        count++;

        mv.findNextPossibleMoves();

        if(mv.checkForWin() || mv.nextMoves.isEmpty()) {
            return mv.score;
        }

        ArrayList<Integer> scores = new ArrayList<>();

        if(isMax) {
            for(Move m : mv.nextMoves) {
                scores.add(minimax(m, false, currentDepth+1, maxDepth));
            }
            mv.score = Collections.max(scores);
            return mv.score;

        }
        else {
            for(Move m : mv.nextMoves) {
                scores.add(minimax(m, true, currentDepth+1, maxDepth));
            }
            mv.score = Collections.min(scores);
            return mv.score;
        }
    }
}
