import java.util.ArrayList;
import java.util.Collections;

class Move {

    int xBB;
    int oBB;
    int mBB;
    int score = 0;
    boolean isMax;

    ArrayList<Move> nextMoves = new ArrayList<>();

    Move(int xBB, int oBB, boolean isMax) {
        this.xBB = xBB;
        this.oBB = oBB;
        this.mBB = oBB | xBB;
        this.isMax = isMax;
    }

    boolean checkForWin() {
        for (int wBB : BitBoard.winBB) {
            if ((wBB & xBB) == wBB) {
                score = 1;
                return true;
            }

            if ((wBB & oBB) == wBB) {
                score = -1;
                return true;
            }
        }
        return false;
    }

    void findNextPossibleMoves() {
        for(int i = 8; i >= 0; i--) {
            if((mBB & BitBoard.mask(i)) == 0) {
                if(isMax) {
                    nextMoves.add(new Move(xBB, oBB | BitBoard.mask(i), false));
                } else {
                    nextMoves.add(new Move(xBB | BitBoard.mask(i), oBB, true));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "XBB: " + Integer.toBinaryString(xBB);
    }
}




class Minimax {

    static int count = 0;

    static int minimax(Move mv, boolean isMax) {
        count++;

        mv.findNextPossibleMoves();

        if(mv.checkForWin() || mv.nextMoves.isEmpty()) {
            return mv.score;
        }

        ArrayList<Integer> scores = new ArrayList<>();

        if(isMax) {
            for(Move m : mv.nextMoves) {
                scores.add(minimax(m, false));
            }
            mv.score = Collections.max(scores);
            return mv.score;

        }
        else {
            for(Move m : mv.nextMoves) {
                scores.add(minimax(m, true));
            }
            mv.score = Collections.min(scores);
            return mv.score;
        }
    }


    // Util functions
    static int findLargest(ArrayList<Integer> scores) {
        int max = scores.getFirst();

        for(int i = 1; i < scores.size(); i++) {
            if(scores.get(i) > max) {
                max = scores.get(i);
            }
        }
        return max;
    }

    // Util functions
    static int findSmallest(ArrayList<Integer> scores) {
        int min = scores.getFirst();

        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i) < min) {
                min = scores.get(i);
            }
        }
        return min;
    }
}
