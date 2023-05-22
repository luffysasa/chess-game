package assignment1;

import javafx.scene.image.Image;

public class Musketeer extends Piece {
    private Image image;

    public Musketeer() {
        super("X", Type.MUSKETEER);
        setImage();
    }

    /**
     * Returns true if the Musketeer can move onto the given cell.
     * @param cell Cell to check if the Musketeer can move onto
     * @return True, if Musketeer can move onto given cell, false otherwise
     */
    @Override
    public boolean canMoveOnto(Cell cell) {
        return cell.hasPiece() && cell.getPiece().getType() == Type.GUARD;
    }

    @Override
    public void setImage(){
        this.image = new Image("file:MusketeerAndGuard/musketeer_Default.png", 100, 100, true, true);
    }

    @Override
    public Image getImage() {
        return this.image;
    }
}
