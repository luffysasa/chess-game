package assignment1.AllViews;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.io.FileNotFoundException;

public class HandleUserOneInfoButton implements EventHandler<ActionEvent> {
    View view;

    public HandleUserOneInfoButton(View view){
        this.view = view;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if(button.getText().equals("Mail")){
            this.view.showUserOneMail();
        }
        else if(button.getText().equals("Store")){
            try {
                this.view.showUserOneStore();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
