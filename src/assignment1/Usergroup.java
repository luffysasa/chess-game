package assignment1;

import java.util.ArrayList;

import assignment1.UserBuilder.User;

public class Usergroup {

    ArrayList<String> UserList;

    public void storeuser(User user){

        this.UserList.add(user.getUserID());


    }

    public ArrayList<String> getstoredlist(){
        return this.UserList;
    }


}
