package assignment1;

public class Visitorvisit implements Visitable{




    @Override
    public boolean accept(VisitorP visitorP) {

        return visitorP.visit(this);

    }
}
