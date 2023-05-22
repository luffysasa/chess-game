package assignment1.UserBuilder;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private List<String> mailBox;

    public MailBox(){
        this.mailBox = new ArrayList<>();
        this.mailBox.add("Hello Welcome to ThreeMusketeers");
    }

    public MailBox(List<String> mailBox){
        this.mailBox = mailBox;
    }

    public List<String> getMailBox(){
        return mailBox;
    }

    public void update(String newMessage){
        this.mailBox.add(newMessage);
    }

    public String toString(){
        return mailBox.toString().replace(", ", ",");
    }
}
