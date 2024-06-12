package UsingArrays;

public class Marker {
    private char markerSymbol;
    private int rowPos;
    private int colPos;

    public Marker(char markerSymbol, int rowPos, int colPos) {
        this.markerSymbol = markerSymbol;
        this.rowPos = rowPos;
        this.colPos = colPos;
    }

    public char getMarkerSymbol() {
        return markerSymbol;
    }

    public int getRowPos() {
        return rowPos;
    }

    public int getColPos() {
        return colPos;
    }
}