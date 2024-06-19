package usingBitboard;

import java.util.ArrayList;

public class Board {

    int xBB; // Bitboard representation of Xs
    int oBB; // Bitboard representation of Os
    int nBB; // Bitboard representation of empty spaces

    static int[] winStates = new int[] { // winning Bitboards
            0b000000111, 0b000111000, 0b111000000,
            0b100100100, 0b010010010, 0b001001001,
            0b100010001, 0b001010100,
    };

    // default board constructor
    public Board(int xbb, int obb) {
        xBB = xbb;
        oBB = obb;
        nBB = ~(xBB | oBB);
    }

    // copy board constructor
    public Board(Board board) {
        this.xBB = board.xBB;
        this.oBB = board.oBB;
        this.nBB = board.nBB;
    }

    // marks space with a symbol
    public int markSpace(int bb, int idx) {
        int mask = 1 << idx;
        if((mask & ~nBB) == 0) {
            nBB &= ~mask; // skibidi toilet
            return bb |= mask;
        }
        // else
        return 0;
    }

    // checks for win
    public boolean checkForWin(int bb) {
        // compares Bitboard with each winning bitboard in winStates
        for(int b : winStates) {
            if((b & bb) == b) {
                return true;
            }
        }
        return false; // otherwise
    }

    // returns list of empty slots
    public ArrayList<Integer> getEmptySlots() {
        ArrayList<Integer> emptySlots = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            if((~nBB & 1 << i) == 0) {emptySlots.add(i);}
        }
        return emptySlots;
    }

    // prints board
    @Override
    public String toString() {
        StringBuilder finalString = new StringBuilder();

        for(int y = 0; y < 3; y++) {
            StringBuilder sb = new StringBuilder("|");
            for(int x = 0; x < 3; x++) {
                int mask = 1 << (x + y * 3);

                // if is player
                if((xBB & mask) != 0) {
                    sb.append(" X |");
                    continue;
                }

                if((oBB & mask) != 0) {
                    sb.append(" O |");
                    continue;
                }

                // empty
                sb.append("   |");
            }
            finalString.append("|---|---|---|\n");
            finalString.append(sb).append("\n");
        }
        finalString.append("|---|---|---|\n");

        return finalString.toString();
    }
}
