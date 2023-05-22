package assignment1;

import assignment1.AllViews.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;

import java.util.List;


public class BoardPanel extends GridPane implements EventHandler<ActionEvent> {

    private final View view;
    private final Board board;
    private Cell from = null;
    private Cell to = null;
    private ThreeMusketeers threeMusketeers;
    private final Cell[] cellArray = new Cell[2];
    private Move move;
    private Cell fromcell;


    public BoardPanel(View view, Board board){
        this.view = view;
        this.board = board;
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #181a1b;");
        int size = 550;
        this.setPrefSize(size, size);
        this.setMinSize(size, size);
        this.setMaxSize(size, size);
        setupboard();
        updateCells();
        Move move;

    }


    public void setupboard(){
            for(Cell[] boardRow : this.board.board){
                for(Cell cell : boardRow){
                    if(cell.hasPiece()) {
                        cell.setGraphic(new ImageView(cell.getPiece().getImage()));
                        cell.setShape(new Circle(2));
                        this.add(cell, cell.getCoordinate().col, cell.getCoordinate().row);

                    }
                }
            }

        }

    public void updateCells() { // TODO
        if (this.board == null) {
            return;
        }
        if (!(view.threeMusketeers.getCurrentAgent() instanceof HumanAgent)) {
            disableAllCells();
            view.runMove();
        }
        if (from == null) {
            disableAllCells();
            enableCertainCells(board.getTurn());

        }
        if (from != null) {

            enableCertainCells(board.getTurn());
            showToCells();

        }
        if (board.isGameOver()) {

            disableAllCells();

        }
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        Cell act = (Cell)actionEvent.getTarget();
        Piece.Type turn = board.getTurn();
        if(from == null) {
            from = act;
            this.fromcell = from;
        }
        else if(from == act) {
            from = null;
            disableAllCells();
        }
        else {
            to = act;
        }




        if (from != null && from.getPiece() != null &&  to != null ) {

            Move move = new Move(from,to);


            if(from.getPiece().getType().equals(turn) && board.isValidMove(move)) {
                this.view.threeMusketeers.move(move);
                update(move);




            }
            disableAllCells();
            from = null;
            to = null; // reset click

        }
        updateCells();
    }

    public void disableAllCells() {
        List<Cell> allcells = board.getAllCells();
        for(Cell cell: allcells) {
            cell.setOnAction(null);

        }
    }

    public void enableAllCells() {
        List<Cell> allcells = board.getAllCells();
        for(Cell cell: allcells) {
            cell.setOnAction(this);

        }
    }

    public void enableCertainCells(Piece.Type a) {
        if (a.equals(Piece.Type.MUSKETEER)){
            List<Cell> b = board.getMusketeerCells();
            for(Cell cell: b) {
                cell.setOnAction(this);

            }

        }
        if (a.equals(Piece.Type.GUARD)){
            List<Cell> b = board.getGuardCells();
            for(Cell cell: b) {
                cell.setOnAction(this);

            }
        }
    }

    public void showToCells() {
        List<Cell> cells = board.getPossibleDestinations(from);

        for(Cell cell: cells) {
            cell.setOnAction(this);

        }
    }

    public void update(Move move){
        if (move.fromCell.hasPiece()) {
            move.fromCell.setGraphic(new ImageView(move.fromCell.getPiece().getImage()));
        } else {
            move.fromCell.setGraphic(new Circle(50));
        }
        if (move.toCell.hasPiece()) {
            move.toCell.setGraphic(new ImageView(move.toCell.getPiece().getImage()));
        } else {
            move.toCell.setGraphic(new Circle(50));
        }
    }



    public void hint() {
        Cell cell = view.threeMusketeers.gethint(this.fromcell);
        Cell finalcell = board.getCell(cell.getCoordinate());
        view.messageLable.setText(new Move(this.fromcell,finalcell).toString());




    }
}
