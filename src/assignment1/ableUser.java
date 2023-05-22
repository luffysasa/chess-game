package assignment1;

import assignment1.UserBuilder.User;
import assignment1.ThreeMusketeers;

public class ableUser implements VisitorP {

    String id;
    Usergroup usergroup;

    public ableUser(String id){
        this.id = id;
    }




    @Override
    public boolean visit(Visitable visitable) {
        if (visitable instanceof Visitorvisit) {
            if (usergroup.getstoredlist().contains(this.id)) {
                return true;
            }
        }
        return false;

    }
}
