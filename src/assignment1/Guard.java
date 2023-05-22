package assignment1;

import javafx.scene.image.Image;

public class Guard extends Piece {
    private Image image;

    public Guard() {
        super("O", Type.GUARD);
        setImage();
    }

    /**
     * Returns true if the Guard can move onto the given cell.
     * @param cell Cell to check if the Guard can move onto
     * @return True, if Guard can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) {
        return !cell.hasPiece();
    }

    @Override
    public void setImage(){
        this.image = new Image("file:MusketeerAndGuard/guard_Default.png", 100, 100, true, true);
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
