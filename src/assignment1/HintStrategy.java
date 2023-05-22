package assignment1;

import java.util.ArrayList;
import java.util.List;

public abstract class HintStrategy {

    Board boardCopy;
    ArrayList<Cell> possiblecells = new ArrayList<>();
    List<Double> scores = new ArrayList<>();

    List<Double> gethint(Cell fromcell, Board board) {
        boardCopy = new Board(board);
        boardCopy.setTurn(board.getTurn());



        possiblecells.addAll(boardCopy.getPossibleDestinations(fromcell));




        for (Cell tocell: possiblecells) {
            Cell fromcellcopy = boardCopy.getCell(fromcell.getCoordinate());
            Cell tocellcopy = boardCopy.getCell(tocell.getCoordinate());

            Move move = new Move(fromcellcopy, tocellcopy);

            boardCopy.move(move);

            BoardEvaluatorImpl eva = new BoardEvaluatorImpl();
            scores.add(eva.evaluateBoard(boardCopy));
            boardCopy = new Board(board);

        }
        return scores;
    }

     abstract Cell getBestMove(Cell fromcell, Board board);


}
