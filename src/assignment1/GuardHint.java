package assignment1;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class GuardHint extends HintStrategy {
    HintStrategy hintStrategy;



    @Override
    Cell getBestMove(Cell fromcell, Board board) {
        Cell res_tocell = hintStrategy.possiblecells.get(gethint(fromcell,board).indexOf(min(scores)));
        return res_tocell;

    }
}
