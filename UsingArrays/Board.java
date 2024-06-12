package UsingArrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {

    private ArrayList<ArrayList<Character>> rowColumnBoard = new ArrayList<>(List.of(
            new ArrayList<>(List.of('-','-','-')), new ArrayList<>(List.of('-','-','-')), new ArrayList<>(List.of('-','-','-'))
    ));

    public void insertCharMarker(char marker, int rowPos, int colPos) {
        this.checkForBoundaryError();
        rowColumnBoard.get(rowPos).add(colPos, marker);
    }

    public void deleteCharMarker(int rowPos, int colPos) {
        rowColumnBoard.get(rowPos).add(colPos,'-');
    }

    public boolean isOccupied(int rowPos, int colPos) {
        return rowColumnBoard.get(rowPos).get(colPos) == 'o' || rowColumnBoard.get(rowPos).get(colPos) == 'x';
    }

    public void printBoard() {
        for(ArrayList<Character> rowArrays : rowColumnBoard) {
            System.out.println(rowArrays.toString());
        }
    }

    public Board copyOf(){
        Board board = new Board();
        Collections.copy(board.getRowColumnBoard(), rowColumnBoard);
        return board;
    }

    private ArrayList<ArrayList<Character>> getRowColumnBoard() {
        return rowColumnBoard;
    }

    /**
     * Occurs when column of board exceeds 3 elements
     */
    private void checkForBoundaryError(){
        //debug purposes
        for(ArrayList<Character> rowArrays : rowColumnBoard) {
            if(rowArrays.size() > 3) {
                    System.out.println("board column exceeded 3 elements");
            }
        }
    }
}