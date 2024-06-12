import java.util.Scanner;

public class Game {

    boolean isPlayersTurn = true;
    BitBoard bitBoard;

    // input scanner
    Scanner scanner = new Scanner(System.in);
    int playerMove;

    public Game() {
        bitBoard = new BitBoard();
        run();
    }

    public void run() {
        while(true) {

            if(isPlayersTurn) {
                System.out.println("XBB : " + Integer.toBinaryString(BitBoard.xBB));
                System.out.println("OBB : " + Integer.toBinaryString(BitBoard.oBB));
                System.out.println("MBB : " + Integer.toBinaryString(BitBoard.mBB));
                System.out.println("Player's turn, pick a move");

                playerMove = scanner.nextInt();
                bitBoard.markXBB(playerMove);
                bitBoard.setMBB();

                isPlayersTurn = false;
            }
            else {
                bitBoard.checkPossibleMoves();
                System.out.println("COUNT: " + Minimax.count);
                System.out.println("AI move : " + BitBoard.nextChosenMove);
                System.out.println("XBB : " + Integer.toBinaryString(BitBoard.xBB));
                System.out.println("OBB : " + Integer.toBinaryString(BitBoard.oBB));
                System.out.println("MBB : " + Integer.toBinaryString(BitBoard.mBB));

                Minimax.count = 0;
                isPlayersTurn = true;
            }
        }
    }
}
