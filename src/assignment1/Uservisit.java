package assignment1;

import assignment1.UserBuilder.User;

import java.util.ArrayList;

public class Uservisit implements Visitable{


    @Override
    public boolean accept(VisitorP visitorP) {

//        if (visitorP instanceof User){
//            return true;
//        }
//        return false;

        return visitorP.visit(this);

    }
}
