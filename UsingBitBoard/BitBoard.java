package UsingBitBoard;

class BitBoard {

    int xBB = 0b000000000; // BB of Xs
    int oBB = 0b000000000; // BB of Os
    int mBB; // BB of Os | Xs

    int[] winBB = new int[] {  // BM of winning moves
            0b000000111, 0b000111000, 0b111000000,
            0b001001001, 0b010010010, 0b100100100,
            0b100010001, 0b001010100
    };

    BitBoard() {}

    static int mask(int i) {return 1 << i;}

    void mark(int i, int bb) {bb |= mask(i);}

    void setMBB() {mBB = xBB | oBB;}

    boolean checkForOverlap(int i) {return((mBB & mask(i)) != 0);}

    boolean checkForWin(int bb) {
        for(int wBB : winBB) {
            if((wBB & bb) == wBB) {return true;}
        }
        return false;
    }
}
