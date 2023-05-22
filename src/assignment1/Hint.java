package assignment1;

public class Hint {  // Need hint Use this class
    String name;
    HintStrategy hintStrategy;

    public Hint(String strategyname){
        this.name = strategyname;
    }

    void mode(Piece.Type mode){
        if(mode.equals(Piece.Type.MUSKETEER)){
            hintStrategy = new MusketeerHint();
        }
        if(mode.equals(Piece.Type.GUARD)){
            hintStrategy = new GuardHint();
        }
    }


    Cell getbesthint(Cell fromcell, Board board){
        return hintStrategy.getBestMove(fromcell,board);
    }


}
