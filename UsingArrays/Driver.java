package UsingArrays;

import java.util.LinkedList;
import java.util.Queue;

public class Driver {
    private static Queue<Marker> xInsertionOrder = new LinkedList<>();
    private static Queue<Marker> oInsertionOrder = new LinkedList<>();
    private static final char X = 'x';
    private static final char O = 'o';

    public static void main(String[] args) {
        Board gameBoard = new Board();

        addNewMarker(gameBoard, X, 0,0);
        addNewMarker(gameBoard, O, 0, 1);
    }

    /**
     * adds a new marker into the board
     * @param board
     * @param marker
     * @param rowPos
     * @param colPos
     */
    private static void addNewMarker(Board board, char marker, int rowPos, int colPos) {
        board.insertCharMarker(marker, rowPos, colPos);

        switch(marker) {
            case 'x':
                xInsertionOrder.add(new Marker(marker, rowPos, colPos));
                break;
            case 'y':
                oInsertionOrder.add(new Marker(marker, rowPos, colPos));
                break;
            default:
                System.out.println("Error: incorrect marker inserted");
        }
    }
}