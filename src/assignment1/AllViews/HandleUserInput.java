package assignment1.AllViews;

import assignment1.Board;
import assignment1.ThreeMusketeers;
import assignment1.UserBuilder.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class HandleUserInput implements EventHandler<ActionEvent> {
    ThreeMusketeers threeMusketeers;
    View view;

    public HandleUserInput(View view, ThreeMusketeers threeMusketeers){
        this.view = view;
        this.threeMusketeers = threeMusketeers;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Login")) {
            VBox InputVBox = (VBox) button.getParent();
            TextField UserOne = (TextField) InputVBox.getChildren().get(1);
            TextField UserTwo = (TextField) InputVBox.getChildren().get(3);
            if (!UserOne.getText().equals("") && !UserTwo.getText().equals("")) {
                this.threeMusketeers.setUserOne(new User(UserOne.getText(),
                        "MUSKETEER",
                        this.threeMusketeers.getBoard()));
                try {
                    this.threeMusketeers.loadOrSaveUser(this.threeMusketeers.getUserOne());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(this.threeMusketeers.getUserOne().saveInfo());

                this.threeMusketeers.setUserTwo(new User(UserTwo.getText(),
                        "GUARD",
                        this.threeMusketeers.getBoard()));
                try {
                    this.threeMusketeers.loadOrSaveUser(this.threeMusketeers.getUserTwo());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(this.threeMusketeers.getUserTwo().saveInfo());

                this.threeMusketeers.getPublisher().register(this.threeMusketeers.getUserOne());
                this.threeMusketeers.getPublisher().register(this.threeMusketeers.getUserTwo());

                this.view.showBoard();
                this.view.showPublisher();
            }
        }
        else if(button.getText().equals("Back")){
            this.view.showMainMenu();
        }
    }
}
