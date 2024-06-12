package UsingArrays;

import java.util.*;

public class Move {
    private Board board;
    private Queue<Marker> xInsertionOrder;
    private Queue<Marker> oInsertionOrder;
    private int score;
    private boolean isMax;
    private int currentDepth;
    private int maxDepth;
    ArrayList<Move> nextMoves = new ArrayList<>();

    public Move(Board board, Queue<Marker> xInsertionOrder, Queue<Marker> oInsertionOrder, boolean isMax, int currentDepth, int maxDepth) {
        this.board = board;
        this.xInsertionOrder = xInsertionOrder;
        this.oInsertionOrder = oInsertionOrder;
        this.isMax = isMax;
        this.score = 0;
        this.currentDepth = currentDepth;
        this.maxDepth = maxDepth;
//        if(!isBoardInWinState()) {
//            findNextPossibleMoves();
//        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isBoardInWinState() {
        // (0,0), (1,1), (2,2) _ (0,2), (1,1), (2,0)
        // (0,0), (0,1), (0,2) _ (1,0), (1,1), (1,2) _ (2,0), (2,1), (2,2)
        // (0,0), (1,0), (2,0) _ (0,1), (1,1), (2,1) _ (0,2), (1,2), (2,2)

        ArrayList<ArrayList<Character>> mainBoard = board.getRowColumnBoard();

        for(int row = 0; row <3; row++) {
            if(mainBoard.get(row).get(0) == mainBoard.get(row).get(1) &&
                    mainBoard.get(row).get(1) == mainBoard.get(row).get(2)) {
                if(mainBoard.get(row).get(0) == 'x') {
                    score = 1;
                } else {
                    score = -1;
                }
                return true;
            }
        }

        for(int col = 0; col <3; col++) {
            if(mainBoard.get(0).get(col) == mainBoard.get(1).get(col) &&
            mainBoard.get(1).get(col) == mainBoard.get(1).get(col)) {
                if(mainBoard.get(0).get(col) == 'x') {
                    score = 1;
                } else {
                    score = -1;
                }
                return true;
            }
        }

        if (mainBoard.get(0).get(0) == mainBoard.get(1).get(1) &&
        mainBoard.get(1).get(1) == mainBoard.get(2).get(2)) {
            if (mainBoard.get(0).get(0) == 'x') {
                score = 1;
            } else {
                score = -1;
            }
            return true;
        }

        if (mainBoard.get(0).get(2) == mainBoard.get(1).get(1) &&
        mainBoard.get(1).get(1) == mainBoard.get(2).get(0)) {
            if (mainBoard.get(0).get(2) == 'x') {
                score = 1;
            } else {
                score = -1;
            }
            return true;
        }

        return false;
    }

    public void findNextPossibleMoves() {
        if(currentDepth == maxDepth) {
            return;
        }

        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                Board boardCopy = board.copyOf();
                if(!board.isOccupied(row, col)) {
                    if(isMax) {
                        Queue<Marker> xInsertionOrderCopy = getNextXInsertionOrder(row, col, boardCopy);
                        boardCopy.insertCharMarker('x', row, col);

                        nextMoves.add(new Move(
                                boardCopy, //new board
                                xInsertionOrderCopy, // new x insertion order
                                oInsertionOrder,
                                !isMax,
                                currentDepth + 1,
                                maxDepth));
                    }else{
                        Queue<Marker> oInsertionOrderCopy = getNextOInsertionOrder(row, col, boardCopy);
                        boardCopy.insertCharMarker('o', row, col);

                        nextMoves.add(new Move(
                                boardCopy,
                                xInsertionOrder,
                                oInsertionOrderCopy,
                                !isMax,
                                currentDepth + 1,
                                maxDepth));
                    }
                }
            }
        }
    }

    private Queue<Marker> getNextXInsertionOrder(int row, int col, Board boardCopy) {
        Queue<Marker> copyXOrder = new LinkedList<>(xInsertionOrder);

        if(copyXOrder.size() >= 3){
            boardCopy.deleteCharMarker(copyXOrder.peek().getRowPos(), copyXOrder.peek().getColPos());
            copyXOrder.remove();
        }
        copyXOrder.add(new Marker('x', row, col));
        return copyXOrder;
    }

    private Queue<Marker> getNextOInsertionOrder(int row, int col, Board boardCopy) {
        Queue<Marker> copyOOrder = new LinkedList<>(oInsertionOrder);

        if(copyOOrder.size() >= 3){
            boardCopy.deleteCharMarker(copyOOrder.peek().getRowPos(), copyOOrder.peek().getColPos());
            copyOOrder.remove();
        }
        copyOOrder.add(new Marker('o', row, col));
        return copyOOrder;
    }
}
