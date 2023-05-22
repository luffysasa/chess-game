package assignment1;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class MusketeerHint extends HintStrategy{
    HintStrategy hintStrategy;


    @Override
    Cell getBestMove(Cell fromcell, Board board) {
        Cell res_tocell = hintStrategy.possiblecells.get(gethint(fromcell,board).indexOf(max(scores)));
        return res_tocell;

    }
}

