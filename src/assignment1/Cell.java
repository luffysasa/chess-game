package assignment1;

import javafx.scene.control.Button;

public class Cell extends Button {
    private Coordinate coordinate;
    private Piece piece;

    /**
     * Creates a cell with the given coordinate.
     * Piece is null if there is no piece on the cell.
     * @param coordinate Coordinate of the cell on the board.
     */
    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Create a copy of a Cell
     * @param cell a Cell to make a copy of
     */
    public Cell(Cell cell) {
        this.coordinate = cell.coordinate;
        this.piece = cell.piece;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }

    @Override
    public String toString() {
        return hasPiece() ? piece.getSymbol() : " ";
    }

    protected void setOptionsColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #17871b;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    protected void setAgentFromColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #6c0404;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    protected void setAgentToColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #ff0700;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }

    public void setDefaultColor() {
        this.setStyle("""
                -fx-background-color: transparent;
                -fx-border-color: #00307b;
                -fx-border-radius: 50;
                -fx-border-width: 5;
                -fx-border-insets: 10, 17, 8, 8;
                """);
    }
}
