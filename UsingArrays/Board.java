package UsingArrays;

import java.util.*;

public class Board {

    private ArrayList<ArrayList<Character>> rowColumnBoard = new ArrayList<>(List.of(
            new ArrayList<>(List.of('-','-','-')), new ArrayList<>(List.of('-','-','-')), new ArrayList<>(List.of('-','-','-'))
    ));

    static LinkedHashMap<int[], Integer> nextPossibleMoveSet = new LinkedHashMap<>();

    static int[] nextChosenMove;

    public void insertCharMarker(char marker, int rowPos, int colPos) {
        // this.checkForBoundaryError();
        rowColumnBoard.get(rowPos).set(colPos, marker);
    }

    public void checkPossibleMovesArrays(Queue<Marker> xInsertionOrder, Queue<Marker> oInsertionOrder, int currentDepth, int maxDepth) {
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                if(!this.isOccupied(row, col)) {
                    Move move = new Move(this, xInsertionOrder, oInsertionOrder, false, currentDepth+1, maxDepth);
                    int[] pos = {row, col};
                    nextPossibleMoveSet.put(pos, Minimax.minimax(move, false, currentDepth+1, maxDepth));
                    System.out.println("row:" + row + ", col:" + col);
                }
            }
        }
        System.out.println(nextPossibleMoveSet);
        nextChosenMove = Collections.min(nextPossibleMoveSet.entrySet(), Map.Entry.comparingByValue()).getKey();
        nextPossibleMoveSet.clear();
        this.insertCharMarker('x', nextChosenMove[0], nextChosenMove[1]);
        xInsertionOrder.add(new Marker('x',nextChosenMove[0], nextChosenMove[1]));
    }

    public boolean checkForWin() {
        ArrayList<ArrayList<Character>> mainBoard = this.getRowColumnBoard();

        this.printBoard();

        for(int row = 0; row <3; row++) {
            if(mainBoard.get(row).get(0) == mainBoard.get(row).get(1) &&
                    mainBoard.get(row).get(1) == mainBoard.get(row).get(2) &&
            mainBoard.get(row).get(0) != '-') {
                System.out.println("won 1");
                return true;
            }
        }

        for(int col = 0; col <3; col++) {
            if(mainBoard.get(0).get(col) == mainBoard.get(1).get(col) &&
                    mainBoard.get(1).get(col) == mainBoard.get(2).get(col) &&
                    mainBoard.get(0).get(col) != '-') {
                System.out.println("won 2");
                return true;
            }
        }

        if (mainBoard.get(0).get(0) == mainBoard.get(1).get(1) &&
                mainBoard.get(1).get(1) == mainBoard.get(2).get(2) &&
                mainBoard.get(0).get(0) != '-') {
            System.out.println("won 3");
            return true;
        }

        if (mainBoard.get(0).get(2) == mainBoard.get(1).get(1) &&
                mainBoard.get(1).get(1) == mainBoard.get(2).get(0) &&
                mainBoard.get(2).get(0) != '-') {
            System.out.println("won 4");
            return true;
        }
        return false;
    }

    public void deleteCharMarker(int rowPos, int colPos) {
        rowColumnBoard.get(rowPos).set(colPos,'-');
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

    public ArrayList<ArrayList<Character>> getRowColumnBoard() {
        return rowColumnBoard;
    }

    public void checkPossibleMoves() {

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