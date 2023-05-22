package assignment1;

public class ableVisitor implements VisitorP{

    @Override
    public boolean visit(Visitable visitable) {
        if (visitable instanceof Visitorvisit) {
            return true;
        }
        return false;
    }
}
