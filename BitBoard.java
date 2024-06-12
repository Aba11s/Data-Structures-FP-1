import java.util.*;

class BitBoard {

    static int xBB = 0b000000000; // BB of Xs
    static int oBB = 0b000000000; // BB of Os
    static int mBB; // BB of Os | Xs

    static int[] winBB = new int[] {  // BM of winning moves
            0b000000111, 0b000111000, 0b111000000,
            0b001001001, 0b010010010, 0b100100100,
            0b100010001, 0b001010100
    };

    // active
    static LinkedHashMap<Integer, Integer> nextPossibleMoveSet = new LinkedHashMap<>();
    static int nextChosenMove;


    BitBoard() {}

    static int mask(int i) {return 1 << i;}

    void markOBB(int i) {
        oBB |= mask(i);
    }

    void markXBB(int i) {
        xBB |= mask(i);
    }

    void setMBB() {mBB = xBB | oBB;}

    void checkPossibleMoves() {
        for(int i = 8; i >= 0; i--) {
            if((mBB & mask(i)) == 0){
                Move move = new Move(xBB, oBB | mask(i), false);
                nextPossibleMoveSet.put(i, Minimax.minimax(move, false));
                System.out.println("index: " + i);
            }
        }
        System.out.println(nextPossibleMoveSet);
        nextChosenMove = Collections.min(nextPossibleMoveSet.entrySet(), Map.Entry.comparingByValue()).getKey();
        nextPossibleMoveSet.clear();
        markOBB(nextChosenMove);
        setMBB();
    }

    void chooseBestPossibleMove() {
    }

    boolean checkForOverlap(int i) {return((mBB & mask(i)) != 0);}

    boolean checkForWin(int bb) {
        for(int wBB : winBB) {
            if((wBB & bb) == wBB) {return true;}
        }
        return false;
    }
}
