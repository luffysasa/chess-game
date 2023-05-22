package assignment1.AllViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class HandleUserTwoInfoButton implements EventHandler<ActionEvent> {
    View view;

    public HandleUserTwoInfoButton(View view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Mail")){
            this.view.showUserTwoMail();
        }
        else if(button.getText().equals("Store")){
            try {
                this.view.showUserTwoStore();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}