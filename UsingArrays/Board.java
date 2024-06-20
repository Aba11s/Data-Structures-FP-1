package UsingArrays;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private ArrayList<ArrayList<Character>> rowColumnBoard;
    private ArrayList<ArrayList<Integer>> emptySlotRowColumn;

    public Board() {
        rowColumnBoard = new ArrayList<>(List.of(
                new ArrayList<>(List.of('-','-','-')), // row 1
                new ArrayList<>(List.of('-','-','-')), // row 2
                new ArrayList<>(List.of('-','-','-'))  // row 3
        ));
        emptySlotRowColumn = new ArrayList<>();

        for(int row = 0; row <3; row++) {
            for (int col = 0; col <3; col++) {
                emptySlotRowColumn.add(new ArrayList<>(List.of(row, col)));
            }
        }
    }

    /**
     * Makes a copy of an existing board
     * @param board
     */
    public Board(Board board) {
        rowColumnBoard = new ArrayList<>(List.of(
                new ArrayList<>(List.of('-','-','-')), // row 1
                new ArrayList<>(List.of('-','-','-')), // row 2
                new ArrayList<>(List.of('-','-','-'))  // row 3
        ));
        emptySlotRowColumn = new ArrayList<>();

        for (int row = 0; row <3; row++) {
            for (int col = 0; col <3; col++) {
                this.rowColumnBoard.get(row).set(col, board.getRowColumnBoard().get(row).get(col));
            }
        }

        for (ArrayList<Integer> emptySlot : board.getEmptySlotRowColumn()) {
            int row = emptySlot.get(0);
            int col = emptySlot.get(1);

            this.emptySlotRowColumn.add(new ArrayList<>(List.of(row, col)));
        }
    }

    public void insertCharMarker(char marker, int rowPos, int colPos) {
        ArrayList<Integer> removeThisPosition = new ArrayList<>(List.of(rowPos, colPos));
        emptySlotRowColumn.remove(removeThisPosition);

        rowColumnBoard.get(rowPos).set(colPos, marker);
    }

    public boolean checkForWin(char marker) {
        ArrayList<ArrayList<Character>> mainBoard = this.getRowColumnBoard();

        for(int row = 0; row <3; row++) {
            if(mainBoard.get(row).get(0) == marker &&
                    mainBoard.get(row).get(0) == mainBoard.get(row).get(1) &&
                    mainBoard.get(row).get(1) == mainBoard.get(row).get(2)) {
                return true;
            }
        }

        for(int col = 0; col <3; col++) {
            if(mainBoard.get(0).get(col) == marker &&
                    mainBoard.get(0).get(col) == mainBoard.get(1).get(col) &&
                    mainBoard.get(1).get(col) == mainBoard.get(2).get(col)) {
                return true;
            }
        }

        if (mainBoard.get(0).get(0) == marker &&
                mainBoard.get(0).get(0) == mainBoard.get(1).get(1) &&
                mainBoard.get(1).get(1) == mainBoard.get(2).get(2)) {
            return true;
        }

        if (mainBoard.get(0).get(2) == marker &&
                mainBoard.get(0).get(2) == mainBoard.get(1).get(1) &&
                mainBoard.get(1).get(1) == mainBoard.get(2).get(0)) {
            return true;
        }
        return false;
    }

    public void printBoard() {
        for(ArrayList<Character> rowArrays : rowColumnBoard) {
            System.out.println(rowArrays.toString());
        }
    }

    public ArrayList<ArrayList<Character>> getRowColumnBoard() {
        return rowColumnBoard;
    }

    public ArrayList<ArrayList<Integer>> getEmptySlotRowColumn() {
        return this.emptySlotRowColumn;
    }
}