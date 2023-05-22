package assignment1.AllViews;

import assignment1.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.List;

public class BoardView extends GridPane implements EventHandler<ActionEvent> {
    private View view;
    private Board board;
    private ThreeMusketeers threeMusketeers;
    private final Cell[] cellArray = new Cell[2];

    public BoardView(View view, Board board, ThreeMusketeers threeMusketeers){
        this.view = view;
        this.board = board;
        this.threeMusketeers = threeMusketeers;
        setBoardView();
    }

    public BoardView(View view, Board board){
        this.view = view;
        this.board = board;
        setNonHumanBoard();
    }

    private void setNonHumanBoard() {
        for(Cell[] boardRow : this.board.board){
            for(Cell cell : boardRow){
                if(cell.hasPiece()) {
                    this.add(cell, cell.getCoordinate().col, cell.getCoordinate().row);
                    cell.setGraphic(new ImageView(cell.getPiece().getImage()));
                    cell.setOnAction(this);
                    cell.setShape(new Circle(100));
                }
            }
        }
    }

    public void setBoardView(){
        for(Cell[] boardRow : this.board.board){
            for(Cell cell : boardRow){
                if(cell.hasPiece()) {
                    this.add(cell, cell.getCoordinate().col, cell.getCoordinate().row);
                    if(cell.getPiece().getType().getType().equals("MUSKETEER")) {
                        Image musketeerImage = new Image(
                                String.format("file:MusketeerAndGuard/musketeer_%s.png", this.threeMusketeers.getUserOne().getSkins().getCurrentSkin()),
                                100, 100, true, true);
                        cell.setGraphic(new ImageView(musketeerImage));
                    }
                    else if(cell.getPiece().getType().getType().equals("GUARD")){
                        Image guardImage = new Image(
                                String.format("file:MusketeerAndGuard/guard_%s.png", this.threeMusketeers.getUserTwo().getSkins().getCurrentSkin()),
                                100, 100, true, true);
                        cell.setGraphic(new ImageView(guardImage));
                    }
                    cell.setOnAction(this);
                    cell.setShape(new Circle(100));
                }
            }
        }
    }

    protected void setAllCells(){
        List<Cell> allGuards = this.board.getGuardCells();
        List<Cell> allMusketeers = this.board.getMusketeerCells();

        for(Cell[] eachRow : this.board.board){
            for(Cell eachCell : eachRow){
                eachCell.setDisable(true);
            }
        }
        
        if(this.cellArray[0] == null){
            if(this.board.getTurn().equals(Piece.Type.MUSKETEER)){
                for(Cell eachMusketeer : allMusketeers){
                    if(this.board.getPossibleDestinations(eachMusketeer).size() > 0){
                        eachMusketeer.setDisable(false);
                    }
                }
            }
            else{
                for(Cell eachGuard : allGuards){
                    if(this.board.getPossibleDestinations(eachGuard).size() > 0){
                        eachGuard.setDisable(false);
                    }
                }
            }
        }

        else {
            if (this.board.getTurn().equals(Piece.Type.MUSKETEER)) {
                for (Cell eachMusketeer : allMusketeers) {
                    if (this.board.getPossibleDestinations(eachMusketeer).size() > 0) {
                        eachMusketeer.setDisable(false);
                    }
                }
            }

            else {
                for (Cell eachGuard : allGuards) {
                    if (this.board.getPossibleDestinations(eachGuard).size() > 0) {
                        eachGuard.setDisable(false);
                    }
                }
            }
            this.cellArray[0].setDisable(true);
            List<Cell> currPossibleDestinations = this.board.getPossibleDestinations(this.cellArray[0]);
            for (Cell eachDestination : currPossibleDestinations) {
                eachDestination.setDisable(false);
            }
        }
        
        if (this.board.isGameOver()) {
        	for(Cell[] eachRow : this.board.board){
                for(Cell eachCell : eachRow){
                    eachCell.setDisable(true);
                }
            }
        	view.showGameOver(this.board.getWinner());
        }
        
    }



    @Override
    public void handle(ActionEvent actionEvent) {
        if(cellArray[0] == null){
            Cell selectedFromCell = (Cell) actionEvent.getSource();
            cellArray[0] = selectedFromCell;
        }
        else{
            Cell selectedToCell = (Cell) actionEvent.getSource();
            cellArray[1] = selectedToCell;
            Move newMove = new Move(cellArray[0], cellArray[1]);
            if(this.board.isValidMove(newMove)){
                this.view.threeMusketeers.getBoard().move(newMove);
                if(cellArray[0].hasPiece()) {
                    cellArray[0].setGraphic(new ImageView(cellArray[0].getPiece().getImage()));
                }
                else{
                    cellArray[0].setGraphic(new Circle(50));
                }
                if(cellArray[1].hasPiece()) {
                    cellArray[1].setGraphic(new ImageView(cellArray[1].getPiece().getImage()));
                }
                else{
                    cellArray[1].setGraphic(new Circle(50));
                }
                cellArray[0] = null;
                cellArray[1] = null;
            }
            else if(this.cellArray[0].getPiece().getType().equals(this.cellArray[1].getPiece().getType())){
                this.cellArray[0] = cellArray[1];
                this.cellArray[1] = null;
            }
        }
        setAllCells();
    }
}

