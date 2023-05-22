package assignment1;

import assignment1.Exceptions.InvalidMoveException;
import java.util.List;
import java.util.Scanner;

public class HumanAgent extends Agent {

    Cell fromcell;

    public HumanAgent(Board board) {
        super(board);
    }

    /**
     * Asks the human for a move with from and to coordinates and makes sure its valid.
     * Create a Move object with the chosen fromCell and toCell
     * @return the valid human inputted Move
     */
    @Override
    public Move getMove() {
        List<Cell> possibleCells = board.getPossibleCells();

        Cell fromCell = getFromCell(possibleCells);
        this.fromcell = fromCell;
        List<Cell> possibleDestinations = board.getPossibleDestinations(fromCell);
        Cell toCell = getToCell(possibleDestinations);

        return new Move(fromCell,toCell);
    }

    private Cell getFromCell(List<Cell> possibleCells) {
        String pieceMessage = String.format("[%s] Possible pieces are %s. Enter the piece you want to move: ",
                board.getTurn().getType(), possibleCells.stream().map(Cell::getCoordinate).toList());
        Coordinate fromCoordinate = getCoordinate(pieceMessage);

        Cell fromCell = board.getCell(fromCoordinate);
        if (!possibleCells.contains(fromCell)) {
            System.out.printf("%s is invalid.%n", fromCoordinate);
            return getFromCell(possibleCells);
        }

        return fromCell;
    }

    private Cell getToCell(List<Cell> possibleDestinations) {
        boolean bool;
        System.out.println(String.format("Do you need a hint?? Yes Enter'Y' NO Enter 'N'", board.getTurn().getType()));//print
        bool = getMessage();
        if(bool){
            Cell cell = board.hints(board.getCell(this.fromcell.getCoordinate()),board);
            Cell tocell = board.getCell(cell.getCoordinate());
            System.out.print("Please go to :");//print


            System.out.print("Do you want move to :");//print
            System.out.print(tocell.getCoordinate());//print
            System.out.print("Print: Y(yes) or N(no)");//print
            Boolean boolin = getMessage();
            if(boolin){
                return tocell;
            }else{
                getMove();
            }

        }else{
            String toCoordinateMessage = String.format("[%s] Possible destinations are %s. Enter where you want to move: ",
                    board.getTurn().getType(), possibleDestinations.stream().map(Cell::getCoordinate).toList());//print

            Coordinate toCoordinate = getCoordinate(toCoordinateMessage);
            Cell toCell = board.getCell(toCoordinate);
            if (!possibleDestinations.contains(toCell)) {
                System.out.printf("%s is an invalid destination.\n", toCoordinate);//print
                return getToCell(possibleDestinations);
            }
            return toCell;

        }
        return null;
    }

    public static Coordinate getCoordinate(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        String coordinateStr = scanner.nextLine();
        try {
            return Utils.parseUserMove(coordinateStr.strip().toUpperCase());
        } catch (InvalidMoveException e) {
            System.out.println(e.getMessage());
            return getCoordinate(message);
        }
    }

    public boolean getMessage() {
        Scanner scanner1 = new Scanner(System.in);
        while (!scanner1.hasNext("[YyNn]")) {
            System.out.print("Invalid option. Enter 'Y' or 'N': ");//print
            scanner1.next();
        }
        String string = scanner1.next().toUpperCase();
        if(string.equals("Y")){//print
            return true;
        }
        return false;
    }
}
